package np.com.yogeshg.shophelper.controller;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import np.com.yogeshg.shophelper.MainActivity;
import np.com.yogeshg.shophelper.R;
import np.com.yogeshg.shophelper.database.Database;
import np.com.yogeshg.shophelper.dbo.dbo_PreviousTransaction;

/**
 * Created by yogesh on 12/10/2017.
 */
public class Controller_NewSession extends Activity {

    String date_global;
    String initial_global;
    int session_global;
    EditText value;

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.session_new_firstpage);
        value=(EditText)findViewById(R.id.editText7);
        readDatabase();
        //Toast.makeText(this,date_global+"\n"+getDate(),Toast.LENGTH_LONG).show();
        value.setText(initial_global );

    }

    public void click_Next(View view)
    {



            dbo_PreviousTransaction dbo = new dbo_PreviousTransaction(this);
            dbo.setDate(date_global);
            dbo.setIncome(getTotalIncome(0) + "");
            dbo.setExpenses(getTotalIncome(1) + "");
            dbo.setDuepaid(getTotalIncome(2) + "");
            dbo.setDuereceived(getTotalIncome(3) + "");
            dbo.setItems_sold_due(getTotalIncome(4) + "");
            dbo.setItems_bought_due(getTotalIncome(5) + "");
            dbo.saveToDatabase();
            updateDatabase();

        Toast.makeText(this, "session created successfully", Toast.LENGTH_LONG).show();




        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public int getSession(){
        Database dbhandler=new Database(this,null,null,1);
        SQLiteDatabase db=dbhandler.getDb();
        int session=0;
        try {
            String query="SELECT * FROM previous_transaction";
            Cursor c=db.rawQuery(query,null);
            c.moveToLast();


            session=c.getInt(c.getColumnIndex("id"));
            Toast.makeText(this,session+"",Toast.LENGTH_LONG).show();

        }catch(Exception e){
            //Toast.makeText(con,e.toString()+"yogu exception",Toast.LENGTH_LONG).show();

        }
        return (session+1);
    }

    public void readDatabase(){
         Database dbhandler=new Database(this,null,null,1);
        SQLiteDatabase db=dbhandler.getDb();
        int amount=0;
        try {
            String query = "SELECT * FROM start_transaction" ;
            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();
            date_global=c.getString(c.getColumnIndex("date"));
            initial_global=c.getString(c.getColumnIndex("initial"));
            session_global=c.getInt(c.getColumnIndex("session"));


        }catch(Exception e){

        }

    }

    public void updateDatabase(){
        Database dbhandler=new Database(this,null,null,1);
        SQLiteDatabase db=dbhandler.getDb();
        try {
            String query="UPDATE start_transaction SET date='"+getDate()+"',initial='"+value.getText().toString()+"'"+",session='"+getSession()+"'";
            db.execSQL(query);
           //Toast.makeText(this,"new Session started with date:"+getDate()+"\namount: "+value.getText().toString(),Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();

        }


    }

    public int getTotalIncome(int type){
        Database dbhandler=new Database(this,null,null,1);
        SQLiteDatabase db=dbhandler.getDb();
        int amount=0;
        try {
            String query = "SELECT * FROM vendor_transaction where type=" + type+"AND date="+date_global;
            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();
            while (!c.isAfterLast()) {
                amount += Integer.parseInt(c.getString(c.getColumnIndex("amount")));
                c.moveToNext();
            }
        }catch(Exception e){

        }
        return amount;

    }

    public String getDate(){
        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);
        return thisDate;

    }

    public void click_Back(View view){
        onBackPressed();
    }
}
