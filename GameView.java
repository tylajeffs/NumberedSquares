package edu.byuh.cis.cs203.numberedsquares;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import edu.byuh.cis.cs203.numberedsquares.NumberedSquare;

/**
 * Created by tylajeffs
 */

public class GameView extends View
{

    //create fields
    private boolean calcDone;
    private float w,h;
    private int howMany;

    //list of squares
    private List<NumberedSquare> squares;

    /**
     * GameView constructor. Just initializes a few variables
     * @param context - required
     */
    public GameView(Context context)
    {
        super(context);

        //set how many squares you want
        howMany = 7;

        //set the calculations done to false
        calcDone = false;
        squares = new ArrayList<>();
    }

    /**
     * All the rendering happens here. The first time onDraw is called, we also do some
     * one-time initialization of the graphics objects (since the width and height of
     * the screen are not known until onDraw is called).
     * @param c
     */
    @Override
    public void onDraw(Canvas c)
    {
        if (!calcDone)
        {

            //get the width and height
            w = c.getWidth();
            h = c.getHeight();

            //create the squares
            createSquares(howMany);

            //set calcDone to true
            calcDone = true;
        }

        //set the color
        c.drawColor(Color.CYAN);

        //draw each of the squares in the list
        for (NumberedSquare ns : squares)
        {
            //draw the square
            ns.draw(c);
        }
    }





    /**
     * Helper method for creating five NumberedSquare objects
     * @param num the number of squares you want created
     */
    private void createSquares(int num)
    {
        //clear the list of squares
        squares.clear();

        //reset the counter back to 1
        NumberedSquare.resetCounter();

        //create 5 new numbered squares
        for (int i=0; i<num; i++)
        {

            //create new numbered square and add it to the squares list
            NumberedSquare ns = new NumberedSquare(w, h);

            //create a boolean to see if they overlap or not - assume they don't overlap
            boolean overlaps = false;

            for(NumberedSquare square:squares)
            {

                if(ns.overlaps(square))
                {
                    //set overlaps to true
                    overlaps = true;

                    //decrease the counter to show that it got deleted - need to keep numbers correct
                    ns.decreaseCounter();

                    //subtract 1 from i so the code will make a new square
                    //to replace the one that overlapped
                    i--;

                    //break the loop and try again
                    break;
                }

            }

            //if it doesn't overlap, add it to the squares list
            if(overlaps == false)
            {
                //add ns to the squares list
                squares.add(ns);
            }

        }

    }






    /**
     * this method gets called automatically whenever a user taps the screen
     *
     * @param m - mandatory parameter, motionevent
     * @return always returns true
     */

    @Override
    public boolean onTouchEvent(MotionEvent m)
    {

        //figure out if screen was tapped (based on release)
        if(m.getAction() == MotionEvent.ACTION_UP)
        {
            //send message to Logcat saying the screen was tapped
            Log.d("log", "you tapped the screen");


            //create the squares again in new locations
            createSquares(howMany);

            //redraw the squares in the list using invalidate method
            invalidate();


        }

        //always returns true
        return true;
    }

}
