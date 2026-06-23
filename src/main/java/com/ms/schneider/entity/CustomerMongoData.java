package com.ms.schneider.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customer_data")
public class CustomerMongoData {

    @Id
    private String id;

    private String customerId;
    private String firstName;
    private String lastName;

    private int birthDay;
    private int birthMonth;
    private int birthYear;

    private String email;

    private AddressData address;

    private Instant receivedAt;
}
