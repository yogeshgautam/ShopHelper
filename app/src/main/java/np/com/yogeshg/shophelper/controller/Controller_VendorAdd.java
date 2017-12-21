package np.com.yogeshg.shophelper.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import np.com.yogeshg.shophelper.R;
import np.com.yogeshg.shophelper.dbo.dbo_VendorDetail;
import np.com.yogeshg.shophelper.model.ModelVendorDetail;

/**
 * Created by yogesh on 12/1/2017.
 */
public class Controller_VendorAdd extends Activity{

    EditText name,address,phone,remarks;

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.vendor_add_new_vendor);

        name=(EditText)findViewById(R.id.editText);
        address=(EditText)findViewById(R.id.editText2);
        phone=(EditText)findViewById(R.id.editText3);
        remarks=(EditText)findViewById(R.id.editText4);
    }

    public void click_save(View v){
        ModelVendorDetail model=new ModelVendorDetail();
        model.setName(name.getText().toString());
        model.setAddress(address.getText().toString());
        model.setPhone(phone.getText().toString());
        model.setRemarks(remarks.getText().toString());

        dbo_VendorDetail dbo=new dbo_VendorDetail(this,model);
        dbo.saveToDatabase();
        onBackPressed();


    }

    public void click_cancel(View v){
        onBackPressed();

    }
    public void click_Back(View view){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,Controller_VendorMain.class);
        startActivity(i);
    }
}
