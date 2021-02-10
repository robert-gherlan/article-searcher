package ro.inf.ucv.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import ro.inf.ucv.dto.UserDto;
import ro.inf.ucv.entity.User;
import ro.inf.ucv.exception.UserNotFoundException;
import ro.inf.ucv.mapper.UserDtoMapper;
import ro.inf.ucv.repository.UserRepository;

@AllArgsConstructor
@Service
@Transactional
public class UserService {

	private UserRepository userRepository;

	private PasswordEncoder passwordEncoder;

	public UserDto save(UserDto userDto) {
		String encodedPassword = passwordEncoder.encode(userDto.getPassword());
		userDto.setPassword(encodedPassword);
		User savedUser = userRepository.save(UserDtoMapper.toUser(userDto));
		return UserDtoMapper.toUserDto(savedUser);
	}

	public List<UserDto> findAll() {
		Iterable<User> foundUsers = userRepository.findAll();
		if (!foundUsers.iterator().hasNext()) {
			throw new UserNotFoundException("No users were found");
		}

		return StreamSupport.stream(foundUsers.spliterator(), false).map(UserDtoMapper::toUserDto)
				.collect(Collectors.toList());
	}

	public User findById(String id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User with " + id + " id was not found."));
	}

	public UserDetails findByUsername(String userName) {
		return userRepository.findByUsername(userName);
	}

	public boolean existsByEmail(String email) {
		boolean exists = false;
		if (email != null) {
			exists = userRepository.countByEmail(email) > 0;
		}

		return exists;
	}

	public boolean existsByUsername(String username) {
		boolean exists = false;
		if (username != null) {
			exists = userRepository.countByUsername(username) > 0;
		}

		return exists;
	}
}