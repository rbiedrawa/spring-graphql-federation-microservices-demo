package com.rbiedrawa.graphql.app.graphs;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.rbiedrawa.graphql.api.users.client.AddCustomerGraphQLQuery;
import com.rbiedrawa.graphql.api.users.client.AddCustomerProjectionRoot;
import com.rbiedrawa.graphql.api.users.client.CustomersGraphQLQuery;
import com.rbiedrawa.graphql.api.users.client.CustomersProjectionRoot;
import com.rbiedrawa.graphql.api.users.types.NewCustomer;
import com.rbiedrawa.graphql.api.users.types.Customer;
import com.rbiedrawa.graphql.app.api.CustomerService;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest;
import graphql.ExecutionResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@SpringBootTest(classes = {DgsAutoConfiguration.class, CustomerController.class})
class CustomerControllerTest {

	@Autowired
	DgsQueryExecutor dgsQueryExecutor;

	@MockBean
	CustomerService CustomerService;

	@BeforeEach
	public void before() {
		List<Customer> mockedCustomers = List.of(
			Customer.newBuilder().id(1).firstName("test").build()
										);
		Mockito.when(CustomerService.findAll()).thenAnswer(invocation -> mockedCustomers);
	}

	@Test
	void should_return_data_using_simple_query() {
		// Given
		String query = "{ customers { firstName }}";
		String jsonPath = "data.customers[*].firstName";

		// When
		List<String> titles = dgsQueryExecutor.executeAndExtractJsonPath(query, jsonPath);

		// Then
		assertThat(titles).contains("test");
	}

	@Test
	void should_return_data_using_queryAPI() {
		// Given
		GraphQLQueryRequest graphQLQueryRequest = new GraphQLQueryRequest(CustomersGraphQLQuery.newRequest().firstNameFilter("test").build(), new CustomersProjectionRoot().firstName());
		String jsonPath = "data.customers[*].firstName";

		// When
		List<String> titles = dgsQueryExecutor.executeAndExtractJsonPath(graphQLQueryRequest.serialize(), jsonPath);

		// Then
		assertThat(titles).hasSize(1).contains("test");
	}

	@Test
	void should_handle_exceptions() {
		// Given
		Mockito.when(CustomerService.findAll()).thenThrow(new RuntimeException("failed to fetch data"));

		// When
		ExecutionResult result = dgsQueryExecutor.execute(
			" { customers { firstName }}");

		// Then
		assertThat(result.getErrors()).isNotEmpty();
		assertThat(result.getErrors().get(0).getMessage()).isEqualTo("java.lang.RuntimeException: failed to fetch data");
	}

	@Test
	void should_add_new_Customer() {
		// Given
		var newCustomerGqlRequest = new GraphQLQueryRequest(AddCustomerGraphQLQuery.newRequest().customer(NewCustomer.newBuilder().firstName("New Test User").build()).build(), new AddCustomerProjectionRoot().id().firstName());

		// When
		ExecutionResult result = dgsQueryExecutor.execute(newCustomerGqlRequest.serialize());

		// Then
		assertThat(result.isDataPresent()).isTrue();
	}

}