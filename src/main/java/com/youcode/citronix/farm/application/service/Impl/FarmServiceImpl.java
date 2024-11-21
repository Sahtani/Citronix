package com.youcode.citronix.farm.application.service.Impl;

import com.youcode.citronix.common.service.AbstractService;
import com.youcode.citronix.farm.application.dto.request.FarmRequestDTO;
import com.youcode.citronix.farm.application.dto.response.FarmResponseDTO;
import com.youcode.citronix.farm.application.mapper.FarmMapper;
import com.youcode.citronix.farm.application.service.FarmService;
import com.youcode.citronix.farm.domain.entity.Farm;
import com.youcode.citronix.farm.domain.repository.FarmRepository;
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

    public List<FarmResponseDTO> searchFarms(String name, String location, Double area) {
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

        // Convertir les entités Farm en objets FarmResponse
        List<FarmResponseDTO> farmResponses = new ArrayList<>();
        for (Farm farmEntity : farms) {
            FarmResponseDTO response = new FarmResponseDTO(farmEntity.getName(), farmEntity.getLocation(), farmEntity.getTotalArea(),farmEntity.getCreationDate());
            farmResponses.add(response);
        }

        return farmResponses;
    }


}
