package edu.sfsu.app;

import java.util.ArrayList;
import java.util.List;

public class BeerExpert {
    List<String> getBrands(String color) {

        ArrayList<String> brands = new ArrayList<>();

        if(color.equals("Amber")) {
            brands.add("Jack Adams");
            brands.add("Red Moose");
        } else if(color.equals("Dark")) {
            brands.add("Coors");
            brands.add("Guiness");
        } else {
            brands.add("Jail Pale Ale");
            brands.add("Gout Stout");
        }

        return brands;
    }
}
