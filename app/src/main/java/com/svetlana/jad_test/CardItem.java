package com.svetlana.jad_test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Svetlana on 09.10.2017.
 */

public class CardItem {
    public List<String> keys;
    public List<String> values;

    public CardItem(List<String> keys, List<String> values) {
        this.keys = keys;
        this.values = values;
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
}
