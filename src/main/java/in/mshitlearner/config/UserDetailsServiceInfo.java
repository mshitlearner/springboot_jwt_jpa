package in.mshitlearner.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.mshitlearner.entity.UserInfo;
import in.mshitlearner.repository.UsersRepository;

@Service
public class UserDetailsServiceInfo implements UserDetailsService {
	
	@Autowired
	private UsersRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<UserInfo> userInfo = userRepository.findByUserId(username);
		return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
	}

}
