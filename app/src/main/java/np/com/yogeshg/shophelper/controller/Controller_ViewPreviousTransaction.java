package np.com.yogeshg.shophelper.controller;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import np.com.yogeshg.shophelper.Controller_interface;
import np.com.yogeshg.shophelper.R;
import np.com.yogeshg.shophelper.database.Database;
import np.com.yogeshg.shophelper.database.Database_DetailGetValue;

/**
 * Created by yogesh on 12/3/2017.
 */
public class Controller_ViewPreviousTransaction extends Activity {
    ListView listView;
    String data[],for_list[];
    int idd[];

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.previous_transaction);

        try {

            listView = (ListView) findViewById(R.id.listView4);
            Database_DetailGetValue todo=new Database_DetailGetValue(this);
            int count=todo.getNumberOfPreviousTransaction();
            for_list=new String[count];
            for_list = readDatabase();
            ListAdapter yogeshAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, for_list) {
            };
            listView.setAdapter(yogeshAdapter);


        }catch(Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                goToView(position);
            }
        });




    }

    public void goToView(int position){
        Controller_interface.previous_id=idd[position];
        Intent i=new Intent(this,Controller_PreviousTransactionDetail.class);
        startActivity(i);
    }

    public String[] readDatabase(){
        Database_DetailGetValue todo=new Database_DetailGetValue(this);
        int count=todo.getNumberOfPreviousTransaction();
        data=new String[count];
        idd=new int[count];
        Database dbhandler=new Database(this,null,null,1);
        SQLiteDatabase db=dbhandler.getDb();
        int i=0;
        String query = "SELECT * from previous_transaction";
        Cursor c=db.rawQuery(query,null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            String date=c.getString(c.getColumnIndex("date"));
            String income=c.getString(c.getColumnIndex("income"));
            String expenses=c.getString(c.getColumnIndex("expenses"));


            int id=c.getInt(c.getColumnIndex("id"));
            data[i]="id: "+id+"\nDate: "+date;
            idd[i]=id;
            i++;
            c.moveToNext();
        }
        return data;
    }

    public void click_Back(View view){
        onBackPressed();
    }
}
