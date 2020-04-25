package com.example.kokborokdictionary.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.kokborokdictionary.Values.Constants;
import com.example.kokborokdictionary.model.DataGroup;
import com.example.kokborokdictionary.model.DataRow;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteAssetHelper {
    private Context context;
    private static final String DB_NAME = "kokborok_db.sqlite";
    private static final int DB_VERSION = 1;

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static String getDbName() {
        return DB_NAME;
    }

    public static int getDbVersion() {
        return DB_VERSION;
    }


    public ArrayList<DataRow> findSearchValue(String key, String value) {
        ArrayList<DataRow> mList = new ArrayList<>();

        String sql = "SELECT * FROM word ";
        String[] selectArgs = {Constants.DataRow.ENGLISH + " LIKE '%" + value + "%'"};//,
                //Constants.DataRow.KOKBOROK + " LIKE '%" + value + "%'",
                //Constants.DataRow.BANGLA + " LIKE '%" + value + "%'"};
        try {
            SQLiteDatabase sqdb = this.getReadableDatabase();

            Cursor cursor = sqdb.rawQuery("SELECT * FROM "
                    + " word " + " where "
                    + Constants.DataRow.ENGLISH + " like '%" + value + "%'"
                    + " OR " + Constants.DataRow.KOKBOROK + " LIKE '%" + value + "%'"
                    + " OR " + Constants.DataRow.BANGLA + " LIKE '%" + value + "%'", null);
            if (cursor == null) {
                throw new Exception("Cursor was null");
            }

            String[] cols = cursor.getColumnNames();
            int size = cols.length;

            Log.e("d", size + "");

            int i = 0;
            while (i < cols.length) {
                //Log.e("col", cols[i] + " " + cols[i].length());
                i++;
            }

            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(Constants.DataRow.ID));
                String english = cursor.getString(cursor.getColumnIndex(Constants.DataRow.ENGLISH));
                String kokborok = cursor.getString(cursor.getColumnIndex(Constants.DataRow.KOKBOROK));
                String bangla = cursor.getString(cursor.getColumnIndex(Constants.DataRow.BANGLA));
                int isfavourite = cursor.getInt(cursor.getColumnIndex(Constants.DataRow.FAVOURITE));

                DataRow dr = new DataRow(id, english, kokborok, bangla, isfavourite);
                mList.add(dr);
            }

            cursor.close();

        } catch (Exception e) {
            Log.e("error", e.toString());
        }


        return mList;
    }


    public ArrayList<DataGroup> findWord(String word){
        ArrayList<DataGroup> dataGroups = new ArrayList<>();

        DataGroup englishDataGroup = new DataGroup(Constants.DataRow.ENGLISH, word, findSearchKeyValue(Constants.DataRow.ENGLISH, word));
        DataGroup kokborokDataGroup = new DataGroup(Constants.DataRow.KOKBOROK, word, findSearchKeyValue(Constants.DataRow.KOKBOROK, word));
        DataGroup banglaDataGroup = new DataGroup(Constants.DataRow.BANGLA, word, findSearchKeyValue(Constants.DataRow.BANGLA, word));

        dataGroups.add(englishDataGroup);
        dataGroups.add(kokborokDataGroup);
        dataGroups.add(banglaDataGroup);

        return dataGroups;
    }

    private ArrayList<DataRow> findSearchKeyValue(String key, String value) {
        ArrayList<DataRow> mList = new ArrayList<>();
        try {
            SQLiteDatabase sqdb = this.getReadableDatabase();

//            Cursor cursor = sqdb.rawQuery("SELECT * FROM "
//                    + " word " + " where "
//                    + Constants.DataRow.ENGLISH + " like '%" + value + "%'"
//                    + " OR " + Constants.DataRow.KOKBOROK + " LIKE '%" + value + "%'"
//                    + " OR " + Constants.DataRow.BANGLA + " LIKE '%" + value + "%'", null);


            Cursor cursor = sqdb.rawQuery("SELECT * FROM "
                    + " word " + " where "
                    + key + " like '%" + value + "%'"
                    , null);

            if (cursor == null) {
                throw new Exception("Cursor was null");
            }

            String[] cols = cursor.getColumnNames();
            int size = cols.length;

            Log.e("d", size + "");

            int i = 0;
            while (i < cols.length) {
                //Log.e("col", cols[i] + " " + cols[i].length());
                i++;
            }

            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(Constants.DataRow.ID));
                String english = cursor.getString(cursor.getColumnIndex(Constants.DataRow.ENGLISH));
                String kokborok = cursor.getString(cursor.getColumnIndex(Constants.DataRow.KOKBOROK));
                String bangla = cursor.getString(cursor.getColumnIndex(Constants.DataRow.BANGLA));
                int isfavourite = cursor.getInt(cursor.getColumnIndex(Constants.DataRow.FAVOURITE));

                DataRow dr = new DataRow(id, english, kokborok, bangla, isfavourite);
                mList.add(dr);
            }

            cursor.close();

        } catch (Exception e) {
            Log.e("error", e.toString());
        }
        return mList;
    }
}
