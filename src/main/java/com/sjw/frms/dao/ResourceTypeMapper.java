package com.sjw.frms.dao;

import com.sjw.frms.model.ResourceType;

import java.util.List;
import java.util.Map;

public interface ResourceTypeMapper {
    int deleteByPrimaryKey(String typeId);

    int insert(ResourceType record);

    ResourceType selectByPrimaryKey(String typeId);

    int updateByPrimaryKey(ResourceType record);

    List<ResourceType> selectList(Map<String,Object> map);

    int selectCount(Map<String,Object> map);
}