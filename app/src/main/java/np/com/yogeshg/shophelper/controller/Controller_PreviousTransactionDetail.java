package np.com.yogeshg.shophelper.controller;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import np.com.yogeshg.shophelper.Controller_interface;
import np.com.yogeshg.shophelper.R;
import np.com.yogeshg.shophelper.database.Database;

/**
 * Created by yogesh on 12/3/2017.
 */
public class Controller_PreviousTransactionDetail extends Activity {
    TextView initialamount,income,expenses,boughtdue,solddue,duepaid,duereceived;
    TextView total;
    Database dbhandler;
    SQLiteDatabase db;
    String date_global,initial_global;

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.previous_transaction_detail);

        initialamount=(TextView)findViewById(R.id.textView14);
        income=(TextView)findViewById(R.id.textView15);
        expenses=(TextView)findViewById(R.id.textView16);
        boughtdue=(TextView)findViewById(R.id.textView17);
        solddue=(TextView)findViewById(R.id.textView18);
        duepaid=(TextView)findViewById(R.id.textView22);
        duereceived=(TextView)findViewById(R.id.textView23);
        total=(TextView)findViewById(R.id.textView19);
        dbhandler=new Database(this,null,null,1);

        try {

            income.setText(getTotalIncome(0)+"");
            expenses.setText(getTotalIncome(1)+"");
            duepaid.setText(getTotalIncome(2)+"");
            duereceived.setText(getTotalIncome(3)+"");
            solddue.setText(getTotalIncome(4)+"");
            boughtdue.setText(getTotalIncome(5)+"");

            int total1=getTotalIncome(0)-getTotalIncome(1)+getTotalIncome(3)-getTotalIncome(2);
            total.setText("Final Amount on Box:  "+total1);



        }catch(Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }



    }

    public int getTotalIncome(int type){
        db=dbhandler.getDb();
        int amount=0;
        try {
            String query = "SELECT * FROM vendor_transaction where type=" + type+" AND session='"+Controller_interface.previous_id+"'";
            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();
            while (!c.isAfterLast()) {
                try {
                    amount += Integer.parseInt(c.getString(c.getColumnIndex("amount")));
                }catch(Exception e){
                    amount=amount+0;
                }
                c.moveToNext();
            }
        }catch(Exception e){

        }
        return amount;

    }

    public void readDatabase(){
        Database dbhandler=new Database(this,null,null,1);
        SQLiteDatabase db=dbhandler.getDb();
        int amount=0;
        try {
            String query = "SELECT * FROM previous_transaction where id="+ Controller_interface.previous_id;
            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();

            //session_global=c.getInt(c.getColumnIndex("session"));


        }catch(Exception e){

        }

    }

    public void click_Back(View view){
       Intent i=new Intent(this,Controller_ViewPreviousTransaction.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,Controller_ViewPreviousTransaction.class);
        startActivity(i);    }
}
