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
import java.time.LocalDateTime;
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
    public List<TreeResponseDTO> getTreesByFieldId(Long fieldId) {
        return treeRepository.findAllByFieldId(fieldId).stream()
                .map(treeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public double getTotalProductivityByField(Long fieldId) {
        // Récupérer tous les arbres par champ
        List<Tree> trees = treeRepository.findAllByFieldId(fieldId);

        // Calculer la productivité totale
        return trees.stream()
                .mapToDouble(tree -> {
                    // Vérifier si la date de plantation est valide avant de calculer la productivité
                    if (tree.getPlantingDate() != null) {
                        return  tree.calculateAnnualProductivity(tree.getPlantingDate());
                    } else {
                        // Si la date de plantation est null, retourner 0 ou une autre valeur par défaut
                        return 0.0;
                    }
                })
                .sum();
    }

    @Override
    public TreeResponseDTO save(TreeRequestDTO treeRequestDTO) {
        Field field = fieldRepository.findById(treeRequestDTO.fieldId())
                .orElseThrow(() -> new EntityNotFoundException("Field not found with ID: " + treeRequestDTO.fieldId()));

        long currentTreeCount = treeRepository.countByFieldId(field.getId());

        validateTree(treeRequestDTO, field, currentTreeCount);

        Tree tree = mapper.toEntity(treeRequestDTO).setField(field);
        Tree savedTree = treeRepository.save(tree);

        return treeMapper.toDto(savedTree);
    }

    @Override
    public TreeResponseDTO update(Long treeId, TreeRequestDTO treeRequestDTO) {
        Tree existingTree = treeRepository.findById(treeId)
                .orElseThrow(() -> new EntityNotFoundException("Tree not found with ID: " + treeId));

        Field field = fieldRepository.findById(treeRequestDTO.fieldId())
                .orElseThrow(() -> new EntityNotFoundException("Field not found with ID: " + treeRequestDTO.fieldId()));

        long currentTreeCount = treeRepository.countByFieldId(field.getId());

        validateTree(treeRequestDTO, field, currentTreeCount);

        existingTree.setPlantingDate(treeRequestDTO.plantingDate())
                .setField(field);

        Tree updatedTree = treeRepository.save(existingTree);
        return treeMapper.toDto(updatedTree);
    }

    private boolean isPlantingSeasonValid(LocalDate plantingDate) {
        return plantingDate.getMonthValue() >= 3 && plantingDate.getMonthValue() <= 5;
    }

    public int calculateAge(LocalDate plantingDate,Long fieldId) {
        return LocalDate.now().getYear() - plantingDate.getYear();
    }

    private void validateTree(TreeRequestDTO treeRequestDTO, Field field, Long currentTreeCount) {
        double fieldAreaHectares = field.getArea();

        // Vérifier la densité maximale des arbres
        if (currentTreeCount >= fieldAreaHectares * 100) {
            throw new IllegalStateException("Maximum density achieved: the field cannot contain more than " +
                    (fieldAreaHectares * 100) + " trees.");
        }

        // Valider la date de plantation
        if (treeRequestDTO.plantingDate().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Planting date cannot be in the future.");
        }

        // Vérifier la saison de plantation
        if (!isPlantingSeasonValid(LocalDate.from(treeRequestDTO.plantingDate()))) {
            throw new IllegalStateException("Trees can only be planted between March and May.");
        }

        // Calculer l'âge pour vérifier la productivité maximale
        int age = calculateAge(LocalDate.from(treeRequestDTO.plantingDate()), field.getId());
        if (age > 20) {
            throw new IllegalStateException("The tree has exceeded maximum productivity age (20 years).");
        }
    }

//    private double  calculateAnnualProductivity(LocalDateTime plantingDate){
//        Tree tree = new Tree();
//      return    tree.calculateAnnualProductivity();
//    }

}
