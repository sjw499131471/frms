package com.sjw.frms.service.impl;

import com.sjw.frms.dao.ResourceMapper;
import com.sjw.frms.dao.ResourceTypeMapper;
import com.sjw.frms.model.ResourceType;
import com.sjw.frms.service.IResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ResourceTypeServiceImpl implements IResourceTypeService {
    @Autowired
    ResourceTypeMapper resourceTypeMapper;
    @Override
    public int deleteByPrimaryKey(String typeId) {
        return resourceTypeMapper.deleteByPrimaryKey(typeId);
    }

    @Override
    public int insert(ResourceType record) {
        return resourceTypeMapper.insert(record);
    }

    @Override
    public ResourceType selectByPrimaryKey(String typeId) {
        return resourceTypeMapper.selectByPrimaryKey(typeId);
    }

    @Override
    public int updateByPrimaryKey(ResourceType record) {
        return resourceTypeMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ResourceType> selectList(Map<String, Object> map) {
        return resourceTypeMapper.selectList(map);
    }

    @Override
    public int selectCount(Map<String, Object> map) {
        return resourceTypeMapper.selectCount(map);
    }
}
