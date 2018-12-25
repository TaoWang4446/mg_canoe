package com.hworld.canoe.rest;

import com.hworld.canoe.dao.AreaDao;
import com.hworld.canoe.framework.controller.BaseController;
import com.hworld.canoe.framework.db.canoedb.entity.Area;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("area")
public class AreaRest extends BaseController<AreaDao, Area> {

}
