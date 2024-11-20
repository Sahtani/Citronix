package com.youcode.citronix.farm.application.service.Impl;

import com.youcode.citronix.common.service.AbstractService;
import com.youcode.citronix.farm.application.dto.request.TreeRequestDTO;
import com.youcode.citronix.farm.application.dto.response.TreeResponseDTO;
import com.youcode.citronix.farm.application.mapper.TreeMapper;
import com.youcode.citronix.farm.application.service.TreeService;
import com.youcode.citronix.farm.domain.entity.Tree;
import com.youcode.citronix.farm.domain.repository.FiledRepository;
import com.youcode.citronix.farm.domain.repository.TreeRepository;
import org.springframework.stereotype.Service;

@Service
public class TreeServiceImpl extends AbstractService<Tree, TreeRequestDTO, TreeResponseDTO,Long> implements TreeService {

    private final TreeRepository treeRepository;
    private final TreeMapper treeMapper;
    private final FiledRepository fieldRepository;

    public TreeServiceImpl(TreeRepository treeRepository, TreeMapper treeMapper, FiledRepository fieldRepository) {
        super(treeRepository, treeMapper);
        this.treeRepository = treeRepository;
        this.treeMapper = treeMapper;
        this.fieldRepository = fieldRepository;
    }
}
