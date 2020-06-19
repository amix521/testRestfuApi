package org.amix.switchs;

import org.amix.util.ModelListUtils;

import com.alibaba.fastjson.JSONArray;

public class Switchs {
	
	public String switchs(int modelSwitchId){
		JSONArray modelList = ModelListUtils.get_list().getJSONArray("models");
		JSONArray messagesList = ModelListUtils.get_list().getJSONArray("messages");
		
		if(!isInList(modelList,modelSwitchId)){
			modelSwitchId=1;
		}
		
		return "{\"id\":\""+modelSwitchId+"\",\"name\":\""+modelList.getString(modelSwitchId-1)+"\",\"message\":\""+messagesList.getString(modelSwitchId-1)+"\"}";
	}
	
	public boolean isInList(JSONArray list,int num){
		
		for(int i=0;i<=list.size();i++){
			if(i==num&&list.get(i)==null){
				return false;
			}
		}
		return true;
	}
}
