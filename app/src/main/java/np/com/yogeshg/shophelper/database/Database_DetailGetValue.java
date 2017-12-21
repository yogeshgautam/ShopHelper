package np.com.yogeshg.shophelper.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by yogesh on 12/5/2017.
 */
public class Database_DetailGetValue  {

    Database dbhandler;
    SQLiteDatabase db;
    Context con;


    public Database_DetailGetValue(Context c){
        con=c;

    }

    public String[] getNames(){
        int i=0;
        int count=getNumberOfData();
        String[] result=new String[count];
        try{
        dbhandler=new Database(con,null,null,1);
        db=dbhandler.getDb();


        String query="SELECT * from vendor_detail";
        Cursor c=db.rawQuery(query,null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            result[i]=c.getString(c.getColumnIndex("name"));

            c.moveToNext();
            i++;
        }
    }catch (Exception e){
        Toast.makeText(con,e.toString()+"yogesh",Toast.LENGTH_LONG).toString();
    }

        return result;

    }

    public String getName(int id) {
        int i = 0;
        int count = getNumberOfData();
        String result="";
        try {
            dbhandler = new Database(con, null, null, 1);
            db = dbhandler.getDb();

            //Toast.makeText(con,"diwas k xa",Toast.LENGTH_LONG).show();
            String query = "SELECT * from vendor_detail where id='"+id+"'";
            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();

            result = c.getString(c.getColumnIndex("name"));
            //Toast.makeText(con,c.getString(c.getColumnIndex("name"))+"\nyogesh",Toast.LENGTH_LONG).show();



        } catch (Exception e) {
            Toast.makeText(con, e.toString() + "yogesh", Toast.LENGTH_LONG).toString();
        }
        return result;
    }

        public int[] getId(){
        int i=0;
        int count=getNumberOfData();
        int[] vendor_id=new int[count];
        try{
            dbhandler=new Database(con,null,null,1);
            db=dbhandler.getDb();


            String query="SELECT * from vendor_detail";
            Cursor c=db.rawQuery(query,null);
            c.moveToFirst();
            while(!c.isAfterLast()){

                vendor_id[i]=c.getInt(c.getColumnIndex("id"));
                c.moveToNext();
                i++;
            }
        }catch (Exception e){
            Toast.makeText(con,e.toString()+"yogesh getting ids\ntotal id: "+i,Toast.LENGTH_LONG).toString();
        }

        return vendor_id;

    }

    public  int getNumberOfData(){
        int i=0;
        try {
            dbhandler = new Database(con, null, null, 1);
            db = dbhandler.getDb();

            String query = "SELECT * from vendor_detail";
            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();
            while (!c.isAfterLast()) {
                i++;
                c.moveToNext();

            }
        }catch (Exception e){
            Toast.makeText(con,e.toString(),Toast.LENGTH_LONG).toString();
        }
        return i;

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //for table vendor_transaction

    public  int getNumberOfDataVendorTransaction(){
        int i=0;
        try {
            dbhandler = new Database(con, null, null, 1);
            db = dbhandler.getDb();

            String query = "SELECT * from vendor_transaction";
            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();
            while (!c.isAfterLast()) {
                i++;
                c.moveToNext();

            }
        }catch (Exception e){
            Toast.makeText(con,e.toString(),Toast.LENGTH_LONG).toString();
        }
        return i;

    }


    public  int getNumberOfPreviousTransaction(){
        int i=0;
        try {
            dbhandler = new Database(con, null, null, 1);
            db = dbhandler.getDb();

            String query = "SELECT * from previous_transaction";
            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();
            while (!c.isAfterLast()) {
                i++;
                c.moveToNext();

            }
        }catch (Exception e){
            Toast.makeText(con,e.toString(),Toast.LENGTH_LONG).toString();
        }
        return i;

    }

    public int[] getIdVendorTransaction(){
        int i=0;
        int count=getNumberOfData();
        int[] vendor_id=new int[count];
        try{
            dbhandler=new Database(con,null,null,1);
            db=dbhandler.getDb();


            String query="SELECT * from vendor_transaction";
            Cursor c=db.rawQuery(query,null);
            c.moveToFirst();
            while(!c.isAfterLast()){

                vendor_id[i]=c.getInt(c.getColumnIndex("id"));
                c.moveToNext();
                i++;
            }
        }catch (Exception e){
            Toast.makeText(con,e.toString()+"yogesh",Toast.LENGTH_LONG).toString();
        }

        return vendor_id;

    }
}
