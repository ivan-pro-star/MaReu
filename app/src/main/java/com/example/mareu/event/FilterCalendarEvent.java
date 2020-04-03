package com.example.mareu.event;

import java.util.Calendar;
import java.util.Date;

public class FilterCalendarEvent {
    private Calendar start;
    private Calendar end;

    /**
     * Constructor.
     * @param start
     * @param end
     */
    public FilterCalendarEvent(Calendar start, Calendar end) {
        this.start = start;
        this.end = end;
    }
    public Calendar getStart() {
        return start;
    }
    public Calendar getEnd(){return end;}
}
