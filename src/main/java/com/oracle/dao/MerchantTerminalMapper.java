package com.oracle.dao;

import java.util.List;

import com.oracle.entity.MerchantTerminal;

public interface MerchantTerminalMapper {
    int insert(List<MerchantTerminal> list);

    int insertSelective(MerchantTerminal record);
    
    int deleteBatch(List<String> list);
}