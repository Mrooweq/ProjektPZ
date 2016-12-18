package com.malinki.pz.bll;

import com.malinki.pz.dal.domain.UserDTO;

public class UserConverter {

	public static UserDTO fromUserUVMToUserDTO(UserUVM p) {
		return new UserDTO.UserDTOBuilder()
				.login(p.getLogin())
				.password(p.getPassword())
				.build();
	}
	
	public static UserUVM fromUserDTOToUserUVM(UserDTO p) {
		return new UserUVM.UserUVMBuilder()
				.login(p.getLogin())
				.password(p.getPassword())
				.build();
	}
}
