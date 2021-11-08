package com.zk.mybatisplus.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Properties;

@Slf4j
public class CsvUtil {
	public static Properties prop = null;

	public static String getSysProperty(String name) {
		String propFile = "sys.properties";
		if (prop == null) {
			prop = new Properties();
		}
		try {
			// 文件流的编码方式
			prop.load(new InputStreamReader(new FileInputStream(new File(propFile + ""))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String v = prop.getProperty(name);
		return v;
	}

	final static private String csvPath = "/data/drone/drone_socket_nest/data/";
	/**
	 * 写文件
	 */
	public static void writeFileForCsv(String fileName, String[] str) throws Exception {
		fileName = csvPath+fileName;
		// 第一步：设置输出的文件路径
		// 如果该目录下不存在该文件，则文件会被创建到指定目录下。如果该目录有同名文件，那么该文件将被覆盖。
		File tempFilePath = new File(fileName);
		if (tempFilePath.exists() == false) {// 文件不存在创建文件
			tempFilePath.getParentFile().mkdirs();
			tempFilePath.createNewFile();
		}
		try {
			// 第二步：通过BufferedReader类创建一个使用默认大小输出缓冲区的缓冲字符输出流
			BufferedWriter writeText = new BufferedWriter(new FileWriter(
					tempFilePath, true));
			// 第三步：将文档的下一行数据赋值给lineData，并判断是否为空，若不为空则输出
			StringBuffer content = new StringBuffer();
			for (int i = 0; i < str.length; i++) {
				if (i == 0) {
					content.append(str[i]);
				} else {
					content.append("," + str[i]);
				}
			}
			writeText.write(content.toString());
			writeText.newLine(); // 换行
			// 使用缓冲区的刷新方法将数据刷到目的地中
			writeText.flush();
			// 关闭缓冲区，缓冲区没有调用系统底层资源，真正调用底层资源的是FileWriter对象，缓冲区仅仅是一个提高效率的作用
			// 因此，此处的close()方法关闭的是被缓存的流对象
			writeText.close();
		} catch (Exception e) {
			log.info("没有找到指定文件");
			e.printStackTrace();
		}
	}
}