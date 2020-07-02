package org.amix.test;

import java.io.File;
import java.io.IOException;

import org.amix.lzw.face.FaceHelper;

public class testFace {
	public static void main(String[] args) throws IOException {
		 String img1 = "E:\\faces\\PAYPCID2000000028_front.jpg";
	     String img2 = "E:\\faces\\PAYPCID2000000028_live.jpg";
	     System.out.println("result:"+FaceHelper.compare(new File(img1), new File(img2)));
	}
}
