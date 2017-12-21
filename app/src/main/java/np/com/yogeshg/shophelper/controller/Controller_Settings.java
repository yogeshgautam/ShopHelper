package np.com.yogeshg.shophelper.controller;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import np.com.yogeshg.shophelper.MainActivity;
import np.com.yogeshg.shophelper.R;
import np.com.yogeshg.shophelper.database.Database;

/**
 * Created by yogesh on 12/17/2017.
 */
public class Controller_Settings extends Activity {
    EditText email;

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.settings_main);
        email=(EditText)findViewById(R.id.editText8);
        readDatabase();

    }

    public void readDatabase(){
        Database dbhandler=new Database(this,null,null,1);
        SQLiteDatabase db=dbhandler.getDb();
        int amount=0;
        try {
            String query = "SELECT * FROM start_transaction" ;
            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();
            String email1=c.getString(c.getColumnIndex("email"));
            email.setText(email1);

        }catch(Exception e){

        }

    }

    public void click_save(View v){
        String email2=email.getText().toString();
        Database dbhandler=new Database(this,null,null,1);
        SQLiteDatabase db=dbhandler.getDb();
        String query="UPDATE start_transaction SET email='"+email2+"'";
        db.execSQL(query);
        Toast.makeText(this,"Email Updated Successfully",Toast.LENGTH_LONG).show();
    }


    public void click_Back(View v){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
