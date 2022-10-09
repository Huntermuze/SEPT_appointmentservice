package com.septgroup.appointmentservice.dto;

import java.time.Duration;
import java.time.LocalDateTime;

public class Timeslot {
    private LocalDateTime start;
    private Duration lengthOfSlot;

    public static Timeslot getTimeslotFromString(String timeslotString) {
        String[] dateAndDuration = timeslotString.split("#");
        return new Timeslot(LocalDateTime.parse(dateAndDuration[0]), Duration.parse(dateAndDuration[1]));
    }

    public Timeslot(LocalDateTime start, Duration lengthOfSlot) {
        this.start = start;
        this.lengthOfSlot = lengthOfSlot;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public Duration getLengthOfSlot() {
        return lengthOfSlot;
    }

    public void setLengthOfSlot(Duration lengthOfSlot) {
        this.lengthOfSlot = lengthOfSlot;
    }

    @Override
    public String toString() {
        return start.toString() + "#" + lengthOfSlot.toString();
    }
}
