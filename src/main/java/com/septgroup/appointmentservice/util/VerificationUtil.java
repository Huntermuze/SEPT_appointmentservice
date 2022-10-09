package com.septgroup.appointmentservice.util;

import com.septgroup.appointmentservice.exception.InvalidIdException;

import java.util.UUID;

public class VerificationUtil {

    public static long ifValidGetApptID(String id) throws InvalidIdException {
        long parsedID;
        try {
            parsedID = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new InvalidIdException(String.format("%s is not a valid appointment id!", id));
        }
        return parsedID;
    }

    public static UUID ifValidGetUUID(String uuid) throws InvalidIdException {
        UUID id;
        try {
            id = UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new InvalidIdException(e.getMessage());
        }

        return id;
    }

    // TODO need to use regex here, as apprently fromString is limited.
    //  https://stackoverflow.com/questions/20041051/how-to-judge-a-string-is-uuid-type
    public static void throwIfNotValidUUID(String uuid) throws InvalidIdException {
        try {
            UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new InvalidIdException(e.getMessage());
        }
    }
}
