package com.mono_to_micro.app.review.impl;

import com.mono_to_micro.app.company.Company;
import com.mono_to_micro.app.company.CompanyService;
import com.mono_to_micro.app.job.Job;
import com.mono_to_micro.app.review.Review;
import com.mono_to_micro.app.review.ReviewRepository;
import com.mono_to_micro.app.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId); //custom method findByCompanyId
        return reviews;

    }

    @Override
    public boolean addReview(Long companyID, Review review) {
        Company company = companyService.getCompanyById(companyID);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long companyID, Long reviewID) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyID);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewID))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(Long companyID, Long reviewID, Review updatedReview) {
        if(companyService.getCompanyById(companyID) != null) {
            updatedReview.setCompany(companyService.getCompanyById(companyID));
            updatedReview.setId(reviewID);
            reviewRepository.save(updatedReview);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if(companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId)) {
            Review review = reviewRepository.findById(reviewId).orElse(null);
            Company company = review.getCompany(); //bidirectional mapping
            company.getReviews().remove(review);
            review.setCompany(null); //set as null to delete
            companyService.updateCompany(company, companyId);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
