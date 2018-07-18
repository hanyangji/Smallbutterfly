package com.oracle.serviceimple;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.dao.MerchantTerminalMapper;
import com.oracle.entity.MerchantInfo;
import com.oracle.entity.MerchantTerminal;
import com.oracle.service.MerchantTerminalService;
import com.oracle.utils.ConstContent;
import com.oracle.utils.DateFormat;
import com.oracle.utils.EntityAttribute;
import com.oracle.utils.FTPUtil;
import com.oracle.utils.MailUtil;
import com.oracle.utils.ParseTxtFile;
import com.oracle.utils.SevenZipOption;

/**
 * 商户终端导入
 * @author hava
 * 2018年6月28日下午3:46:39
 */
@Service
public class MerchantTerminalServiceimpl implements MerchantTerminalService {
	
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private MerchantTerminalMapper mm;

	@Override
	public int insert(MerchantTerminal merchantTerminal) {
		boolean mchtErrorFlag = false;
		//当天时间(yyyyMMdd)
		String date = DateFormat.getCurrentDate();
		//压缩文件名(文件所属编号+当天日期+文件格式)
		String remote = ConstContent.PREFIX + date + ConstContent.SURFIX;
		//压缩文件路径(存放服务器路径+当天文件夹)
		String localDir = ConstContent.LOCALPATH + DateFormat.getDate();
		//压缩文件绝对路径
		String localFile = localDir +"/" + remote;
		//ftp下载文件
//		FTPUtil.download(ConstContent.REMOTE_DIR_MCHT, remote, DateFormat.getDate());
		logger.info("商户终端文件下载成功");
		//解压文件
//		SevenZipOption.extractItemsSimple(localFile);
		logger.info("商户终端文件解压成功");
		//读取数据文件内容
		String txtFile = ConstContent.LOCALPATH+ "/" + date + "_" + ConstContent.TXTNAME;
//		List<Map<String,Object>> list=ParseTxtFile.parseTXT(txtFile);
		List<Map<String, Object>> list = ParseTxtFile.parseTXT("/Users/hanyangji/Desktop/20180710_Merchant.txt");
		//封装实体列表 
		List<MerchantTerminal> listEntity=EntityAttribute.setEntityMerchantTerminal(list);
		//删除原有数据,防止重复导入
//		delete(listEntity);
		logger.info("开始导入商户终端信息");
		int mchtTermCount=0;
		int errMchtTerm=0;
		long start = System.currentTimeMillis();
		for(int i=0;i<listEntity.size();i++) {
			if(mm.insertSelective(listEntity.get(i))!=0) {
				mchtTermCount++;
			}else {
				mchtErrorFlag=true;
				mchtTermCount++;
			}
		}
		long end = System.currentTimeMillis();
		long useTime = (end - start)/(1000);
		String log1 = "----------------------------------写入商户终端信息完毕，写入成功" + mchtTermCount + "条数据, "
				+ "写入失败" + errMchtTerm + "条，用时"
				+ useTime + "秒";
		logger.info(log1);
		MailUtil mail = new MailUtil();
		// 添加数据出错
		if (mchtErrorFlag) {
			mail.send(ConstContent.HAVAU, null, log1);
		}
		return 0;
	}
	
	private void delete(List<MerchantTerminal> listEntity) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < listEntity.size(); i++) {
			list.add(listEntity.get(i).get商户编码());
		}
		int deleteCount = mm.deleteBatch(list);
		String log = "已删除原数据" + deleteCount + "条";
		logger.info(log);
	}
}
