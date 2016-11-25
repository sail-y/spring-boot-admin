package com.dmc.service.impl;

import com.dmc.mapper.ResourceTypeMapper;
import com.dmc.model.ResourceType;
import com.dmc.service.ResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ResourceTypeServiceImpl implements ResourceTypeService {

    @Autowired
    private ResourceTypeMapper resourceTypeMapper;

    @Override
    @Cacheable(value = "resourceTypeServiceCache", key = "'resourceTypeList'")
    public List<ResourceType> getResourceTypeList() {
        return resourceTypeMapper.getResourceTypeList();
    }

}
