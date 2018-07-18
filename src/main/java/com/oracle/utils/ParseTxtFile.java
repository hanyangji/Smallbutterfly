package com.oracle.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author hava
 * 2018年6月26日
 */
public class ParseTxtFile {
	/**
	 * 
	 * 2018年6月26日上午11:44:34
	 */
	public static List<Map<String,Object>> parseTXT(String fileName){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"GB18030"));
			String titles = reader.readLine();
			System.out.println(titles);
			String[] titleArray = titles.split("\\|");
			String content = null;
			while((content=reader.readLine())!=null) {
				String[] line1Str=content.split("\\|");
				Map<String,Object> map=new HashMap<String,Object>();
				for(int i=0;i<line1Str.length;i++) {
					if(!"".equals(line1Str[i])) {
//						System.out.println(titleArray[i]+":"+line1Str[i]);
						map.put(titleArray[i], line1Str[i]);
					}
				}
				list.add(map);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void main(String[] args) {
		String date = DateFormat.getCurrentDate();
		String remote = ConstContent.PREFIX + date + ConstContent.SURFIX;
		String localDir = ConstContent.LOCALPATH ;
		String localFile = localDir +  "/" + remote;
		String archiveFile = "/Users/hanyangji/Desktop/99993420_20180621.7z";
		SevenZipOption.extractItemsSimple(archiveFile);
		
		String txtFile = localDir + "/"+ date +"_"  + ConstContent.TXTNAME;
		
		parseTXT(txtFile);
		
	}
}
