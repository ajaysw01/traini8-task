package com.ajaysw.service;

import com.ajaysw.model.TrainingCenter;

import java.util.List;

public interface TrainingCenterService {
    public TrainingCenter createTrainingCenter(TrainingCenter trainingCenter);
    public List<TrainingCenter> getAllTrainingCenters(String city, String state) ;
    public TrainingCenter getTrainingCenterById(Long id);
    public TrainingCenter updateTrainingCenter(Long id, TrainingCenter trainingCenterDetails);
    public void deleteTrainingCenter(Long id);
    }
