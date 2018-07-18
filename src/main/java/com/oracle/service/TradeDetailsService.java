package com.oracle.service;

import com.oracle.entity.TradeDetails;

/*
 * sqlserver交易明细
 */
public interface TradeDetailsService {

	int insert(TradeDetails tradeDetails);
}
