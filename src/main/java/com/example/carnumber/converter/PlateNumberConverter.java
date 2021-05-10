package com.example.carnumber.converter;
import com.example.carnumber.model.PlateNumber;

public class PlateNumberConverter {

    public String toString(PlateNumber number) {
        return number.toString();
    }

    public PlateNumber fromString(long id, String number) {
        char[] chars = number.toCharArray();
        return new PlateNumber(
                id,
                chars[0],
                String.valueOf(chars[1]) + chars[2] + chars[3],
                chars[4],
                chars[5]
        );
    }
}
