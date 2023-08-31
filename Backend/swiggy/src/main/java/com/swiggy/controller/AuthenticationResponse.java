package com.swiggy.controller;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
	private String token;
	private Date issuedAt;
	private Date expireAt;
}
