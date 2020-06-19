package org.amix.getmodel;

import org.amix.util.FileUtils;
import org.amix.util.ModelListUtils;
import org.amix.util.ModelTexturesUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class GetModel {

	
public String getModel(String queryParam){
		
		String[] params=queryParam.split("-");
		
		int modelId = Integer.parseInt(params[0]);
		int modelTexturesId = params[1]==null ? Integer.parseInt(params[1]) : 0;
		
		
		String modelName = ModelListUtils.id_to_name(modelId);
		
		JSONObject json=null;
		if(modelName.indexOf("[")!=-1){
			modelName = modelTexturesId > 0 ? JSON.parseArray(modelName).getString(modelTexturesId-1) : JSON.parseArray(modelName).getString(0);
			json=JSON.parseObject(FileUtils.readFileToString("model/"+modelName+"/index.json"));
		}else{
			json=JSON.parseObject(FileUtils.readFileToString("model/"+modelName+"/index.json"));
			
			if (modelTexturesId > 0) {
		        String modelTexturesName = ModelTexturesUtils.get_name(modelName, modelTexturesId);
		        
		        if (modelTexturesName!=null) {
		        	String ss=modelTexturesName.indexOf("[")!=-1 ? modelTexturesName : JSON.parseArray(modelTexturesName).toString();
		        	json = JSON.parseObject("{\"textures\":\""+ss+"\"}");
		        }	
		    }
		}
		
		String textures=json.getString("textures");
		textures=textures.replace("texture", "../model/"+modelName+"/texture");
		json.remove("textures");
		
		json.put("textures", JSONArray.parse(textures));
		json.put("model", "../model/"+modelName+"/"+json.get("model"));
		
		if(json.get("pose")!=null){
			String pose=json.getString("pose");
			json.remove("pose");
			json.put("pose", "../model/"+modelName+"/"+pose);
		}
		
		if(json.get("physics")!=null){
			String physics=json.getString("physics");
			json.remove("physics");
			json.put("physics", "../model/"+modelName+"/"+physics);
		}
		
		if(json.get("motions")!=null){
			String motions=json.getString("motions");
			motions.replace("sounds", "../model/"+modelName+"/sounds");
			motions.replace("motions", "../model/"+modelName+"/motions");
			json.remove("motions");
			json.put("motions", JSONArray.parse(motions));
		}
			
		if(json.get("expressions")!=null){
			String expressions=json.getString("expressions");
			expressions.replace("expressions", "../model/"+modelName+"/expressions");
			json.remove("expressions");
			json.put("expressions", JSONArray.parse(expressions));
		}
		
		
		return json.toString();
	}
}
