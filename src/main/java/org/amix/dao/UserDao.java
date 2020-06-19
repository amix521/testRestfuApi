package org.amix.dao;

import java.util.List;

import org.amix.entity.User;
//import org.apache.ibatis.annotations.Param;

public interface UserDao{
	
	public int insert(User user);
	
	public int delete(Long id);
	
	public int update(User user);
	
    public User getUser(Long id);
    
    public List<User> getUserList(User user);
    
//    public int update(@Param("id")int id,@Param("userVo")UserVo userVo);
}
