package com.rbiedrawa.graphql.app.graphs;

import java.util.List;
import java.util.Map;

import com.rbiedrawa.graphql.api.review.DgsConstants;
import com.rbiedrawa.graphql.api.review.types.Customer;
import com.rbiedrawa.graphql.api.review.types.Review;
import com.rbiedrawa.graphql.app.api.ReviewService;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsEntityFetcher;
import lombok.AllArgsConstructor;

@DgsComponent
@AllArgsConstructor
public class ReviewController {

	private final ReviewService reviewService;

	@DgsEntityFetcher(name = DgsConstants.CUSTOMER.TYPE_NAME)
	public Customer customer(Map<String, Object> values) {
		return new Customer((Integer) values.get("id"), null);
	}

	@DgsData(parentType = DgsConstants.CUSTOMER.TYPE_NAME, field = DgsConstants.CUSTOMER.Reviews)
	public List<Review> findUserReviews(DgsDataFetchingEnvironment dataFetchingEnvironment)  {
		Customer customer = dataFetchingEnvironment.getSource();
		return reviewService.findByUserId(customer.getId());
	}
}
