package com.youcode.citronix.harvest.application.service.Impl;

import com.youcode.citronix.common.service.AbstractService;
import com.youcode.citronix.farm.domain.entity.Field;
import com.youcode.citronix.farm.domain.entity.Tree;
import com.youcode.citronix.farm.domain.repository.TreeRepository;
import com.youcode.citronix.harvest.application.dto.request.HarvestDetailRequestDTO;
import com.youcode.citronix.harvest.application.dto.response.HarvestDetailResponseDTO;
import com.youcode.citronix.harvest.application.dto.response.HarvestResponseDTO;
import com.youcode.citronix.harvest.application.mapper.HarvestDetailMapper;
import com.youcode.citronix.harvest.application.service.HarvestDetailService;
import com.youcode.citronix.harvest.domain.entity.Harvest;
import com.youcode.citronix.harvest.domain.entity.HarvestDetail;
import com.youcode.citronix.harvest.domain.repository.HarvestDetailRepository;
import com.youcode.citronix.harvest.domain.repository.HarvestRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;

@Service
@Validated
public class HarvestDetailServiceImpl extends AbstractService<HarvestDetail, HarvestDetailRequestDTO, HarvestDetailResponseDTO, Long>
        implements HarvestDetailService {

    private final HarvestDetailRepository harvestDetailRepository;
    private final HarvestDetailMapper mapper;
    private final HarvestRepository harvestRepository;
    private final TreeRepository treeRepository;


    public HarvestDetailServiceImpl(HarvestDetailRepository harvestDetailRepository,
                                    HarvestDetailMapper mapper,
                                    HarvestRepository harvestRepository,
                                    TreeRepository treeRepository) {
        super(harvestDetailRepository, mapper);
        this.harvestDetailRepository = harvestDetailRepository;
        this.mapper = mapper;
        this.harvestRepository = harvestRepository;
        this.treeRepository = treeRepository;
    }

    @Override
    public HarvestDetailResponseDTO save(HarvestDetailRequestDTO request) {
        // Charger la récolte associée
        Harvest harvest = harvestRepository.findById(request.harvestId())
                .orElseThrow(() -> new EntityNotFoundException("Harvest not found with ID: " + request.harvestId()));

        // Charger l'arbre associé
        Tree tree = treeRepository.findById(request.treeId())
                .orElseThrow(() -> new EntityNotFoundException("Tree not found with ID: " + request.treeId()));

        // Validation des données
        validateHarvestData(harvest, tree, request);

        // Créer et sauvegarder le détail de récolte
        HarvestDetail detail = mapper.toEntity(request)
                .setHarvest(harvest)
                .setTree(tree)
                .setHarvestedQuantity(request.harvestedQuantity());

        // Mettre à jour la quantité totale de la récolte
        double updatedQuantity = harvest.getTotalQuantity() + detail.getHarvestedQuantity();
        harvest.setTotalQuantity(updatedQuantity);

        // Sauvegarder les détails de la récolte
        HarvestDetail savedDetail = harvestDetailRepository.save(detail);
        return mapper.toDto(savedDetail);
    }

    private void validateHarvestData(Harvest harvest, Tree tree, HarvestDetailRequestDTO request) {
        // Vérifier la date de récolte par rapport à la date de plantation
        if (harvest.getHarvestDate().isBefore(tree.getPlantingDate())) {
            throw new IllegalArgumentException("Harvest date cannot be before the tree's plantation date.");
        }

        // Vérifier si l'arbre est encore productif
        if (!tree.isProductive(tree.getPlantingDate())) {
            throw new IllegalArgumentException("The tree is no longer productive.");
        }



        // Vérifier si l'arbre a déjà été récolté pour cette saison
        if (harvestDetailRepository.existsByTreeIdAndHarvestSeason(request.treeId(), harvest.getSeason())) {
            throw new IllegalStateException("This tree has already been harvested for the season " + harvest.getSeason());
        }

        // Vérifier s'il existe déjà une récolte pour le champ et la saison
        if (isHarvestInSameSeasonForField(tree.getField(), harvest)) {
            throw new IllegalArgumentException("A harvest already exists for this field in the same season.");
        }
    }

    private boolean isHarvestInSameSeasonForField(Field field, Harvest harvest) {
        List<Tree> trees = treeRepository.findByField(field);

        List<HarvestDetail> harvestDetails = harvestDetailRepository.findByTreeIn(trees);

        for (HarvestDetail detail : harvestDetails) {
            Harvest existingHarvest = detail.getHarvest();

            if (Objects.equals(existingHarvest.getId(), harvest.getId())) {
                return false;
            } else {
                if (isSameSeason(existingHarvest, harvest)) {
                    return true;
                }
            }


        }

        return false;
    }


    private boolean isTreeHarvestedInSameSeason(Tree tree, Harvest harvest) {
        List<HarvestDetail> harvestDetails = harvestDetailRepository.findByTreeIn(List.of(tree));

        return harvestDetails.stream()
                .map(HarvestDetail::getHarvest)
                .anyMatch(existingHarvest -> isSameSeason(existingHarvest, harvest));
    }

    private boolean isSameSeason(Harvest harvest1, Harvest harvest2) {
        if (harvest1.getSeason() == harvest2.getSeason()) {
            int year1 = harvest1.getHarvestDate().getYear();
            int year2 = harvest2.getHarvestDate().getYear();

            if (year1 != year2) {
                return false;
            } else {
                return true;
            }
        }

        return false;
    }
}
