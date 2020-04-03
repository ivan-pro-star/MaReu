package com.example.mareu.service;

import com.example.mareu.model.Reunion;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Reunion API Service
 * */
public interface ApiService {
///**
//     * Get all my reunions
//     *
//     * @return {@link List}
//     */
//    List<Reunion>  getReunionsByRoom();
//   /**
//     * Get all my reunions
//     *
//     * @return {@link List}
//     */
//    List<Reunion>  getReunionsByCalendar();
///**
//     * Get Filter my reunions by room
//     * @param  room
//     * @return {@link List}
//     */
//    List<Reunion>  getFilterReunionsByRoom(int room);
//   /**
//     * Get Filter my reunions bycandar
//     * @param before
//     * @param after
//     * @return {@link List}
//     */
//    List<Reunion>  getFilterReunionsByCalendar(Date before, Date after);


/**
     * Get Filter my reunions bycandar
     * @param sorterByCalendar
     * @return {@link List}
     */
    List<Reunion>  getReunions(boolean sorterByCalendar);

/**
     * Get Filter my reunions bycandar
     * @param room
     * @return {@link List}
     */
    List<Reunion>  getReunions(int room);

/**
     * Get Filter my reunions bycandar
     * @param start
     * @param end
     * @return {@link List}
     */
    List<Reunion>  getReunions(Calendar start, Calendar end);

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
}
