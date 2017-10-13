package com.svetlana.jad_test.JSON;

import android.os.AsyncTask;

import com.svetlana.jad_test.CardModel;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Класс представляет интерфейс для получения списка ключей и значений из файла в формате json
 * Результатом работы будет карточка - объект класс CardModel
 */

public class ParseJSON extends AsyncTask<Void, Void, CardModel> {

    private String urlString;
    private String cardTitle;

    StringBuffer sb = new StringBuffer("http://.jsontest.com/");

    public ParseJSON(String service, String cardTitle) {
        sb.insert(7, service);
        this.urlString = sb.toString();
        this.cardTitle = cardTitle;
    }

    public ParseJSON(String service, String data, String cardTitle) {
        sb.insert(7, service);
        sb.append(data);
        this.urlString = sb.toString();
        this.cardTitle = cardTitle;
    }

    @Override
    protected CardModel doInBackground(Void... params) {
        ArrayList<String> tmpKeys = new ArrayList<>();
        ArrayList<String> tmpValues = new ArrayList<>();

        try {
            URL url = new URL(urlString);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            String resultJSON = buffer.toString();

            // Получение результата из файла json
            JSONObject dataObj;
            dataObj = new JSONObject(resultJSON);
            Iterator keys = dataObj.keys();

            while(keys.hasNext()) {
                String k = (String)keys.next();
                tmpKeys.add(k);
                if(!dataObj.optString(k).equals(""))
                    tmpValues.add(dataObj.optString(k));
                else tmpValues.add("NULL");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new CardModel(tmpKeys, tmpValues, cardTitle);
    }

}

