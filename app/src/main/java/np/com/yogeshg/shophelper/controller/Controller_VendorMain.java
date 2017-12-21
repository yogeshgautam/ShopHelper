package np.com.yogeshg.shophelper.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import np.com.yogeshg.shophelper.R;

/**
 * Created by yogesh on 12/1/2017.
 */
public class Controller_VendorMain extends Activity {

    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.vendor_main);

    }

    public void click_vendor_new(View view){

        Intent i=new Intent(this,Controller_VendorAdd.class);
        startActivity(i);
    }

    public void click_vendor_view(View view){
        Intent i=new Intent(this,Controller_VendorView.class);
        startActivity(i);
    }

    public void click_Back(View view){
        onBackPressed();
    }

}
