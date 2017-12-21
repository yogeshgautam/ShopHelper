package np.com.yogeshg.shophelper.dbo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import np.com.yogeshg.shophelper.database.Database;

/**
 * Created by yogesh on 12/10/2017.
 */
public class dbo_PreviousTransaction {
    String date,income,expenses,items_bought_due,items_sold_due,duepaid,duereceived;
    Context con;


    public dbo_PreviousTransaction(Context c){
        con=c;

    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public void setExpenses(String expenses) {
        this.expenses = expenses;
    }

    public void setItems_bought_due(String items_bought_due) {
        this.items_bought_due = items_bought_due;
    }

    public void setItems_sold_due(String items_sold_due) {
        this.items_sold_due = items_sold_due;
    }

    public void setDuepaid(String duepaid) {
        this.duepaid = duepaid;
    }

    public void setDuereceived(String duereceived) {
        this.duereceived = duereceived;
    }

    public void saveToDatabase(){
        Database dbhandler=new Database(con,null,null,1);
        SQLiteDatabase db=dbhandler.getDb();
        try {
            String query = "insert into previous_transaction(date,income,expenses,item_bought_due,items_sold_due,duepaid,duereceived) values('" + date + "','" + income + "','" + expenses + "','"+items_bought_due+"','" +items_sold_due+"','"+duepaid+"','"  + duereceived + "');";
            db.execSQL(query);
            Toast.makeText(con,"New Session Started",Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(con,e.toString(),Toast.LENGTH_LONG).show();

        }

    }
}
