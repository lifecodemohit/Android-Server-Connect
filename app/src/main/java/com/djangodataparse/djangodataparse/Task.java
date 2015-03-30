package com.djangodataparse.djangodataparse;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

class Task extends AsyncTask<String,String,String> {


    @Override
    protected String doInBackground(String... params) {

        JSONObject jsonobj = new JSONObject();
        try {
            String data = params[2];        // Please check if the index is 1 or 2.

//          Input format is as follows
//          <bus_no>;<latitude>;<longitude>  ("<" ">" for clarity)

            String dtemp[] = data.split(";");
            jsonobj.put("bus_no",dtemp[0]);
            jsonobj.put("latitude",dtemp[1]);
            jsonobj.put("longitude",dtemp[2]);

            /*for (int i=0;i<3;i++)
                System.out.print(dtemp[i]);*/
        } catch (JSONException e) {
            e.printStackTrace();
        }
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost httppostreq = new HttpPost("http://192.168.53.36:8080/");
        try {
            StringEntity se = new StringEntity(jsonobj.toString());
            se.setContentType("application/json;charset=UTF-8");
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));
            httppostreq.setEntity(se);
            HttpResponse httpresponse = httpclient.execute(httppostreq);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Updated on Server";
    }
}
