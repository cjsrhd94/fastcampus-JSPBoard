package com.fastcampus.biz.user;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

// VO Å¬·¡½º
@Getter
@Setter
public class UserVO {
	private String id;
	private String password;
	private String name;
	private Date regDate;
	private String role; 
}
