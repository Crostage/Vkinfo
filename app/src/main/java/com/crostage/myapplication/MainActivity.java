package com.crostage.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import static com.crostage.myapplication.utils.NetworkUtils.generateUrl;
import static com.crostage.myapplication.utils.NetworkUtils.getResponseFromURL;

public class MainActivity extends AppCompatActivity {

    private EditText searchField;
    private Button searchButton;
    private TextView result;
    private TextView error_message;
    private ProgressBar loadingIndicator;

    private void showResultTextView(){
        result.setVisibility(View.VISIBLE);
        error_message.setVisibility(View.INVISIBLE);
    }

    private void showErrorTextView(){
        result.setVisibility(View.INVISIBLE);
        error_message.setVisibility(View.VISIBLE);
    }

    class VKQueryTask extends AsyncTask<URL,Void,String>{

        @Override
        protected void onPreExecute() {
            loadingIndicator.setVisibility(View.VISIBLE);
        }

        //метод, который является основным трэдом, который выполняется параллельно
        @Override
        protected String doInBackground(URL... urls) {
            String response = null;
            try {
                response = getResponseFromURL(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }

        //метод, который вполняется сразу после завершения метода doInBackground
        @Override
        protected void onPostExecute(String response){

           if (response==null&&response.equals("")){
               showErrorTextView();
           } else {
               String res = null;

               try {
                   JSONObject jsonObject = new JSONObject(response);
                   JSONArray jsonArr =  jsonObject.getJSONArray("response");
                   JSONObject jsonObject1 = jsonArr.getJSONObject(0);
                   res = jsonObject1.getString("first_name")+" "+jsonObject1.getString("last_name");
               } catch (JSONException e) {
                   e.printStackTrace();
               }

               result.setText(res);
              showResultTextView();

           }
            loadingIndicator.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchButton = findViewById(R.id.b_search_vk);
        searchField = findViewById(R.id.et_search_field);
        result = findViewById(R.id.tv_result);
        error_message = findViewById(R.id.tv_error_message);
        loadingIndicator = findViewById(R.id.pd_loading_indicator);
    }

    public void clickSearch(View view) {

        String id = searchField.getText().toString();
        URL url = generateUrl(id);

        new VKQueryTask().execute(url); //запуск треда - деприкейт (
    }
}