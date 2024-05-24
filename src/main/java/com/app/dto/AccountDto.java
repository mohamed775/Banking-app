package com.app.dto;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@Builder
public class AccountDto {

	
	private Long id ;
	
	private String username ;
	
	private double balance ;
	
	
}
