// Copyright (C) 2012-2013 GXCODE All rights reserved
package com.springboot.common.base.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 *       
 * 绫诲悕绉帮細IBaseMapper    
 * 绫绘弿杩帮細DAO灞傛硾鍨嬫帴鍙ｏ紝瀹氫箟鍩烘湰鐨凞AO鍔熻兘    
 * 鍒涘缓浜猴細guoxin    
 * 鍒涘缓鏃堕棿锛�2016骞�4鏈�28鏃� 涓嬪崍9:11:33    
 * @version     
 *
 */
public abstract interface IBaseMapper<T, PK extends Serializable> {
	/**
	 * 
	 * deleteByPrimaryKey
	 * 鎻忚堪锛氭牴鎹富閿垹闄ゆ暟鎹�
	 * 浣滆�咃細guoxin 
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(PK id);

    /**
     * insert
     * 鎻忚堪锛氭彃鍏ユ暟鎹紝锛堜笉鍒ゆ柇鏁版嵁瀛樺湪锛�
     * 浣滆�咃細guoxin 
     * @param record
     * @return
     */
    int insert(T record);

    /**
     * 
     * insertSelective
     * 鎻忚堪锛氭彃鍏ユ暟鎹紝鍙彃鍏ラ潪绌烘暟鎹�    
     * 浣滆�咃細guoxin 
     * @param record
     * @return
     */
    int insertSelective(T record);

    /**
     * 
     * selectByPrimaryKey
     * 鎻忚堪锛氫富閿煡璇㈡暟鎹�
     * 浣滆�咃細guoxin 
     * @param id
     * @return
     */
    T selectByPrimaryKey(PK id);

    /**
     * 
     * updateByPrimaryKeySelective
     * 鎻忚堪锛氭牴鎹富閿紝鏇存柊闈炵┖鏁版嵁
     * 浣滆�咃細guoxin 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * 
     * updateByPrimaryKey
     * 鎻忚堪锛氭牴鎹富閿紝鏇存柊鎵�鏈夋暟鎹�  
     * 浣滆�咃細guoxin 
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);
    /**
     * 
     * selectPageList
     * 鎻忚堪锛氬垎椤垫煡璇�
     * 浣滆�咃細guoxin 
     * @param map
     * @return
     */
    List<T> selectPageList(Map<String,Object> map);
    
    /**
     * 
     * selectList
     * 鎻忚堪锛氭煡璇㈠垪琛� 
     * 浣滆�咃細guoxin 
     * @param map
     * @return
     */
    List<T> selectList(Map<String,Object> map);
    
    /**
     * 
     * selectCount
     * 鎻忚堪锛氭煡璇㈡暟閲�
     * 浣滆�咃細guoxin 
     * @param map
     * @return
     */
    int selectCount(Map<String,Object> map);
    
    /**
     * 
     * selectOne
     * 鎻忚堪锛氭煡璇㈠崟鏉℃暟鎹�
     * 浣滆�咃細guoxin 
     * @param record
     * @return
     */
    T selectOne(T record);
}
