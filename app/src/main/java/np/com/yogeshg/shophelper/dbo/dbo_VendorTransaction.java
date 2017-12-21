package np.com.yogeshg.shophelper.dbo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import np.com.yogeshg.shophelper.database.Database;
import np.com.yogeshg.shophelper.model.ModelVendorTransaction;

/**
 * Created by yogesh on 12/5/2017.
 */
public class dbo_VendorTransaction {

    int vendor_id;
    int type;
    int session;
    String date;
    String amount;
    String remarks;

    Context con;
    Database dbhandler;
    SQLiteDatabase db;

    public dbo_VendorTransaction(Context c, ModelVendorTransaction model){
        con=c;
        vendor_id=model.getId();
        type=model.getType();
        session=getSession();
        amount=model.getAmount();
        remarks=model.getRemarks();
        date=model.getDate();

    }



    public void saveToDatabase(){
        dbhandler=new Database(con,null,null,1);
        db=dbhandler.getDb();
        try {
            String query = "insert into vendor_transaction(vendor_id,date,type,session,amount,remarks) values('" + vendor_id + "','" + date + "','" + type + "','"+session+ "','"+amount+"','" + remarks + "');";
            db.execSQL(query);
            Toast.makeText(con,"Value Added Successfully",Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(con,e.toString(),Toast.LENGTH_LONG).show();

        }

    }

    public int getSession(){
        dbhandler=new Database(con,null,null,1);
        db=dbhandler.getDb();
        int session=0;
        try {
            String query="SELECT * FROM previous_transaction";
            Cursor c=db.rawQuery(query,null);
            c.moveToLast();


            session=c.getInt(c.getColumnIndex("id"));
            Toast.makeText(con,session+"",Toast.LENGTH_LONG).show();

        }catch(Exception e){
            //Toast.makeText(con,e.toString()+"yogu exception",Toast.LENGTH_LONG).show();

        }
        return (session+1);
    }





}
