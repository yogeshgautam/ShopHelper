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
 * Created by yogesh on 12/9/2017.
 */
public class Controller_TransactionDetail extends Activity {

    TextView id,date,type,amount,remarks;

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.vendor_make_new_transaction);
        id=(TextView)findViewById(R.id.id);
        date=(TextView)findViewById(R.id.date);
        type=(TextView)findViewById(R.id.type);
        amount=(TextView)findViewById(R.id.amount);
        remarks=(TextView)findViewById(R.id.remarks);
        try {
            updateValue();
        }catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }

    }
    public void updateValue(){
        Database dbhandler=new Database(this,null,null,1);
        SQLiteDatabase db=dbhandler.getDb();

        String query="SELECT * FROM vendor_transaction WHERE id="+ Controller_interface.detail_id;
        Cursor c=db.rawQuery(query,null);
        c.moveToFirst();
        int vendor_id1=c.getInt(c.getColumnIndex("vendor_id"));
        String date1=c.getString(c.getColumnIndex("date"));
        int type1=c.getInt(c.getColumnIndex("type"));
        String amount1=c.getString(c.getColumnIndex("amount"));
        String remarks1=c.getString(c.getColumnIndex("remarks"));
        c.close();
        id.setText(vendor_id1+"");
        date.setText(date1);
        type.setText(getType(type1));
        amount.setText(amount1);
        remarks.setText(remarks1);


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

    @Override
    public void onBackPressed() {
        if(Controller_interface.position==0){
            Intent i=new Intent(this,Controller_PresentTransactionMain.class);
            startActivity(i);
        }
        else{
            Intent i=new Intent(this,Controller_VendorTransaction.class);
            startActivity(i);
        }
    }
}
