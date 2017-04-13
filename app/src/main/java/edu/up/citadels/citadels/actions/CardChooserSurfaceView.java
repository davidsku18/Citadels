package edu.up.citadels.citadels.actions;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.ArrayList;

import edu.up.citadels.R;

/**
 * Created by kurtisdavidson on 4/9/17.
 */

public class CardChooserSurfaceView extends SurfaceView
{
    private Paint defaultPaint = new Paint();
    ArrayList<Bitmap> characterCards;


        Bitmap assassin = BitmapFactory.decodeResource(getResources(), R.drawable.assasin);
        Bitmap thief = BitmapFactory.decodeResource(getResources(), R.drawable.thief);
        Bitmap magician = BitmapFactory.decodeResource(getResources(), R.drawable.magician);
        Bitmap king = BitmapFactory.decodeResource(getResources(), R.drawable.king);
        Bitmap bishop = BitmapFactory.decodeResource(getResources(), R.drawable.bishop);
        Bitmap merchant = BitmapFactory.decodeResource(getResources(), R.drawable.merchant);
        Bitmap architect = BitmapFactory.decodeResource(getResources(), R.drawable.architect);
        Bitmap warlord = BitmapFactory.decodeResource(getResources(), R.drawable.warlord);

        Bitmap assassinScaled = Bitmap.createScaledBitmap(assassin, this.getWidth()/4, this.getHeight()/2, true);
        Bitmap thiefScaled = Bitmap.createScaledBitmap(thief, this.getWidth()/4, this.getHeight()/2, true);
        Bitmap magicianScaled = Bitmap.createScaledBitmap(magician, this.getWidth()/4, this.getHeight()/2, true);
        Bitmap kingScaled = Bitmap.createScaledBitmap(king, this.getWidth()/4, this.getHeight()/2, true);
        Bitmap bishopScdled = Bitmap.createScaledBitmap(bishop, this.getWidth()/4, this.getHeight()/2, true);
        Bitmap merchantScaled = Bitmap.createScaledBitmap(merchant, this.getWidth()/4, this.getHeight()/2, true);
        Bitmap architectScaled = Bitmap.createScaledBitmap(architect, this.getWidth()/4, this.getHeight()/2, true);
        Bitmap warlordScaled = Bitmap.createScaledBitmap(warlord, this.getWidth()/4, this.getHeight()/2, true);

    /*
        characterCards.add(assassinScaled);
        characterCards.add(thiefScaled);
        characterCards.add(magicianScaled);
        characterCards.add(kingScaled);
        characterCards.add(bishopScdled);
        characterCards.add(merchantScaled);
        characterCards.add(architectScaled);
        characterCards.add(warlordScaled);
        */


    private void init(){
        setWillNotDraw(false);
    }

    public CardChooserSurfaceView(Context context) {
        super(context);
        init();
    }

    public CardChooserSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CardChooserSurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        //addToCharacterCardsArray();
        defaultPaint.setColor(0xFFFFFFFF);
        /*
        for (Bitmap c: characterCards) {
            canvas.drawBitmap(c, 0, 0, defaultPaint);
        }
        */
        canvas.drawBitmap(assassinScaled, 0, 0, defaultPaint);

    }
}
