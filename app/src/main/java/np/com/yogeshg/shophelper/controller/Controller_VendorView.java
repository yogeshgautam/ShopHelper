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
 * Created by yogesh on 12/1/2017.
 */
public class Controller_VendorView extends Activity {
    ListView listView;
    String data[],forlist[];
    int idd[];

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.vendor_view_vendor);

        try {

            listView = (ListView) findViewById(R.id.listView2);

            Database_DetailGetValue todo=new Database_DetailGetValue(this);
            int count=todo.getNumberOfData();
            forlist = new String[count];
            forlist=readDatabase();
            //Toast.makeText(this,"yogesh is back",Toast.LENGTH_LONG).show();
            ListAdapter yogeshAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, forlist) {
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
        try {
            Controller_interface.id = idd[position];
            //Toast.makeText(this, " " + idd[position], Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, Controller_VendorTransaction.class);
            startActivity(i);
        }catch (Exception e){
            Toast.makeText(this,e.toString()+"dipesh",Toast.LENGTH_LONG).show();
        }

    }

    public String[] readDatabase(){
        Database_DetailGetValue todo=new Database_DetailGetValue(this);
        int count=todo.getNumberOfData();
        data=new String[count];
        idd=new int[count];
        Database dbhandler=new Database(this,null,null,1);
        SQLiteDatabase db=dbhandler.getDb();
        int i=0;
        String query = "SELECT * from vendor_detail";
        Cursor c=db.rawQuery(query,null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            String name=c.getString(c.getColumnIndex("name"));
            String address=c.getString(c.getColumnIndex("address"));
            int id=c.getInt(c.getColumnIndex("id"));
            data[i]="id: "+id+"\nName: "+name+"\nAddress: "+address;
            idd[i]=id;
            i++;
            c.moveToNext();
        }
        return data;
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
