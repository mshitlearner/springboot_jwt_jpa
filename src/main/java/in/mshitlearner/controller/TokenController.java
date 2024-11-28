package in.mshitlearner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.mshitlearner.dto.AuthRequest;
import in.mshitlearner.jwt.service.JwtService;

@RestController
@RequestMapping("/token")
public class TokenController {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@PostMapping(value = "/generator")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			return jwtService.generateToken(authRequest.getUserName());
		} else {
			throw new UsernameNotFoundException("invalid user request !");
		}

	}
}
