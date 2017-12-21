package np.com.yogeshg.shophelper.dbo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import np.com.yogeshg.shophelper.database.Database;
import np.com.yogeshg.shophelper.model.ModelVendorDetail;

/**
 * Created by yogesh on 12/1/2017.
 */
public class dbo_VendorDetail {

    Context con;
    String name;
    String address;
    String phone;
    String remarks;

    Database dbhandler;
    SQLiteDatabase db;

    public dbo_VendorDetail(Context c, ModelVendorDetail model){
        con=c;
        name=model.getName();
        address=model.getAddress();
        phone=model.getPhone();
        remarks=model.getRemarks();

    }

    public void saveToDatabase(){
        dbhandler=new Database(con,null,null,1);
        db=dbhandler.getDb();
        try {
            String query = "insert into vendor_detail(name,address,phone,remarks) values('" + name + "','" + address + "','" + phone + "','" + remarks + "');";
            db.execSQL(query);
            Toast.makeText(con,"Value Added to Database",Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(con,e.toString(),Toast.LENGTH_LONG).show();

        }
    }

    public void updateDatabase(int id){
        dbhandler=new Database(con,null,null,1);
        db=dbhandler.getDb();
        try {
            String query="UPDATE vendor_detail SET name='"+name+"',address='"+address+"',phone='"+phone+"',remarks='"+remarks+"' WHERE id="+id;
            db.execSQL(query);
            Toast.makeText(con,"Value Added to Database",Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(con,e.toString(),Toast.LENGTH_LONG).show();

        }


    }


}
