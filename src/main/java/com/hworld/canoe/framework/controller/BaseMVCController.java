package com.hworld.canoe.framework.controller;

import com.hworld.canoe.framework.logger.BaseLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class BaseMVCController extends BaseLogger {

    public static final String redirect = "redirect:";

    public static final String forward = "forward:";

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;


    public abstract void form(Model model);


    public void addModelParameter(HttpServletRequest request, Model model) {
        Map<String, String[]> map = request.getParameterMap();
        Set<Map.Entry<String, String[]>> keSet = map.entrySet();
        for (Iterator itr = keSet.iterator(); itr.hasNext(); ) {

            Map.Entry<String, String[]> me = (Map.Entry<String, String[]>) itr.next();
            String key = me.getKey();
            String[] value = me.getValue();
            if (value == null || value.length == 0) {
                continue;
            } else if (value.length == 1) {
                model.addAttribute(key, value[0]);
            } else {
                model.addAttribute(key, value);
            }
        }
    }
}
