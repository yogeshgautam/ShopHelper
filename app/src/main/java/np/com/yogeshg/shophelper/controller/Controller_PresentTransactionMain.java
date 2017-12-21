package np.com.yogeshg.shophelper.controller;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import np.com.yogeshg.shophelper.Controller_interface;
import np.com.yogeshg.shophelper.MainActivity;
import np.com.yogeshg.shophelper.R;
import np.com.yogeshg.shophelper.database.Database;
import np.com.yogeshg.shophelper.database.Database_DetailGetValue;
import np.com.yogeshg.shophelper.model.ModelVendorTransaction;

/**
 * Created by yogesh on 12/3/2017.
 */
public class Controller_PresentTransactionMain extends Activity {
    ListView listView;
    Database dbhandler;
    SQLiteDatabase db;

    int id[];
    String for_list[];


    String yogesh[];
    int totalamount=0;

    TextView total_text;
    String date_global,initial_global,session_global;

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.present_transaction_main);
        total_text=(TextView)findViewById(R.id.textView5);
        readDatabases();


        dbhandler=new Database(this,null,null,1);
        db=dbhandler.getDb();

        int count=getTotalCount();

        for_list=new String[count];
        id=new int[count];
        for_list=getValue();

        try {


            listView = (ListView) findViewById(R.id.listView3);


            ListAdapter yogeshAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, for_list) {
            };
            listView.setAdapter(yogeshAdapter);
            total_text.setText("Total Transaction :\n   " + totalamount);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    goToView(position);
                }
            });
        }catch(Exception e){
            //Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }



    }

    public void goToView(int position){
        Controller_interface.position=0;
        Controller_interface.detail_id=id[position];
        Intent i=new Intent(this,Controller_TransactionDetail.class);
        startActivity(i);
    }

    public void click_new(View view){

        Controller_interface.position=0;

        Intent i=new Intent(this,Controller_PresentTransactionNew.class);
        startActivity(i);
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


        }catch(Exception e){

        }

    }

    public String[] getValue(){


        int count=getTotalCount();
        if(count!=0) {
            yogesh = new String[count];
            int i = 0;


            try {

                String query = "SELECT * FROM vendor_transaction WHERE session='"+session_global+"'";
                dbhandler = new Database(this, null, null, 1);
                db = dbhandler.getDb();
                Cursor c = db.rawQuery(query, null);
                c.moveToLast();
                while (!c.isBeforeFirst()) {
                    int vendor_id = c.getInt(c.getColumnIndex("vendor_id"));
                    String date = c.getString(c.getColumnIndex("date"));
                    int typee = c.getInt(c.getColumnIndex("type"));
                    String amount = c.getString(c.getColumnIndex("amount"));
                    String remarks = c.getString(c.getColumnIndex("remarks"));

                    totalamount += Integer.parseInt(amount);

                    id[i] = c.getInt(c.getColumnIndex("id"));

                    yogesh[i] = "Agent: " + getAgent(vendor_id) + "\nType: " + getType(typee) + "\nAmount: " + amount;
                    i++;
                    c.moveToPrevious();
                }

            } catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }

        return yogesh;
    }

    public int getTotalCount(){
        int i=0;
        String query = "SELECT * FROM vendor_transaction WHERE session='"+session_global+"'";
        dbhandler = new Database(this, null, null, 1);
        db = dbhandler.getDb();
        Cursor c = db.rawQuery(query, null);
        c.moveToLast();
        while (!c.isBeforeFirst()) {
            i++;
            c.moveToPrevious();
        }
        return i;

    }

    public String getDate(){
        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);
        return thisDate;

    }

    public void readDatabases(){
        Database dbhandler=new Database(this,null,null,1);
        SQLiteDatabase db=dbhandler.getDb();
        int amount=0;
        try {
            String query = "SELECT * FROM start_transaction" ;
            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();
            date_global=c.getString(c.getColumnIndex("date"));
            initial_global=c.getString(c.getColumnIndex("initial"));
            session_global=c.getString(c.getColumnIndex("session"));

        }catch(Exception e){

        }

    }

    public String getType(int type){
       String result="";
        if(type==0){
            result="Normal Income";
        }else if(type==1){
            result="Normal Expenses";
        }else if(type==2){
            result="Paid Due Amount";
        }else if(type==3){
            result="Received Due Amount";
        }else if(type==4){
            result="Sold Items in Due";
        }else if(type==5){
            result="Received Items in Due";
        }
        return result;

    }

    public String getAgent(int id){
        int i=0;
        String result="Others";
        try {
            dbhandler = new Database(this, null, null, 1);
            db = dbhandler.getDb();

            String query = "SELECT * from vendor_detail where id="+id;
            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();
            String name=c.getString(c.getColumnIndex("name"));
            String address=c.getString(c.getColumnIndex("address"));
            int idd=c.getInt(c.getColumnIndex("id"));

            result=name+" (id: "+id+", Address: "+address+")";
        }catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).toString();
        }
        return result;

    }

    public void click_Back(View view){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }







}
