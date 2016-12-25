package com.malinki.pz.bll;

import com.malinki.pz.lib.UserDTO;
import com.malinki.pz.lib.UserUVM;

public class UserConverter {

	public static UserDTO fromUserUVMToUserDTO(UserUVM user) {
		if(user == null)
			return null;
		else
			return new UserDTO.UserDTOBuilder()
					.username(user.getUsername())
					.password(user.getPassword())
					.firstname(user.getFirstname())
					.lastname(user.getLastname())
					.email(user.getEmail())
					.build();
	}

	public static UserUVM fromUserDTOToUserUVM(UserDTO user) {
		if(user == null)
			return null;
		else
			return new UserUVM.UserUVMBuilder()
					.username(user.getUsername())
					.password(user.getPassword())
					.firstname(user.getFirstname())
					.lastname(user.getLastname())
					.email(user.getEmail())
					.build();
	}
}
