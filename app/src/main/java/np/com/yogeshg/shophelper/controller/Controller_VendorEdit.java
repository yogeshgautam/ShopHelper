package np.com.yogeshg.shophelper.controller;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import np.com.yogeshg.shophelper.Controller_interface;
import np.com.yogeshg.shophelper.R;
import np.com.yogeshg.shophelper.database.Database;
import np.com.yogeshg.shophelper.dbo.dbo_VendorDetail;
import np.com.yogeshg.shophelper.model.ModelVendorDetail;

/**
 * Created by yogesh on 12/8/2017.
 */
public class Controller_VendorEdit extends Activity{

    EditText name,address,phone,remarks;

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.vendor_edit);
        name=(EditText)findViewById(R.id.textView24);
        address=(EditText)findViewById(R.id.textView25);
        phone=(EditText)findViewById(R.id.textView26);
        remarks=(EditText)findViewById(R.id.textView27);

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
        ModelVendorDetail model=new ModelVendorDetail();
        model.setName(name.getText().toString());
        model.setAddress(address.getText().toString());
        model.setPhone(phone.getText().toString());
        model.setRemarks(remarks.getText().toString());

        dbo_VendorDetail dbo=new dbo_VendorDetail(this,model);
        dbo.updateDatabase(Controller_interface.id);

        Toast.makeText(this,"value Updated Successfully",Toast.LENGTH_LONG).show();
        onBackPressed();
    }

    public void click_Cancel(View v){
        onBackPressed();
    }
    public void click_Back(View view){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,Controller_VendorDetail.class);
        startActivity(i);
    }
}
