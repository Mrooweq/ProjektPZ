package com.malinki.pz.dal;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.malinki.pz.dal.domain.UserDTO;

public interface Mapper {
	@Select("INSERT INTO \"USER\" VALUES (getMinID, #{login}, #{password})")
	UserDTO addUser(@Param("login") String login, @Param("password") String password);
	
	@Select("COMMIT")
	void commit();
	
	@Select("SELECT isLoginAlreadyUsed(#{str}) FROM DUAL") //zwraca 1 jeśli już istnieje uzytkownik o podanym loginie; 0 jesli nie istnieje
	int isLoginAlreadyUsed(@Param("str") String str);
	
	@Select("SELECT * FROM \"USER\" where id = ( select count(*) from \"USER\")") 
	UserDTO getLastAddedUser();
	
	//////////////////				ponizej wyrazenia na razie niepotrzebne lub sluzace do celow testowych
	
	@Select("SELECT * FROM \"USER\" where id = #{id}")
	UserDTO getUser(int id);
	
	@Select("DELETE \"USER\"")
	void deleteAllUsers();
}