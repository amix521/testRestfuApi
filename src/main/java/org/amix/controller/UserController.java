package org.amix.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

import org.amix.dto.UserDto;
import org.amix.entity.User;
import org.amix.model.UserVo;
import org.amix.service.UserService;
import org.amix.target.LoginRequired;
import org.amix.target.MyLog;
import org.amix.util.dozer.DozerUtil;

@Slf4j  //这个只在ideal里能使用,eclipse里用需要做ini的配置
@RestController
@RequestMapping("/users")
@Api(value = "user操作接口", tags = "UserController", description = "user操作接口")
public class UserController {
	
	@Autowired
    private UserService userService;
    
    /**
     * 创建user
     * @param user
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ApiOperation(value = "创建用户", notes = "创建用户")
    @LoginRequired
    public String save(@ApiParam(value = "用户信息", required = true) User user){
    	
    	userService.save(user);
        return "success";
    }
    
    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除用户", notes = "根据id删除用户")
    public String delete(@ApiParam(value = "id", required = false) @PathVariable Long id){
    	
        userService.delete(id);
        return "success";
    }
    
    /**
     * 根据id更新用户user信息
     * @param id
     * @param user
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ApiOperation(value = "更新用户", notes = "根据id更新用户user信息")
    public String update(@ApiParam(value = "用户信息", required = false) UserVo userVo){
    	UserDto userDto=DozerUtil.map(userVo, UserDto.class);
    	System.out.println(userDto);
        userService.update(userDto);
        return "success";
    }
    
    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据id查询用户", notes = "根据id查询用户")
    @MyLog
    public String get(@ApiParam(value = "用户id", required = true) @PathVariable Long id){
    	
    	String reqUser=JSONObject.toJSONString(userService.get(id));
    	log.info(reqUser);
        return  reqUser;
    }
    
    /**
     * 根据条件查询用户列表
     * @param name
     * @param sex
     * @param age
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户列表", notes = "根据条件查询用户列表")
    @MyLog
    public String getList(@ApiParam(value = "姓名", required = false) @RequestParam(value = "name", required=false) String name,
    						@ApiParam(value = "性别", required = false) @RequestParam(value = "sex", required=false) String sex,
    						@ApiParam(value = "年龄", required = false) @RequestParam(value = "age",defaultValue="0", required=false) Integer age,
    						@ApiParam(value = "电话", required = false) @RequestParam(value = "mobile", required=false) String mobile){
    	
    	User qUser=new User();
    	qUser.setName(name);
    	qUser.setSex(sex);
    	qUser.setAge(age);
    	qUser.setMobile(mobile);
    	
    	String reqUser=JSONObject.toJSONString(userService.getList(qUser));
    	log.info(reqUser);
        return  reqUser;
    }
}
