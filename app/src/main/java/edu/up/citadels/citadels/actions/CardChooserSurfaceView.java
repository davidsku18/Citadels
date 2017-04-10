package edu.up.citadels.citadels.actions;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Created by kurtisdavidson on 4/9/17.
 */

public class CardChooserSurfaceView extends SurfaceView
{

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

    }
}
