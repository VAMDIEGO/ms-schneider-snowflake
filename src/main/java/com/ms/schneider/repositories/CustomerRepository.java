package com.ms.schneider.repositories;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ms.schneider.dto.CustomerDTO;
@Repository
public class CustomerRepository {

	private final JdbcTemplate jdbcTemplate;

	public CustomerRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@SuppressWarnings("deprecation")
	public List<CustomerDTO> findCustomers() {

		String sql = "SELECT C_CUSTOMER_ID, C_FIRST_NAME, C_LAST_NAME, C_EMAIL_ADDRESS, C_BIRTH_DAY, C_BIRTH_MONTH, C_BIRTH_DAY FROM CUSTOMER LIMIT 100";

		return jdbcTemplate.query(sql, new Object[] {},
				(rs, rowNum) -> CustomerDTO.builder().customerId(rs.getString("C_CUSTOMER_ID")).firstName(rs.getString("C_FIRST_NAME"))
						.lastName(rs.getString("C_LAST_NAME")).email(rs.getString("C_EMAIL_ADDRESS")).birthDay(rs.getInt("C_BIRTH_DAY"))
						.birthMonth(rs.getInt("C_BIRTH_MONTH")).birthYear(rs.getInt("C_BIRTH_YEAR")).build());
	}
}
