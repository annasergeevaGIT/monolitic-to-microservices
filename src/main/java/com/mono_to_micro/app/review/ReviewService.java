package com.mono_to_micro.app.review;

import com.mono_to_micro.app.job.Job;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);

    boolean addReview(Long companyID, Review review);

    Review getReview(Long companyID, Long reviewID);

    boolean updateReview(Long companyID, Long reviewID, Review review);

    boolean deleteReview(Long companyId, Long reviewId);
}
