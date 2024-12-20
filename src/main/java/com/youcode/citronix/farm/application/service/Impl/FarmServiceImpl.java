package com.youcode.citronix.farm.application.service.Impl;

import com.youcode.citronix.common.exception.EntityExistsByNameException;
import com.youcode.citronix.common.exception.EntityNotFoundException;
import com.youcode.citronix.common.service.AbstractService;
import com.youcode.citronix.farm.application.dto.request.FarmRequestDTO;
import com.youcode.citronix.farm.application.dto.response.FarmCriteriaDTO;
import com.youcode.citronix.farm.application.dto.response.FarmResponseDTO;
import com.youcode.citronix.farm.application.mapper.FarmMapper;
import com.youcode.citronix.farm.application.service.FarmService;
import com.youcode.citronix.farm.domain.entity.Farm;
import com.youcode.citronix.farm.domain.repository.FarmRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class FarmServiceImpl extends AbstractService<Farm, FarmRequestDTO, FarmResponseDTO, Long> implements FarmService {

    private final FarmRepository farmRepository;
    private final FarmMapper mapper;
    private EntityManager entityManager;

    public FarmServiceImpl(FarmRepository farmRepository, FarmMapper mapper, EntityManager entityManager) {
        super(farmRepository, mapper);
        this.farmRepository = farmRepository;
        this.mapper = mapper;
        this.entityManager = entityManager;
    }

    @Override
    public FarmResponseDTO save(FarmRequestDTO farmRequestDTO) {
        if(farmRepository.existsByName(farmRequestDTO.name())){
            throw new EntityExistsByNameException(farmRequestDTO.name());
        }
        if (farmRequestDTO.totalArea() <= 0.2) {
            throw new IllegalArgumentException("The total area must be greater than 0.2 hectares ");
        }
        Farm farm = mapper.toEntity(farmRequestDTO);

        Farm savedFarm = farmRepository.save(farm);
        return mapper.toDto(savedFarm);
    }

    @Override
    public FarmResponseDTO update(Long id, FarmRequestDTO farmRequestDTO) {
        if (farmRequestDTO.totalArea() <= 2000) {
            throw new IllegalArgumentException("The total area must be greater than 2000 m²");
        }

        Farm existingFarm = farmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Farm with ID " + id + " not found."));

        existingFarm.setName(farmRequestDTO.name());
        existingFarm.setLocation(farmRequestDTO.location());
        existingFarm.setTotalArea(farmRequestDTO.totalArea());
        existingFarm.setCreationDate(farmRequestDTO.creationDate());
        Farm updatedFarm = farmRepository.save(existingFarm);

        return mapper.toDto(updatedFarm);
    }


    public List<FarmCriteriaDTO> searchFarms(String name, String location, Double area) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Farm> cq = cb.createQuery(Farm.class);

        Root<Farm> farm = cq.from(Farm.class);

        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            predicates.add(cb.like(farm.get("name"), "%" + name + "%"));
        }
        if (location != null && !location.isEmpty()) {
            predicates.add(cb.like(farm.get("location"), "%" + location + "%"));
        }
        if (area != null) {
            predicates.add(cb.equal(farm.get("area"), area));
        }

        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        TypedQuery<Farm> query = entityManager.createQuery(cq);
        List<Farm> farms = query.getResultList();

        List<FarmCriteriaDTO> farmCriteriaDTOS = new ArrayList<>();
        for (Farm farmEntity : farms) {
            FarmCriteriaDTO response = new FarmCriteriaDTO(farmEntity.getId(),farmEntity.getName(), farmEntity.getLocation(), farmEntity.getTotalArea(),farmEntity.getCreationDate());
            farmCriteriaDTOS.add(response);
        }
        return farmCriteriaDTOS;
    }


}
