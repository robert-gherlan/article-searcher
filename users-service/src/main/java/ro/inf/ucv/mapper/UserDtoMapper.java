package ro.inf.ucv.mapper;

import ro.inf.ucv.dto.UserDto;
import ro.inf.ucv.entity.User;

public class UserDtoMapper {

	private UserDtoMapper() {
	}

	public static final User toUser(UserDto userDto) {
		return new User(userDto);
	}

	public static final UserDto toUserDto(User user) {
		return new UserDto(user);
	}

}
