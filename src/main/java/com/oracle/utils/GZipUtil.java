package com.oracle.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;



public class GZipUtil {
	/**
	 * 内卡gzip解压
	 * @param path
	 * @param file
	 * @throws IOException
	 */
	public static void uncompressGZForInnerCard(String path, String file) throws IOException{
		
		InputStream input = new FileInputStream(path + file);
		String outFile = file.substring(0, file.lastIndexOf("."));
		byte[] bytes = new byte[1024];
		GzipCompressorInputStream gzin = new GzipCompressorInputStream(input);
		OutputStream output = new FileOutputStream(path + outFile);
		int len = 0;
		while((len=gzin.read(bytes))!=-1){
			output.write(bytes, 0, len);
		}
		output.flush();
		output.close();
		input.close();
		uncompressTARForInnerCard(path, outFile);
	}
	
	/**
	 * 通用gzip解压
	 * @param path
	 * @param file
	 * @throws IOException
	 */
	public static void uncompressGZ(String path, String file) {
		InputStream input;
		try {
			input = new FileInputStream(path + file);
			String outFile = file.substring(0, file.lastIndexOf("."));
			byte[] bytes = new byte[1024];
			GzipCompressorInputStream gzin;
			gzin = new GzipCompressorInputStream(input);
			OutputStream output = new FileOutputStream(path + outFile);
			int len = 0;
			while((len=gzin.read(bytes))!=-1){
				output.write(bytes, 0, len);
			}
			output.flush();
			output.close();
			input.close();
			uncompressTAR(path, outFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 内卡tar解压
	 * @param path
	 * @param file
	 * @throws IOException
	 */
	public static void uncompressTARForInnerCard(String path, String file) throws IOException{
		TarArchiveInputStream tais = 
		        new TarArchiveInputStream(new FileInputStream(path + file));
		TarArchiveEntry tarArchiveEntry = null;
		while((tarArchiveEntry = tais.getNextTarEntry()) != null){
            //String name = new String(tarArchiveEntry.getName().getBytes(), "gbk");
			String name = ConstContent.PREFIX_INNER_CSV + DateFormat.getDate() + ConstContent.SURFIX_INNER_CSV;
            File tarFile = new File(path + name);

            BufferedOutputStream bos = 
            new BufferedOutputStream(new FileOutputStream(tarFile));

            int read = -1;
            byte[] buffer = new byte[1024];
            while((read = tais.read(buffer)) != -1){
                bos.write(buffer, 0, read);
            }
            bos.flush();
            bos.close();
        }
		tais.close();
	}
	
	/**
	 * 通用tar解压
	 * @param path
	 * @param file
	 * @throws IOException
	 */
	public static void uncompressTAR(String path, String file) throws IOException{
		TarArchiveInputStream tais = 
		        new TarArchiveInputStream(new FileInputStream(path + file));
		TarArchiveEntry tarArchiveEntry = null;
		while((tarArchiveEntry = tais.getNextTarEntry()) != null){
            String name = tarArchiveEntry.getName();
            File tarFile = new File(path + name);

            BufferedOutputStream bos = 
            new BufferedOutputStream(new FileOutputStream(tarFile));

            int read = -1;
            byte[] buffer = new byte[1024];
            while((read = tais.read(buffer)) != -1){
                bos.write(buffer, 0, read);
            }
            bos.flush();
            bos.close();
        }
		tais.close();
	}
	
	
	public static void main(String[] args) {
			uncompressGZ(ConstContent.LOCALPATH , 
							ConstContent.PREFIX_INNER_CARD + DateFormat.getDate() + 
									ConstContent.SURFIX_INNER_CARD);
		
	}
}
