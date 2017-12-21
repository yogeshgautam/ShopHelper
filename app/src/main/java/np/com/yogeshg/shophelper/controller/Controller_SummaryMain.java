package np.com.yogeshg.shophelper.controller;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

import np.com.yogeshg.shophelper.R;
import np.com.yogeshg.shophelper.database.Database;
import np.com.yogeshg.shophelper.database.Database_DetailGetValue;
import np.com.yogeshg.shophelper.dbo.dbo_VendorDetail;

/**
 * Created by yogesh on 12/3/2017.
 */
public class Controller_SummaryMain extends Activity {

    TextView initialamount,income,expenses,boughtdue,solddue,duepaid,duereceived;
    TextView total;
    Database dbhandler;
    SQLiteDatabase db;
    String date_global,initial_global,email_global;
    int session_global;

    String email1,date1;
    int session1;

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.summary_main);

        initialamount=(TextView)findViewById(R.id.textView14);
        income=(TextView)findViewById(R.id.textView15);
        expenses=(TextView)findViewById(R.id.textView16);
        boughtdue=(TextView)findViewById(R.id.textView17);
        solddue=(TextView)findViewById(R.id.textView18);
        duepaid=(TextView)findViewById(R.id.textView22);
        duereceived=(TextView)findViewById(R.id.textView23);
        total=(TextView)findViewById(R.id.textView19);

        dbhandler=new Database(this,null,null,1);
        readDatabase();
        initialamount.setText(initial_global);
        //Toast.makeText(this,getTotalIncome(0)+"\n"+getTotalIncome(1)+"\n"+getTotalIncome(2)+"\n"+getTotalIncome(3)+"\n"+getTotalIncome(4)+"\n"+getTotalIncome(5)+"yogesh",Toast.LENGTH_LONG).show();

        try {

            income.setText(getTotalIncome(0)+"");
            expenses.setText(getTotalIncome(1)+"");
            duepaid.setText(getTotalIncome(2)+"");
            duereceived.setText(getTotalIncome(3)+"");
            solddue.setText(getTotalIncome(4)+"");
            boughtdue.setText(getTotalIncome(5)+"");

            int total1=Integer.parseInt(initial_global)+getTotalIncome(0)-getTotalIncome(1)+getTotalIncome(3)-getTotalIncome(2);
            total.setText("Final Amount on Box:  "+total1);



        }catch(Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }


    }

    public int getTotalIncome(int type){
        db=dbhandler.getDb();
        int amount=0;
        try {
            String query = "SELECT * FROM vendor_transaction where type=" + type+" AND session='"+session_global+"'";
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
            email_global=c.getString(c.getColumnIndex("email"));


        }catch(Exception e){

        }

    }

    public void click_backup(View v){
        sendEmail("yogesh\ngautam\tis don");

    }

    public void sendEmail(String body){
        Intent emailIntent=new Intent(Intent.ACTION_SEND);

        emailIntent.setType("text/html");
        emailIntent.putExtra(Intent.EXTRA_EMAIL,email_global);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Backup of date:"+date_global+"  Session:"+session_global);
        //Toast.makeText(this,getResult(),Toast.LENGTH_LONG).show();
        emailIntent.putExtra(Intent.EXTRA_TEXT,getResult());


        try{
            startActivity(Intent.createChooser(emailIntent,"Send Mail..."));
            finish();
        }catch(Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }

    }

    public String getResult(){
        String result="<html><body><table border=\"1\"><tr><th>Agent</th><th>type</th><th>amount</th><th>remarks</th>\n";
        db=dbhandler.getDb();

        try {
            String query = "SELECT * FROM vendor_transaction where session='"+session_global+"'";
            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();
            while (!c.isAfterLast()) {
                int vendor_id=c.getInt(c.getColumnIndex("vendor_id"));
                int type=c.getInt(c.getColumnIndex("type"));
                String amount=c.getString(c.getColumnIndex("amount"));
                String remarks=c.getString(c.getColumnIndex("remarks"));
                if(getVendorName(vendor_id)==""){
                    result+="<tr><td>  "+"Others"+"   </td><td>   "+getType(type)+"</td><td>     "+amount+"   </td><td>"+remarks+"   </td></tr>\n";

                }
                else {
                    result += "<tr><td>  " + getVendorName(vendor_id) + "(" + vendor_id + ")" + "   </td><td>   " + getType(type) + "</td><td>     " + amount + "   </td><td>" + remarks + "   </td></tr>\n";
                }
                c.moveToNext();
            }
            result+="</table></body></html>";
        }catch(Exception e){

        }
        return result;

    }

    public String getVendorName(int id){
        Database_DetailGetValue data=new Database_DetailGetValue(this);
        String result=data.getName(id);
        return result;

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






    public void click_Back(View view){
        onBackPressed();
    }

}
