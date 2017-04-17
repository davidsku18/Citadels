package edu.up.citadels.citadels;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import edu.up.citadels.citadels.actions.CardChooserSurfaceView;
import edu.up.citadels.citadels.actions.ChooseCharacterCard;
import edu.up.citadels.citadels.actions.ChooseDistrictCard;
import edu.up.citadels.citadels.actions.CitadelsBuildDistrictCard;
import edu.up.citadels.citadels.actions.ChooseDistrictCard;
import edu.up.citadels.citadels.actions.EndTurn;
import edu.up.citadels.citadels.actions.TakeGold;
import edu.up.citadels.citadels.actions.UseSpecialAbility;
import edu.up.citadels.game.GameHumanPlayer;
import edu.up.citadels.game.infoMsg.GameInfo;
import edu.up.citadels.R;
import edu.up.citadels.game.GameMainActivity;
import edu.up.citadels.game.infoMsg.GameInfo;
import edu.up.citadels.game.infoMsg.IllegalMoveInfo;
import edu.up.citadels.game.infoMsg.NotYourTurnInfo;

import static edu.up.citadels.R.array.characterCardSpinnerHandName;
import static edu.up.citadels.R.array.p1Action;

/**
 * A GUI that allows a human to play Citadels.
 *
 * @author Bryce Amato
 * @author Gavin Low
 * @author Victor Nguyen
 * @author Kurtis Davidson
 *
 * @version 3/10/2017
 */

public class CitadelsHumanPlayer extends GameHumanPlayer implements View.OnClickListener, View.OnCreateContextMenuListener
{
    String name;
    private ImageButton player1_Card1;
    private ImageButton player1_Card2;
    private boolean p1_Card1Bool = true; // boolean for player1_Card1
    private boolean p1_Card2Bool = true; // boolean for player1_Cart2

    private ImageButton assassinButton;
    private ImageButton thiefButton;
    private ImageButton magicianButton;
    private ImageButton kingButton;
    private ImageButton bishopButton;
    private ImageButton merchantButton;
    private ImageButton architectButton;
    private ImageButton warlordButton;

    private HorizontalScrollView horizontalScrollView;

    // Image Buttons for player1
    private ImageButton p1_D1;
    private ImageButton p1_D2;
    private ImageButton p1_D3;
    private ImageButton p1_D4;
    private ImageButton p1_D5;
    private ImageButton p1_D6;
    private ImageButton p1_D7;
    private ImageButton p1_D8;

    private ImageButton p2_D1;
    private ImageButton p2_D2;
    private ImageButton p2_D3;
    private ImageButton p2_D4;
    private ImageButton p2_D5;
    private ImageButton p2_D6;
    private ImageButton p2_D7;
    private ImageButton p2_D8;

    private ImageButton p3_D1;
    private ImageButton p3_D2;
    private ImageButton p3_D3;
    private ImageButton p3_D4;
    private ImageButton p3_D5;
    private ImageButton p3_D6;
    private ImageButton p3_D7;
    private ImageButton p3_D8;

    private TextView player1GoldCount;
    private TextView player2GoldCount;
    private TextView player3GoldCount;
    private TextView player1Score;
    private TextView player2Score;
    private TextView player3Score;
    private TextView cardInfo; //initializes cardInfo TextView
    private boolean d1_Info = false; //initializes d1_Info Boolean
    private boolean d2_Info = false; //initializes d2_Info Boolean
    private boolean d3_Info = false; //initializes d3_Info Boolean
    private boolean d4_Info = false; //initializes d4_Info Boolean

    private boolean hasGone = false;
    private boolean hasGoneAbility = false;
    private boolean hasBuilt = false;

    private ArrayList<Bitmap> p1City;
    private ArrayList<Bitmap> p1HandImages;
    private ArrayList<String> p1HandNames;
    private ArrayList<String> p2HandNames;
    private ArrayList<String> p3HandNames;
    private ArrayList<String> p1HandArrayList;

    private Button menu_Button;
    private Spinner actionSpinner;
    private Spinner player1HandSpinner;
    private ArrayAdapter p1HandAdapter;

    private CitadelsDistrictCard cdc;

