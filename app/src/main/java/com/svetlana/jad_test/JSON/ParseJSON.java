package com.svetlana.jad_test.JSON;

import android.os.AsyncTask;

import com.svetlana.jad_test.CardItem;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Svetlana on 07.10.2017.
 */

public class ParseJSON extends AsyncTask<Void, Void, CardItem> {

    private String urlString;
    private String[] titles;

    public ParseJSON(String key, String[] titles) {
        this.urlString = key;
        this.titles = titles;
    }

    @Override
    protected CardItem doInBackground(Void... params) {
        List<String> tmpKeys = new ArrayList<>();
        List<String> tmpValues = new ArrayList<>();

        try {
            URL url = new URL(urlString);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
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
            for (String title : titles) {
                tmpKeys.add(title);
                if(!dataObj.optString(title).equals(""))
                    tmpValues.add(dataObj.optString(title));
                else tmpValues.add("NULL");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new CardItem(tmpKeys, tmpValues);
    }

}

