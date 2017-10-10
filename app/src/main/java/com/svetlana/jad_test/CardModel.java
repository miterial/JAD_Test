package com.svetlana.jad_test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Svetlana on 09.10.2017.
 */

public class CardModel {
    private List<String> keys;
    private List<String> values;
    private String cardTitle;

    public CardModel(List<String> keys, List<String> values, String cardTitle) {
        this.keys = keys;
        this.values = values;
        this.cardTitle = cardTitle;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }
}
