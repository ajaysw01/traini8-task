package com.ajaysw.service;

import com.ajaysw.exception.ResourceNotFoundException;
import com.ajaysw.model.TrainingCenter;
import com.ajaysw.repository.TrainingCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class TrainingCenterServiceImpl implements TrainingCenterService {

    @Autowired
    private TrainingCenterRepository trainingCenterRepository;

    // CREATE a new Training Center
    public TrainingCenter createTrainingCenter(TrainingCenter trainingCenter) {
        long epochMillis = System.currentTimeMillis();
        LocalDateTime createdOn = Instant.ofEpochMilli(epochMillis)
                .atZone(ZoneId.systemDefault()).toLocalDateTime();
        trainingCenter.setCreatedOn(createdOn); // Set the createdOn timestamp
        return trainingCenterRepository.save(trainingCenter);
    }

    // READ - Get all Training Centers with optional filtering by city or state
    public List<TrainingCenter> getAllTrainingCenters(String city, String state) {
        if (city != null) {
            return trainingCenterRepository.findByAddress_City(city);
        } else if (state != null) {
            return trainingCenterRepository.findByAddress_State(state);
        }
        return trainingCenterRepository.findAll();
    }

    // READ - Get a single Training Center by ID
    public TrainingCenter getTrainingCenterById(Long id) {
        return trainingCenterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Training Center not found with id " + id));
    }

    // UPDATE an existing Training Center
    public TrainingCenter updateTrainingCenter(Long id, TrainingCenter trainingCenterDetails) {
        TrainingCenter existingCenter = trainingCenterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Training Center not found with id " + id));

        // Update fields based on new details
        existingCenter.setCenterName(trainingCenterDetails.getCenterName());
        existingCenter.setCenterCode(trainingCenterDetails.getCenterCode());
        existingCenter.setAddress(trainingCenterDetails.getAddress());
        existingCenter.setStudentCapacity(trainingCenterDetails.getStudentCapacity());
        existingCenter.setCoursesOffered(trainingCenterDetails.getCoursesOffered());
        existingCenter.setContactEmail(trainingCenterDetails.getContactEmail());
        existingCenter.setContactPhone(trainingCenterDetails.getContactPhone());

        return trainingCenterRepository.save(existingCenter);
    }

    // DELETE a Training Center by ID
    public void deleteTrainingCenter(Long id) {
        TrainingCenter existingCenter = trainingCenterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Training Center not found with id " + id));
        trainingCenterRepository.delete(existingCenter);
    }
}
