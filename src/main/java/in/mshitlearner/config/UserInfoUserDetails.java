package in.mshitlearner.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import in.mshitlearner.entity.UserInfo;

public class UserInfoUserDetails implements UserDetails {
	
	private String name;
	private String password;
	private List<GrantedAuthority> authority;
	
	public UserInfoUserDetails(UserInfo userInfo) {
		name = userInfo.getUserId();
		password = userInfo.getPassword();
		authority = Arrays.stream(userInfo.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authority;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return name;
	}

}
