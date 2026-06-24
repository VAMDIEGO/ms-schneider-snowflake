package com.ms.schneider.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ms.schneider.dto.CustomerDTO;
import com.ms.schneider.repositories.CustomerRepository;
import com.ms.schneider.service.ICustomerKafkaProducer;

@ExtendWith(MockitoExtension.class)
class SnowflakeServiceTest {

	@Mock
	private CustomerRepository repository;

	@Mock
	private ICustomerKafkaProducer kafkaProducer;

	@InjectMocks
	private SnowflakeService snowflakeService;

	private CustomerDTO sampleCustomer;

	@BeforeEach
	void setUp() {
		sampleCustomer = new CustomerDTO();
		sampleCustomer.setCustomerId("AAAAAAAAIBFBCJEA");
	}

	@Nested
	@DisplayName("")
	class GetCustomersTests {

		@Test
		void shouldReturnPaginatedCustomers() {
			int page = 1;
			int size = 10;
			List<CustomerDTO> expectedList = Arrays.asList(sampleCustomer, new CustomerDTO());
			when(repository.findCustomers(page, size)).thenReturn(expectedList);

			List<CustomerDTO> actualList = snowflakeService.getCustomers(page, size);

			assertNotNull(actualList);
			assertEquals(2, actualList.size());
			verify(repository, times(1)).findCustomers(page, size);
		}
	}

	@Nested
	class GetCustomerByIdTests {

		@Test
		void shouldReturnCustomerAndSendToKafkaWhenCustomerExists() {
			String targetId = "AAAAAAAAIBFBCJEA";
			when(repository.findCustomerById(targetId)).thenReturn(sampleCustomer);
			CustomerDTO result = snowflakeService.getCustomerById(targetId);

			assertNotNull(result, "The customer must not be null");
			assertEquals("AAAAAAAAIBFBCJEA", result.getCustomerId());

			verify(repository, times(1)).findCustomerById(targetId);
			verify(kafkaProducer, times(1)).sendCustomer(sampleCustomer);
		}

		@Test
		void shouldReturnNullAndNotSendToKafkaWhenCustomerDoesNotExist() {
			String targetId = "UNKNOWN-ID";
			when(repository.findCustomerById(targetId)).thenReturn(null);
			CustomerDTO result = snowflakeService.getCustomerById(targetId);
			assertNull(result, "mUST BE EMPTY");

			verify(repository, times(1)).findCustomerById(targetId);
			verify(kafkaProducer, never()).sendCustomer(sampleCustomer);
			verifyNoInteractions(kafkaProducer);
		}
	}
}