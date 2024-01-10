package com.example.app;

import java.util.ArrayList;
import java.util.List;

public class DataObject {
    private static final List<CardInfo> listData = new ArrayList<>();


    public static List<CardInfo> getListData() {
        return listData;
    }
}
