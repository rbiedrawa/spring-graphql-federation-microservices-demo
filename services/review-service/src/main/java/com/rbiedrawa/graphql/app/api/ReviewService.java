package com.rbiedrawa.graphql.app.api;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.rbiedrawa.graphql.api.review.types.Review;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReviewService {

	private static final Map<Integer, List<Review>> CUSTOMER_REVIEWS_DB = new ConcurrentHashMap<>(Map.of(
		1, List.of(Review.newBuilder().id(1).rating(10).message("Good").build(), Review.newBuilder().id(2).rating(5).message("Not Bad").build()),
		2, List.of(Review.newBuilder().id(3).rating(10).message("Good").build())
		));


	public List<Review> findByUserId(Integer userId) {
		var reviews = CUSTOMER_REVIEWS_DB.get(userId);
		log.info("Found {} reviews for user {}", reviews, userId);
		return reviews;
	}
}
