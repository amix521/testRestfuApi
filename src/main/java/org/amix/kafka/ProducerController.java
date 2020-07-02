package org.amix.kafka;

import org.amix.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "kafka测试接口", tags = "ProducerController", description = "kafka测试接口，新增一个UserDto")
@RestController
public class ProducerController {

	@Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;
	
    @PostMapping("/user/save")
    @ApiOperation(value = "创建用户", notes = "创建用户")
    public boolean saveUser(@ApiParam(value = "用户信息", required = true)@RequestBody UserDto userDto){
        kafkaTemplate.send("userTopic",userDto);
        return true;
    }
}
