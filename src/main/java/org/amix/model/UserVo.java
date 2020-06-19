package org.amix.model;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class UserVo {
	
	@NotBlank 
	@Length(max=11)
	private long userId;
	
	@NotBlank 
	@Length(max=50)
    private String userName ;
	
	@NotBlank 
	@Length(max=2)
    private String userSex;
    
	@NotNull 
	@Length(max=3)
    private long userAge;
    
	@NotNull
	@Length(max=20)
    private String userMobile;
    
	@NotNull
	@Length(max=20)
	private String regtime;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public long getUserAge() {
		return userAge;
	}

	public void setUserAge(long userAge) {
		this.userAge = userAge;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	@Override
	public String toString()
	{
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("userId",getUserId())
			.append("userName",getUserName())
			.append("userSex",getUserSex())
			.append("userAge",getUserAge())
			.append("userMobile",getUserMobile())
			.append("regtime",getRegtime())
			.toString();
	}
}
