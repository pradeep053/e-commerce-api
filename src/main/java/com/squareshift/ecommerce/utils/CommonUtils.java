package com.squareshift.ecommerce.utils;

import com.squareshift.ecommerce.constants.Constants;

public class CommonUtils {

    public static float calculateTotalAmount(long distance,long weightInGrams){
        float weightKg=getWeightInKg(weightInGrams);
        int distanceIndex=getDistanceIndex(distance);
        int weightIndex=getWeightIndex(weightKg);
        return Constants.AMOUNT_MAP[weightIndex][distanceIndex];
    }

    private static int getDistanceIndex(long distance) {
        if(distance<5)
            return 0;
        else if(distance>=5 && distance<20)
            return 1;
        else if(distance>=20 && distance<50)
            return 2;
        else if(distance>=50 && distance<500)
            return 3;
        else if(distance>=500 && distance<800)
            return 4;
        else
            return 5;

    }

    private static int getWeightIndex(float weightKg) {
        if(weightKg<2.0)
            return 0;
        else if(weightKg>2.0 && weightKg<=5.0)
            return 1;
        else if(weightKg>5.0 && weightKg<=20.0)
            return 2;
        else
            return 3;
    }

    public static float getWeightInKg(long weightInGrams){
        return ((float) weightInGrams/1000);
    }
}
