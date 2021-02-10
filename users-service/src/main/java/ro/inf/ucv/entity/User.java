package ro.inf.ucv.entity;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import ro.inf.ucv.dto.UserDto;

@Data
@Document(indexName = "auth_service_users")
public class User implements UserDetails {

	private static final long serialVersionUID = 8968937363018365315L;

	@Id
	private String id;

	@Field(type = FieldType.Object)
	private UUID identifier = UUID.randomUUID();

	@Field(type = FieldType.Keyword)
	private String email;

	@Field(type = FieldType.Keyword)
	private String username;

	@Field(type = FieldType.Auto)
	private String password;

	@Field(type = FieldType.Keyword)
	private String firstName;

	@Field(type = FieldType.Keyword)
	private String lastName;

	@Field(type = FieldType.Boolean)
	private boolean enabled = true;

	@Field(type = FieldType.Boolean)
	private boolean locked = false;

	@Field(type = FieldType.Date)
	private Date registerDate;

	@Field(type = FieldType.Date)
	private Date expirationDate;

	@Field(type = FieldType.Nested, includeInParent = true)
	private List<Role> roles;

	protected User() {
	}

	public User(UserDto userDto) {
		this.email = userDto.getEmail();
		this.username = userDto.getUsername();
		this.password = userDto.getPassword();
		this.firstName = userDto.getFirstName();
		this.lastName = userDto.getLastName();
		Calendar calendar = Calendar.getInstance();
		this.registerDate = calendar.getTime();
		calendar.add(Calendar.MONTH, 3);
		this.expirationDate = calendar.getTime();
		this.roles = Arrays.asList(new Role("USER"));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream().map(Role::getName).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return Long.compare(new Date().getTime(), expirationDate.getTime()) < 0;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}
