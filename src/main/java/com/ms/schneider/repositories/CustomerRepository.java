package com.ms.schneider.repositories;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ms.schneider.dto.AddressDTO;
import com.ms.schneider.dto.CustomerDTO;
@Repository
public class CustomerRepository {

	private final JdbcTemplate jdbcTemplate;

	public CustomerRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<CustomerDTO> findCustomers() {

        String sql = """
            SELECT 
                c.C_CUSTOMER_ID,
                c.C_FIRST_NAME,
                c.C_LAST_NAME,
                c.C_EMAIL_ADDRESS,
                c.C_BIRTH_DAY,
                c.C_BIRTH_MONTH,
                c.C_BIRTH_YEAR,
                ca.CA_STREET_NAME,
                ca.CA_STREET_NUMBER,
                ca.CA_CITY,
                ca.CA_STATE,
                ca.CA_COUNTRY
            FROM CUSTOMER c
            JOIN CUSTOMER_ADDRESS ca 
                ON c.C_CURRENT_ADDR_SK = ca.CA_ADDRESS_SK
            LIMIT 100
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> CustomerDTO.builder()
                .customerId(rs.getString("C_CUSTOMER_ID"))
                .firstName(rs.getString("C_FIRST_NAME"))
                .lastName(rs.getString("C_LAST_NAME"))
                .email(rs.getString("C_EMAIL_ADDRESS"))
                .birthDay(rs.getInt("C_BIRTH_DAY"))
                .birthMonth(rs.getInt("C_BIRTH_MONTH"))
                .birthYear(rs.getInt("C_BIRTH_YEAR"))
                .address(
                        AddressDTO.builder()
                                .street(rs.getString("CA_STREET_NAME"))
                                .number(rs.getString("CA_STREET_NUMBER"))
                                .city(rs.getString("CA_CITY"))
                                .state(rs.getString("CA_STATE"))
                                .country(rs.getString("CA_COUNTRY"))
                                .build()
                )
                .build());
    }
}
