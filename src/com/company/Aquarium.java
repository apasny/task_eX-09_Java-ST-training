package com.company;

import java.util.List;

public class Aquarium {

    public static int getTotalPrice(List<AquariumComponent> list) {
        int sum = 0;
        for (AquariumComponent component :
                list)
            sum += component.getPrice();
        return sum;
    }

}
