package edu.byuh.cis.cs203.numberedsquares;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;

/**
 * created by tylajeffs
 */

public class MainActivity extends Activity {

    //fields
    private GameView gv;

    /**
     * It all starts here
     * @param savedInstanceState - passed in by OS. Ignore for now.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        //create new view
        gv = new GameView(this);
        setContentView(gv);
    }


}
