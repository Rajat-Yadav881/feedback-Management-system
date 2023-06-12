package com.feedback.feedbackManagementSystem.service;

import com.feedback.feedbackManagementSystem.model.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback createFeedback(Feedback feedback);

    Feedback updateFeedback(Long feedbackId, Feedback feedback);

    void deleteFeedback(Long feedbackId);

    List<Feedback> getAllFeedback(int page, int size);

    Feedback getFeedbackById(Long feedbackId);
}