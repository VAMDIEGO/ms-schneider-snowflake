package com.ms.schneider.entity;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressData {

    private String street;
    private String number;
    private String city;
    private String state;
    private String country;
}