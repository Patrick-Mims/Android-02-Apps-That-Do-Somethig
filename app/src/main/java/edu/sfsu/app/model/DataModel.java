package edu.sfsu.app.model;

import java.util.ArrayList;
import java.util.List;

public class DataModel {
    List<String> getBrands(String color) {
        List<String> brands = new ArrayList<>();

        if(color.equals("amber")) {
            brands.add("Jack Adams");
            brands.add("Red Moose");
        } else {
            brands.add("Jail Pale Ale");
            brands.add("Gout Stout");
        }

        return brands;
    }
}