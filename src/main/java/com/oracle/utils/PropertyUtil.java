package com.oracle.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
	public static String getProperty(String source, String key) throws FileNotFoundException, IOException{
		Properties prop = new Properties();
		prop.load(PropertyUtil.class.getResourceAsStream(source));
		return prop.getProperty(key);
	}
	
}
