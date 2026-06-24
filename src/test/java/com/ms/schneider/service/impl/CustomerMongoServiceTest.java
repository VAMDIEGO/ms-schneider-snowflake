package com.ms.schneider.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ms.schneider.entity.CustomerMongoData;
import com.ms.schneider.repositories.CustomerMongoRepository;

@ExtendWith(MockitoExtension.class)
class CustomerMongoServiceTest {

    @Mock
    private CustomerMongoRepository repository;

    @InjectMocks
    private CustomerMongoService customerMongoService;

    @Test
    void shouldReturnListOfCustomersWhenDataExists() {
        // Arrange
        CustomerMongoData customer1 = new CustomerMongoData();
        CustomerMongoData customer2 = new CustomerMongoData();
        List<CustomerMongoData> expectedCustomers = Arrays.asList(customer1, customer2);
        when(repository.findAll()).thenReturn(expectedCustomers);
        List<CustomerMongoData> actualCustomers = customerMongoService.getAllCustomers();
        assertNotNull(actualCustomers, "The list must not be null");
        assertEquals(2, actualCustomers.size(), "The list must contain two customers");
        
        // Verificamos que el método findAll() del repositorio se ejecutó exactamente una vez
        verify(repository, times(1)).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenNoDataExists() {
        // Arrange
        when(repository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<CustomerMongoData> actualCustomers = customerMongoService.getAllCustomers();

        // Assert
        assertNotNull(actualCustomers);
        assertTrue(actualCustomers.isEmpty(), "Ther list must be empty");
        verify(repository, times(1)).findAll();
    }
}