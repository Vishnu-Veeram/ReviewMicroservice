package com.vicky.ReviewMicroservice.review.impl;

import com.vicky.ReviewMicroservice.review.Review;
import com.vicky.ReviewMicroservice.review.ReviewRepository;
import com.vicky.ReviewMicroservice.review.ReviewService;
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
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        if(companyId != null && review != null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null) {
            reviewRepository.delete(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateReview(Long reviewId, Review updatedReview) {
        if(reviewId != null){
            Review review = reviewRepository.findById(reviewId).orElse(null);
            if (review != null) {
                review.setTitle(updatedReview.getTitle());
                review.setRating(updatedReview.getRating());
                review.setDescription(updatedReview.getDescription());
                review.setCompanyId(updatedReview.getCompanyId());
                reviewRepository.save(review);
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
}
