package com.newproject.Mondongo.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.newproject.Mondongo.exceptions.MyCustomApiException;

public enum Gender {
    MALE("Male", "Masculino"),
    FEMALE("Female", "Femenino"),
    OTHER("Other", "Otro");

    private final String GENDER;
    private final String GENDER_SPANISH;

    Gender(String GENDER, String GENDER_SPANISH) {
        this.GENDER = GENDER;
        this.GENDER_SPANISH = GENDER_SPANISH;
    }

    @JsonValue
    public String getGENDER() {
        return GENDER;
    }

    @JsonCreator
    public static Gender fromString(String text) {
        for (Gender gender : Gender.values()) {
            if (gender.GENDER.equalsIgnoreCase(text) ||
                    gender.GENDER_SPANISH.equalsIgnoreCase(text)) {
                return gender;
            }
        }
        throw new MyCustomApiException("Unknown gender value: " + text);
    }
}