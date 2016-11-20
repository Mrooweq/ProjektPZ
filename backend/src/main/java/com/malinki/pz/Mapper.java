package com.malinki.pz;

import java.util.Map;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.malinki.pz.controller.User;

public interface Mapper {
	@Select("SELECT * FROM person where PID = #{id}")
	@Results({
		@Result(property = "id", column = "PID"),
		@Result(property = "name", column = "NAME")
	})
	User getUser(int id);

	@Select("INSERT INTO person VALUES (${PID}, '${NAME}')")
	User addUser(Map<String, Object> params);

	@Select("commit")
	void commit();
}