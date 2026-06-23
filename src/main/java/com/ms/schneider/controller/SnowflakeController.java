package com.ms.schneider.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ms.schneider.dto.CustomerDTO;
import com.ms.schneider.service.ISnowflakeService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/customers")
@Tag(name = "Clientes", description = "Operaciones de gestión de clientes")
@Validated
@AllArgsConstructor
public class SnowflakeController {

	private final ISnowflakeService iSnowflakeService;

	@GetMapping()
	public List<CustomerDTO> getCustomers(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size) {

	    return iSnowflakeService.getCustomers(page, size);
	}
	
	@GetMapping("/fetch/{id}")
	public CustomerDTO getCustomerById(@PathVariable String id) {
	    return iSnowflakeService.getCustomerById(id);
	}
}
