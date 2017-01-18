package com.malinki.pz.dal.mappers;

import com.malinki.pz.lib.entity.UserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends Mapper {
	@Insert("INSERT INTO \"User\" VALUES (seq_User.NEXTVAL, #{username}, #{password}, #{firstname}, #{lastname}, #{email})")
	void registerUser(UserDTO user);

	@Select("SELECT isLoginAlreadyUsed(#{str}) FROM DUAL")
	int isLoginAlreadyUsed(@Param("str") String str);	//returns 1 if username with such login already exists
	
	@Select("SELECT isEmailAlreadyUsed(#{str}) FROM DUAL")
	int isEmailAlreadyUsed(@Param("str") String str);
	
	@Select("SELECT isUsernameAndPasswordCorrect(#{username}, #{password}) FROM DUAL")
	int isUsernameAndPasswordCorrect(UserDTO user);	//return 1 if username and password are correct

	@Select("SELECT * FROM \"User\" where Username = #{username}")
	UserDTO getUserByUsername(@Param("username") String username);
}