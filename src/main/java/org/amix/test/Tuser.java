package org.amix.test;

import org.amix.entity.User;

public class Tuser {
	private User user;

	public Tuser() {
		user = new User();
		user.setName("amix");
	}

	public User get(Long l) {
		this.user.setAge(l);
		return this.user;
	}
	
	public String show(String content){
        return "hi,"+content;
    }
}