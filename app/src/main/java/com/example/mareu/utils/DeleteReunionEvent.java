package com.example.mareu.utils;
import com.example.mareu.model.Reunion;
public class DeleteReunionEvent {
    /**
     * Neighbour to delete
     */
    public Reunion reunion;

    /**
     * Constructor.
     * @param reunion
     */
    public DeleteReunionEvent(Reunion reunion) {
        this.reunion = reunion;
    }
}

