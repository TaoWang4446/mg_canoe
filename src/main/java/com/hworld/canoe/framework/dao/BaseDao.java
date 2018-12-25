package com.hworld.canoe.framework.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hworld.canoe.framework.logger.BaseLogger;
import com.hworld.canoe.framework.msg.resp.TableResultResponse;
import com.hworld.canoe.framework.sql.Query;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;


public abstract class BaseDao<M extends Mapper<T>, T> extends BaseLogger {
    @Autowired
    protected M mapper;

 /*   @Autowired
    protected EntityUtils entityUtils;*/

    public void setMapper(M mapper) {
        this.mapper = mapper;
    }

    public T selectOne(T entity) {
        return mapper.selectOne(entity);
    }


    public T selectById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }


    public List<T> selectList(T entity) {
        return mapper.select(entity);
    }


    public List<T> selectListAll() {
        return mapper.selectAll();
    }


    public Long selectCount(T entity) {
        return new Long(mapper.selectCount(entity));
    }

   /* public T setCreatAndUpdatInfoWithId(T entity) {
        entityUtils.setCreatAndUpdatInfoWithId(entity);
        return entity;
    }


    public T setCreatAndUpdatInfoNoId(T entity) {
        entityUtils.setCreatAndUpdatInfoNoId(entity);
        return entity;
    }
*/

    public T insert(T entity) {
//        entityUtils.setCreatAndUpdatInfo(entity);
        mapper.insert(entity);
        return entity;
    }

    public int insertii(T entity) {
//        entityUtils.setCreatAndUpdatInfo(entity);
        return mapper.insert(entity);
    }


    public void insertSelective(T entity) {
//        entityUtils.setCreatAndUpdatInfo(entity);
        mapper.insertSelective(entity);
    }


    public void delete(T entity) {
        mapper.delete(entity);
    }


    public void deleteById(Object id) {
        mapper.deleteByPrimaryKey(id);
    }


    public int updateById(T entity) {
//        entityUtils.setUpdatedInfo(entity);
        return mapper.updateByPrimaryKey(entity);
    }


    public int updateSelectiveById(T entity) {
//        entityUtils.setUpdatedInfo(entity);
        return mapper.updateByPrimaryKeySelective(entity);

    }

    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    public int selectCountByExample(Object example) {
        return mapper.selectCountByExample(example);
    }

    public TableResultResponse<T> selectByQuery(Query query) {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Example example = new Example(clazz);
        if (query.entrySet().size() > 0) {
            Example.Criteria criteria = example.createCriteria();
            for (Map.Entry<String, Object> entry : query.entrySet()) {
/*
                if (CommonConstants.ID.equals(entry.getKey())) {
                    criteria.andEqualTo(entry.getKey(), entry.getValue().toString());//.andLike(entry.getKey(), "%" + entry.getValue().toString() + "%");
                } else {
                    criteria.andLike(entry.getKey(), "%" + entry.getValue().toString() + "%");
                }
*/
                if (!StringUtils.isEmpty(entry.getValue().toString())) {
                    criteria.andEqualTo(entry.getKey(), entry.getValue().toString());//.andLike(entry.getKey(), "%" + entry.getValue().toString() + "%");

                }

            }
        }
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<T> list = mapper.selectByExample(example);
        return new TableResultResponse<T>(result.getTotal(), list);
    }

}
