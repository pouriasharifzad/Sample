package com.example.resume.download.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.resume.download.models.FileInfo;
import com.example.resume.shop.models.Product;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String FILE_PATH_COLUMN_NAME = "path";
    private static final String FILE_SIZE_COLUMN_NAME = "size";
    private static final String FILE_NAME_COLUMN_NAME = "name";

    final static String DBName1 = "members";
    final static int version1 = 1;


    public DBHelper(Context context) {

        super(context, DBName1, null, version1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table members(name text , username text primary key,password text)";
        db.execSQL(query);

        String query2 = "create table products(id integer primary key AUTOINCREMENT,title text,price text,imgurl text,rating integer)";
        db.execSQL(query2);

        String query3 = "create table FileInfo(id integer primary key,name varchar(50), path varchar(250), size integer)";
        db.execSQL(query3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");

    }




    public Boolean insertproduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", product.getTitle());
        contentValues.put("price", String.valueOf(product.getPrice()));
        contentValues.put("imgurl", product.getImgresource());
        contentValues.put("rating", 0f);
        Long ins = db.insert("products", null, contentValues);
        db.close();
        if (ins == -1) return false;
        else return true;

    }

    public ArrayList<Product> selectProducts() {

        String query = "select * from products";
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Product> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String price = cursor.getString(2);
                String imgurl = cursor.getString(3);
                Float rating = cursor.getFloat(4);
                Product product = new Product(id, title, Integer.parseInt(price), imgurl, rating);
                list.add(product);
            }
        }
        db.close();
        return list;

    }

    public Product select(int id) {


        SQLiteDatabase db = this.getReadableDatabase();
        String[] column = new String[]{"id", "title", "price", "imgurl", "rating"};
        Cursor cursor = db.query("products", column, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int mId = cursor.getInt(0);
                String mTitle = cursor.getString(1);
                int mPrice = cursor.getInt(2);
                String mImgurl = cursor.getString(3);
                Float rating = cursor.getFloat(4);
                Product product = new Product(mId, mTitle, mPrice, mImgurl, rating);
                cursor.close();
                db.close();
                return product;
            }
        }

        cursor.close();
        db.close();
        return null;


    }


    public long insert(FileInfo fileInfo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FILE_NAME_COLUMN_NAME, fileInfo.getFileName());
        contentValues.put(FILE_PATH_COLUMN_NAME, fileInfo.getPath());
        contentValues.put(FILE_SIZE_COLUMN_NAME, fileInfo.getFileSize());


        long rowId = db.insert("FileInfo", null, contentValues);
        db.close();
        return rowId;
    }


    public Boolean updateproduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", product.getTitle());
        contentValues.put("price", String.valueOf(product.getPrice()));
        contentValues.put("imgurl", product.getImgresource());
        contentValues.put("rating", product.getRating());
        int dbsize = db.update("products", contentValues, "id=?", new String[]{String.valueOf(product.getId())});
        db.close();
        return dbsize > 0;
    }

}
