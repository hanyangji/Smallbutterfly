package com.oracle.dao;

import java.util.List;

import com.oracle.entity.Ader;
import com.oracle.entity.MerchantInfo;

public interface AderMapper {
	
    int insert(List<Ader> list);

    int insertSelective(Ader record);
    
    int delete(List<String> list);
}