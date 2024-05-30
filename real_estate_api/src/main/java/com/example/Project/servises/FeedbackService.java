package com.example.Project.servises;

import com.example.Project.Exception.ResourceNotFoundException;
import com.example.Project.repositories.FeedbackRepository;
import com.example.Project.tables.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public Optional<Feedback> getFeedbackById(String id) {
        return feedbackRepository.findById(id);
    }

    public Feedback createFeedback(Feedback feedback) {
        feedback.setCreatedAt(LocalDateTime.now());
        return feedbackRepository.save(feedback);
    }

    public Feedback updateFeedback(String id, Feedback feedbackDetails) {
        return feedbackRepository.findById(id).map(feedback -> {
            feedback.setFeedBackId(feedbackDetails.getFeedBackId());
            feedback.setComments(feedbackDetails.getComments());
            feedback.setRating(feedbackDetails.getRating());
            feedback.setCreatedAt(feedbackDetails.getCreatedAt());
            return feedbackRepository.save(feedback);
        }).orElseThrow(() -> new ResourceNotFoundException("Feedback not found with id " + id));
    }

    public void deleteFeedback(String id) {
        feedbackRepository.deleteById(id);
    }
}

