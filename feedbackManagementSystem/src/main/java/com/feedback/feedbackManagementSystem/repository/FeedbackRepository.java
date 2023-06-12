package com.feedback.feedbackManagementSystem.repository;

import com.feedback.feedbackManagementSystem.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    // Additional custom query methods can be defined here if needed
}
