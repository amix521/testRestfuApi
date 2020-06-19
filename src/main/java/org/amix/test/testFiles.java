package org.amix.test;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

public class testFiles {
	public static void main(String[] args) {
		
		
   	 	File file = null;
		try {
			file = ResourceUtils.getFile("classpath:dozer");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String[] ss=file.list();
        System.out.println(ss);
	}
}
