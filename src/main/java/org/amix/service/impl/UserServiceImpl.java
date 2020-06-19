package org.amix.service.impl;


import java.util.List;

import org.amix.dao.UserDao;
import org.amix.dto.UserDto;
import org.amix.entity.User;
import org.amix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	UserDao userDao;
	
	public int save(User user){
		userDao.insert(user);
		log.info("存了一个用户");
		return 1;
	};
	
	public int delete(Long id){
		userDao.delete(id);
		log.info("删了一个用户");
		return 1;
	};
	
	public int update(UserDto userDto){
		
		User user=new User();
		user.setAge(userDto.getAge());
		user.setId(userDto.getId());
		user.setMobile(userDto.getMobile());
		user.setName(userDto.getName());
		user.setSex(userDto.getSex());
		user.setDatetime(userDto.getDatetime());
		
		userDao.update(user);
		log.info("修改了一个用户");
		return 1;
	};
	
    public User get(Long id){
    	User user = userDao.getUser(id);
    	log.info("查了一个用户");
    	return user;
    }
    
    @Override
	public List<User> getList(User user) {
		List<User> list = userDao.getUserList(user);
		log.info("查了一堆用户");
		return list;
	}
    
}
