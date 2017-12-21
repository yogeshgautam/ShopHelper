package np.com.yogeshg.shophelper.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import np.com.yogeshg.shophelper.Controller_interface;
import np.com.yogeshg.shophelper.R;
import np.com.yogeshg.shophelper.database.Database;

/**
 * Created by yogesh on 12/3/2017.
 */
public class Controller_VendorDetail extends Activity {

    TextView name,address,phone,remarks;
    Button delete;
    //Context con=this.getApplicationContext();

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.vendor_view_detail);
        name=(TextView)findViewById(R.id.textView24);
        address=(TextView)findViewById(R.id.textView25);
        phone=(TextView)findViewById(R.id.textView26);
        remarks=(TextView)findViewById(R.id.textView27);
        delete=(Button)findViewById(R.id.button2);

        //Toast.makeText(this,"viewing profile",Toast.LENGTH_LONG).show();

        readDatabase();
    }

    public void readDatabase()
    {
        String query="SELECT * FROM vendor_detail where id="+ Controller_interface.id;
        Database dbhandler=new Database(this,null,null,1);
        SQLiteDatabase db=dbhandler.getDb();
        Cursor c=db.rawQuery(query,null);
        c.moveToFirst();
        String name1=c.getString(c.getColumnIndex("name"));
        String address1=c.getString(c.getColumnIndex("address"));
        String phone1=c.getString(c.getColumnIndex("phone"));
        String remarks1=c.getString(c.getColumnIndex("remarks"));

        name.setText(name1);
        address.setText(address1);
        phone.setText(phone1);
        remarks.setText(remarks1);



    }

    public void click_Save(View v){
        Intent i=new Intent(this,Controller_VendorEdit.class);
        startActivity(i);
    }

    public void click_Delete(View v){



                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("Are you Sure you want to Delete\n Warning:this is permanent.");
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteFromDatabase();
                    }
                });

                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();




    }

    public void deleteFromDatabase(){
        try {
                Database dbhandler=new Database(this,null,null,1);
                SQLiteDatabase db=dbhandler.getDb();
                String query = "DELETE FROM vendor_detail WHERE id=" + Controller_interface.id + ";";
                db.execSQL(query);
                Toast.makeText(this, "Data deleted successfully", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(this, "yogesh\n" + e.toString(), Toast.LENGTH_LONG).show();
            }

    }

    public void click_Back(View view){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,Controller_VendorTransaction.class);
        startActivity(i);
    }
}
