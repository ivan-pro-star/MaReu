package com.example.mareu.model;

import com.example.mareu.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class Reunion {

    private long id;
    private int room;
    private String subject;
    private String allParticipants;
    private Calendar calendar;

    public Reunion(long id, int room, String subject, String allParticipants, Calendar calendar) {
        this.id = id;
        this.room = room;
        this.subject = subject;
        this.allParticipants = allParticipants;
        this.calendar = calendar;
    }

    public long getId() {
        return id;
    }

    public int getRoom() {
        return room;
    }

    public String getSubject() {
        return subject;
    }
    public String getAllParticipants() {
        return allParticipants;
    }
    public Calendar getCalendar() {
        return calendar;
    }

    public String getFormattedDate() {
        return Utils.FORMAT_DATE.format(calendar.getTime());
    }
     public String getFormattedHour() {
        return Utils.FORMAT_TIME.format(calendar.getTime());
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reunion reunion = (Reunion) o;
        return Objects.equals(id, reunion.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
