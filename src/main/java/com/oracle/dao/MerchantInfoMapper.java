package com.oracle.dao;

import java.util.List;

import com.oracle.entity.MerchantInfo;

public interface MerchantInfoMapper {
    int deleteBatch(List<String>  list);

    int insert(List<MerchantInfo> list);

    int insertSelective(MerchantInfo record);

    MerchantInfo selectByPrimaryKey(String 商户编码);

    int updateByPrimaryKeySelective(MerchantInfo record);

    int updateByPrimaryKey(MerchantInfo record);
    
    int delete(String 商户编码);
}