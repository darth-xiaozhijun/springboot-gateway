package com.springboot.core.service.impl;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.springboot.api.service.IBaseService;
import com.springboot.common.base.dao.IBaseMapper;


public abstract class BaseServiceImpl<T, PK extends Serializable> implements
		IBaseService<T, PK> {

	/**
	 * 日志对象
	 */
	protected Logger log = LoggerFactory.getLogger(super.getClass());

	protected abstract IBaseMapper<T, PK> getEntityMapper();

	@Override
	public int deleteByPrimaryKey(PK id) {
		return getEntityMapper().deleteByPrimaryKey(id);
	}

	@Override
	public int insert(T record) {
		return getEntityMapper().insert(record);
	}

	@Override
	public int insertSelective(T record) {
		return getEntityMapper().insertSelective(record);
	}

	@Override
	public T selectByPrimaryKey(PK id) {
		return (T) getEntityMapper().selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(T record) {
		return getEntityMapper().updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(T record) {
		return getEntityMapper().updateByPrimaryKey(record);
	}

	@Override
	public List<T> selectPageList(Map<String, Object> map) {
		return getEntityMapper().selectPageList(map);
	}

	@Override
	public List<T> selectList(Map<String, Object> map) {
		return getEntityMapper().selectList(map);
	}

	@Override
	public int selectCount(Map<String, Object> map) {
		return getEntityMapper().selectCount(map);
	}

	@Override
	public T selectOne(T record) {
		return (T) getEntityMapper().selectOne(record);
	}
}
