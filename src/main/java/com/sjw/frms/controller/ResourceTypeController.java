package com.sjw.frms.controller;

import com.sjw.frms.service.IResourceTypeService;
import com.sjw.frms.utils.ParamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/resourceType")
public class ResourceTypeController {
    @Autowired
    IResourceTypeService resourceTypeService;

    @RequestMapping
    public String viewGroupedResource(Model model,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("page",0);
        map.put("limit",100);
        model.addAttribute("types",resourceTypeService.selectList(map));
        model.addAttribute("memberId",request.getParameter("memberId"));
        return "view-grouped-resource";
    }

    @RequestMapping("/getList")
    @ResponseBody
    public Map<String, Object> getList(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();

        Map<String,Object> map = new HashMap<>();
        map.put("page",0);
        map.put("limit",100);
        result.put("data", resourceTypeService.selectList(map));
        return result;
    }
}
