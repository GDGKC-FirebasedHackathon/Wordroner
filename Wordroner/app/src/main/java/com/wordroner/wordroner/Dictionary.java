package com.wordroner.wordroner;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by HeeIll on 2017-02-18.
 */

public class Dictionary {
    private String definition;

    //return definitions of word
    public String ShowDefinitions(String word) throws InterruptedException, ExecutionException {
        final String language = "en";
        final String word_id = word.toLowerCase(); //word id is case sensitive and lowercase is required
        definition = "123";

        return  new CallbackTask().execute("https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + word_id).get();
    }


    //in android calling network requests on the main thread forbidden by default
    //create class to do async job
    private class CallbackTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {

            //TODO: replace with your own app id and app key
            final String app_id = "32d2d75c";
            final String app_key = "24f2eb16bf1cf1e81751f567a02dc2c7";
            try {
                URL url = new URL(params[0]);
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.setRequestProperty("app_id", app_id);
                urlConnection.setRequestProperty("app_key", app_key);

                // read the output from the server
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();

                String line = null;
                int i = 1;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("\"definitions\"")) {
                        line = reader.readLine().trim();

                        stringBuilder.append(i + "." + line + "\n\n");
                        i++;
                    }

                    //show only maximum 3 definitions
                    if (i > 3)
                        break;
                }

                return stringBuilder.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return e.toString();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }
}
