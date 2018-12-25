package com.hworld.canoe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hworld.canoe.dao.AreaDao;
import com.hworld.canoe.framework.db.canoedb.entity.Area;
import com.hworld.canoe.framework.db.canoedb.mapper.AreaMapper;
import com.hworld.canoe.framework.msg.req.TableRequest;
import com.hworld.canoe.framework.msg.resp.ObjectRestResponse;
import com.hworld.canoe.framework.msg.resp.TableResponse;
import com.hworld.canoe.framework.service.impl.BaseServiceImpl;

@Service
public class AreaService extends BaseServiceImpl<AreaMapper, Area> {

    @Autowired
    AreaDao areaDao;

    public TableResponse<Area> list(TableRequest request) {
        Area area = areaDao.selectById(1L);
        Page<Area> pages = PageHelper.startPage(request.getOffset(), request.getLimit());
        List<Area> list = areaDao.list(request.getParams());
        return TableResponse.<Area>builder().recordsTotal(pages.getTotal()).recordsFiltered(pages.getTotal()).data(list).build();

    }


    public ObjectRestResponse<?> deleteArea(Long id) {
        //删除逻辑
        //本例简单模拟
        if (id / 2 == 0) {
            return new ObjectRestResponse<>();
        } else {
            return new ObjectRestResponse<>(500, "删除失败,单数...");
        }
    }
}
