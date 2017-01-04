package com.malinki.pz.dal;

import com.malinki.pz.lib.UserDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

public interface UserMapper {
	@Select("INSERT INTO \"User\" VALUES (getMinUserID, #{username}, #{password}, #{firstname}, #{lastname}, #{email})")
	UserDTO registerUser(UserDTO user);

	@Select("SELECT isLoginAlreadyUsed(#{str}) FROM DUAL")
	int isLoginAlreadyUsed(@Param("str") String str);	//returns 1 if username with such login already exists
	
	@Select("SELECT isEmailAlreadyUsed(#{str}) FROM DUAL")
	int isEmailAlreadyUsed(@Param("str") String str);
	
	@Select("SELECT isUsernameAndPasswordCorrect(#{username}, #{password}) FROM DUAL")
	int isUsernameAndPasswordCorrect(UserDTO user);	//return 1 if username and password are correct

	@Select("COMMIT")
	void commit();

	//////////////////

	@Select("SELECT * FROM \"User\" where ID_User = ( select count(*) from \"User\")")
	UserDTO getLastAddedUser();

	@Select("SELECT * FROM \"User\" where Username = #{username}")
	UserDTO getUserByUsername(@Param("username") String username);

	@Select("DELETE \"User\"")
	void deleteAllUsers();
}