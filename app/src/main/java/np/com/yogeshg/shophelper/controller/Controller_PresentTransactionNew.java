package np.com.yogeshg.shophelper.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import np.com.yogeshg.shophelper.Controller_interface;
import np.com.yogeshg.shophelper.R;
import np.com.yogeshg.shophelper.database.Database_DetailGetValue;
import np.com.yogeshg.shophelper.dbo.dbo_VendorDetail;
import np.com.yogeshg.shophelper.dbo.dbo_VendorTransaction;
import np.com.yogeshg.shophelper.model.ModelVendorTransaction;

/**
 * Created by yogesh on 12/3/2017.
 */
public class Controller_PresentTransactionNew extends Activity {

    Spinner type,agent;
    EditText amount,remarks;

    int[] vendor_id;

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.present_transaction_new);

        amount=(EditText)findViewById(R.id.editText6);
        remarks=(EditText)findViewById(R.id.editText5);


        spinnercare();
        spinnercare2();






    }

    public void click_Save(View v){
        try {
            if (amount.getText().toString().length() > 0) {

                ModelVendorTransaction model = new ModelVendorTransaction();
                int position = agent.getSelectedItemPosition();

                if(position==0){
                    model.setId(0);
                }else {
                    model.setId(vendor_id[position-1]);
                    //Toast.makeText(this,vendor_id[position-1]+"tori",Toast.LENGTH_LONG).show();
                }

                position = type.getSelectedItemPosition();
                model.setType(position);
                model.setAmount(amount.getText().toString());
                model.setRemarks(remarks.getText().toString());
                model.setDate(getDate());

                dbo_VendorTransaction dbo = new dbo_VendorTransaction(this, model);
                dbo.saveToDatabase();
                onBackPressed();
                // Toast.makeText(this,model.getId()+"\n"+model.getType()+"\n"+model.getAmount()+"\n"+model.getDate()+"\n"+model.getRemarks(),Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Please enter Amount", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            Toast.makeText(this,e.toString()+"yogesh",Toast.LENGTH_LONG).show();
        }

    }
    public String getDate(){
        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);
        return thisDate;

    }

    public void spinnercare2(){
        agent=(Spinner)findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter;
        List<String> list;
        list = new ArrayList<String>();
        try {

            Database_DetailGetValue value = new Database_DetailGetValue(this);
            int count = value.getNumberOfData();
            String[] result = value.getNames();
            vendor_id=value.getId();
            list.add("Others");

            for (int i = 0; i <count; i++) {
                list.add("name:"+result[i]+"::id-"+vendor_id[i]);
            }

            adapter = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_spinner_item, list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            agent.setAdapter(adapter);
        }catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    public void spinnercare(){
        type = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter;
        List<String> list;

        list = new ArrayList<String>();
        list.add("Normal Income");
        list.add("Normal Expenses");
        list.add("Pay Due Amount");
        list.add("Receive Due Amount");
        list.add("Sell Items in Due");
        list.add("Receive Items in DUe");

        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapter);
    }

    public void click_Back(View view){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if(Controller_interface.position==0) {
            Intent i = new Intent(this, Controller_PresentTransactionMain.class);
            startActivity(i);
        }else{
            Intent i = new Intent(this, Controller_VendorTransaction.class);
            startActivity(i);
        }
    }
}
