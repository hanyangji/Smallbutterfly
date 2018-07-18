package com.oracle.serviceimple;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.dao.TradeDetailsMapper;
import com.oracle.entity.MerchantTerminal;
import com.oracle.entity.TradeDetails;
import com.oracle.service.TradeDetailsService;
import com.oracle.utils.CSVReader;
import com.oracle.utils.ConstContent;
import com.oracle.utils.DateFormat;
import com.oracle.utils.EntityAttribute;
import com.oracle.utils.FTPUtil;
import com.oracle.utils.GZipUtil;
import com.oracle.utils.MailUtil;
import com.oracle.utils.ParseTxtFile;
/**
 * 交易明细导入mssql,字段数较多,采用非批量添加
 * @author hava
 * 2018年7月10日上午9:31:19
 */
@Service
public class TradeDetailsServiceimple implements TradeDetailsService {
	
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private TradeDetailsMapper tm;

	@Override
	public int insert(TradeDetails tradeDetails) {
		boolean mchtErrorFlag = false;
		String date = DateFormat.getDate();
		String remote = ConstContent.PREFIX_CSV + date + ConstContent.SURFIX_CSV;
		String localDir = ConstContent.LOCALPATH + DateFormat.getDate();
		String localFile = localDir + "/" + remote;
//		FTPUtil.download(remote, localDir);
		logger.info("交易流水文件下载成功");
		//解压文件
//		GZipUtil.uncompressGZ(localDir,remote);
		logger.info("交易流水文件解压成功");
		//读取数据文件内容
		String txtFile = ConstContent.LOCALPATH+ "/" + ConstContent.PREFIX_CSV +"_"+date + ConstContent.SURFIX_CSV;
//		List<Map<String,Object>> list=CSVReader.readFromCSV(txtFile);
		List<Map<String,Object>> list=CSVReader.readFromCSV("/Users/hanyangji/Desktop/txn_330000_20180709.csv");
		//封装实体列表 
		List<TradeDetails> listEntity=EntityAttribute.setEntityTradeDetails(list);
//		delete(listEntity);
		logger.info("开始导入交易流水信息");
		int mchtCount = 0;
		int errMcht = 0;
		long start = System.currentTimeMillis();
		for(int i=0;i<listEntity.size();i++) {
			
			if(tm.insertSelective(listEntity.get(i))!=0) {
				mchtCount++;
			}else {
				mchtErrorFlag=true;
				errMcht++;
			}
		}
		long end = System.currentTimeMillis();
		long useTime = (end - start)/(1000);
		String log1 = "----------------------------------写入交易流水信息完毕，写入成功" + mchtCount + "条数据, "
				+ "写入失败" + errMcht + "条，用时"
				+ useTime + "秒";
		logger.info(log1);
		MailUtil mail = new MailUtil();
		// 添加数据出错
		if (mchtErrorFlag) {
			mail.send(ConstContent.HAVAU, null, log1);
		}
		return 0;
	}
	
	/**
	 * 删除原有数据,防止重复导入
	 * 2018年7月9日下午1:29:51
	 */
	private void delete(List<TradeDetails> listEntity) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < listEntity.size(); i++) {
			list.add(listEntity.get(i).get商户编码());
		}
		int deleteCount = tm.deleteBatch(list);
		String log = "已删除原数据" + deleteCount + "条";
		logger.info(log);
	}

}
