package com.sjw.frms.service.impl;

import com.sjw.frms.dao.ResourceMapper;
import com.sjw.frms.model.Resource;
import com.sjw.frms.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ResourceServiceImpl implements IResourceService {
    @Autowired
    ResourceMapper resourceMapper;

    @Override
    public int deleteByPrimaryKey(String resourceId) {
        return resourceMapper.deleteByPrimaryKey(resourceId);
    }

    @Override
    public int insert(Resource record) {
        return resourceMapper.insert(record);
    }

    @Override
    public Resource selectByPrimaryKey(String resourceId) {
        return resourceMapper.selectByPrimaryKey(resourceId);
    }

    @Override
    public int updateByPrimaryKey(Resource record) {
        return resourceMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Resource> selectList(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        if (map.containsKey("typeId")){
            String typeId = map.get("typeId").toString();
            if (typeId != null && !("1".equals(typeId)))
                sb.append("AND RESOURCE_TYPE_ID='" + typeId + "'");
        }
        if (map.containsKey("memberId")){
            String memberId = map.get("memberId").toString();
            sb.append("AND MEMBER_ID='" + memberId + "'");
        }
        map.put("where",sb.toString());
        return resourceMapper.selectList(map);
    }

    @Override
    public int selectCount(Map<String, Object> map) {
        return resourceMapper.selectCount(map);
    }
}
