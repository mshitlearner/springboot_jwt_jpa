package in.mshitlearner.jwt.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

	private static final String SECRET_KEY = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

	public String generateToken(String userName) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userName);
	}

	private String createToken(Map<String, Object> claims, String userName) {
		// TODO Auto-generated method stub
		return Jwts.builder() // new JwtBuilder instance that can be configured and then used to create JWT
								// compact serializedstrings.
				.setClaims(claims) // All 3 Components Header, Payload and Signature are consider to Claims
				.setSubject(userName) // 2 Payload Construction - User Name
				.setIssuedAt(new Date(System.currentTimeMillis())) // 2 Payload Construction -Token Generated
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // 2 Payload Construction -Token Expiry time 30 Minutes
				.setAudience("DEEPTHI") // 2 Payload Construction - Audience
				.signWith(SignatureAlgorithm.HS256, getSignKey()) // 3 Signature - Keeping the Sceret Key and Setting the Header Algorithm element ALG
				.setHeaderParam("typ", "JWT").compact();
	}

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}
}
