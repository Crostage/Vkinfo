package com.crostage.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        Editable text = searchField.getText();
        result.setText(text);
    }
}