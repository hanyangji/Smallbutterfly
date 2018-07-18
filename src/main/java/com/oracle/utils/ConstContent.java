package com.oracle.utils;

public class ConstContent {
	//数据文件存放根目录，子目录每天生成一个日期文件夹，日期为交易日日期
	public static String LOCALPATH = "/Users/hanyangji/Desktop/";
	
	//商户终端
	public static String PREFIX = "99993300_";
	public static String SURFIX = ".7z";
	public static String TXTNAME = "Merchant.txt";
	public static String REMOTE_DIR_MCHT = "/9999330000/";
	public static String PREFIX_ZS = "99993420_";//舟山商户文件前缀
	public static String TXTNAME_ZS = "ZS_Merchant.txt";
	
	//交易明细
	public static String PREFIX_CSV = "txn_330000_";
	public static String SURFIX_CSV = ".csv";
	
	//内卡交易330000_20170806.tar.gz
	public static String PREFIX_INNER_CARD = "330000_";
	public static String SURFIX_INNER_CARD = ".tar.gz";
	public static String PREFIX_INNER_CSV = "330000_inner_";
	public static String SURFIX_INNER_CSV = ".csv";
	
	//直连交易330000_ZL_20170806.tar.gz
	public static String PREFIX_ZL_CARD = "330000_ZL_";
	public static String SURFIX_ZL_CARD	= ".tar.gz";
	public static String REMOTE_DIR_ZL = "/AGTTxn/";
	public static String PREFIX_ZL_CSV = "330000_";
	public static String SURFIX_ZL_CSV = ".csv";
	
	//任务状态
	public static boolean TRANSE_LIST = false;
	public static boolean TRANSE_ZL = false;
	public static boolean TRANSE_INNER = false;
	
	//邮箱
	public static String HAVAU="havau@outlook.com";
	
	
}
