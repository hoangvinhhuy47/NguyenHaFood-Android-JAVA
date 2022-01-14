package com.example.nguyenhafood.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.nguyenhafood.Model.LoginUser.Customer;

public class DBHelper extends SQLiteOpenHelper {
    SQLiteDatabase db = this.getReadableDatabase();

    public DBHelper(@Nullable Context context) {
        super(context, "NguyenHaFood.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table User(ID int primary key,CustomerID text,UserName text,FullName text,PassWordEnCode text, PassWord text)");
        db.execSQL("Create Table UserER(ID int primary key,USERID text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists LoginUser");
        db.execSQL("drop table if exists UserER");
    }

    public boolean InserUser(String UserName, String CustomerID, String FullName, String PassWordEnCode, String Pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("ID", 1);
        contentValue.put("CustomerID", CustomerID);
        contentValue.put("UserName", UserName);
        contentValue.put("FullName", FullName);
        contentValue.put("PassWordEnCode", PassWordEnCode);
        contentValue.put("PassWord", Pass);
        long rowInserted = db.insert("User", null, contentValue);
        if (rowInserted == -1) return false;
        else return true;
    }

    public boolean InsertUserER(String USERID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", 1);
        contentValues.put("USERID", USERID);
        long ins = db.insert("UserER", null, contentValues);
        if (ins == -1) {
            return false;
        } else {
            return true;
        }
    }

    //hàm get dữ liệu
    public String GetDL() {
        try {
            Cursor cursor = getReadableDatabase().rawQuery("Select * from UserER", null);

            if (cursor.moveToFirst()) {
                return cursor.getString(1);
            }
        } catch (Exception exception) {
            return null;
        }
        return null;
    }

    public boolean DeleteUserUR(int ID) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.isOpen();
        return db.delete("UserER", "ID =?", new String[]{String.valueOf(ID)}) > 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    // là hàm kiểm tra dữ liệu

    public Customer GetUser() {
        Customer customer = new Customer();
        try (Cursor cursor = db.rawQuery("Select * from User  ", null)) {
            if (cursor.moveToFirst()) {
                customer.setCustomerID(cursor.getString(1));
                customer.setFullName(cursor.getString(3));
                customer.setUserName(cursor.getString(2));
                customer.setPassword(cursor.getString(5));
            }
            return customer;
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean DeleteUser(int ID) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.isOpen();
        return db.delete("User", "ID =?", new String[]{String.valueOf(ID)}) > 0;
    }

}