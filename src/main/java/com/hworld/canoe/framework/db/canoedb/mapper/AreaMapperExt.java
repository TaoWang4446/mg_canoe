package com.hworld.canoe.framework.db.canoedb.mapper;

import com.hworld.canoe.framework.db.canoedb.entity.Area;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AreaMapperExt {
    List<Area> list(@Param("params")Map<String, Object> params);
}
