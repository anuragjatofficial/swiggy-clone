package com.swiggy.utils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtService {

	public boolean isTokenValid(String token, UserDetails userDetails) {
		log.info("token validation called");
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername())&& (!isTokenExpired(token)));
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public String extractUsername(String jwt) {
		return extractClaim(jwt, Claims::getSubject);
	}
	
	public Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSiginKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	public Key getSiginKey() {
		byte[] keyByte = Decoders.BASE64.decode(SecurityDetails.JWT_KEY);
		return Keys.hmacShaKeyFor(keyByte);
	}
	
	// to generate token 
	
	public String generateToken(UserDetails userDetails) {
		log.info("token generation requested ");
		return generateToken(new HashMap<>(), userDetails);
	}
	
	public String generateToken(
			Map<String,Object> extraClaims,
			UserDetails userDetails
	) {
		return Jwts
				.builder()
				.setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+ 1000*60*24))
				.signWith(getSiginKey(),SignatureAlgorithm.HS384)
				.compact();
	}
	

}
