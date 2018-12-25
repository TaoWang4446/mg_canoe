package com.hworld.canoe.dao;

import com.hworld.canoe.framework.dao.BaseDao;
import com.hworld.canoe.framework.db.canoedb.entity.Area;
import com.hworld.canoe.framework.db.canoedb.mapper.AreaMapper;
import com.hworld.canoe.framework.db.canoedb.mapper.AreaMapperExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class AreaDao extends BaseDao<AreaMapper, Area> {
    @Autowired
    AreaMapperExt areaMapperExt;

    public List<Area> list(Map<String, Object> params) {
        return areaMapperExt.list(params);
    }
}
