package com.example.kokborokdictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
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
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                    }
//                });
                findAndDisplayResult(search_key);

            }
        });
    }

    private class AsyncProcess extends AsyncTask<String, Integer, ArrayList<DataGroup>>{

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
            progress_root.setVisibility(View.VISIBLE);

        }

        @Override
        protected ArrayList<DataGroup> doInBackground(String... strings) {
//            findAndDisplayResult(strings[0]);
            //onProgressUpdate(0);
            String search_key = strings[0];
            return dbHandler.findWord(search_key);
            //onProressUpdate(100);
            //return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //super.onProgressUpdate(values);
//            if(values[0]==0){
//                progress_root.setVisibility(View.VISIBLE);
//            }else{
//                progress_root.setVisibility(View.GONE);
//            }

        }

        @Override
        protected void onPostExecute(ArrayList<DataGroup> dataGroups) {
            //super.onPostExecute(aBoolean);
            //Log.e("data", mList.toString());
            mList.clear();
            mList.addAll(dataGroups);

            adapter = new ResultAdapter(MainActivity.this, mList);
            result_rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            result_rv.setAdapter(adapter);

            //adapter.notifyDataSetChanged();

            progress_root.setVisibility(View.GONE);
        }
    }

    private void findAndDisplayResult(String search_key) {
        new AsyncProcess().execute(search_key);

        //result.setText(mList.toString());
        /*Log.e("data", mList.toString());
        adapter = new ResultAdapter(this, mList);
        result_rv.setLayoutManager(new LinearLayoutManager(this));
        result_rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();*/
    }
}
