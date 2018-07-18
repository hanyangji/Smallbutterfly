package com.oracle.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

public class FTPUtil {
	static Logger logger = Logger.getLogger(FTPUtil.class);


	/**
	 * FTP服务器连接
	 */
	public static FTPClient connectFTP(){
		FTPClient client = null;
		try {
			String server = PropertyUtil.getProperty("ftp.properties", "server");
			String port = PropertyUtil.getProperty("ftp.properties", "port");
			String username = PropertyUtil.getProperty("ftp.properties", "username");
			String password = PropertyUtil.getProperty("ftp.properties", "password");

			client = new FTPClient();
			if(port != null){
				client.connect(server, Integer.valueOf(port).intValue());
			} else {
				client.connect(server);
			}
			if(client.login(username, password)){//登录登录成功
				logger.info("ftp连接成功");
				return client;
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			logger.info("ftp连接失败!");
			e.printStackTrace();
			return null;
		} catch (Exception ex) {

			ex.printStackTrace();
			return null;
		}

		return null;
	}

	public static void disconnect(FTPClient client) throws IOException{
		if(client.isConnected()){
			client.disconnect();
		}
	}


	/**
	 * 设置连接属性
	 * @param client
	 * @throws IOException
	 */
	public static void setFTPClient(FTPClient client) throws IOException{
		/* Binary模式不会对数据进行任何处理。
		 * Ascii模式会将回车换行转换为本机的回车字符。* */
		client.setFileType(FTPClient.BINARY_FILE_TYPE);
		client.setControlEncoding("UTF-8");
	}

	/**
	 * 下载(源生)
	 * @param remoteFile
	 * @param localFile
	 * @throws IOException 
	 */
	public static void downloadFile(String remoteFile, String localFile) throws IOException{
		FTPClient client = connectFTP();
		setFTPClient(client);

		StringBuffer sb = new StringBuffer();
		String temp = null;
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(client.retrieveFileStream(remoteFile)));

		while((temp=reader.readLine())!=null){
			sb.append(temp);
			sb.append("\r\n");
		}
		reader.close();
		disconnect(client);

		FileWriter writer = new FileWriter(localFile, false);
		writer.write(sb.toString());
		writer.close();
	}

	/**
	 * 文件下载，ftp根目录
	 * @param remote
	 * @param local
	 * @throws IOException
	 */
	public static void download(String remoteFile, String localDir) {

		FTPClient client = connectFTP();

		logger.info("待下载文件:" + remoteFile);
		FTPFile[] files;
		try {
			files = client.listFiles(remoteFile);
		
		System.out.println(files.length);

		if(files.length>0){

			String local = localDir + "/" + remoteFile;
			File dir = new File(localDir);
			if(!dir.exists()){
				dir.mkdirs();
			}
			File localFile = new File(local);
			if(!localFile.exists()){
				localFile.createNewFile();
			}
			FileOutputStream fis = new FileOutputStream(localFile);
			client.retrieveFile(remoteFile, fis);
			fis.close();
			disconnect(client);
		} else {
			logger.info("下载失败，目标文件" + remoteFile + "不存在");
			throw new IOException("目标文件不存在");
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 文件下载，非ftp根目录
	 * @param remoteDir
	 * @param remote
	 * @param localDir
	 * @throws IOException 
	 */
	public static void download(String remoteDir, String remoteFile, String localDir) {
		try {
		FTPClient client = connectFTP();

		logger.info("待下载文件:" + remoteFile);
		client.changeWorkingDirectory(remoteDir);
		FTPFile[] files = client.listFiles(remoteFile);
		System.out.println(files.length);

		if(files.length>0){

			String local = localDir + "/" + remoteFile;
			File dir = new File(localDir);
			if(!dir.exists()){
				dir.mkdirs();
			}
			File localFile = new File(local);
			if(!localFile.exists()){
				localFile.createNewFile();
			}
			FileOutputStream fis = new FileOutputStream(localFile);
			client.retrieveFile(remoteFile, fis);
			fis.close();
			disconnect(client);
		} else {
			logger.info("下载失败，目标文件" + remoteFile + "不存在");
			throw new IOException("目标文件不存在");
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	
	public static void main(String[] args) {
		connectFTP();
	}

}
