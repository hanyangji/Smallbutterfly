package com.oracle.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csvreader.CsvReader;


public class CSVReader {
	public static  List<Map<String, Object>> readFromCSV(String filePath) {
		
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();//将解析出来的数据存储在list中		
		int rows =  0;
		int columns = 0;
		try{
			CsvReader reader = new CsvReader(filePath, ',' , Charset.forName("GB18030"));
			//1. 获得表头字段名称 存储在列表中
			reader.readHeaders();			
			String[] headers = reader.getHeaders();
			columns = headers.length;
			
			//2. 获得记录
			while(reader.readRecord()){
				String[] rowRecord = reader.getValues();
				Map<String, Object> rowMap = new HashMap<String, Object>();
				for(int i=0; i<rowRecord.length; i++){
					rowMap.put(headers[i].trim(), rowRecord[i]);//组装每行的列数据
				}
				datas.add(rowMap);//将每行数据作为一条记录 存储在list中
				rows++;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("INFO: " + filePath +  " 共有:" + rows +"行，" + columns + "列!");
		return datas;
	}
	public static void main(String[] args) throws IOException {
		List<Map<String, Object>> datas=readFromCSV("/Users/hanyangji/Desktop/txn_330000_20180709.csv");
		for(int i=0;i<datas.size();i++) {
			System.out.println(datas.get(i));
		}
	}
}
