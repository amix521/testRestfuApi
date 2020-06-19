package org.amix.switchs.switchtextures;

import org.amix.util.ModelListUtils;
import org.amix.util.ModelTexturesUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class SwitchTextures {

	public String switchTextures(String queryParam){
		
		String[] params=queryParam.split("-");
		
		int modelId = Integer.parseInt(params[0]);
		int modelTexturesId = params[1]==null ? Integer.parseInt(params[1]) : 0;
		
		
		String modelName = ModelListUtils.id_to_name(modelId);
		JSONArray modelTexturesList =modelName.indexOf("[")!=-1?JSON.parseArray("{\"textures\":\""+modelName+"\"}"):ModelTexturesUtils.get_list(modelName);
		
		int modelTexturesNewId = modelTexturesId == 0 ? 2 : modelTexturesId + 1;
		
		if(modelTexturesList.get(modelTexturesNewId-1)==null){
			modelTexturesNewId=1;
		}
				
		String nameStr=modelTexturesList.getString(modelTexturesNewId-1);
		String modelStr=modelName.indexOf("[")!=-1?JSON.parseArray("{\"textures\":\""+modelName+"\"}").getString(modelTexturesNewId-1):modelName;
		
		return "{\"id\":\""+modelTexturesNewId+"\",\"name\":\""+nameStr+"\",\"model\":\""+modelStr+"\"}";
	}
}
