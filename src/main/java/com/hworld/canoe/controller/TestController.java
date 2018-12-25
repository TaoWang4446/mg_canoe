package com.hworld.canoe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hworld.canoe.domain.req.TestRequest;
import com.hworld.canoe.framework.constant.CommonConstant;
import com.hworld.canoe.framework.controller.BaseMVCController;
import com.hworld.canoe.framework.db.canoedb.entity.Area;
import com.hworld.canoe.framework.msg.req.TableRequest;
import com.hworld.canoe.framework.msg.resp.ObjectRestResponse;
import com.hworld.canoe.framework.msg.resp.TableResponse;
import com.hworld.canoe.framework.validation.SurveillanceFeatureList;
import com.hworld.canoe.service.AreaService;
import com.hworld.canoe.validation.TestValidation;

@Controller
@RequestMapping
public class TestController extends BaseMVCController {

    private static String INDEX_PAGE = "/page/index.html";

    @Autowired
    AreaService areaService;

    @Autowired
    TestValidation testValidation;

    /*@RequestMapping("/")
    public String index(Model model) {
        form(model);
        return INDEX_PAGE;
    }*/


    @RequestMapping("/list.do")
    public @ResponseBody
    TableResponse<Area> list(TableRequest request) {
        return areaService.list(request);
    }


    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    public String add(Model model, TestRequest req) {
        SurveillanceFeatureList msg = testValidation.check(req);
        if (msg.size() > 0) {
            form(model);
            model.addAttribute("obj", req);
            model.addAttribute(CommonConstant.ERROR_PAGE, msg);
            return INDEX_PAGE;
        }
        //添加逻辑操作
        //areaService.add();
        return "redirect:/company/businessUnit/page.do";
    }


    @RequestMapping("/delete.do")
    public @ResponseBody
    ObjectRestResponse<?> delete(Long id) {
        //删除逻辑
        return areaService.deleteArea(id);
    }


    @Override
    public void form(Model model) {
        model.addAttribute("obj", new TestRequest());
        model.addAttribute("project", "皮划艇");

    }
}
