package edu.up.citadels;

import java.util.ArrayList;
import java.util.List;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
{

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
    private TextView player1GoldCount;
    private int p1Gold = 2;
    private TextView cardInfo; //initializes cardInfo TextView
    private boolean d1_Info = false; //initializes d1_Info Boolean
    private boolean d2_Info = false; //initializes d2_Info Boolean
    private boolean d3_Info = false; //initializes d3_Info Boolean
    private boolean d4_Info = false; //initializes d4_Info Boolean

    private ArrayList<Bitmap> p1HandImages;

    private String[] p1Action;
    private boolean p = true;
    private Button menu_Button;
    private Spinner actionSpinner;
    private Spinner player1HandSpinner;

    List<String> player1DistrictsCards = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1_Card1 = (ImageButton) findViewById(R.id.player1_Card1);
        player1_Card2 = (ImageButton) findViewById(R.id.player1_Card2);

        p1_D1 = (ImageButton) findViewById(R.id.p1_D1);
        p1_D2 = (ImageButton) findViewById(R.id.p1_D2);
        p1_D3 = (ImageButton) findViewById(R.id.p1_D3);
        p1_D4 = (ImageButton) findViewById(R.id.p1_D4);

        cardInfo = (TextView)findViewById(R.id.helpText); //sets cardInfo to the helpText TextView

        //String[] p1Hand = getResources().getStringArray(R.array.p1Hand);
        player1GoldCount = (TextView)findViewById(R.id.Gold_Count);

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

        //get values for the spinner
       // String[] p1ActionSpinnerNames = getResources().getStringArray(R.array.p1Action);

        //set initial value for the p1 gold to 2
        p1Gold = 2;
        player1GoldCount.setText("Gold: " + p1Gold);



        // define a listener for the spinner
        actionSpinner.setOnItemSelectedListener(new P1ActionSpinnerListener());

        //initialize the array adapter
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                                                    android.R.id.text1, p1Action);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //connect spinner to the adapter
        actionSpinner.setAdapter(adapter);

        /*
         * Author: Kurtis Davidson
         *
         * Listens to when user clicks on player 1's district card 1 and changes the background color
         * to black, when pressed again it reverts its color
         */
        player1_Card1.setOnClickListener(new View.OnClickListener()
        {
            Drawable defaultImageColor = player1_Card1.getBackground(); // gets default background color
            @Override
            public void onClick(View view)
            {
                if(p1_Card1Bool)
                {
                    player1_Card1.setBackgroundColor(getResources().getColor(R.color.blackButtonPressColor));
                }
                else
                {
                    player1_Card1.setBackgroundDrawable(defaultImageColor);
                }
                p1_Card1Bool=!p1_Card1Bool;
            }
        });
        /*
         * Author: Kurtis Davidson
         *
         * Listens to when user clicks on player 1's district card 2 and changes the background color
         * to black, when pressed again it reverts its color
         */
        player1_Card2.setOnClickListener(new View.OnClickListener()
        {
            Drawable defaultImageColor = player1_Card2.getBackground(); // gets default background color
            @Override
            public void onClick(View view)
            {
                if(p1_Card2Bool)
                {
                    player1_Card2.setBackgroundColor(getResources().getColor(R.color.blackButtonPressColor));
                }
                else
                {
                    player1_Card2.setBackgroundDrawable(defaultImageColor);
                }
                p1_Card2Bool=!p1_Card2Bool;
            }
        });


//////////////////////District Card Info Text Views///////////////////////////////////
        //Click on the card to display useful info about the card
        //Click on the card again to remove displayed information
        //The card with the displayed information must be clicked to show the next card's information
        p1_D1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(cardInfo.getText() == "")
                {
                    cardInfo.setText("Manor\nValue: 4\nColor: Gold"); //displays card info (name, value, color)
                    d1_Info = true;

                }
               else if(d1_Info == true)
                {
                    cardInfo.setText("");
                    d1_Info = false;
                }
            }
        });

        p1_D2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(cardInfo.getText() == "")
                {
                    cardInfo.setText("Town Hall\nValue: 3\nColor: Gold");
                    d2_Info = true;
                }
                else if( d2_Info == true)
                {
                    cardInfo.setText("");
                    d2_Info = false;
                }

            }
        });

        p1_D3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(cardInfo.getText() == "")
                {
                    cardInfo.setText("Church\nValue: 2\nColor: Blue");
                    d3_Info = true;

                }
                else if( d3_Info == true)
                {
                    cardInfo.setText("");
                    d3_Info = false;
                }
            }
        });

        p1_D4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(cardInfo.getText() == "")
                {
                    cardInfo.setText("Battlefield\nValue: 3\nColor: Red");
                    d4_Info = true;

                }
                else if(d4_Info == true)
                {
                    cardInfo.setText("");
                    d4_Info = false;
                }
            }
        });


    }
    /*
    * Author: Bryce Amato
    *
    * This listener will keep track of what is selected in the player 1 action listener
    * and respond accordingly.
    */
    private class P1ActionSpinnerListener implements AdapterView.OnItemSelectedListener
    {

        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                   int position, long id)
        {
                if(position == 0)
                {
                    //do nothing
                }else if(position == 1)
                {
                    p1Gold = p1Gold + 2;
                    player1GoldCount.setText("Gold: " + p1Gold);
                }else if(position == 2)
                {
                    //do nothing yet
                }else if(position == 3)
                {
                    //do nothing yet
                }
        }

        /**
         * @see android.widget.AdapterView.OnItemSelectedListener#onNothingSelected(
         *                  android.widget.AdapterView)
         */
        @Override
        public void onNothingSelected(AdapterView<?> parentView)
        {

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
