package com.jalil_be_app.madang_app.utils;

import com.jalil_be_app.madang_app.model.enums.SeatCategory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Component
public class GenerateTransactionNumber {
    public static String generateTransactionNumber(SeatCategory seatCategory){
        String prefix = null;
        if (seatCategory.equals(SeatCategory.FAMILY)){
            prefix = "fam";
        } else if (seatCategory.equals(SeatCategory.WORK)) {
            prefix = "wrk";
        } else if (seatCategory.equals(SeatCategory.CUSTOM)){
            prefix = "ctm";
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(new Date());

        Random random = new Random();
        int randomInt = random.nextInt(9999);

        return prefix + date + String.format("%06d", randomInt);
    }

}
