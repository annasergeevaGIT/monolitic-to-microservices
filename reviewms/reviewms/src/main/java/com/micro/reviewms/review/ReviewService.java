package com.micro.reviewms.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);

    boolean addReview(Long companyID, Review review);

    Review getReview(Long reviewID);

    boolean updateReview(Long reviewID, Review review);

    boolean deleteReview(Long reviewId);
}
