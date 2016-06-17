package com.lukas.model;

import java.util.ArrayList;
import java.util.List;

public class Purchase {

    private List<Record> recordList = new ArrayList<>();


    public void addItemToList(Record record) {
        recordList.add(record);
    }

    public Double sumPrices() {
        Double sum = 0.00;
        for (Record record : recordList) {
            sum = sum + record.sumOfItem();
        }

        return sum;
    }


    public List<Record> getRecords() {
        return recordList;
    }
}
