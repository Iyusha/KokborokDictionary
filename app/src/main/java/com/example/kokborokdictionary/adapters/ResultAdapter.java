package com.example.kokborokdictionary.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.Gravity;
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
            String text = "<b>"+dataGroup.getDataRows().size() + "</b> record" + (dataGroup.getDataRows().size() == 0 ? "" : "s") + " found in <b><i>" + dataGroup.getGroupKey().toUpperCase()+"</i></b> word";

            Spanned spanned;
            if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M){
                spanned = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY);
            }else{
                spanned = Html.fromHtml(text);
            }
            label.setText(spanned);
            
            //description.setText(dataGroup.getDataRows().toString());

            for (int i = 0; i < dataGroup.getDataRows().size(); i++) {

                container_root.setBackgroundColor(colors[i % colors.length]);

                DataRow dataRow = dataGroup.getDataRows().get(i);

                LinearLayout root = new LinearLayout(context);
                root.setBackgroundResource(R.drawable.border);
                root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                LinearLayout.LayoutParams SLlayoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 2.0f);

                if (i == 0) {
                    //create header
                    LinearLayout root1 = new LinearLayout(context);
                    root1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    root1.setBackgroundColor(Color.WHITE);
                    //root1.setPadding(10, 10, 10, 10);

                    TextView tv0 = new TextView(context);
                    tv0.setBackgroundResource(R.drawable.border);
                    tv0.setPadding(10, 10, 10, 10);
                    tv0.setLayoutParams(SLlayoutParams);
                    TextView tv1 = new TextView(context);
                    tv1.setBackgroundResource(R.drawable.border);
                    tv1.setPadding(10, 10, 10, 10);
                    tv1.setLayoutParams(layoutParams);
                    TextView tv2 = new TextView(context);
                    tv2.setBackgroundResource(R.drawable.border);
                    tv2.setPadding(10, 10, 10, 10);
                    tv2.setLayoutParams(layoutParams);
                    TextView tv3 = new TextView(context);
                    tv3.setBackgroundResource(R.drawable.border);
                    tv3.setPadding(10, 10, 10, 10);
                    tv3.setLayoutParams(layoutParams);

                    tv0.setText("SL");
                    tv1.setText("English");
                    tv2.setText("Bangla");
                    tv3.setText("Kokborok");

                    tv0.setTypeface(null, Typeface.BOLD);
                    tv0.setTextSize(16);
                    tv1.setTypeface(null, Typeface.BOLD);
                    tv1.setTextSize(16);
                    tv2.setTypeface(null, Typeface.BOLD);
                    tv1.setTextSize(16);
                    tv3.setTypeface(null, Typeface.BOLD);
                    tv1.setTextSize(16);

                    tv0.setGravity(Gravity.CENTER);

                    root1.addView(tv0);
                    root1.addView(tv1);
                    root1.addView(tv3);
                    root1.addView(tv2);

                    rows.addView(root1);
                    rows.addView(getHR());
                }

                TextView tv0 = new TextView(context);
                tv0.setBackgroundResource(R.drawable.border);
                tv0.setPadding(10, 10, 10, 10);
                tv0.setLayoutParams(SLlayoutParams);
                TextView tv1 = new TextView(context);
                tv1.setBackgroundResource(R.drawable.border);
                tv1.setPadding(10, 10, 10, 10);
                tv1.setLayoutParams(layoutParams);
                TextView tv2 = new TextView(context);
                tv2.setBackgroundResource(R.drawable.border);
                tv2.setPadding(10, 10, 10, 10);
                tv2.setLayoutParams(layoutParams);
                TextView tv3 = new TextView(context);
                tv3.setBackgroundResource(R.drawable.border);
                tv3.setPadding(10, 10, 10, 10);
                tv3.setLayoutParams(layoutParams);

                tv0.setText(String.valueOf(i+1));
                tv1.setText(dataRow.getEnglish().trim());
                tv2.setText(dataRow.getBangla().trim());
                tv3.setText(dataRow.getKokborok().trim());

                tv0.setPadding(5, 5, 5, 5);
                tv1.setPadding(5, 5, 5, 5);
                tv2.setPadding(5, 5, 5, 5);
                tv3.setPadding(5, 5, 5, 5);

                tv0.setGravity(Gravity.CENTER);

                root.addView(tv0);
                root.addView(tv1);
                root.addView(tv3);
                root.addView(tv2);

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
