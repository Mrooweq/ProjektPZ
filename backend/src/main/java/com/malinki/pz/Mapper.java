package com.malinki.pz;

import java.util.Map;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.malinki.pz.controller.User;

public interface Mapper {
	@Select("SELECT * FROM \"USER\" where id = #{id}")
	@Results({
		@Result(property = "id", column = "ID"),
		@Result(property = "login", column = "LOGIN"),
		@Result(property = "password", column = "PASSWORD")
	})
	User getUser(int id);

	@Select("INSERT INTO \"USER\" VALUES (${id}, '${login}', '${password}')")
	User addUser(Map<String, Object> params);

	@Select("DELETE \"USER\"")
	void deleteAllUsers();
	
	@Select("COMMIT")
	void commit();
}