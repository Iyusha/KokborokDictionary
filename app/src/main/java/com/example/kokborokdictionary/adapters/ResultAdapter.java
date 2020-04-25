package com.example.kokborokdictionary.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kokborokdictionary.R;
import com.example.kokborokdictionary.model.DataGroup;
import com.example.kokborokdictionary.model.DataRow;

import java.util.ArrayList;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {
    private Context context;
    private ArrayList<DataGroup> dataGroups;

    public ResultAdapter(Context context, ArrayList<DataGroup> dataGroups) {
        this.context = context;
        this.dataGroups = dataGroups;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ResultViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.result_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        ((ResultViewHolder) holder).bind(dataGroups.get(position));
    }

    @Override
    public int getItemCount() {
        return dataGroups.size();
    }

    class ResultViewHolder extends RecyclerView.ViewHolder {
        private TextView label;
        private TextView description;
        private TextView english, bangla, kokborok;

        private LinearLayout container_root, rows;

        ResultViewHolder(@NonNull View itemView) {
            super(itemView);

            label = itemView.findViewById(R.id.label);
            description = itemView.findViewById(R.id.description);

            container_root = itemView.findViewById(R.id.container_root);
            rows = itemView.findViewById(R.id.rows);
            //english = itemView.findViewById(R.id.english_text);
            //bangla = itemView.findViewById(R.id.bangla_text);
            //kokborok = itemView.findViewById(R.id.kokborok_text);
        }

        private int[] colors = new int[]{Color.WHITE, Color.WHITE, Color.WHITE};

        void bind(DataGroup dataGroup) {

            if(dataGroup.getDataRows().size()==0){
                container_root.setVisibility(View.GONE);
                return;
            }
            label.setText(dataGroup.getDataRows().size() + " record" + (dataGroup.getDataRows().size() == 0 ? "" : "s") + " found in " + dataGroup.getGroupKey()+" word");
            //description.setText(dataGroup.getDataRows().toString());

            for (int i = 0; i < dataGroup.getDataRows().size(); i++) {

                container_root.setBackgroundColor(colors[i % colors.length]);

                DataRow dataRow = dataGroup.getDataRows().get(i);

                LinearLayout root = new LinearLayout(context);
                root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);


                if (i == 0) {
                    //create header
                    LinearLayout root1 = new LinearLayout(context);
                    root1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    root1.setBackgroundColor(Color.WHITE);
                    root1.setPadding(10, 10, 10, 10);

                    TextView tv1 = new TextView(context);
                    tv1.setLayoutParams(layoutParams);
                    TextView tv2 = new TextView(context);
                    tv2.setLayoutParams(layoutParams);
                    TextView tv3 = new TextView(context);
                    tv3.setLayoutParams(layoutParams);

                    tv1.setText("English");
                    tv2.setText("Bangla");
                    tv3.setText("Kokborok");

                    tv1.setTypeface(null, Typeface.BOLD);
                    tv1.setTextSize(16);
                    tv2.setTypeface(null, Typeface.BOLD);
                    tv1.setTextSize(16);
                    tv3.setTypeface(null, Typeface.BOLD);
                    tv1.setTextSize(16);

                    root1.addView(tv1);
                    root1.addView(tv2);
                    root1.addView(tv3);

                    rows.addView(root1);
                    rows.addView(getHR());
                }

                TextView tv1 = new TextView(context);
                tv1.setLayoutParams(layoutParams);
                TextView tv2 = new TextView(context);
                tv2.setLayoutParams(layoutParams);
                TextView tv3 = new TextView(context);
                tv3.setLayoutParams(layoutParams);

                tv1.setText(dataRow.getEnglish().trim());
                tv2.setText(dataRow.getBangla().trim());
                tv3.setText(dataRow.getKokborok().trim());

                tv1.setPadding(5, 5, 5, 5);
                tv2.setPadding(5, 5, 5, 5);
                tv3.setPadding(5, 5, 5, 5);


                root.addView(tv1);
                root.addView(tv2);
                root.addView(tv3);

                rows.addView(root);
                rows.addView(getHR());
                //english.setText(dataRow.getEnglish());
                //bangla.setText(dataRow.getBangla());
                //kokborok.setText(dataRow.getKokborok());
            }


        }

        View getHR() {
            LinearLayout hr = new LinearLayout(context);
            hr.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
            hr.setBackgroundColor(Color.WHITE);
            return hr;
        }
    }


}
