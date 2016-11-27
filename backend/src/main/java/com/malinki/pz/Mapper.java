package com.malinki.pz;

import java.util.Map;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.malinki.pz.controller.Animal;

public interface Mapper {
	@Select("SELECT * FROM Animal where id = #{id}")
	@Results({
		@Result(property = "id", column = "id"),
		@Result(property = "name", column = "name"),
		@Result(property = "age", column = "age")
	})
	Animal getAnimal(int id);

	@Select("INSERT INTO Animal VALUES (${id}, '${name}', ${age})")
	Animal addAnimal(Map<String, Object> params);

	@Select("DELETE Animal")
	void deleteAllAnimals();
	
	@Select("commit")
	void commit();
}