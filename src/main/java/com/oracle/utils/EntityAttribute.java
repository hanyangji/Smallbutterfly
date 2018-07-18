package com.oracle.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.dao.MerchantInfoMapper;
import com.oracle.entity.MerchantInfo;
import com.oracle.entity.MerchantTerminal;
import com.oracle.entity.TradeDetails;

/**
 * 
 * @author hava
 * 2018年6月27日下午2:41:57
 */
@Service
public class EntityAttribute {
	@Autowired
	private static MerchantInfoMapper mim;


	public static void main(String[] args) {
//		setEntityTradeDetails(CSVReader.readFromCSV("/Users/hanyangji/Desktop/txn_330000_20180709.csv"));
//			setEntity(ParseTxtFile.parseTXT("/Users/hanyangji/Desktop/20180710_Merchant.txt"));
		
//		List<Integer> list=new ArrayList<Integer>();
//		for(int i=0;i<120;i++) {
//			list.add(i);
//		}
//		MerchantInfo merchantInfo=new MerchantInfo();
//		Field[] field=merchantInfo.getClass().getDeclaredFields();
//		int k=2100/field.length-1;
//		int j=list.size()-list.size()%k;
//		for(int i=0;i<j;i+=k) {
//			System.out.println(list.subList(i, i+k));
//		}
//		if(j!=list.size())
//		System.out.println(list.subList(j, list.size()));
		
		
//		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
//		
//		for(int i=0;i<2;i++) {
//			Map<String,Object> map=new HashMap<String,Object>();
//			map.put("ss", i+"");
//			Integer t=i;
//			map.put("dd", t);
//			list.add(map);
//		}
//		List<show> li=setEntity( list);
//		for(int i=0;i<li.size();i++) {
//			System.out.println(li.get(i).toString());
//		}
		Double s=Double.parseDouble("439226****2431");
		System.out.println(s);
	}


	public static List<MerchantInfo> setEntity(List<Map<String, Object>> list) {
		List<MerchantInfo> listentity = new ArrayList<MerchantInfo>();
		try {
			MerchantInfo entity12=new MerchantInfo();
			Field[] field = entity12.getClass().getDeclaredFields();
			for (int j = 0; j < list.size(); j++) {
				MerchantInfo entity=new MerchantInfo();
				Map<String, Object> map = list.get(j);
					for (int i = 0; i < field.length; i++) {
						String name = field[i].getName();
						String type = field[i].getGenericType().toString();
						Object value = map.get(name);
						// 将属性首字母大写
						if(name.substring(0, 1).matches("[a-z]+"))
						name = name.replaceFirst(name.substring(0, 1), name.substring(0, 1).toUpperCase());
						// 调用set方法完成赋值
						Method method=null;
						if(type.equals("class java.lang.String"))
							method= entity.getClass().getMethod("set" + name,String.class);
						if(type.equals("class java.lang.Integer"))
							method= entity.getClass().getMethod("set" + name,Integer.class);
						if(type.equals("class java.lang.Boolean"))
							method= entity.getClass().getMethod("set" + name,Boolean.class);
						if(type.equals("class java.util.Date"))
							method= entity.getClass().getMethod("set" + name,Date.class);
						if(type.equals("class java.lang.Double"))
							method= entity.getClass().getMethod("set" + name,Double.class);
						method.invoke(entity, value);
					}
				listentity.add(entity);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
		return listentity;
	}
	
	public static List<TradeDetails> setEntityTradeDetails(List<Map<String, Object>> list) {
		List<TradeDetails> listentity = new ArrayList<TradeDetails>();
		try {
			TradeDetails entity12=new TradeDetails();
			Field[] field = entity12.getClass().getDeclaredFields();
			for (int j = 0; j < list.size(); j++) {
				TradeDetails entity=new TradeDetails();
				Map<String, Object> map = list.get(j);
					for (int i = 0; i < field.length; i++) {
						String name = field[i].getName();
						String type = field[i].getGenericType().toString();
						Object value = map.get(name);
						// 将属性首字母大写
						if(name.substring(0, 1).matches("[a-z]+"))
						name = name.replaceFirst(name.substring(0, 1), name.substring(0, 1).toUpperCase());
						// 调用set方法完成赋值
						Method method=null;
						if(type.equals("class java.lang.String"))
							method= entity.getClass().getMethod("set" + name,String.class);
						if(type.equals("class java.lang.Integer"))
							method= entity.getClass().getMethod("set" + name,Integer.class);
						if(type.equals("class java.lang.Boolean"))
							method= entity.getClass().getMethod("set" + name,Boolean.class);
						if(type.equals("class java.util.Date"))
							method= entity.getClass().getMethod("set" + name,Date.class);
						if(type.equals("class java.lang.Double")) {
							method= entity.getClass().getMethod("set" + name,Double.class);
							value=Double.parseDouble(value.toString());
						}
						method.invoke(entity, value);
					}
					System.out.println(entity.get交易金额());
				listentity.add(entity);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
		return listentity;
	}
	public static List<MerchantTerminal> setEntityMerchantTerminal(List<Map<String, Object>> list) {
		List<MerchantTerminal> listentity = new ArrayList<MerchantTerminal>();
		try {
			MerchantTerminal entity12=new MerchantTerminal();
			Field[] field = entity12.getClass().getDeclaredFields();
			for (int j = 0; j < list.size(); j++) {
				MerchantTerminal entity=new MerchantTerminal();
				Map<String, Object> map = list.get(j);
					for (int i = 0; i < field.length; i++) {
						String name = field[i].getName();
						String type = field[i].getGenericType().toString();
						Object value = map.get(name);
						// 将属性首字母大写
						if(name.substring(0, 1).matches("[a-z]+"))
						name = name.replaceFirst(name.substring(0, 1), name.substring(0, 1).toUpperCase());
						// 调用set方法完成赋值
						Method method=null;
						if(type.equals("class java.lang.String"))
							method= entity.getClass().getMethod("set" + name,String.class);
						if(type.equals("class java.lang.Integer"))
							method= entity.getClass().getMethod("set" + name,Integer.class);
						if(type.equals("class java.lang.Boolean"))
							method= entity.getClass().getMethod("set" + name,Boolean.class);
						if(type.equals("class java.util.Date"))
							method= entity.getClass().getMethod("set" + name,Date.class);
						if(type.equals("class java.lang.Double"))
							method= entity.getClass().getMethod("set" + name,Double.class);
						method.invoke(entity, value);
					}
				listentity.add(entity);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
		return listentity;
	}
}
