package com.svetlana.jad_test;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс представляет модель карточки
 */

public class CardModel {
    //Список ключей
    private ArrayList<String> keys;
    //Список значений
    private ArrayList<String> values;
    //Заголовок карточки
    private String cardTitle;

    public CardModel(ArrayList<String> keys, ArrayList<String> values, String cardTitle) {
        this.keys = keys;
        this.values = values;
        this.cardTitle = cardTitle;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(ArrayList<String> keys) {
        this.keys = keys;
    }

    public ArrayList<String> getValues() {
        return values;
    }

    public void setValues(ArrayList<String> values) {
        this.values = values;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }
}
