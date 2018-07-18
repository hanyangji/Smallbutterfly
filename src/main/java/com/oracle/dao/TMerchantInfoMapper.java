package com.oracle.dao;

import java.util.List;

import com.oracle.entity.TMerchantInfo;

public interface TMerchantInfoMapper {
    int deleteByPrimaryKey(String mchtCode);

    int insert(List<TMerchantInfo> list);

    int insertSelective(TMerchantInfo record);

    TMerchantInfo selectByPrimaryKey(String mchtCode);

    int updateByPrimaryKeySelective(TMerchantInfo record);

    int updateByPrimaryKey(TMerchantInfo record);
    
    int deleteBatch(List<String> list);
}