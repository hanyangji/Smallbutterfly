package com.oracle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.data.DataSourceConst;
import com.oracle.data.DataSourceContextHolder;
import com.oracle.entity.MerchantInfo;
import com.oracle.entity.MerchantTerminal;
import com.oracle.entity.TradeDetails;
import com.oracle.service.MerchantInfoService;
import com.oracle.service.MerchantTerminalService;
import com.oracle.service.TMerchantInfoService;
import com.oracle.service.TMerchantTerminalService;
import com.oracle.service.TradeDetailsService;

@Controller
public class TestCopyTableController {

	@Autowired
	private MerchantInfoService mis;
	@Autowired
	private MerchantTerminalService mts;
	@Autowired
	private TradeDetailsService tds;
	@Autowired
	private TMerchantInfoService tmis;
	@Autowired
	private TMerchantTerminalService tmts;
	
	@RequestMapping("mis")
	public void mis() {
		mis.insert(new MerchantInfo());
	}
	@RequestMapping("mts")
	public void mts() {
		mts.insert(new MerchantTerminal());
	}
	@RequestMapping("tds")
	public void tds() {
		tds.insert(new TradeDetails());
	}
	@RequestMapping("miso")
	public void miso() {
		DataSourceContextHolder.setDataSourceType(DataSourceConst.ORACLE);
		tmis.insert();
	}
	@RequestMapping("mtso")
	public void mtso() {
		DataSourceContextHolder.setDataSourceType(DataSourceConst.ORACLE);
		tmts.insert();
	}

}
