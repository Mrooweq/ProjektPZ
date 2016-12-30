package com.malinki.pz.dal;

import com.malinki.pz.lib.UserDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface Mapper {
	@Select("INSERT INTO \"User\" VALUES (getMinUserID, #{firstname}, #{lastname}, #{username}, #{password}, #{email})")
	UserDTO registerUser(
			@Param("firstname") String firstname,
			@Param("lastname") String lastname,
			@Param("username") String username,
			@Param("password") String password,
			@Param("email") String email);

	@Select("SELECT isLoginAlreadyUsed(#{str}) FROM DUAL")
	int isLoginAlreadyUsed(@Param("str") String str);	//returns 1 if user with such login already exists
	
	@Select("SELECT isEmailAlreadyUsed(#{str}) FROM DUAL")
	int isEmailAlreadyUsed(@Param("str") String str);
	
	@Select("SELECT isUsernameAndPasswordCorrect(#{username}, #{password}) FROM DUAL")
	int isUsernameAndPasswordCorrect(@Param("username") String username, @Param("password") String password);	//return 1 if username and password are correct

	@Select("SELECT * FROM \"User\" where ID_User = ( select count(*) from \"User\")")
	UserDTO getLastAddedUser();
	
	@Select("COMMIT")
	void commit();

	//////////////////

	@Select("SELECT * FROM \"User\" where Username = #{username}")
	UserDTO getUserByUsername(@Param("username") String username);

	@Select("DELETE \"User\"")
	void deleteAllUsers();
}