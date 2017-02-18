package edu.up.citadels;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity{

    private ImageButton player1_Card1;
    private ImageButton player1_Card2;
    private boolean p1_Card1Bool = true; // boolean for player1_Card1
    private boolean p1_Card2Bool = true; // boolean for player1_Cart2
    private ImageButton p1_D1;
    private ImageButton p1_D2;
    private ImageButton p1_D3;
    private ImageButton p1_D4;
    private ImageButton p1_D5;
    private ImageButton p1_D6;
    private ImageButton p1_D7;
    private ImageButton p1_D8;
    private boolean p = true;
    private Button menu_Button;
    private Spinner actionSpinner;
    private Spinner player1HandSpinner;
    List<String> player1DistrictsCards = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1_Card1 = (ImageButton) findViewById(R.id.player1_Card1);
        player1_Card2 = (ImageButton) findViewById(R.id.player1_Card2);

        p1_D1 = (ImageButton) findViewById(R.id.p1_D1);
        p1_D2 = (ImageButton) findViewById(R.id.p1_D2);
        p1_D3 = (ImageButton) findViewById(R.id.p1_D3);
        p1_D4 = (ImageButton) findViewById(R.id.p1_D4);


       /*
        *   @author Victor Nguyen
        *   Creates the floating menu
        *   Must long press to open the menu
        *
        *
        */
        menu_Button = (Button) findViewById(R.id.Menu);
        menu_Button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                registerForContextMenu(menu_Button);


            }


        });

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //String[] p1Hand = getResources().getStringArray(R.array.p1Hand);

        player1HandSpinner = (Spinner) findViewById(R.id.player1HandSpinner);
        actionSpinner = (Spinner) findViewById(R.id.actionSpinner);

        player1_Card1.setOnClickListener(new View.OnClickListener()
        {
            Drawable defaultImageColor = player1_Card1.getBackground(); // gets default background color
            @Override
            public void onClick(View view)
            {
                if(p1_Card1Bool) {
                    player1_Card1.setBackgroundColor(getResources().getColor(R.color.blackButtonPressColor));
                }
                else{
                    player1_Card1.setBackgroundDrawable(defaultImageColor);
                }
                p1_Card1Bool=!p1_Card1Bool;
            }
        });

        player1_Card2.setOnClickListener(new View.OnClickListener()
        {
            Drawable defaultImageColor = player1_Card2.getBackground(); // gets default background color
            @Override
            public void onClick(View view)
            {
                if(p1_Card2Bool) {
                    player1_Card2.setBackgroundColor(getResources().getColor(R.color.blackButtonPressColor));
                }
                else{
                    player1_Card2.setBackgroundDrawable(defaultImageColor);
                }
                p1_Card2Bool=!p1_Card2Bool;
            }
        });

        p1_D1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

        p1_D2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

        p1_D3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

        p1_D4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });


    }

    private class SpinnerLister implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }


    /*
     *   @author Victor Nguyen
     *   Implementing the method to create the floating menu
     *
     *   Sources: https://developer.android.com/guide/topics/ui/menus.html#PopupMenu
     *
     *
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ingame, menu);
    }
}
