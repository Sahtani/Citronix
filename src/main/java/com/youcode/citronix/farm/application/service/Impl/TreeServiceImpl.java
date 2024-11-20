package com.youcode.citronix.farm.application.service.Impl;

import com.youcode.citronix.common.service.AbstractService;
import com.youcode.citronix.farm.application.dto.request.TreeRequestDTO;
import com.youcode.citronix.farm.application.dto.response.TreeResponseDTO;
import com.youcode.citronix.farm.application.mapper.TreeMapper;
import com.youcode.citronix.farm.application.service.TreeService;
import com.youcode.citronix.farm.domain.entity.Field;
import com.youcode.citronix.farm.domain.entity.Tree;
import com.youcode.citronix.farm.domain.repository.FiledRepository;
import com.youcode.citronix.farm.domain.repository.TreeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreeServiceImpl extends AbstractService<Tree, TreeRequestDTO, TreeResponseDTO,Long> implements TreeService {

    private final TreeRepository treeRepository;
    private final TreeMapper treeMapper;
    private final FiledRepository fieldRepository;
    private final   List<Tree> trees;

    public TreeServiceImpl(TreeRepository treeRepository, TreeMapper treeMapper, FiledRepository fieldRepository, List<Tree> trees) {
        super(treeRepository, treeMapper);
        this.treeRepository = treeRepository;
        this.treeMapper = treeMapper;
        this.fieldRepository = fieldRepository;
        this.trees = trees;
    }

    @Override
    public List<Tree> getTreesByFieldId(Long fieldId) {
        return trees.stream()
                .filter(tree -> tree.getField().getId().equals(fieldId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Tree> getTotalProductivityByField(Long fieldId) {
        return trees.stream()
                .filter(tree -> tree.getField().getId().equals(fieldId))
                .collect(Collectors.toList());
    }

    public TreeResponseDTO save(TreeRequestDTO treeRequestDTO) {

        Field field = fieldRepository.findById(treeRequestDTO.fieldId())
                .orElseThrow(() -> new EntityNotFoundException("Champ introuvable avec l'ID : " + treeRequestDTO.fieldId()));

        long currentTreeCount = treeRepository.countByFieldId(field.getId());
        double fieldAreaHectares = field.getArea();
        if (currentTreeCount + 1 > fieldAreaHectares * 100) {
            throw new IllegalStateException("Densité maximale atteinte : le champ ne peut pas contenir plus de " +
                    (fieldAreaHectares * 100) + " arbres.");
        }

        // Vérification de la période de plantation
        if (!isPlantingSeasonValid(LocalDate.from(treeRequestDTO.plantingDate()))) {
            throw new IllegalStateException("Les arbres ne peuvent être plantés qu'entre mars et mai.");
        }

        // Vérification de l'âge maximum pour la productivité
        int age = calculateAge(LocalDate.from(treeRequestDTO.plantingDate()));
        if (age > 20) {
            throw new IllegalStateException("L'arbre a dépassé l'âge maximum de productivité (20 ans).");
        }

        Tree tree = mapper.toEntity(treeRequestDTO);
        Tree savedTree = treeRepository.save(tree);
        return treeMapper.toDto(savedTree);
    }

    private boolean isPlantingSeasonValid(LocalDate plantingDate) {
        return plantingDate.getMonthValue() >= 3 && plantingDate.getMonthValue() <= 5;
    }

    private int calculateAge(LocalDate plantingDate) {
        return LocalDate.now().getYear() - plantingDate.getYear();
    }

}