    private ArrayAdapter p1HandAdapter;

    // Our activity
    private Activity myActivity;

    // Our surface view
    CardChooserSurfaceView ccsv;

    // Our edu.up.citadels.game state
    protected CitadelsGameState state = new CitadelsGameState();

    CitadelsDistrictCard card = null;

    List<String> player1DistrictsCards = new ArrayList<String>();

    private int layoutId;

    /**
     * constructor
     *
     * @param initName
     * 		the player's name
     *
     */
    public CitadelsHumanPlayer(String initName, int layoutId)
    {
        super(initName);
        this.layoutId = layoutId;
    }

    /**
     * callback method: we have received a message from the game
     *
     * @param info
     * 		the message we have received from the game
     */
    @Override
    public void receiveInfo(GameInfo info)
    {
        Log.i("CitadelsComputerPlayer", "receiving updated state ("+info.getClass()+")");
        if (info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo) {
            // if we had an out-of-turn or illegal move, flash the screen
            //surface.flash(Color.RED, 50);
            //Do nothing yet TODO
        }
        else if (!(info instanceof CitadelsGameState)) {
            // otherwise, if it's not a game-state message, ignore
            return;
        }
        else {
            // it's a game-state object: update the state. Since we have an animation
            // going, there is no need to explicitly display anything. That will happen
            // at the next animation-tick, which should occur within 1/20 of a second
            this.state = (CitadelsGameState)info;
            Log.i("human player", "receiving");
        }
    }

