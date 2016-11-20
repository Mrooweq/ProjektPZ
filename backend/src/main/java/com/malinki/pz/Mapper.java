package com.malinki.pz;

import org.apache.ibatis.annotations.Select;

import com.malinki.pz.controller.User;

public interface Mapper {
	  @Select("SELECT * FROM User WHERE @rid = #{id}")
	  User selectUser(String id);
	}