package com.sjw.frms.service;

import com.sjw.frms.model.Member;

import java.util.List;
import java.util.Map;

public interface IMemberService {
    int deleteByPrimaryKey(String memberId);

    int insert(Member record);

    Member selectByPrimaryKey(String memberId);

    int updateByPrimaryKey(Member record);

    List<Member> selectList(Map<String,Object> map);

    int selectCount(Map<String,Object> map);
}
