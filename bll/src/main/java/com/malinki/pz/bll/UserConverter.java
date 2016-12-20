package com.malinki.pz.bll;

import com.malinki.pz.dal.domain.UserDTO;

public class UserConverter {

	public static UserDTO fromUserUVMToUserDTO(UserUVM p) {
		return new UserDTO.UserDTOBuilder()
				.username(p.getUsername())
				.password(p.getPassword())
				.firstname(p.getFirstname())
				.lastname(p.getLastname())
				.email(p.getEmail())
				.build();
	}
	
	public static UserUVM fromUserDTOToUserUVM(UserDTO p) {
		return new UserUVM.UserUVMBuilder()
				.username(p.getUsername())
				.password(p.getPassword())
				.firstname(p.getFirstname())
				.lastname(p.getLastname())
				.email(p.getEmail())
				.build();
	}
}
