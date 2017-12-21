package np.com.yogeshg.shophelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import np.com.yogeshg.shophelper.controller.Controller_NewSession;
import np.com.yogeshg.shophelper.controller.Controller_PresentTransactionMain;
import np.com.yogeshg.shophelper.controller.Controller_Settings;
import np.com.yogeshg.shophelper.controller.Controller_SummaryMain;
import np.com.yogeshg.shophelper.controller.Controller_VendorMain;
import np.com.yogeshg.shophelper.controller.Controller_ViewPreviousTransaction;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click_presentTransaction(View view){
        Intent i=new Intent(this, Controller_PresentTransactionMain.class);
        startActivity(i);

    }

    public void click_TransactionSummary(View view){
        Intent i=new Intent(this, Controller_SummaryMain.class);
        startActivity(i);


    }

    public void click_previousTransactions(View view){
        Intent i=new Intent(this, Controller_ViewPreviousTransaction.class);
        startActivity(i);

    }

    public void click_Vendor(View view){
        Intent i=new Intent(this, Controller_VendorMain.class);
        startActivity(i);

    }

    public void click_newDay(View view){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you Sure you want to Start New Session\n Warning:this is permanent.");
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                delete();
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

    public void click_settings(View view){
        Intent i=new Intent(this, Controller_Settings.class);
        startActivity(i);

    }

    public void delete(){
        Intent i=new Intent(this, Controller_NewSession.class);
        startActivity(i);
    }
}
