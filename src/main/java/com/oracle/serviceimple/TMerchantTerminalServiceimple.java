package com.oracle.serviceimple;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.dao.TMerchantTerminalMapper;
import com.oracle.entity.TMerchantInfo;
import com.oracle.entity.TMerchantTerminal;
import com.oracle.service.TMerchantTerminalService;
import com.oracle.utils.ConstContent;
import com.oracle.utils.DateFormat;
import com.oracle.utils.EntityAttribute;
import com.oracle.utils.FTPUtil;
import com.oracle.utils.MailUtil;
import com.oracle.utils.ParseTxtFile;
import com.oracle.utils.SevenZipOption;
@Service
public class TMerchantTerminalServiceimple implements TMerchantTerminalService {

	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private TMerchantTerminalMapper tm;
	
	
	@Override
	public int insert() {
		// 当天时间(yyyyMMdd)
		String date = DateFormat.getCurrentDate();
		// 压缩文件名(文件所属编号+当天日期+文件格式)
		String remote = ConstContent.PREFIX + date + ConstContent.SURFIX;
		// 压缩文件路径(存放服务器路径+当天文件夹)
		String localDir = ConstContent.LOCALPATH + DateFormat.getDate();
		// 压缩文件绝对路径
		String localFile = localDir + "/" + remote;
		// ftp下载文件
		FTPUtil.download(ConstContent.REMOTE_DIR_MCHT, remote, DateFormat.getDate());
		logger.info("商户终端文件下载成功");
		// 解压文件
		SevenZipOption.extractItemsSimple(localFile);
		logger.info("商户终端数据文件解压成功");
		// 读取数据文件内容
		String txtFile = ConstContent.LOCALPATH + "/" + date + "_" + ConstContent.TXTNAME;
		List<Map<String, Object>> list = ParseTxtFile.parseTXT(txtFile);
		// 封装实体列表
//		List<TMerchantTerminal> listEntity = EntityAttribute.setEntity(new TMerchantTerminal(), list);
		// 删除原有数据
//		delete(listEntity);
		// 插入数据库
//		ins(new TMerchantTerminal(), listEntity, list);
		return 0;
	}
	
	
	private void delete(List<TMerchantTerminal> listEntity) {
		List<TMerchantTerminal> list = new ArrayList<TMerchantTerminal>();
		for (int i = 0; i < listEntity.size(); i++) {
			TMerchantTerminal tt=new TMerchantTerminal();
			tt.setCustomerid(listEntity.get(i).getCustomerid());
			tt.setTerminalid(listEntity.get(i).getTerminalid());
			list.add(tt);
		}
		int deleteCount = tm.deleteBatch(list);
		String log = "已删除原数据" + deleteCount + "条";
		logger.info(log);
	}

	public void ins(TMerchantTerminal merchantInfo, List<TMerchantTerminal> listEntity, List<Map<String, Object>> list) {
		boolean mchtErrorFlag = false;
		Field[] field = merchantInfo.getClass().getDeclaredFields();
		// 分批次批量插入数据库
		int k = 2100 / field.length - 1;
		int j = list.size() - list.size() % k;
		logger.info("开始导入商户终端信息");
		int mchtCoun = 0;
		int errMcht = 0;
		long start = System.currentTimeMillis();
		Map<String, String> map = new HashMap<String, String>();
		int poi = 0;
		int pos = 0;
		for (int i = 0; i < j; i += k) {
			poi = i;
			pos = i + k;
			int num = tm.insert(listEntity.subList(i, i + k));
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
			int n = tm.insert(listEntity.subList(j, list.size()));
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
		String log1 = "----------------------------------写入商户终端信息完毕，写入成功" + mchtCount + "条数据, " + "写入失败" + errMcht
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
