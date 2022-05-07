package com.sjw.frms.controller;

import com.sjw.frms.model.Member;
import com.sjw.frms.service.IMemberService;
import com.sjw.frms.utils.ParamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    IMemberService memberService;

    @RequestMapping
    public String member(Model model){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("page",0);
        map.put("limit",10);
        List<Member> list = memberService.selectList(map);
        model.addAttribute("members",list);
        return "membersPage";
    }

    @RequestMapping("/getList")
    @ResponseBody
    public Map<String, Object> getList(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();

        Map<String,Object> map = ParamHandler.getParamMapForMysql(request);
        result.put("data", memberService.selectList(map));
        result.put("code", 0);
        result.put("count", memberService.selectCount(null));
        return result;
    }

    @ResponseBody
    @RequestMapping("/add")
    public Map<String, Object> add(@RequestParam String memberName){
        Map<String, Object> result = new HashMap<String, Object>();
        Member member = new Member();
        member.setMemberName(memberName);
        int count = memberService.insert(member);
        if (count>0){
            result.put("message","1");
        }else {
            result.put("message","失败");
        }
        return result;
    }
}
