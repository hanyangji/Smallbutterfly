package com.oracle.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.dubbo.config.annotation.Reference;
import com.oracle.dao.AderMapper;
import com.oracle.dao.AdorMapper;
import com.oracle.data.DataSourceConst;
import com.oracle.data.DataSourceContextHolder;
import com.oracle.entity.Ader;
import com.oracle.entity.Ador;
import com.oracle.service.MerchantInfoService;
import com.oracle.serviceimple.MerchantInfoServiceimpl;
import com.oracle.utils.EntityAttribute;

@Controller
public class ResderController {

	@Autowired
	private AderMapper as;
	
	@Autowired
	private AdorMapper add;
	
	@RequestMapping("insert")
	public void insert() {
		DataSourceContextHolder.setDataSourceType(DataSourceConst.SQLSERVER);
		List<Ader> list=new ArrayList<Ader>();
		Map<String,String> map=new HashMap<>();
		for(int i=0;i<100;i++) {
			Ader ader=new Ader();
			ader.setBankname("b"+i);
			ader.setId("i"+i);
			ader.setName(null);
			list.add(ader);
		}
		as.insert(list);
		long start = System.currentTimeMillis();
		long end = System.currentTimeMillis();
		long useTime = (end - start);
		System.out.println(useTime);
	}
	
	@RequestMapping("inserto")
	public void inserot() {
		DataSourceContextHolder.setDataSourceType(DataSourceConst.ORACLE);
		List<Ador> list=new ArrayList<Ador>();
		Map<String,String> map=new HashMap<>();
		for(int i=0;i<500;i++) {
			Ador ader=new Ador();
			ader.setTest("test"+i);
			ader.setHh("hh"+i);
			list.add(ader);
		}
		long start = System.currentTimeMillis();
		add.insert(list);
		long end = System.currentTimeMillis();
		long useTime = (end - start);
		System.out.println(useTime);
	}
	
	@RequestMapping("delete")
	public void delete() {
		DataSourceContextHolder.setDataSourceType(DataSourceConst.ORACLE);
		List<Ador> list=new ArrayList<Ador>();
		for(int i=0;i<400;i++) {
			Ador ader=new Ador();
			ader.setTest("test"+i);
			ader.setHh("hh"+i);
			list.add(ader);
		}
		System.out.println(add.deleteBatch(list));
	}
	
	
}
