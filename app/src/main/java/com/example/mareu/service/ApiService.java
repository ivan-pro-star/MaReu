package com.example.mareu.service;

import com.example.mareu.model.Reunion;

import java.util.List;

/**
 * Reunion API Service
 * */
public interface ApiService {
/**
     * Get all my reunions
     *
     * @return {@link List}
     */
    List<Reunion>  getReunionsByRoom();
   /**
     * Get all my reunions
     *
     * @return {@link List}
     */
    List<Reunion>  getReunionsByCalendar();

/**
     * Create a reunion
     *
     * @param reunion
     */
    void createReunion(Reunion reunion);
/**
     * delete a reunion
     *
     * @param reunion
     */
    void deleteReunion(Reunion reunion);
    /**
     * delete a reunion
     *
     * @param reunions
     */
    void setReunion(List<Reunion> reunions);

}
