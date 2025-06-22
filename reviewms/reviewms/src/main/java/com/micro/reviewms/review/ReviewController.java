package com.micro.reviewms.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {this.reviewService = reviewService;}

    @GetMapping // GET http://localhost:8083/reviews?companyId=1  companyId is a query parameter as long as it is not a part of review microservice.
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId) { // http://localhost:8083/reviews/1 // reviewId is a path variable
        return new ResponseEntity<>(reviewService.getReview(reviewId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createReview(@RequestParam Long companyId, // http://localhost:8083/reviews?companyId=1 // companyId is a query parameter
                                               @RequestBody Review review) {
        boolean isReviewSaved = reviewService.addReview(companyId, review);
        if (isReviewSaved) {
            return new ResponseEntity<>("Review added successfully",HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Review was not added", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId, @RequestBody Review review) {
        boolean isReviewUpdated = reviewService.updateReview(reviewId,review);
        if(isReviewUpdated){
            return ResponseEntity.ok("Review updated");
        }
        return new ResponseEntity<>("Review was not updated", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        boolean isReviewDeleted = reviewService.deleteReview(reviewId);
        if (isReviewDeleted) {
            return ResponseEntity.ok("Review deleted");
        }
        return new ResponseEntity<>("Review was not deleted", HttpStatus.NOT_FOUND);
    }
}
