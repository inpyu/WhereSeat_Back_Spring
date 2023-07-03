package dev.umc.whereseat.domain.review.service;

import static dev.umc.whereseat.common.ErrorStatus.*;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.umc.whereseat.domain.review.dto.Request.ReviewCreateInDTO;
import dev.umc.whereseat.domain.review.dto.Request.ReviewUpdateInDTO;
import dev.umc.whereseat.domain.review.dto.Response.ReviewCreateOutDTO;
import dev.umc.whereseat.domain.review.dto.Response.ReviewUpdateOutDTO;
import dev.umc.whereseat.domain.review.entity.Review;
import dev.umc.whereseat.domain.review.exception.ReviewException;
import dev.umc.whereseat.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

	private final ReviewRepository reviewRepository;

	/**
	 * 리뷰 생성
	 */
	public ReviewCreateOutDTO createReview(ReviewCreateInDTO dto) {
		// 리뷰 엔티티 생성
		Review review = dto.toEntity();
		reviewRepository.save(review);

		return ReviewCreateOutDTO.of(review);
	}

	/**
	 * 리뷰 업데이트
	 */
	public ReviewUpdateOutDTO updateReview(Long reviewId, ReviewUpdateInDTO reviewUpdateInDTO) {
		Review review = reviewRepository.findById(reviewId);

		Review updatedReview = review.update(reviewUpdateInDTO);
		reviewRepository.save(updatedReview);

		return ReviewUpdateOutDTO.of(updatedReview);
	}
}
