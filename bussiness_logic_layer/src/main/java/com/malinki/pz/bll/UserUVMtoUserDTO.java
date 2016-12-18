package com.malinki.pz.bll;

import com.malinki.pz.dal.domain.UserDTO;

public class UserUVMtoUserDTO {
	public static UserDTO parse(UserUVM userUVM){
		return new UserDTO(userUVM.getLogin(), userUVM.getPassword());
	}
}
