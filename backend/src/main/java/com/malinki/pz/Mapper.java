package com.malinki.pz;

import java.util.Map;
import org.apache.ibatis.annotations.Select;

import com.malinki.pz.controller.User;

public interface Mapper {
	@Select("SELECT * FROM \"USER\" where id = #{id}")
	User getUser(int id);

	@Select("INSERT INTO \"USER\" VALUES (getMinID, '${login}', '${password}')")
	User addUser(Map<String, Object> params);

	@Select("DELETE \"USER\"")
	void deleteAllUsers();
	
	@Select("COMMIT")
	void commit();
}