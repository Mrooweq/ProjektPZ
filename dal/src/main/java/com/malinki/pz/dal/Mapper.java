package com.malinki.pz.dal;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.malinki.pz.dal.domain.UserDTO;

public interface Mapper {
	@Select("INSERT INTO \"USER\" VALUES (getMinUserID, #{firstname}, #{lastname}, #{username}, #{password}, #{email})")
	UserDTO registerUser(
			@Param("firstname") String firstname,
			@Param("lastname") String lastname,
			@Param("username") String username,
			@Param("password") String password,
			@Param("email") String email);

	@Select("SELECT isLoginAlreadyUsed(#{str}) FROM DUAL") //returns 1 if user with such login already exists
	int isLoginAlreadyUsed(@Param("str") String str);
	
	@Select("SELECT isUsernameAndPasswordCorrect(#{username}, #{password}) FROM DUAL") //return 1 if username and password are correct
	int isUsernameAndPasswordCorrect(@Param("username") String username, @Param("password") String password);

	@Select("SELECT * FROM \"USER\" where id = ( select count(*) from \"USER\")") 
	UserDTO getLastAddedUser();
	
	@Select("COMMIT")
	void commit();

	//////////////////				ponizej wyrazenia na razie niepotrzebne lub sluzace do celow testowych

	@Select("SELECT * FROM \"USER\" where username = #{username}")
	UserDTO getUserByUsername(@Param("username") String username);

	@Select("DELETE \"USER\"")
	void deleteAllUsers();
}