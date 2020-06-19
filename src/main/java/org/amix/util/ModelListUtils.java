package org.amix.util;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author Administrator
 * desc: 模型列表工具类
 */
public class ModelListUtils {
	
	/* 获取模型列表 */
	public static JSONObject get_list(){

		 String file1 =FileUtils.readFileToString("model_list.json");
		JSONObject jsonobject= JSON.parseObject(file1);
		return jsonobject;
	}
	
	/* 获取模组名称 */
	public static String id_to_name(int id){
		JSONArray list = get_list().getJSONArray("models");
		return (String) list.get(id);
	}
	
	/* 转换模型名称 */
	public static String name_to_id(String name){
		String id = null;
		JSONArray list = get_list().getJSONArray("models");
		for(int i=0;i<list.size();i++){
			Map<?, ?> dataMap =(Map<?, ?>) list.get(i);
			String dataName=(String) dataMap.get("name");
			if(dataName!=null){
				return i+"";
			}
		}
		return id;
	}
	
	
}
