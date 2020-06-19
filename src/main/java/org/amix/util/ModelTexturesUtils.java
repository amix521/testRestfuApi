package org.amix.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class ModelTexturesUtils {
	
	/* 获取材质名称 */
	public static String get_name(String modelName,int id){
		JSONArray list=get_list(modelName);
		
		return list.getString(id);
	}
	
	public static JSONArray get_list(String modelName) {
		
		JSONArray list=null;
		if(FileUtils.fileExists("model/"+modelName+"/textures.cache")){
			
			String file1=FileUtils.readFileToString("model/"+modelName+"/textures.cache");
			list = JSON.parseArray(file1);
		}else{
			String file1=FileUtils.readFileToString("/"+modelName);
			list = JSON.parseArray(file1);
		}
		
		return list;
	}
	
	public static Map<?, String> get_textures(String modelName){
		
		Map<String, String> tmp=new HashMap<String, String>();
		
		if(FileUtils.fileExists("model/"+modelName+"/textures_order.json")){
			String file1=FileUtils.readFileToString("model/"+modelName+"/textures_order.json");
			
			@SuppressWarnings("rawtypes")
			List<ArrayList> orderList=JSON.parseArray(file1,ArrayList.class);
			for(int k=0;k<=orderList.size() ; k++){

				@SuppressWarnings("unchecked")
				ArrayList<String> vList=orderList.get(k);
				Map<String, String> tmp2=new HashMap<String, String>();
				for(String texturesDir : vList){
					
					String file2=FileUtils.readFileToString("model/"+modelName+"/"+texturesDir+"/*");
					List<String> nList=JSON.parseArray(file2,String.class);
					
					Map<String, String> tmp3=new HashMap<String, String>();
					for(int n=0;k<=nList.size() ; n++){
						String m =nList.get(n);
						tmp3.put("merge."+n, m.replace("model/"+modelName+"/", ""));
					}
					tmp2.putAll(tmp3);
				}
				
				tmp.putAll(tmp2);
			}
			
		}else{
			String file1=FileUtils.readFileToString("model/"+modelName+"/textures/*");
			List<String> orderList=JSON.parseArray(file1,String.class);
			Map<String, String> tmp2=new HashMap<String, String>();
			for(int i=0;i<=orderList.size();i++){
				String m=orderList.get(i);
				tmp2.put("merge."+i, m.replace("model/"+modelName+"/", ""));
			}
			
		}
		
		return tmp;
	}
	
}
