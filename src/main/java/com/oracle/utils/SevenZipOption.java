package com.oracle.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

import net.sf.sevenzipjbinding.ExtractOperationResult;
import net.sf.sevenzipjbinding.IInArchive;
import net.sf.sevenzipjbinding.ISequentialOutStream;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;
import net.sf.sevenzipjbinding.simple.ISimpleInArchive;
import net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem;

/**
 * 7z压缩包工具类
 * @author hecl
 *
 */
public class SevenZipOption {
	
	
	/**
	 * 打开压缩包
	 * @param archiveFilename
	 * @throws SevenZipException
	 * @throws FileNotFoundException
	 */
	public void openArchive(String archiveFilename) 
			throws SevenZipException, FileNotFoundException {

		RandomAccessFile randomAccessFile = new RandomAccessFile(archiveFilename, "r");

		IInArchive inArchive = SevenZip.openInArchive(null, // Choose format automatically
				new RandomAccessFileInStream(randomAccessFile));
	}

	/**
	 * 统计压缩包中的文件数
	 * @param archiveFilename
	 */
	public void countArchiveNumber(String archiveFilename){

		RandomAccessFile randomAccessFile = null;
		IInArchive inArchive = null;
		try {
			randomAccessFile = new RandomAccessFile(archiveFilename, "r");
			inArchive = SevenZip.openInArchive(null, // autodetect archive type
					new RandomAccessFileInStream(randomAccessFile));

			System.out.println("Count of items in archive: " 
					+ inArchive.getNumberOfItems());

		} catch (Exception e) {
			System.err.println("Error occurs: " + e);
		} finally {
			if (inArchive != null) {
				try {
					inArchive.close();
				} catch (SevenZipException e) {
					System.err.println("Error closing archive: " + e);
				}
			}
			if (randomAccessFile != null) {
				try {
					randomAccessFile.close();
				} catch (IOException e) {
					System.err.println("Error closing file: " + e);
				}
			}
		}
	}


	/**
	 * 列出压缩包中的数据
	 * @param archiveFilename
	 */
	public void listItemsSimple(String archiveFilename){
		RandomAccessFile randomAccessFile = null;
		IInArchive inArchive = null;
		try {
			randomAccessFile = new RandomAccessFile(archiveFilename, "r");
			inArchive = SevenZip.openInArchive(null, // autodetect archive type
					new RandomAccessFileInStream(randomAccessFile));

			// Getting simple interface of the archive inArchive
			ISimpleInArchive simpleInArchive = inArchive.getSimpleInterface();

			System.out.println("   Size   | Compr.Sz. | Filename");
			System.out.println("----------+-----------+---------");

			for (ISimpleInArchiveItem item : simpleInArchive.getArchiveItems()) {
				System.out.println(String.format("%9s | %9s | %s", // 
						item.getSize(), 
						item.getPackedSize(), 
						item.getPath()));
			}
		} catch (Exception e) {
			System.err.println("Error occurs: " + e);
		} finally {
			if (inArchive != null) {
				try {
					inArchive.close();
				} catch (SevenZipException e) {
					System.err.println("Error closing archive: " + e);
				}
			}
			if (randomAccessFile != null) {
				try {
					randomAccessFile.close();
				} catch (IOException e) {
					System.err.println("Error closing file: " + e);
				}
			}
		}
	}

	public static void extractItemsSimple(String archiveFilename){
		final File localFile = new File(archiveFilename);
		RandomAccessFile randomAccessFile = null;
		IInArchive inArchive = null;
		try {
			randomAccessFile = new RandomAccessFile(archiveFilename, "r");
			inArchive = SevenZip.openInArchive(null, // autodetect archive type
					new RandomAccessFileInStream(randomAccessFile));

			// Getting simple interface of the archive inArchive
			ISimpleInArchive simpleInArchive = inArchive.getSimpleInterface();

			System.out.println("   Hash   |    Size    | Filename");
			System.out.println("----------+------------+---------");

			for (final ISimpleInArchiveItem item : simpleInArchive.getArchiveItems()) {
				final int[] hash = new int[] { 0 };
				if (!item.isFolder()) {
					ExtractOperationResult result;

					final long[] sizeArray = new long[1];
					
					String date = DateFormat.getCurrentDate();
					//File file = new File(item.getPath());
					File file = new File(localFile.getParent() + "/" + date + "_" + item.getPath());
					//System.out.println(file.getAbsolutePath());
					//error occours below
					final FileOutputStream fos;
					fos = new FileOutputStream(file);
					result = item.extractSlow(new ISequentialOutStream() {
						public int write(byte[] data) throws SevenZipException {

							
							try {
								
								fos.write(data);
								fos.flush();
								

							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							hash[0] ^= Arrays.hashCode(data); // Consume data
							sizeArray[0] += data.length;
							return data.length; // Return amount of consumed data
						}
					});

					if (result == ExtractOperationResult.OK) {
						fos.close();
						System.out.println(String.format("%9X | %10s | %s", 
								hash[0], sizeArray[0], item.getPath()));
					} else {
						System.err.println("Error extracting item: " + result);
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Error occurs: " + e);
		} finally {
			if (inArchive != null) {
				try {
					inArchive.close();
				} catch (SevenZipException e) {
					System.err.println("Error closing archive: " + e);
				}
			}
			if (randomAccessFile != null) {
				try {
					randomAccessFile.close();
				} catch (IOException e) {
					System.err.println("Error closing file: " + e);
				}
			}
		}
	}

	public static void main(String[] args) {
		String archiveFile = "/Users/hanyangji/Desktop/99993420_20180621.7z";
		SevenZipOption.extractItemsSimple(archiveFile);
	}
}
