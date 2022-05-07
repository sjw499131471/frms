package com.sjw.frms.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class ParamHandler {
    private static void rebuildParamMap (Map<String, Object> queryParam){
        if (queryParam != null) {
            int page = Integer.parseInt(queryParam.get("page").toString());
            int limit = Integer.parseInt(queryParam.get("limit").toString());
            queryParam.put("page", (page-1)*limit);
            queryParam.put("limit", limit);
        }

    }

    public static Map<String,Object> getParamMap(HttpServletRequest request){
        Map<String,Object> map = new HashMap<String,Object>();
        @SuppressWarnings("rawtypes")
        Enumeration enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            map.put(paraName, request.getParameter(paraName));
        }
        return map;
    }

    public static Map<String,Object> getParamMapForMysql(HttpServletRequest request){
        Map<String,Object> map = getParamMap(request);
        rebuildParamMap(map);
        return map;
    }
}
