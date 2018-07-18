package com.oracle.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailUtil {

	private String host = ""; // smtp服务器
	private String from = ""; // 发件人地址
	private String subject = ""; // 邮件标题
	private String affixPath = ""; // 附件路径
	private String affixName = ""; // 附件名称
	private String user = ""; // 发件人用户名
	private String pwd = ""; // 发件人密码


	void init() throws FileNotFoundException, IOException{
		//验证发件人所需信息
		this.host = PropertyUtil.getProperty("/resource/mail.properties", "server");
		this.host = "220.181.12.13";//在使用代理且无dns解析时使用
		this.user = PropertyUtil.getProperty("/resource/mail.properties", "user");
		this.pwd = PropertyUtil.getProperty("/resource/mail.properties", "password");
		//邮件的收发件地址
		this.from = PropertyUtil.getProperty("/resource/mail.properties", "from");
		this.subject = PropertyUtil.getProperty("/resource/mail.properties", "subject");
		//附件信息
		this.affixPath = PropertyUtil.getProperty("/resource/mail.properties", "affixPath");
		
	}

	public void send(String to, String fileName, String content)  {
		//初始化参数
		try {
			init();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.affixName = fileName;
		Properties props = new Properties();
		//设置代理
	    props.setProperty("proxySet", "true");  
//	    props.setProperty("http.proxyHost", "10.48.1.33");
//	    props.setProperty("http.proxyPort", "80");
//        props.setProperty("socksProxyHost", "10.48.1.33");  
//        props.setProperty("socksProxyPort", "80");  
		props.setProperty("ProxyHost","10.48.2.18");
		props.setProperty("ProxyPort","1080"); 
		// 设置发送邮件的邮件服务器
		props.put("mail.smtp.host", host);
		// 验证发送邮件的有效性
		props.put("mail.smtp.auth", "true");
		// 构建session
		Session session = Session.getDefaultInstance(props);
		//邮件发送信息记录
		//session.setDebug(true);
		// 用session为参数定义消息对象
		MimeMessage message = new MimeMessage(session);
		try {
			// 加载发件人地址
			message.setFrom(new InternetAddress(from));
			// 加载收件人地址
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// 加载标题
			message.setSubject(subject);

			// 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
			Multipart multipart = new MimeMultipart();

			// 设置邮件的文本内容
			BodyPart contentPart = new MimeBodyPart();
			contentPart.setText(content);
			multipart.addBodyPart(contentPart);
			if(!("".equals(fileName)||fileName==null)){
				// 添加附件
				BodyPart messageBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(affixPath + affixName);
				// 添加附件的内容
				messageBodyPart.setDataHandler(new DataHandler(source));
				// 添加附件的标题
				// 通过Base64编码的转换可以保证中文附件标题名在发送时不会变成乱码
				sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
				messageBodyPart.setFileName("=?GBK?B?"
						+ enc.encode(affixName.getBytes()) + "?=");
				multipart.addBodyPart(messageBodyPart);
			}
				// 将附件对象对象装载到message
				message.setContent(multipart);
			
			// 保存邮件
			message.saveChanges();
			// 发送邮件
			Transport transport = session.getTransport("smtp");
			// 连接服务器的邮箱
			transport.connect(host, user, pwd);
			// 发送
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		MailUtil mail = new MailUtil();
			mail.send("hecl@allinpay.com", null, "proxy test");
	}
}
