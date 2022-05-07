package com.sjw.frms.service.impl;

import com.sjw.frms.dao.MemberMapper;
import com.sjw.frms.model.Member;
import com.sjw.frms.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl implements IMemberService {
    @Autowired
    MemberMapper memberMapper;
    @Override
    public int deleteByPrimaryKey(String memberId) {
        return memberMapper.deleteByPrimaryKey(memberId);
    }

    @Override
    public int insert(Member record) {
        return memberMapper.insert(record);
    }

    @Override
    public Member selectByPrimaryKey(String memberId) {
        return memberMapper.selectByPrimaryKey(memberId);
    }

    @Override
    public int updateByPrimaryKey(Member record) {
        return memberMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Member> selectList(Map<String, Object> map) {
        return memberMapper.selectList(map);
    }

    @Override
    public int selectCount(Map<String, Object> map) {
        return memberMapper.selectCount(map);
    }
}
