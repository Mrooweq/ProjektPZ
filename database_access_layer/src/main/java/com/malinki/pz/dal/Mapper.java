package com.malinki.pz.dal;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.malinki.pz.dal.domain.User;

public interface Mapper {
	@Select("SELECT * FROM \"USER\" where id = #{id}")
	User getUser(int id);

	@Select("INSERT INTO \"USER\" VALUES (getMinID, #{login}, #{password})")
	User addUser(@Param("login") String login, @Param("password") String password);

	@Select("DELETE \"USER\"")
	void deleteAllUsers();
	
	@Select("COMMIT")
	void commit();
	
///
	
	@Select("SELECT * FROM \"USER\" where id = ( select count(*) from \"USER\")")
	User getLastUser();

	//TODO uzupelnic zapytanie i wpisac pamaetry funkcji
	@Select("INSERT INTO \"USER\" VALUES (getMinID, #{name}, #{lastName})")
	void addTicket();
	
	@Select("SELECT * FROM \"AIRLINE\" where name = #{name}")
	String getAirline(@Param("name") String name);
}