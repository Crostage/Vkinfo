package com.crostage.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

import static com.crostage.myapplication.utils.NetworkUtils.generateUrl;
import static com.crostage.myapplication.utils.NetworkUtils.getResponseFromURL;

public class MainActivity extends AppCompatActivity {

    private EditText searchField;
    private Button searchButton;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        searchButton = findViewById(R.id.b_search_vk);
        searchField = findViewById(R.id.et_search_field);
        result = findViewById(R.id.tv_result);
    }

    public void clickSearch(View view) {

        String text = searchField.getText().toString();
        URL url = generateUrl(text);

        try {
            result.setText(getResponseFromURL(url));
        } catch (IOException e) {
            result.setText("Пользователь не найден");
        }


    }
}