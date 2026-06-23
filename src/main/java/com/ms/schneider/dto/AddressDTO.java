package com.ms.schneider.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {
	private String street;
	private String number;
	private String city;
	private String state;
	private String country;
}