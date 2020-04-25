package com.example.kokborokdictionary.model;


import java.io.Serializable;
import java.util.ArrayList;

public class DataGroup implements Serializable {

    private String groupKey, groupKeyValue;
    private ArrayList<DataRow> dataRows = new ArrayList<>();

    public DataGroup(String groupKey, String groupKeyValue) {
        this.groupKey = groupKey;
        this.groupKeyValue = groupKeyValue;
    }

    public DataGroup(String groupKey, String groupKeyValue, ArrayList<DataRow> dataRows) {
        this.groupKey = groupKey;
        this.groupKeyValue = groupKeyValue;
        this.dataRows = dataRows;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public String getGroupKeyValue() {
        return groupKeyValue;
    }

    public void setGroupKeyValue(String groupKeyValue) {
        this.groupKeyValue = groupKeyValue;
    }

    public ArrayList<DataRow> getDataRows() {
        return dataRows;
    }

    public void setDataRows(ArrayList<DataRow> dataRows) {
        this.dataRows = dataRows;
    }

    @Override
    public String toString(){
        return getGroupKey()+"\nWord: "+getGroupKeyValue()+"\nMatches: "+getDataRows().size();
    }
}
