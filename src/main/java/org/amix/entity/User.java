package org.amix.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import lombok.Setter;

@Data
public class User {
	
	@NotBlank 
	@Length(max=11)
	private long id;
	
	@NotBlank 
	@Length(max=50)
    private String name ;
	
	@NotBlank 
	@Length(max=2)
    private String sex;
    
	@NotNull 
	@Length(max=3)
    private long age;
    
	@NotNull
	@Length(max=20)
    private String mobile;
	
	@NotNull
    private Date datetime;
    
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public long getAge() {
		return age;
	}
	public void setAge(long age) {
		this.age = age;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	
	@Override
	public String toString()
	{
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("id",getId())
			.append("name",getName())
			.append("sex",getSex())
			.append("age",getAge())
			.append("mobile",getMobile())
			.append("datetime",getDatetime())
			.toString();
	}
}
