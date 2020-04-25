package com.example.kokborokdictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kokborokdictionary.adapters.ResultAdapter;
import com.example.kokborokdictionary.db.DBHandler;
import com.example.kokborokdictionary.model.DataGroup;
import com.example.kokborokdictionary.model.DataRow;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText search_view;
    private Button search_button;
    private TextView result;
    private FrameLayout progress_root;
    private LinearLayout result_root;
    private RecyclerView result_rv;
    private ResultAdapter adapter;
    private ArrayList<DataGroup> mList = new ArrayList<>();
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new DBHandler(getApplicationContext());

        initaliseAllView();

        initalizeAllListener();
    }

    private void initaliseAllView() {
        search_view = findViewById(R.id.search_view);
        search_button = findViewById(R.id.search_button);
        result = findViewById(R.id.result);
        result_rv = findViewById(R.id.result_rv);
        progress_root = findViewById(R.id.progress_root);
        result_root = findViewById(R.id.result_root);

        adapter = new ResultAdapter(this, mList);
        result_rv.setLayoutManager(new LinearLayoutManager(this));
        result_rv.setAdapter(adapter);
    }

    private void initalizeAllListener() {
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search_key = search_view.getText().toString().trim();
                if (search_key.length() == 0) {
                    return;
                }

                // find out the key from database and display in the UI
                findAndDisplayResult(search_key);

            }
        });
    }


    private void findAndDisplayResult(String search_key) {
        mList = dbHandler.findWord(search_key);
        //result.setText(mList.toString());
        Log.e("data", mList.toString());
        adapter = new ResultAdapter(this, mList);
        result_rv.setLayoutManager(new LinearLayoutManager(this));
        result_rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