    /**
     * call-back method: called whenever the GUI has changed (e.g., at the beginning
     * of the game, or when the screen orientation changes).
     *
     * @param activity
     * 		the current activity
     */
    @Override
    public void setAsGui(GameMainActivity activity) {

        //remember the activity
        myActivity = activity;

        activity.setContentView(layoutId);

        player1_Card1 = (ImageButton) myActivity.findViewById(R.id.player1_Card1);
        player1_Card2 = (ImageButton) myActivity.findViewById(R.id.player1_Card2);

        p1_D1 = (ImageButton) myActivity.findViewById(R.id.p1_D1);
        p1_D2 = (ImageButton) myActivity.findViewById(R.id.p1_D2);
        p1_D3 = (ImageButton) myActivity.findViewById(R.id.p1_D3);
        p1_D4 = (ImageButton) myActivity.findViewById(R.id.p1_D4);
        p1_D5 = (ImageButton) myActivity.findViewById(R.id.p1_D5);
        p1_D6 = (ImageButton) myActivity.findViewById(R.id.p1_D6);
        p1_D7 = (ImageButton) myActivity.findViewById(R.id.p1_D7);
        p1_D8 = (ImageButton) myActivity.findViewById(R.id.p1_D8);

        p2_D1 = (ImageButton) myActivity.findViewById(R.id.p2_D1);
        p2_D2 = (ImageButton) myActivity.findViewById(R.id.p2_D2);
        p2_D3 = (ImageButton) myActivity.findViewById(R.id.p2_D3);
        p2_D4 = (ImageButton) myActivity.findViewById(R.id.p2_D4);
        p2_D5 = (ImageButton) myActivity.findViewById(R.id.p2_D5);
        p2_D6 = (ImageButton) myActivity.findViewById(R.id.p2_D6);
        p2_D7 = (ImageButton) myActivity.findViewById(R.id.p2_D7);
        p2_D8 = (ImageButton) myActivity.findViewById(R.id.p2_D8);

        p3_D1 = (ImageButton) myActivity.findViewById(R.id.p3_D1);
        p3_D2 = (ImageButton) myActivity.findViewById(R.id.p3_D2);
        p3_D3 = (ImageButton) myActivity.findViewById(R.id.p3_D3);
        p3_D4 = (ImageButton) myActivity.findViewById(R.id.p3_D4);
        p3_D5 = (ImageButton) myActivity.findViewById(R.id.p3_D5);
        p3_D6 = (ImageButton) myActivity.findViewById(R.id.p3_D6);
        p3_D7 = (ImageButton) myActivity.findViewById(R.id.p3_D7);
        p3_D8 = (ImageButton) myActivity.findViewById(R.id.p3_D8);

        horizontalScrollView = (HorizontalScrollView) myActivity.findViewById(R.id.horizontalScrollView);

        assassinButton = (ImageButton) myActivity.findViewById(R.id.assassinButton);
        thiefButton = (ImageButton) myActivity.findViewById(R.id.thiefButton);
        magicianButton = (ImageButton) myActivity.findViewById(R.id.magicianButton);
        kingButton = (ImageButton) myActivity.findViewById(R.id.kingButton);
        bishopButton = (ImageButton) myActivity.findViewById(R.id.bishopButton);
        merchantButton = (ImageButton) myActivity.findViewById(R.id.merchantButton);
        architectButton = (ImageButton) myActivity.findViewById(R.id.architectButton);
        warlordButton = (ImageButton) myActivity.findViewById(R.id.warlordButton);

        cardInfo = (TextView) myActivity.findViewById(R.id.helpText); // sets cardInfo to the helpText TextView

        player1GoldCount = (TextView) myActivity.findViewById(R.id.p1_Gold);
        player2GoldCount = (TextView) myActivity.findViewById(R.id.p2_gold);
        player3GoldCount = (TextView) myActivity.findViewById(R.id.p3_Gold);
        player1Score = (TextView)myActivity.findViewById(R.id.P1_Score);
        player2Score = (TextView)myActivity.findViewById(R.id.p2_Score);
        player3Score = (TextView)myActivity.findViewById(R.id.p3_Score);

       /**
        * @Author Victor Nguyen
        *
        * Creates the floating menu
        * Must long press to open the menu
        */
        menu_Button = (Button) myActivity.findViewById(R.id.Menu);
        menu_Button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                myActivity.registerForContextMenu(menu_Button);
            }
        });

        this.player1HandSpinner = (Spinner) myActivity.findViewById(R.id.player1HandSpinner);
        actionSpinner = (Spinner) myActivity.findViewById(R.id.actionSpinner);

        // get values for the spinner

        this.p1HandArrayList = state.getP1HandNames();
        p1HandAdapter = new ArrayAdapter(myActivity, android.R.layout.simple_list_item_1,
                android.R.id.text1, p1HandArrayList);
        p1HandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.player1HandSpinner.setAdapter(p1HandAdapter);
        this.player1HandSpinner.setOnItemSelectedListener(new P1HandSpinnerListener());

        // set values for all players' gold
        player1GoldCount.setText("Gold: " + state.getP1Gold());
        player2GoldCount.setText("Gold: " + state.getP2Gold());
        player3GoldCount.setText("Gold: " + state.getP3Gold());

        player1Score.setText("Score: " + state.getP1Score());
        player2Score.setText("Score: " + state.getP2Score());
        player3Score.setText("Score: " + state.getP3Score());


        // define a listener for the spinner
        String[] p1ActionSpinnerNames = myActivity.getResources().getStringArray(p1Action);
        actionSpinner.setOnItemSelectedListener(new P1ActionSpinnerListener());

        // initializing CardChooserSurfaceView
        //this.ccsv = (CardChooserSurfaceView) this.myActivity.findViewById(R.id.cardtheSurfaceView);

        //initialize the array adapter
        ArrayAdapter adapter = new ArrayAdapter<String>(myActivity, android.R.layout.simple_list_item_1,
                android.R.id.text1, p1ActionSpinnerNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //connect spinner to the adapter
        actionSpinner.setAdapter(adapter);


        /*for(int i = 0; i < state.getP1City().size(); ++i)
        if(state != null)
        {
            receiveInfo(state);
        }
/*
        for(int i = 0; i < state.getP1City().size(); ++i)
        {
            CitadelsDistrictCard cdc = state.getP1DistrictCard(i);
            p1HandNames.add(cdc.getName());
        }

        for(int i = 0; i < state.getP2City().size(); ++i)
        {
            CitadelsDistrictCard cdc = state.getP2DistrictCard(i);
            p2HandNames.add(cdc.getName());
        }

        for(int i = 0; i < state.getP3City().size(); ++i)
        {
            CitadelsDistrictCard cdc = state.getP3DistrictCard(i);
            p3HandNames.add(cdc.getName());
        }
*/      //TODO set random cards invisible
        assassinButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(state.getCharacterDeck(0) != null)
                {
                    state.setChosenCharacterCard(0);
                    humanPlayerChooseCharacterCard(0);
                    cardInfo.setText("You've chosen the assassin card");
                    assassinButton.setVisibility(View.INVISIBLE);
                }
                else
                {
                    state.setChosenCharacterCard(-1);
                    cardInfo.setText("This card is already taken");
                }
            }
        });
        thiefButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(state.getCharacterDeck(1) != null)
                {
                    state.setChosenCharacterCard(1);
                    humanPlayerChooseCharacterCard(1);
                    cardInfo.setText("You've chosen the thief card");
                }
                else
                {
                    state.setChosenCharacterCard(-1);
                    cardInfo.setText("This card is already taken");
                }
            }
        });


        magicianButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(state.getCharacterDeck(2) != null)
                {
                    state.setChosenCharacterCard(2);
                    humanPlayerChooseCharacterCard(2);
                    cardInfo.setText("You've chosen the magician card");
                }
                else
                {
                    state.setChosenCharacterCard(-1);
                    cardInfo.setText("This card is already taken");
                }
            }
        });


        kingButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(state.getCharacterDeck(3) != null)
                {
                    state.setChosenCharacterCard(3);
                    humanPlayerChooseCharacterCard(3);
                    cardInfo.setText("You've chosen the king card");
                }
                else
                {
                    state.setChosenCharacterCard(-1);
                    cardInfo.setText("This card is already taken");
                }
            }
        });

        bishopButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(state.getCharacterDeck(4) != null)
                {
                    state.setChosenCharacterCard(4);
                    humanPlayerChooseCharacterCard(4);
                    cardInfo.setText("You've chosen the bishop card");
                }
                else
                {
                    state.setChosenCharacterCard(-1);
                    cardInfo.setText("This card is already taken");
                }
            }
        });

        merchantButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(state.getCharacterDeck(5) != null)
                {
                    state.setChosenCharacterCard(5);
                    humanPlayerChooseCharacterCard(5);
                }
                else
                {
                    state.setChosenCharacterCard(-1);
                    cardInfo.setText("This card is already taken");
                }
            }
        });

        architectButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(state.getCharacterDeck(6) != null)
                {
                    state.setChosenCharacterCard(6);
                    humanPlayerChooseCharacterCard(6);
                    cardInfo.setText("You've chosen the architect card");
                }
                else
                {
                    state.setChosenCharacterCard(-1);
                    cardInfo.setText("This card is already taken");
                }
            }
        });

        warlordButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(state.getCharacterDeck(7) != null)
                {
                    state.setChosenCharacterCard(7);
                    humanPlayerChooseCharacterCard(7);
                    cardInfo.setText("You've chosen the warlord card");
                }
                else
                {
                    state.setChosenCharacterCard(-1);
                    cardInfo.setText("This card is already taken");
                }
            }
        });
    }


    //This makes this player make a take gold action
    public void humanPlayerTakeGold()
    {
        game.sendAction(new TakeGold(this));
    }

    //This allows the player to take a district card
    public void humanPlayerTakeDistrictCard()
    {
        game.sendAction(new ChooseDistrictCard(this));
    }

    //This allows a player to use their special ability
    public void humanPlayerUseAbility()
    {
        game.sendAction(new UseSpecialAbility(this));
    }

    //This method allows the user to end their turn
    public void humanPlayerEndTurn()
    {
        game.sendAction(new EndTurn(this));
    }

    public void humanPlayerBuildDistrict(CitadelsDistrictCard cdc)
    {
        game.sendAction(new CitadelsBuildDistrictCard(this, cdc));
    }

    public void humanPlayerChooseCharacterCard(int character) { game.sendAction((new ChooseCharacterCard(this, character))); }

    public void setWhichCard(CitadelsDistrictCard cdc)
    {
        this.cdc = cdc;
    }

    /**
     * returns the GUI's top view
     *
     * @return
     * 		the GUI's top view
     */
    @Override
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    @Override
    public void onClick(View v)
    {
        player1_Card1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //if it gets touched do something
            }
        });

        player1_Card2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //do something if it's touched
            }
        });

        /*
        * @Author: Gavin Low
        * @Author: Bryce Amato
        *
        * Click on the card to display useful info about the card
        * Click on the card again to remove displayed information
        * The card with the displayed information must be clicked to show the next card's information
        *
        * EDIT--- It now will display information about the specific card
        *
        */
        p1_D1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                card = state.getP1City().get(0);
                if(card != null)
                {
                    //displays card info (name, value, color)
                    cardInfo.setText(state.getP1DistrictInfo(0));
                }
            }
        });

        p1_D2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                card = state.getP1City().get(1);
                if(card != null)
                {
                    //displays card info (name, value, color)
                    cardInfo.setText(state.getP1DistrictInfo(1));
                }

            }
        });

        p1_D3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                card = state.getP1City().get(2);
                if(card != null)
                {
                    //displays card info (name, value, color)
                    cardInfo.setText(state.getP1DistrictInfo(2));
                }
            }
        });

        p1_D4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                card = state.getP1City().get(3);
                if(card != null)
                {
                    //displays card info (name, value, color)
                    cardInfo.setText(state.getP1DistrictInfo(3));
                }
            }
        });

        p1_D5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                card = state.getP1City().get(4);
                if(card != null)
                {
                    //displays card info (name, value, color)
                    cardInfo.setText(state.getP1DistrictInfo(4));
                }
            }
        });

        p1_D6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                card = state.getP1City().get(5);
                if(card != null)
                {
                    //displays card info (name, value, color)
                    cardInfo.setText(state.getP1DistrictInfo(5));
                }
            }
        });

        p1_D7.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                card = state.getP1City().get(6);
                if(card != null)
                {
                    //displays card info (name, value, color)
                    cardInfo.setText(state.getP1DistrictInfo(6));
                }
            }
        });

        p1_D8.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                card = state.getP1City().get(7);
                if(card != null)
                {
                    //displays card info (name, value, color)
                    cardInfo.setText(state.getP1DistrictInfo(7));
                }
            }
        });

        p2_D1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                card = state.getP2City().get(0);
                if(card != null)
                {
                    //displays card info (name, value, color)
                    cardInfo.setText(state.getP2DistrictInfo(0));
                }
            }
        });

        p2_D2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                card = state.getP2City().get(1);
                if(card != null)
                {
                    //displays card info (name, value, color)
                    cardInfo.setText(state.getP2DistrictInfo(1));
                }

            }
        });

        p2_D3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                card = state.getP2City().get(2);
                if(card != null)
                {
                    //displays card info (name, value, color)
                    cardInfo.setText(state.getP2DistrictInfo(2));
                }
            }
        });

        p2_D4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                card = state.getP2City().get(3);
                if(card != null)
                {
                    //displays card info (name, value, color)
                    cardInfo.setText(state.getP2DistrictInfo(3));
                }
            }
        });

        p2_D5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                card = state.getP2City().get(4);
                if(card != null)
                {
                    //displays card info (name, value, color)
                    cardInfo.setText(state.getP2DistrictInfo(4));
                }
            }
        });

        p2_D6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                card = state.getP2City().get(5);
                if(card != null)
                {
                    //displays card info (name, value, color)
                    cardInfo.setText(state.getP2DistrictInfo(5));
                }
            }
        });

        p2_D7.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                card = state.getP2City().get(6);
                if(card != null)
                {
                    //displays card info (name, value, color)
                    cardInfo.setText(state.getP2DistrictInfo(6));
                }
            }
        });

        p2_D8.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                card = state.getP2City().get(7);
                if(card != null)
                {
                    //displays card info (name, value, color)
                    cardInfo.setText(state.getP2DistrictInfo(7));
                }
            }
        });

        p3_D1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                card = state.getP3City().get(0);
                if(card != null)
                {
                    //displays card info (name, value, color)
                    cardInfo.setText(state.getP3DistrictInfo(0));
                }
            }
        });

        p3_D2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                card = state.getP3City().get(1);
                if(card != null)
                {
                    //displays card info (name, value, color)
                    cardInfo.setText(state.getP3DistrictInfo(1));
                }

            }
        });

        p3_D3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                card = state.getP3City().get(2);
                if(card != null)
                {
                    //displays card info (name, value, color)
                    cardInfo.setText(state.getP3DistrictInfo(2));
                }
            }
        });

        p3_D4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                card = state.getP3City().get(3);
                if(card != null)
                {
                    //displays card info (name, value, color)
                    cardInfo.setText(state.getP3DistrictInfo(3));
                }
            }
        });

        p3_D5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                card = state.getP3City().get(4);
                if(card != null)
                {
                    //displays card info (name, value, color)
                    cardInfo.setText(state.getP3DistrictInfo(4));
                }
            }
        });

        p3_D6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                card = state.getP3City().get(5);
                if(card != null)
                {
                    //displays card info (name, value, color)
                    cardInfo.setText(state.getP3DistrictInfo(5));
                }
            }
        });

        p3_D7.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                card = state.getP3City().get(6);
                if(card != null)
                {
                    //displays card info (name, value, color)
                    cardInfo.setText(state.getP3DistrictInfo(6));
                }
            }
        });

        p3_D8.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                card = state.getP3City().get(7);
                if(card != null)
                {
                    //displays card info (name, value, color)
                    cardInfo.setText(state.getP3DistrictInfo(7));
                }
            }
        });

        //Listeners for the character cards

    }

    private class P1HandSpinnerListener implements AdapterView.OnItemSelectedListener
    {

        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                   int position, long id)
        {
            if (hasBuilt == false)
            {
                CitadelsDistrictCard cardToBuild = (CitadelsDistrictCard)state.getP1Hand().get(position);
                humanPlayerBuildDistrict(cardToBuild);
                p1HandAdapter.remove(p1HandAdapter.getItem(position));
                p1HandAdapter.notifyDataSetChanged();
                hasBuilt = true;
            }else
            {
                //do nothing, they aren't allowed to build more
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

    /**
     * @Author: Bryce Amato
     *
     * This listener will keep track of what is selected in the player 1 action listener
     * and respond accordingly
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
                cardInfo.setText("It is Your Turn.");
            }else if(position == 1)
            {
                if (! hasGone)
                {
                    humanPlayerTakeGold();
                    hasGone = true;
                    cardInfo.setText("Added Two Gold.");
                    player1GoldCount.setText("Gold: " + state.getP1Gold());
                }else
                {
                    //do nothing because they are not allowed to go
                    cardInfo.setText("You Have Already Gone.");
                }
            }else if(position == 2)
            {
                if(! hasGone)
                {
                    CitadelsDistrictCard cdc = state.getDeckOrderDistricts().get(0);
                    setWhichCard(cdc);
                    humanPlayerTakeDistrictCard();
                    p1HandAdapter.add(cdc.getInfo(cdc));
                    p1HandAdapter.notifyDataSetChanged();
                    hasGone = true;
                    cardInfo.setText("District Card Aquired.");
                }
                else
                {
                    //do nothing because they are not allowed to go
                    cardInfo.setText("You Have Already Gone.");
                }
            }else if(position == 3)
            {
                if(! hasGoneAbility)
                {
                    humanPlayerUseAbility();
                    hasGoneAbility = true;
                    cardInfo.setText("Character Ability Used.");
                }
                else
                {
                    //do nothing--- they cannot go
                    cardInfo.setText("You have already used your ability.");
                }
            }else if(position == 4)
            {
                if(hasGone)
                {
                    humanPlayerEndTurn();
                    hasGone = false;
                    hasBuilt = false;
                    hasGoneAbility = false;
                    cardInfo.setText("Turn Ended.");
                }
                else
                {
                    //do nothing because they must do something before ending their turn
                    cardInfo.setText("Must Take Turn First.");
                }
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


    /**
     * @Author Victor Nguyen
     *
     * Implementing the method to create the floating menu
     *
     * Sources: https://developer.android.com/guide/topics/ui/menus.html#PopupMenu
     */
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        myActivity.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = myActivity.getMenuInflater();
        inflater.inflate(R.menu.menu_ingame, menu);
    }
}
