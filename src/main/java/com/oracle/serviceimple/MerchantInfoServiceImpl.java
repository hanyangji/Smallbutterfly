package com.oracle.serviceimple;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.dao.MerchantInfoMapper;
import com.oracle.entity.MerchantInfo;
import com.oracle.service.MerchantInfoService;
import com.oracle.utils.ConstContent;
import com.oracle.utils.DateFormat;
import com.oracle.utils.EntityAttribute;
import com.oracle.utils.MailUtil;
import com.oracle.utils.ParseTxtFile;

/**
 * 商户信息导入
 * @author hava 2018年6月28日下午3:51:35
 */
@Service
public class MerchantInfoServiceimpl implements MerchantInfoService {

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private MerchantInfoMapper mm;


	@Override
	public int insert(MerchantInfo merchantInfo) {
		// 当天时间(yyyyMMdd)
		String date = DateFormat.getCurrentDate();
		// 压缩文件名(文件所属编号+当天日期+文件格式)
		String remote = ConstContent.PREFIX + date + ConstContent.SURFIX;
		// 压缩文件路径(存放服务器路径+当天文件夹)
		String localDir = ConstContent.LOCALPATH ;
		// 压缩文件绝对路径
		String localFile = localDir + "/" + remote;
		// ftp下载文件
//		FTPUtil.download(ConstContent.REMOTE_DIR_MCHT, remote, DateFormat.getDate());
		logger.info("商户文件下载成功");
		// 解压文件
//		SevenZipOption.extractItemsSimple(localFile);
		logger.info("商户数据文件解压成功");
		// 读取数据文件内容
		String txtFile = ConstContent.LOCALPATH + "/" + date + "_" + ConstContent.TXTNAME;
//		List<Map<String, Object>> list = ParseTxtFile.parseTXT(txtFile);
		List<Map<String, Object>> list = ParseTxtFile.parseTXT("/Users/hanyangji/Desktop/20180710_Merchant.txt");
		// 封装实体列表
		List<MerchantInfo> listEntity = EntityAttribute.setEntity(list);
		// 删除原有数据
		delete(listEntity);
		// 插入数据库
		insBatch(merchantInfo, listEntity, list);
		return 0;
	}
	
	/**
	 * 循环删除
	 * 2018年7月10日下午3:26:31
	 */
	private void delete(List<MerchantInfo> listEntity) {
		int count=0;
		for(int i=0;i<listEntity.size();i++) {
			if(mm.delete(listEntity.get(i).get收单商户编码())!=0)
				count++;
		}
		logger.info( "已删除原数据" + count + "条");
	}
	

	/**
	 * 批量删除
	 * 2018年7月10日下午3:24:54
	 */
	private void deleteBatch(List<MerchantInfo> listEntity) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < listEntity.size(); i++) {
			list.add(listEntity.get(i).get收单商户编码());
		}
		int deleteCount = mm.deleteBatch(list);
		String log = "已删除原数据" + deleteCount + "条";
		logger.info(log);
	}
	
	/**
	 * 循环添加
	 * 2018年7月10日下午3:23:38
	 */
	public void ins(List<MerchantInfo> listEntity) {
		boolean mchtErrorFlag = false;
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
	}
	

	/**
	 * 批量添加
	 * 2018年7月10日下午3:23:52
	 */
	public void insBatch(MerchantInfo merchantInfo, List<MerchantInfo> listEntity, List<Map<String, Object>> list) {
		boolean mchtErrorFlag = false;
		Field[] field = merchantInfo.getClass().getDeclaredFields();
		// 分批次批量插入数据库
		int k = 2100 / field.length - 1;
		int j = list.size() - list.size() % k;
		logger.info("开始导入商户信息");
		int mchtCoun = 0;
		int errMcht = 0;
		long start = System.currentTimeMillis();
		Map<String, String> map = new HashMap<String, String>();
		int poi = 0;
		int pos = 0;
		for (int i = 0; i < j; i += k) {
			poi = i;
			pos = i + k;
			int num = mm.insert(listEntity.subList(i, i + k));
			if (num == k) {
				mchtCoun += num;
			} else {
				mchtErrorFlag = true;
				errMcht += k - num;
				map.put("error" + i, poi + "-" + pos);
			}
		}
		int mchtCount = mchtCoun;
		if (j != list.size()) {
			int n = mm.insert(listEntity.subList(j, list.size()));
			if (n == list.size() - j) {
				mchtCount += n;
			} else {
				mchtErrorFlag = true;
				errMcht += k + 1 - n;
				map.put("error", j + "-" + list.size());
			}
		}
		long end = System.currentTimeMillis();
		long useTime = (end - start) / (1000);
		String log1 = "----------------------------------写入商户信息完毕，写入成功" + mchtCount + "条数据, " + "写入失败" + errMcht
				+ "条，用时" + useTime + "秒";
		logger.info(log1);
		String logerr="";
		for (Map.Entry<String, String> entry : map.entrySet()) {
			logerr=entry.getKey() + "位置: " + entry.getValue();
			logger.info(logerr);
		}
		String log3=log1+"---||---"+logerr;
		MailUtil mail = new MailUtil();
		// 添加数据出错
		if (mchtErrorFlag) {
			mail.send(ConstContent.HAVAU, null, log3);
		}
	}

}
