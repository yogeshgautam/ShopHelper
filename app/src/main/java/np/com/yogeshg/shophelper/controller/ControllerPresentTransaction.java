package np.com.yogeshg.shophelper.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by yogesh on 12/1/2017.
 */
public class ControllerPresentTransaction extends Activity {

    public void onCreate(Bundle b)
    {
        super.onCreate(b);


    }

    public void click_Back(View view){
        onBackPressed();
    }
}
