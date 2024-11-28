package in.mshitlearner.jwt.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import in.mshitlearner.config.UserDetailsServiceInfo;
import in.mshitlearner.jwt.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService jwtService;
	
	 @Autowired
	  private UserDetailsServiceInfo userDetailsServiceInfo;
	 
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String token = null;
		String userName = null;
		// Step-1 Derive the token from the response Header. Here in header token is
		// concated with the keyword "Bearer"
		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer ")) {
			token = header.substring(7);
			// Step-2 From token we need to extract user name
			userName = jwtService.extractUsername(token);
		}
		//Step-3 Need to validate the token with the help of userName and need to validate the SecurityContextHolder context authentication is null. 
		// If it null we need to asssign the authToken i.e., UsernamePasswordAuthenticationToken class
		//Step-3.1 - Need to the userDetails Object With the help UserDetailsService class
		//Step-3.2- Once the token is valid we need to assign the UsernamePasswordAuthenticationToken token to the SecurityContextHolder.
		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsServiceInfo.loadUserByUsername(userName);
			if (jwtService.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
