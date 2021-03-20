package com.rbiedrawa.graphql.app.graphs;

import java.util.Collection;
import java.util.stream.Collectors;

import com.rbiedrawa.graphql.api.users.DgsConstants;
import com.rbiedrawa.graphql.api.users.types.NewCustomer;
import com.rbiedrawa.graphql.api.users.types.Customer;
import com.rbiedrawa.graphql.app.api.CustomerService;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import lombok.AllArgsConstructor;

@DgsComponent
@AllArgsConstructor
public class CustomerController {

	private final CustomerService customerService;

	@DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.Customers)
	public Collection<Customer> findAll(@InputArgument("firstNameFilter") String titleFilter) {
		if (null == titleFilter) {
			return customerService.findAll();
		}

		return customerService.findAll()
							  .stream()
							  .filter(customer -> customer.getFirstName().contains(titleFilter))
							  .collect(Collectors.toList());
	}

	@DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.AddCustomer)
	public Customer addCustomer(@InputArgument("customer") NewCustomer newCustomer) {
		return customerService.addCustomer(newCustomer.getFirstName());
	}

}
