package edu.byuh.cis.cs203.numberedsquares;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;


/**
 * Created by tylajeffs
 */

public class NumberedSquare
{

    private float size;

    //id of the square (what number it is on)
    private int id;

    //counts how many squares have been made
    public static int counter = 1;

    private RectF bounds;
    private float screenWidth, screenHeight;
    private Paint textPaint;
    private Paint squarePaint;







    /**
     * Constructor for NumberedSquare. This constructor accepts two parameters,
     * representing the width and height of the display. The constructor finds the
     * smaller of these two values (typically, width is smaller for portrait orientation;
     * height is smaller for landscape) and bases the size of the square on that.
     * @param w - the width of the screen
     * @param h - the height of the screen
     */
    public NumberedSquare(float w, float h)
    {
        //set screen width and height
        screenWidth = w;
        screenHeight = h;

        //figure out the orientation of the device by seeing whether the length or width is larger
        float lesser = Math.min(w,h);

        //set the square size to be 1/8th of the shortest orientation
        size = lesser/8f;

        //set the square id to be equal to which square it is, then increase the counter
        id = counter;
        counter++;

        //create random x and y's for the box to be drawn at
        float x = (float)(Math.random()*(screenWidth-size));
        float y = (float)(Math.random()*(screenHeight-size));

        //create a rectF object using the random coordinates
        bounds = new RectF(x, y, x+size, y+size);

        //create new paint objects for the square and the text
        squarePaint = new Paint();
        textPaint = new Paint();

        //set square attributes
        squarePaint.setStyle(Paint.Style.STROKE);
        squarePaint.setStrokeWidth(lesser/100f);
        squarePaint.setColor(Color.BLACK);

        //set text attributes
        textPaint.setTextSize(65);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }





    /**
     * method to decrease the counter by 1
     */
    public void decreaseCounter()
    {
        //decrease counter by 1
        counter--;
    }




    /**
     * Render the square and its label into the display
     * @param c the Canvas object, representing the dispaly area
     */
    public void draw(Canvas c)
    {
        //draw the rectangle using the rectf bounds object
        c.drawRect(bounds, squarePaint);

        //draw the text at the center of the square
        c.drawText(""+id, bounds.centerX(), bounds.centerY()+size/7, textPaint);
    }





    /**
     * Static convenience method to ensure that the ID numbers don't grow too large.
     */
    public static void resetCounter()
    {
        //reset the counter back to 1
        counter = 1;
    }




    /**
     * Method to see if the squares overlap
     * @param other the numbered square object you want to test against
     */
    public boolean overlaps(NumberedSquare other)
    {
        //return true if the squares overlap, return false if they don't
        return RectF.intersects(this.bounds, other.bounds);
    }


}
