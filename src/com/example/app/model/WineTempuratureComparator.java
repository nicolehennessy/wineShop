package com.example.app.model;

import java.util.Comparator;

public class WineTempuratureComparator implements Comparator<Wine>{

    @Override
    public int compare(Wine w1, Wine w2) {
        return (int)(w1.getTempurature() - w2.getTempurature());
    }
    
}
