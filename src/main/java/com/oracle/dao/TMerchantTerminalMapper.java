package com.oracle.dao;

import java.util.List;

import com.oracle.entity.TMerchantTerminal;

public interface TMerchantTerminalMapper {
    int insert(List<TMerchantTerminal> list);

    int insertSelective(TMerchantTerminal record);
    
    int deleteBatch(List<TMerchantTerminal> list);
}