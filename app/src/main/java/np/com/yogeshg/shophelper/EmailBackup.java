package np.com.yogeshg.shophelper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

/**
 * Created by yogesh on 12/17/2017.
 */
public class EmailBackup {
    Context con;
    int id;
    String email;

    public EmailBackup(Context c,int session_id,String emaal){
        con=c;
        id=session_id;
        email=emaal;
    }



}
