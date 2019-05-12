package com.xumuia.homeapplication;

import android.os.AsyncTask;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class AsyncRequest extends AsyncTask<String, Integer, String>{

       @Override
    protected String doInBackground(String... arg) {

        String url = arg[0];
        String result = new String();

        try{
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0" );
            connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            connection.setRequestProperty("Content-Type", "application/json");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }
            bufferedReader.close();
            return response.toString();

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return "unknown";
    }

    @Override
    protected void onPostExecute(String s) {
      MainActivity.dataFromAsyncTask = s;
    }
}
