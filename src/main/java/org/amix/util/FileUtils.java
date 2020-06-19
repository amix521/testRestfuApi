package org.amix.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.util.ResourceUtils;

public  class FileUtils {
	
	/**
     * 通过本地文件访问json并读取
     *
     * @param path：json文件路径
     * @return：json文件的内容
     */
    public static String readFileToString(String path) {
        StringBuffer laststr = new StringBuffer();
        File file = null;
		try {
			file = ResourceUtils.getFile("classpath:static/live2d/"+path);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}// 打开文件
        BufferedReader reader = null;
        try {
            FileInputStream in = new FileInputStream(file);
            reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));// 读取文件
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr = laststr.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException el) {
                }
            }
        }
        return laststr.toString();
    }
    
    public static boolean fileExists(String path){
    	 File file = null;
		try {
			file = ResourceUtils.getFile("classpath:static/live2d/"+path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return file.exists();
    }
    
    public static void main(String[] args) {
    	System.out.println(fileExists("/model/Potion-Maker/Tia/textures.cache"));
	}
}
