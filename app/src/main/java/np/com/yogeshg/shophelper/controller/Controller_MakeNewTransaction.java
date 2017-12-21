package np.com.yogeshg.shophelper.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import np.com.yogeshg.shophelper.R;

/**
 * Created by yogesh on 12/3/2017.
 */
public class Controller_MakeNewTransaction extends Activity {
    Spinner spinner;

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.vendor_make_new_transaction);
        spinnercare();
    }

    public void spinnercare(){
        spinner = (Spinner) findViewById(R.id.type);
        ArrayAdapter<String> adapter;
        List<String> list;

        list = new ArrayList<String>();
        list.add("Pay Due Amount");
        list.add("Receive Due Amount");
        list.add("Sell Items in Due");
        list.add("Receive Item in Due");


        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void click_Back(View view){
        onBackPressed();
    }
}
