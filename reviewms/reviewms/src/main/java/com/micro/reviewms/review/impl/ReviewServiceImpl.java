package com.micro.reviewms.review.impl;

import com.micro.reviewms.review.Review;
import com.micro.reviewms.review.ReviewRepository;
import com.micro.reviewms.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        //custom method findByCompanyId
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyID, Review review) {
        if (companyID != null && review != null) {
            review.setCompanyId(companyID);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long reviewID) {
        return reviewRepository.findById(reviewID).orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewID, Review updatedReview) {
        Review review = reviewRepository.findById(reviewID).orElse(null);
        if(reviewID != null) {
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setCompanyId(updatedReview.getCompanyId());
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review != null) {
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
