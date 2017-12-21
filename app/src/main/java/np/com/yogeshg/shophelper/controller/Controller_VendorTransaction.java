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
import android.widget.TextView;
import android.widget.Toast;

import np.com.yogeshg.shophelper.Controller_interface;
import np.com.yogeshg.shophelper.R;
import np.com.yogeshg.shophelper.database.Database;
import np.com.yogeshg.shophelper.database.Database_DetailGetValue;

/**
 * Created by yogesh on 12/3/2017.
 */
public class Controller_VendorTransaction extends Activity {
    ListView listView;
    String data[],for_list[];
    int idd;
    int paid,received;
    TextView paid_text,received_text,total_text;

    int ourid[];


    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.vendor_view_vendor_detail);
        listView = (ListView) findViewById(R.id.listView48);
        paid=0;
        received=0;
        paid_text=(TextView)findViewById(R.id.textView2);
        received_text=(TextView)findViewById(R.id.textView3);
        total_text=(TextView)findViewById(R.id.textView4);
        try {

            int count=countData();
            ourid=new int[count];
            for_list=new String[count];
            try {

                    for_list = readDatabase();

            }catch(Exception e){
                Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
            }




            ListAdapter yogeshAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, for_list) {
            };
            listView.setAdapter(yogeshAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    goToView(position);
                }
            });

        }catch(Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }


    }

    public void goToView(int position){
        Controller_interface.detail_id=ourid[position];
        Controller_interface.position=1;
        //Toast.makeText(this,ourid[position]+"",Toast.LENGTH_LONG).show();


        Intent i=new Intent(this,Controller_TransactionDetail.class);
        startActivity(i);
    }

    public int countData(){
        String query="SELECT * FROM vendor_transaction WHERE vendor_id = "+Controller_interface.id;
        Database dbhandler=new Database(this,null,null,1);

        int i=0;

        SQLiteDatabase db = dbhandler.getDb();
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            i++;
            c.moveToNext();
        }
        return i;
    }

    public String[] readDatabase(){

        int count=countData();
        data=new String[count];
        try {
            //Toast.makeText(this,Controller_interface.id,Toast.LENGTH_LONG).show();
        String query="SELECT * FROM vendor_transaction WHERE vendor_id = "+Controller_interface.id;
        Database dbhandler=new Database(this,null,null,1);

        int i=0;

            SQLiteDatabase db = dbhandler.getDb();
            Cursor c = db.rawQuery(query, null);
            c.moveToLast();
            while (!c.isBeforeFirst()) {
                String date = c.getString(c.getColumnIndex("date"));
                int type = c.getInt(c.getColumnIndex("type"));
                String amount = c.getString(c.getColumnIndex("amount"));
                idd=c.getInt(c.getColumnIndex("vendor_id"));
                requestTotal(type,amount);
                int id=c.getInt(c.getColumnIndex("id"));

                data[i] = "Date: " + date + "\nType: " + getType(type) + "\nAmount: " + amount;
                ourid[i]=id;
                i++;
                c.moveToPrevious();


            }
            paid_text.setText("Paid: "+paid);
            received_text.setText("Received: "+received);
            total_text.setText("\nTotal: "+(received-paid));
        }catch(Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
        return data;

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

    public void requestTotal(int type,String amount){
        int a=1;

        if(type==1||type==2||type==4){
            paid+=Integer.parseInt(amount);

        }
        if(type==0||type==3||type==5){
            received+=Integer.parseInt(amount);
        }
    }



    public void click_viewProfile(View view){
        Intent i=new Intent(this,Controller_VendorDetail.class);
        startActivity(i);

    }

    public void click_makeNewTransaction(View view){
        Controller_interface.position=1;
        Intent i=new Intent(this,Controller_PresentTransactionNew.class);
        startActivity(i);
    }

    public void click_Back(View view){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,Controller_VendorView.class);
        startActivity(i);
    }
}
