package org.amix.rand;

import java.util.Random;

import org.amix.util.ModelListUtils;

import com.alibaba.fastjson.JSONArray;

public class Rands {
	
	public String rands(int modelSwitchId){
		
		JSONArray modelList = ModelListUtils.get_list().getJSONArray("models");
		JSONArray messagesList = ModelListUtils.get_list().getJSONArray("messages");
		
		Random r = new Random();
		
		int modelRandId=modelSwitchId;
		while (modelSwitchId==modelRandId) {
			modelRandId=r.nextInt(modelList.size())+1;
		}
		
		return "{\"id\":\""+modelRandId+"\",\"name\":\""+modelList.getString(modelRandId-1)+"\",\"message\":\""+messagesList.getString(modelRandId-1)+"\"}";
	}

}
