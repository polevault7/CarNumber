package com.example.carnumber.model;

import lombok.Value;

@Value
public class PlateNumber {
    long id;
    Character firstLetter;
    String number;
    Character secondLetter;
    Character thirdLetter;

    @Override
    public String toString() {
        return "" + firstLetter + number + secondLetter + thirdLetter;
    }
}
