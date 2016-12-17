package com.malinki.pz.dal;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.malinki.pz.dal.domain.UserDTO;

public interface Mapper {
	@Select("SELECT * FROM \"USER\" where id = #{id}")
	UserDTO getUser(int id);

	@Select("INSERT INTO \"USER\" VALUES (getMinID, #{login}, #{password})")
	UserDTO addUser(@Param("login") String login, @Param("password") String password);

	@Select("DELETE \"USER\"")
	void deleteAllUsers();
	
	@Select("COMMIT")
	void commit();
	
///
	
	@Select("SELECT * FROM \"USER\" where id = ( select count(*) from \"USER\")")
	UserDTO getLastUser();
}