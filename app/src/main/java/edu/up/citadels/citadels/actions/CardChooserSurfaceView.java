package edu.up.citadels.citadels.actions;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.io.Serializable;
import java.util.ArrayList;

import edu.up.citadels.R;

/**
 * Created by kurtisdavidson on 4/9/17.
 */

public class CardChooserSurfaceView extends SurfaceView implements Serializable
{
    private static final long serialVersionUID = 3141L;
    private Paint defaultPaint = new Paint();
    ArrayList<Bitmap> characterCards;
    private int width = this.getWidth();
    private int height = this.getHeight();

        /*
        characterCards.add(assassinScaled);
        characterCards.add(thiefScaled);
        characterCards.add(magicianScaled);
        characterCards.add(kingScaled);
        characterCards.add(bishopScaled);
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
        Bitmap assassin = BitmapFactory.decodeResource(getResources(), R.drawable.assasin);
        Bitmap thief = BitmapFactory.decodeResource(getResources(), R.drawable.thief);
        Bitmap magician = BitmapFactory.decodeResource(getResources(), R.drawable.magician);
        Bitmap king = BitmapFactory.decodeResource(getResources(), R.drawable.king);
        Bitmap bishop = BitmapFactory.decodeResource(getResources(), R.drawable.bishop);
        Bitmap merchant = BitmapFactory.decodeResource(getResources(), R.drawable.merchant);
        Bitmap architect = BitmapFactory.decodeResource(getResources(), R.drawable.architect);
        Bitmap warlord = BitmapFactory.decodeResource(getResources(), R.drawable.warlord);
        Bitmap assassinScaled = Bitmap.createScaledBitmap(assassin, this.getWidth()/4, this.getHeight(), true);
        Bitmap thiefScaled = Bitmap.createScaledBitmap(thief, this.getWidth()/4, this.getHeight(), true);
        Bitmap magicianScaled = Bitmap.createScaledBitmap(magician, this.getWidth()/8, this.getHeight(), true);
        Bitmap kingScaled = Bitmap.createScaledBitmap(king, this.getWidth()/8, this.getHeight(), true);
        Bitmap bishopScdled = Bitmap.createScaledBitmap(bishop, this.getWidth()/8, this.getHeight(), true);
        Bitmap merchantScaled = Bitmap.createScaledBitmap(merchant, this.getWidth()/8, this.getHeight(), true);
        Bitmap architectScaled = Bitmap.createScaledBitmap(architect, this.getWidth()/8, this.getHeight(), true);
        Bitmap warlordScaled = Bitmap.createScaledBitmap(warlord, this.getWidth()/8, this.getHeight(), true);
        canvas.drawBitmap(assassinScaled, 0, 0, defaultPaint);
        canvas.drawBitmap(assassinScaled, 180, 0, defaultPaint);
        canvas.drawBitmap(assassinScaled, 360, 0, defaultPaint);
        canvas.drawBitmap(assassinScaled, 540, 0, defaultPaint);
        canvas.drawBitmap(assassinScaled, 720, 0, defaultPaint);
        canvas.drawBitmap(assassinScaled, 900, 0, defaultPaint);
        canvas.drawBitmap(assassinScaled, 1080, 0, defaultPaint);
        canvas.drawBitmap(assassinScaled, 1260, 0, defaultPaint);
    }
}
