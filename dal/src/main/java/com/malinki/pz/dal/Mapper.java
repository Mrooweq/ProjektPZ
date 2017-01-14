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
	
	@Select("SELECT isUsernameAndPasswordCorrect(#{username}, #{password}) FROM DUAL")
	int isUsernameAndPasswordCorrect(@Param("username") String username, @Param("password") String password);	//return 1 if username and password are correct

	@Select("SELECT * FROM \"User\" where ID_User = ( select count(*) from \"User\")")
	UserDTO getLastAddedUser();

	@Select("INSERT INTO TICKET VALUES (seq_Ticket.NEXTVAL, #{flight}, #{flightClass}, (select ID_User from \"User\" where Username = #{username}))")
	void addTicket(@Param("flight") String flight,
			@Param("flightClass") String flightClass,
			@Param("username") String username);

	@Select("COMMIT")
	void commit();

	//////////////////

	@Select("SELECT * FROM \"User\" where Username = #{username}")
	UserDTO getUserByUsername(@Param("username") String username);

	@Select("DELETE \"User\"")
	void deleteAllUsers();
	
}





















