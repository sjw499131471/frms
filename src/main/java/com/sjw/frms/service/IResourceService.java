package com.sjw.frms.service;

import com.sjw.frms.model.Resource;

import java.util.List;
import java.util.Map;

public interface IResourceService {
    int deleteByPrimaryKey(String resourceId);

    int insert(Resource record);

    Resource selectByPrimaryKey(String resourceId);

    int updateByPrimaryKey(Resource record);

    List<Resource> selectList(Map<String,Object> map);

    int selectCount(Map<String,Object> map);
}
