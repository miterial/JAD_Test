package com.svetlana.jad_test.JSON;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Svetlana on 07.10.2017.
 */

public class ParseJSON extends AsyncTask<Void, Void, String> {

    private HttpURLConnection urlConnection = null;
    private BufferedReader reader = null;
    private String resultJSON = "";

    private String resultValue, urlString;
    private String[] titles;

    public ParseJSON(String key, String[] titles) {
        this.urlString = key;
        this.titles = titles;
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            URL url = new URL(urlString);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            resultJSON = buffer.toString();

            // Получение результата в форме JSON
            JSONObject dataObj;
            dataObj = new JSONObject(resultJSON);
            for(int i = 0; i < titles.length; i++) {
                resultValue += titles[i] + ": " + dataObj.get(titles[i]).toString() + ", ";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultJSON;
    }

    @Override
    protected void onPostExecute(String strJson) {
        super.onPostExecute(strJson);

    }


}

