package com.springboot.api.service;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 *       
 * 类名称：IBaseService    
 * 类描述：通用服务接口类    
 * 创建人：xiaozhijun    
 * 创建时间：2016年4月28日 下午7:46:06    
 * @version     
 *
 */
public interface IBaseService<T,PK extends Serializable> {
	/**
	 * 
	 * deleteByPrimaryKey
	 * 描述：根据主键删除数据
	 * 作者：xiaozhijun 
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(PK id);

    /**
     * insert
     * 描述：插入数据，（不判断数据存在）
     * 作者：xiaozhijun 
     * @param record
     * @return
     */
    int insert(T record);

    /**
     * 
     * insertSelective
     * 描述：插入数据，只插入非空数据    
     * 作者：xiaozhijun 
     * @param record
     * @return
     */
    int insertSelective(T record);

    /**
     * 
     * selectByPrimaryKey
     * 描述：主键查询数据
     * 作者：xiaozhijun 
     * @param id
     * @return
     */
    T selectByPrimaryKey(PK id);

    /**
     * 
     * updateByPrimaryKeySelective
     * 描述：根据主键，更新非空数据
     * 作者：xiaozhijun 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * 
     * updateByPrimaryKey
     * 描述：根据主键，更新所有数据  
     * 作者：xiaozhijun 
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);
    /**
     * 
     * selectPageList
     * 描述：分页查询
     * 作者：xiaozhijun 
     * @param map
     * @return
     */
    List<T> selectPageList(Map<String,Object> map);
    
    /**
     * 
     * selectList
     * 描述：查询列表 
     * 作者：xiaozhijun 
     * @param map
     * @return
     */
    List<T> selectList(Map<String,Object> map);
    
    /**
     * 
     * selectCount
     * 描述：查询数量
     * 作者：xiaozhijun 
     * @param map
     * @return
     */
    int selectCount(Map<String,Object> map);
    
    /**
     * 
     * selectOne
     * 描述：查询单条数据
     * 作者：xiaozhijun 
     * @param record
     * @return
     */
    T selectOne(T record);
    
    

}
