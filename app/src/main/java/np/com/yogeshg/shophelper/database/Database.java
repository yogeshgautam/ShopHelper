package np.com.yogeshg.shophelper.database;

/**
 * Created by yogesh on 12/1/2017.
 */


import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.content.Context;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Database extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "shophelpyogesh.db";
    Context con;
    public Database(Context c, String n, CursorFactory f, int v)
    {
        super(c, DATABASE_NAME, f, DATABASE_VERSION);
        con =c;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            Toast.makeText(con,"onCreate",Toast.LENGTH_LONG).show();
            String query2="CREATE TABLE vendor_detail(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(40),address VARCHAR(50),phone VARCHAR(70),remarks VARCHAR(50));";
            db.execSQL(query2);
            query2="CREATE TABLE vendor_transaction(id INTEGER PRIMARY KEY AUTOINCREMENT,vendor_id INTEGER(40),date VARCHAR(50),type INTEGER(10),session INTEGER(8),amount VARCHAR(60),remarks VARCHAR(60));";
            db.execSQL(query2);
            query2="CREATE TABLE previous_transaction(id INTEGER PRIMARY KEY AUTOINCREMENT,date VARCHAR(40),income VARCHAR(40),expenses VARCHAR(50),item_bought_due VARCHAR(40),items_sold_due VARCHAR(60),duepaid VARCHAR(60),duereceived VARCHAR(50));";
            db.execSQL(query2);
            query2="CREATE TABLE start_transaction(id INTEGER PRIMARY KEY AUTOINCREMENT,date VARCHAR(40),initial VARCHAR(40),session INTEGER(10),email VARCHAR(40));";
            db.execSQL(query2);
            String query = "insert into start_transaction(date,initial,session,email) values('"+getDate()+"','0',1,'your_email@example.com');";
            db.execSQL(query);
            Toast.makeText(con,"DataBase: Table created",Toast.LENGTH_LONG ).show();
            Toast.makeText(con,"Transaction Started for date: "+getDate(),Toast.LENGTH_LONG).show();
        }catch(Exception e) {
            Toast.makeText(con,e.toString(),Toast.LENGTH_LONG ).show();
        }
    }

    public String getDate(){
        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);
        return thisDate;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old_ver, int new_ver) {
        onCreate(db);
    }


    public SQLiteDatabase getDb()
    {
        return this.getWritableDatabase();
    }

}

