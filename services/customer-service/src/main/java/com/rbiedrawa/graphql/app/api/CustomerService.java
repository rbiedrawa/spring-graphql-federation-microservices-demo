package com.rbiedrawa.graphql.app.api;

import static java.util.Map.of;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.rbiedrawa.graphql.api.users.types.Customer;


@Service
public class CustomerService {

	// Simulate database
	private static final Map<Integer, Customer> CUSTOMERS_DB = new ConcurrentHashMap<>(of(
		1, Customer.newBuilder().id(1).firstName("Admin").build(),
		2, Customer.newBuilder().id(2).firstName("User").build()
																						 )
	);

	public Collection<Customer> findAll() {
		return CUSTOMERS_DB.values();
	}

	public Customer addCustomer(String firstName) {
		var newCustomer = Customer.newBuilder()
								  .id(generateId())
								  .firstName(firstName)
								  .build();
		CUSTOMERS_DB.put(newCustomer.getId(), newCustomer);
		return newCustomer;
	}

	private int generateId() {
		return CUSTOMERS_DB.size() + 1;
	}
}
