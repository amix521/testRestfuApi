package org.amix.controller;

import org.amix.getmodel.GetModel;
import org.amix.rand.Rands;
import org.amix.rand.randtextures.RandTextures;
import org.amix.switchs.Switchs;
import org.amix.switchs.switchtextures.SwitchTextures;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/live2d")
@Api(value = "live2d操作接口", tags = "Live2DApiController", description = "live2d操作接口")
public class Live2DApiController {
	
	Switchs switchs=new Switchs();
	
	Rands rands=new Rands();
	
	SwitchTextures switchTextures=new SwitchTextures();
	
	RandTextures randTextures=new RandTextures();
	
	GetModel getModel=new GetModel();
	
	
	/**
	 * get
	 * @param queryParam
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/get/{queryParam}", method = RequestMethod.GET)
    @ApiImplicitParam(name = "queryParam", value = "model的queryParam", required = true, dataType = "String")
    @ApiOperation(value = "获取资源", notes = "获取一个资源的id与资源参数")
    public String getModel(@PathVariable String queryParam){
        return  getModel.getModel(queryParam);
    }
	
	
	/**
	 * switch
	 * @param id
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/switch/{id}", method = RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "model的id", required = true, dataType = "int")
    @ApiOperation(value = "顺序切换模型", notes = "顺序切换模型，或者switch一个model的id")
    public String switchs(@PathVariable int id){
        return  switchs.switchs(id);
    }

	
	/**
	 * rand
	 * @param queryParam
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/rand/{id}", method = RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "model的id", required = true, dataType = "int")
    @ApiOperation(value = "随机模型", notes = "随机切换模型")
    public String rands(@PathVariable int id){
        return  rands.rands(id);
    }
	
	
	/**
	 * @param queryParam
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/switch_textures/{queryParam}", method = RequestMethod.GET)
    @ApiImplicitParam(name = "queryParam", value = "model的queryParam", required = true, dataType = "String")
    @ApiOperation(value = "顺序切换皮肤", notes = "顺序切换皮肤，或者switch一个model的id与资源参数")
    public String switch_textures(@PathVariable String queryParam){
        return  switchTextures.switchTextures(queryParam);
    }
	
	
	/**
	 * rand_textures
	 * @param queryParam
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/rand_textures/{queryParam}", method = RequestMethod.GET)
    @ApiImplicitParam(name = "queryParam", value = "model的queryParam", required = true, dataType = "String")
    @ApiOperation(value = "随机皮肤", notes = "随机切换皮肤")
    public String randTextures(@PathVariable String queryParam){
        return  randTextures.randTextures(queryParam);
    }
	

}
