package org.amix.service;

import java.util.List;

import org.amix.dto.UserDto;
import org.amix.entity.User;

public interface UserService {
	
	public int save(User user);
	
	public int delete(Long id);
	
	public int update(UserDto userDto);
	
    public User get(Long id);
    
    public List<User> getList(User user);

}
