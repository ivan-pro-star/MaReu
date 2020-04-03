package com.example.mareu.event;
import com.example.mareu.model.Reunion;
public class DeleteReunionEvent {
    /**
     * Reunion to delete
     */
    private Reunion reunion;

    /**
     * Constructor.
     * @param reunion
     */
    public DeleteReunionEvent(Reunion reunion) {
        this.reunion = reunion;
    }
    public Reunion getReunion() {
        return reunion;
    }
}

