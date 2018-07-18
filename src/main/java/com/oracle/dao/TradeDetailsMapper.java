package com.oracle.dao;

import java.util.List;

import com.oracle.entity.TradeDetails;

public interface TradeDetailsMapper {
    int insert(List<TradeDetails> list);

    int insertSelective(TradeDetails record);
    
    int deleteBatch(List<String> list);
}