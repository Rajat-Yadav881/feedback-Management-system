package com.feedback.feedbackManagementSystem.service;

import com.feedback.feedbackManagementSystem.model.Feedback;
import com.feedback.feedbackManagementSystem.repository.FeedbackRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService{
    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public Feedback createFeedback(Feedback feedback) {

        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback updateFeedback(Long feedbackId, Feedback feedback) {
        // Validate input data (e.g., using Hibernate Validator annotations)
        // Perform any additional business logic

        Feedback existingFeedback = getFeedbackById(feedbackId);
        existingFeedback.setTitle(feedback.getTitle());
        existingFeedback.setDescription(feedback.getDescription());
        existingFeedback.setLikeDislike(feedback.getLikeDislike());
        existingFeedback.setImageUrl(feedback.getImageUrl());
        existingFeedback.setSubmitterName(feedback.getSubmitterName());
        existingFeedback.setSubmitterEmail(feedback.getSubmitterEmail());

        return feedbackRepository.save(existingFeedback);
    }

    @Override
    public void deleteFeedback(Long feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }

    @Override
    public List<Feedback> getAllFeedback(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return feedbackRepository.findAll(pageable).getContent();
    }

    @Override
    public Feedback getFeedbackById(Long feedbackId) {
        try {
            return feedbackRepository.findById(feedbackId)
                    .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
