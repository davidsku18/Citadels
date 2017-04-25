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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import edu.up.citadels.R;
import edu.up.citadels.citadels.actions.CardChooserSurfaceView;
import edu.up.citadels.citadels.actions.ChooseCharacterCard;
import edu.up.citadels.citadels.actions.ChooseDistrictCard;
import edu.up.citadels.citadels.actions.CitadelsBuildDistrictCard;
import edu.up.citadels.citadels.actions.ChooseDistrictCard;
import edu.up.citadels.citadels.actions.EndTurn;
import edu.up.citadels.citadels.actions.TakeGold;
import edu.up.citadels.citadels.actions.UseSpecialAbility;
import edu.up.citadels.game.GameHumanPlayer;
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

    private ImageButton player2_Card1;
    private ImageButton player2_Card2;

    private ImageButton player3_Card1;
    private ImageButton player3_Card2;

    private ImageButton face1_Up;
    private ImageButton face2_Up;

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
    private TextView player2DistrictCards;
    private TextView player3DistrictCards;
    private int p1Gold = 2;
    private TextView cardInfo; //initializes cardInfo TextView
    private boolean d1_Info = false; //initializes d1_Info Boolean
    private boolean d2_Info = false; //initializes d2_Info Boolean
    private boolean d3_Info = false; //initializes d3_Info Boolean
    private boolean d4_Info = false; //initializes d4_Info Boolean

    private boolean hasGone = false;
    private boolean hasGoneAbility = false;
    private boolean hasBuilt = false;
    private boolean isTurnChar = false;

    private ArrayList<Bitmap> p1City;
    private ArrayList<Bitmap> p1HandImages;
    private ArrayList<String> p1HandNames;
    private ArrayList<String> p2HandNames;
    private ArrayList<String> p3HandNames;
    private ArrayList<String> p1HandArrayList;

    private Button menu_Button;
    private Spinner actionSpinner;
    private Spinner player1HandSpinner;

    private CitadelsDistrictCard cdc;

    private ArrayAdapter p1HandAdapter;

    // Our activity
    private Activity myActivity;

    // Our surface view
    CardChooserSurfaceView ccsv;

    // Our edu.up.citadels.game state
    protected CitadelsGameState state;  //= new CitadelsGameState();

    CitadelsDistrictCard card = null;

    List<String> player1DistrictsCards = new ArrayList<String>();

    private int layoutId;
    private int selectedCard;
    boolean chosenFirstCharacter = false;
    boolean chosenSecondCharacter = false;

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
        if (!(info instanceof CitadelsGameState)) {
            // otherwise, if it's not a game-state message, ignore
            return;
        }
        else {
            // it's a game-state object: update the state. Since we have an animation
            // going, there is no need to explicitly display anything. That will happen
            // at the next animation-tick, which should occur within 1/20 of a second
            this.state = (CitadelsGameState)info;
            this.initializeEverything();
            //cardInfo.setText(state.getAction());
        }
    }

    //this will find the character that each player is and draw it in the designated image button
    public void drawCharacterCard(ImageButton cbutton, int charNum)
    {
        if (charNum == 0)
        {
            cbutton.setBackgroundResource(R.drawable.assasin);
        }
        else if (charNum == 1)
        {
            cbutton.setBackgroundResource(R.drawable.thief);
        }
        else if (charNum == 2)
        {
            cbutton.setBackgroundResource(R.drawable.magician);
        }
        else if (charNum == 3)
        {
            cbutton.setBackgroundResource(R.drawable.king);
        }
        else if (charNum == 4)
        {
            cbutton.setBackgroundResource(R.drawable.bishop);
        }
        else if (charNum == 5)
        {
            cbutton.setBackgroundResource(R.drawable.merchant);
        }
        else if (charNum == 6)
        {
            cbutton.setBackgroundResource(R.drawable.architect);
        }
        else if (charNum == 7)
        {
            cbutton.setBackgroundResource(R.drawable.warlord);
        }
        else if( charNum == -1)
        {
            // when no one has a character
            cbutton.setBackgroundResource(R.drawable.watchtower);
        }
        else //TODO Change to back of card
        {
            cbutton.setBackgroundResource(R.drawable.battlefield);
        }

    }

    //this will take in the district card and find the corresponding background at set the cities accordingly
    public void drawCityCard(ImageButton button, String cardName)
    {
        if (cardName.toLowerCase().equals("battlefield"))
        {
            button.setBackgroundResource(R.drawable.battlefield);
        }
        else if (cardName.toLowerCase().equals("castle"))
        {
            button.setBackgroundResource(R.drawable.castle);
        }
        else if (cardName.toLowerCase().equals("cathedral"))
        {
            button.setBackgroundResource(R.drawable.cathedral);
        }
        else if (cardName.toLowerCase().equals("church"))
        {
            button.setBackgroundResource(R.drawable.church);
        }
        else if (cardName.toLowerCase().equals("docks"))
        {
            button.setBackgroundResource(R.drawable.docks);
        }
        else if (cardName.toLowerCase().equals("fortress"))
        {
            button.setBackgroundResource(R.drawable.fortress);
        }
        else if (cardName.toLowerCase().equals("harbor"))
        {
            button.setBackgroundResource(R.drawable.harbor);
        }
        else if (cardName.toLowerCase().equals("manor"))
        {
            button.setBackgroundResource(R.drawable.manor);
        }
        else if (cardName.toLowerCase().equals("market"))
        {
            button.setBackgroundResource(R.drawable.market);
        }
        else if (cardName.toLowerCase().equals("monastery"))
        {
            button.setBackgroundResource(R.drawable.monastery);
        }
        else if (cardName.toLowerCase().equals("palace"))
        {
            button.setBackgroundResource(R.drawable.palace);
        }
        else if (cardName.toLowerCase().equals("prison"))
        {
            button.setBackgroundResource(R.drawable.prison);
        }
        else if (cardName.toLowerCase().equals("tavern"))
        {
            button.setBackgroundResource(R.drawable.tavern);
        }
        else if (cardName.toLowerCase().equals("temple"))
        {
            button.setBackgroundResource(R.drawable.temple);
        }
        else if (cardName.toLowerCase().equals("city hall"))
        {
            button.setBackgroundResource(R.drawable.townhall);
        }
        else if (cardName.toLowerCase().equals("trading post"))
        {
            button.setBackgroundResource(R.drawable.trading_post);
        }
        else if (cardName.toLowerCase().equals("watchtower"))
        {
            button.setBackgroundResource(R.drawable.watchtower);
        }
        else // Sets image to nothing TODO set to empty
        {

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

        //which players are which character
        player1_Card1 = (ImageButton) myActivity.findViewById(R.id.player1_Card1);
        player1_Card2 = (ImageButton) myActivity.findViewById(R.id.player1_Card2);

        player2_Card1 = (ImageButton) myActivity.findViewById(R.id.player2_Card1);
        player2_Card2 = (ImageButton) myActivity.findViewById(R.id.player2_Card2);

        player3_Card1 = (ImageButton) myActivity.findViewById(R.id.player3_card1);
        player3_Card2 = (ImageButton) myActivity.findViewById(R.id.player3_card2);

        face1_Up = (ImageButton)  myActivity.findViewById(R.id.face1_Up);
        face2_Up = (ImageButton)  myActivity.findViewById(R.id.face2_Up);

        //All of the built districts in the cities
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

        //View on top for the character card selection
        horizontalScrollView = (HorizontalScrollView) myActivity.findViewById(R.id.horizontalScrollView);

        //initialize buttons for choosing characters
        assassinButton = (ImageButton) myActivity.findViewById(R.id.assassinButton);
        thiefButton = (ImageButton) myActivity.findViewById(R.id.thiefButton);
        magicianButton = (ImageButton) myActivity.findViewById(R.id.magicianButton);
        kingButton = (ImageButton) myActivity.findViewById(R.id.kingButton);
        bishopButton = (ImageButton) myActivity.findViewById(R.id.bishopButton);
        merchantButton = (ImageButton) myActivity.findViewById(R.id.merchantButton);
        architectButton = (ImageButton) myActivity.findViewById(R.id.architectButton);
        warlordButton = (ImageButton) myActivity.findViewById(R.id.warlordButton);


        //scales the images so they show up in the designated buttons
        player1_Card1.setScaleType(ImageView.ScaleType.FIT_XY);
        player1_Card2.setScaleType(ImageView.ScaleType.FIT_XY);
        p1_D1.setScaleType(ImageView.ScaleType.FIT_XY);
        p1_D2.setScaleType(ImageView.ScaleType.FIT_XY);
        p1_D3.setScaleType(ImageView.ScaleType.FIT_XY);
        p1_D4.setScaleType(ImageView.ScaleType.FIT_XY);
        p1_D5.setScaleType(ImageView.ScaleType.FIT_XY);
        p1_D6.setScaleType(ImageView.ScaleType.FIT_XY);
        p1_D7.setScaleType(ImageView.ScaleType.FIT_XY);
        p1_D8.setScaleType(ImageView.ScaleType.FIT_XY);

        player2_Card1.setScaleType(ImageView.ScaleType.FIT_XY);
        player2_Card2.setScaleType(ImageView.ScaleType.FIT_XY);
        p2_D1.setScaleType(ImageView.ScaleType.FIT_XY);
        p2_D2.setScaleType(ImageView.ScaleType.FIT_XY);
        p2_D3.setScaleType(ImageView.ScaleType.FIT_XY);
        p2_D4.setScaleType(ImageView.ScaleType.FIT_XY);
        p2_D5.setScaleType(ImageView.ScaleType.FIT_XY);
        p2_D6.setScaleType(ImageView.ScaleType.FIT_XY);
        p2_D7.setScaleType(ImageView.ScaleType.FIT_XY);
        p2_D8.setScaleType(ImageView.ScaleType.FIT_XY);

        player3_Card1.setScaleType(ImageView.ScaleType.FIT_XY);
        player3_Card2.setScaleType(ImageView.ScaleType.FIT_XY);
        p3_D1.setScaleType(ImageView.ScaleType.FIT_XY);
        p3_D2.setScaleType(ImageView.ScaleType.FIT_XY);
        p3_D3.setScaleType(ImageView.ScaleType.FIT_XY);
        p3_D4.setScaleType(ImageView.ScaleType.FIT_XY);
        p3_D5.setScaleType(ImageView.ScaleType.FIT_XY);
        p3_D6.setScaleType(ImageView.ScaleType.FIT_XY);
        p3_D7.setScaleType(ImageView.ScaleType.FIT_XY);
        p3_D8.setScaleType(ImageView.ScaleType.FIT_XY);

        //this textview updates the human player with the latest actions and information
        cardInfo = (TextView) myActivity.findViewById(R.id.helpText); // sets cardInfo to the helpText TextView

        //displays correct information to the human player
        player1GoldCount = (TextView) myActivity.findViewById(R.id.p1_Gold);
        player2GoldCount = (TextView) myActivity.findViewById(R.id.p2_gold);
        player3GoldCount = (TextView) myActivity.findViewById(R.id.p3_Gold);
        player1Score = (TextView) myActivity.findViewById(R.id.P1_Score);
        player2Score = (TextView) myActivity.findViewById(R.id.p2_Score);
        player3Score = (TextView) myActivity.findViewById(R.id.p3_Score);
        player2DistrictCards = (TextView) myActivity.findViewById(R.id.p2_dc);
        player3DistrictCards = (TextView) myActivity.findViewById(R.id.p3_Dc);

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

        //initializes the actions array with legal actions human player can take
        this.player1HandSpinner = (Spinner) myActivity.findViewById(R.id.player1HandSpinner);
        actionSpinner = (Spinner) myActivity.findViewById(R.id.actionSpinner);


        // define a listener for the spinner
        String[] p1ActionSpinnerNames = myActivity.getResources().getStringArray(p1Action);
        actionSpinner.setOnItemSelectedListener(new P1ActionSpinnerListener());

        //initialize the array adapter
        ArrayAdapter adapter = new ArrayAdapter<String>(myActivity, android.R.layout.simple_list_item_1,
                android.R.id.text1, p1ActionSpinnerNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //connect spinner to the adapter
        actionSpinner.setAdapter(adapter);
    }

    //this allows us to pass in a character and find a string representation of the name for display purposes
    public String getStringName(int character)
    {
        String theCharacter;
        if(character == 0)
        {
            theCharacter = "Assassin";
        }else if(character == 1)
        {
            theCharacter = "Thief";
        }else if(character == 2)
        {
            theCharacter = "Magician";
        }else if(character == 3)
        {
            theCharacter = "King";
        }else if(character == 4)
        {
            theCharacter = "Bishop";
        }else if(character == 5)
        {
            theCharacter = "Merchant";
        }else if(character == 6)
        {
            theCharacter = "Architect";
        }else if(character == 7)
        {
            theCharacter = "Warlord";
        }else{  theCharacter = null;    }
        return theCharacter;
    }

    //this is called AFTER we have a reference to the state. It updates all info and initializes button listeners
    public void initializeEverything()
    {
        // set values for all players' gold
        player1GoldCount.setText("Gold: " + state.getP1Gold());
        player2GoldCount.setText("Gold: " + state.getP2Gold());
        player3GoldCount.setText("Gold: " + state.getP3Gold());

        player1Score.setText("Score: " + state.getP1Score());
        player2Score.setText("Score: " + state.getP2Score());
        player3Score.setText("Score: " + state.getP3Score());

        player2DistrictCards.setText("Districts: " + state.getP2Hand().size());
        player3DistrictCards.setText("Districts: " + state.getP3Hand().size());

        this.p1HandArrayList = state.getP1HandNames();
        p1HandAdapter = new ArrayAdapter(myActivity, android.R.layout.simple_list_item_1,
                android.R.id.text1, p1HandArrayList);
        p1HandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.player1HandSpinner.setAdapter(p1HandAdapter);
        this.player1HandSpinner.setOnItemSelectedListener(new P1HandSpinnerListener());

        drawCharacterCard(player1_Card1, state.getP1Character1());
        drawCharacterCard(player1_Card2, state.getP1Character2());
        drawCharacterCard(player2_Card1, state.getP2Character1());
        drawCharacterCard(player2_Card2, state.getP2Character2());
        drawCharacterCard(player3_Card1, state.getP3Character1());
        drawCharacterCard(player3_Card2, state.getP3Character2());
        drawCharacterCard(face1_Up, state.getCharacter1FaceUp());
        drawCharacterCard(face2_Up, state.getCharacter2FaceUp());

        //draws images
        for(int i = 0; i < state.getP1City().size(); ++i)
        {
            if( i == 0)
            {
                drawCityCard(p1_D1, state.getP1CityCard(i).getName());
            }
            else if (i == 1)
            {
                drawCityCard(p1_D2, state.getP1CityCard(i).getName());
            }
            else if (i == 2)
            {
                drawCityCard(p1_D3, state.getP1CityCard(i).getName());
            }
            else if (i == 3)
            {
                drawCityCard(p1_D4, state.getP1CityCard(i).getName());
            }
            else if (i == 4)
            {
                drawCityCard(p1_D5, state.getP1CityCard(i).getName());
            }
            else if (i == 5)
            {
                drawCityCard(p1_D6, state.getP1CityCard(i).getName());
            }
            else if (i == 6)
            {
                drawCityCard(p1_D7, state.getP1CityCard(i).getName());
            }
            else if (i == 7)
            {
                drawCityCard(p1_D8, state.getP1CityCard(i).getName());
            }
        }

        //draws images
        for(int i = 0; i < state.getP2City().size(); ++i)
        {
            if( i == 0)
            {
                drawCityCard(p2_D1, state.getP2CityCard(i).getName());
            }
            else if (i == 1)
            {
                drawCityCard(p2_D2, state.getP2CityCard(i).getName());
            }
            else if (i == 2)
            {
                drawCityCard(p2_D3, state.getP2CityCard(i).getName());
            }
            else if (i == 3)
            {
                drawCityCard(p2_D4, state.getP2CityCard(i).getName());
            }
            else if (i == 4)
            {
                drawCityCard(p2_D5, state.getP2CityCard(i).getName());
            }
            else if (i == 5)
            {
                drawCityCard(p2_D6, state.getP2CityCard(i).getName());
            }
            else if (i == 6)
            {
                drawCityCard(p2_D7, state.getP2CityCard(i).getName());
            }
            else if (i == 7)
            {
                drawCityCard(p2_D8, state.getP2CityCard(i).getName());
            }
        }

        //draws images
        for(int i = 0; i < state.getP3City().size(); ++i)
        {
            if( i == 0)
            {
                drawCityCard(p3_D1, state.getP3CityCard(i).getName());
            }
            else if (i == 1)
            {
                drawCityCard(p3_D2, state.getP3CityCard(i).getName());
            }
            else if (i == 2)
            {
                drawCityCard(p3_D3, state.getP3CityCard(i).getName());
            }
            else if (i == 3)
            {
                drawCityCard(p3_D4, state.getP3CityCard(i).getName());
            }
            else if (i == 4)
            {
                drawCityCard(p3_D5, state.getP3CityCard(i).getName());
            }
            else if (i == 5)
            {
                drawCityCard(p3_D6, state.getP3CityCard(i).getName());
            }
            else if (i == 6)
            {
                drawCityCard(p3_D7, state.getP3CityCard(i).getName());
            }
            else if (i == 7)
            {
                drawCityCard(p3_D8, state.getP3CityCard(i).getName());
            }
        }

        //draws character images
        drawCharacterCard(player1_Card1, state.getP1Character1());
        drawCharacterCard(player1_Card2, state.getP1Character2());
        drawCharacterCard(player2_Card1, state.getP2Character1());
        drawCharacterCard(player2_Card2, state.getP2Character2());
        drawCharacterCard(player3_Card1, state.getP3Character1());
        drawCharacterCard(player3_Card2, state.getP3Character2());

        assassinButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (isTurnChar) {
                    if (state.getCharacterFromDeck(0) != null) {
                        if (chosenFirstCharacter == false) {
                            humanPlayerChooseCharacterCard1(0);
                            chosenFirstCharacter = true;
                        } else if (chosenSecondCharacter == false) {
                            humanPlayerChooseCharacterCard2(0);
                            chosenSecondCharacter = true;
                        }
                        cardInfo.setText("You've chosen the assassin card");
                    } else {
                        if (chosenFirstCharacter == false) {
                            humanPlayerChooseCharacterCard1(-1);
                        } else if (chosenSecondCharacter == false) {
                            humanPlayerChooseCharacterCard2(-1);
                        }
                        cardInfo.setText("This card is already taken");
                    }
                    if (chosenFirstCharacter && chosenSecondCharacter) {
                        chosenFirstCharacter = false;
                        chosenSecondCharacter = false;
                    }
                }

            }
        });
        thiefButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (isTurnChar) {
                    if (state.getCharacterFromDeck(1) != null) {
                        if (chosenFirstCharacter == false) {
                            humanPlayerChooseCharacterCard1(1);
                            chosenFirstCharacter = true;
                        } else if (chosenSecondCharacter == false) {
                            humanPlayerChooseCharacterCard2(1);
                            chosenSecondCharacter = true;
                        }
                        cardInfo.setText("You've chosen the thief card");
                    } else {
                        if (chosenFirstCharacter == false) {
                            humanPlayerChooseCharacterCard1(-1);
                        } else if (chosenSecondCharacter == false) {
                            humanPlayerChooseCharacterCard2(-1);
                        }
                        cardInfo.setText("This card is already taken");
                    }

                    if (chosenFirstCharacter && chosenSecondCharacter) {
                        chosenFirstCharacter = false;
                        chosenSecondCharacter = false;
                    }
                }

            }
        });


        magicianButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (isTurnChar) {
                    if (state.getCharacterFromDeck(2) != null) {
                        if (chosenFirstCharacter == false) {
                            humanPlayerChooseCharacterCard1(2);
                            chosenFirstCharacter = true;
                        } else if (chosenSecondCharacter == false) {
                            humanPlayerChooseCharacterCard2(2);
                            chosenSecondCharacter = true;
                        }
                        cardInfo.setText("You've chosen the magician card");
                    } else {
                        if (chosenFirstCharacter == false) {
                            humanPlayerChooseCharacterCard1(-1);
                        } else if (chosenSecondCharacter == false) {
                            humanPlayerChooseCharacterCard2(-1);
                        }
                        cardInfo.setText("This card is already taken");
                    }
                    if (chosenFirstCharacter && chosenSecondCharacter) {
                        chosenFirstCharacter = false;
                        chosenSecondCharacter = false;
                    }
                }
            }
        });


        kingButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (isTurnChar) {
                    if (state.getCharacterFromDeck(3) != null) {
                        if (chosenFirstCharacter == false) {
                            humanPlayerChooseCharacterCard1(3);
                            chosenFirstCharacter = true;
                        } else if (chosenSecondCharacter == false) {
                            humanPlayerChooseCharacterCard2(3);
                            chosenSecondCharacter = true;
                        }
                        cardInfo.setText("You've chosen the king card");
                    } else {
                        if (chosenFirstCharacter == false) {
                            humanPlayerChooseCharacterCard1(-1);
                        } else if (chosenSecondCharacter == false) {
                            humanPlayerChooseCharacterCard2(-1);
                        }
                        cardInfo.setText("This card is already taken");
                    }
                    if (chosenFirstCharacter && chosenSecondCharacter) {
                        chosenFirstCharacter = false;
                        chosenSecondCharacter = false;
                    }
                }
            }
        });

        bishopButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                if (isTurnChar) {
                    if (state.getCharacterFromDeck(4) != null) {
                        if (chosenFirstCharacter == false) {
                            humanPlayerChooseCharacterCard1(4);
                            chosenFirstCharacter = true;
                        } else if (chosenSecondCharacter == false) {
                            humanPlayerChooseCharacterCard2(4);
                            chosenSecondCharacter = true;
                        }
                        cardInfo.setText("You've chosen the bishop card");
                    } else {
                        if (chosenFirstCharacter == false) {
                            humanPlayerChooseCharacterCard1(-1);
                        } else if (chosenSecondCharacter == false) {
                            humanPlayerChooseCharacterCard2(-1);
                        }
                        cardInfo.setText("This card is already taken");
                    }
                    if (chosenFirstCharacter && chosenSecondCharacter) {
                        chosenFirstCharacter = false;
                        chosenSecondCharacter = false;
                    }
                }
            }
        });

        merchantButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(isTurnChar) {
                    if(state.getCharacterFromDeck(5) != null) {
                        if (chosenFirstCharacter == false)
                        {
                            humanPlayerChooseCharacterCard1(5);
                            chosenFirstCharacter = true;
                        }
                        else if (chosenSecondCharacter == false)
                        {
                            humanPlayerChooseCharacterCard2(5);
                            chosenSecondCharacter = true;
                        }
                        cardInfo.setText("You've chosen the merchant card");
                    }
                    else
                    {
                        if (chosenFirstCharacter == false) {
                            humanPlayerChooseCharacterCard1(-1);
                        }
                        else if (chosenSecondCharacter == false)
                        {
                            humanPlayerChooseCharacterCard2(-1);
                        }
                        cardInfo.setText("This card is already taken");
                    }
                    if(chosenFirstCharacter && chosenSecondCharacter)
                    {
                        chosenFirstCharacter = false;
                        chosenSecondCharacter = false;
                    }
                }
            }
        });

        architectButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(isTurnChar){
                    if(state.getCharacterFromDeck(6) != null) {
                        if (chosenFirstCharacter == false)
                        {
                            humanPlayerChooseCharacterCard1(6);
                            chosenFirstCharacter = true;
                        }
                        else if (chosenSecondCharacter == false)
                        {
                            humanPlayerChooseCharacterCard2(6);
                            chosenSecondCharacter = true;
                        }
                        cardInfo.setText("You've chosen the architect card");
                    }
                    else
                    {
                        if (chosenFirstCharacter == false) {
                            humanPlayerChooseCharacterCard1(-1);
                        }
                        else if (chosenSecondCharacter == false)
                        {
                            humanPlayerChooseCharacterCard2(-1);
                        }
                        cardInfo.setText("This card is already taken");
                    }
                    if(chosenFirstCharacter && chosenSecondCharacter)
                    {
                        chosenFirstCharacter = false;
                        chosenSecondCharacter = false;
                    }
                }
            }
        });

        warlordButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (isTurnChar){
                    if(state.getCharacterFromDeck(7) != null) {
                        if (chosenFirstCharacter == false)
                        {
                            humanPlayerChooseCharacterCard1(7);
                            chosenFirstCharacter = true;
                        }
                        else if (chosenSecondCharacter == false)
                        {
                            humanPlayerChooseCharacterCard2(7);
                            chosenSecondCharacter = true;
                        }
                        cardInfo.setText("You've chosen the warlord card");
                    }
                    else
                    {
                        if (chosenFirstCharacter == false) {
                            humanPlayerChooseCharacterCard1(-1);
                        }
                        else if (chosenSecondCharacter == false)
                        {
                            humanPlayerChooseCharacterCard2(-1);
                        }
                        cardInfo.setText("This card is already taken");
                    }
                    if(chosenFirstCharacter && chosenSecondCharacter)
                    {
                        chosenFirstCharacter = false;
                        chosenSecondCharacter = false;
                    }
                }
            }
        });

        //the following listeners allow the user to get the names of all players' characters
        player1_Card1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try {
                    cardInfo.setText("Your First Character is: " + getStringName(state.getP1Character1()));
                }catch(NullPointerException ie)
                {

                }
            }
        });

        player1_Card2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try {
                    cardInfo.setText("Your Second Character is: " + getStringName(state.getP1Character2()));
                }catch(NullPointerException ie)
                {

                }
            }
        });

        player2_Card1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try {
                    cardInfo.setText("Player 2's First Character is: " + getStringName(state.getP2Character1()));
                }catch(NullPointerException ie)
                {

                }
            }
        });

        player2_Card2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try {
                    cardInfo.setText("Player 2's Second Character is: " + getStringName(state.getP2Character2()));
                }catch(NullPointerException ie)
                {

                }
            }
        });

        player3_Card1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try {
                    cardInfo.setText("Player 3's First Character is: " + getStringName(state.getP3Character1()));
                }catch(NullPointerException ie)
                {

                }
            }
        });

        player3_Card2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try {
                    cardInfo.setText("Player 3's Second Character is: " + getStringName(state.getP3Character2()));
                }catch(NullPointerException ie)
                {

                }
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
               try
               {
                   card = state.getP1City().get(0);
                   if(card != null)
                   {
                       //displays card info (name, value, color)
                       cardInfo.setText(state.getP1DistrictInfo(0));
                   }
               }
               catch(IndexOutOfBoundsException e)
               {
               }
            }
        });

        p1_D2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    card = state.getP1City().get(1);
                    if(card != null)
                    {
                        //displays card info (name, value, color)
                        cardInfo.setText(state.getP1DistrictInfo(1));
                    }
                }
                catch(IndexOutOfBoundsException e)
                {
                }

            }
        });

        p1_D3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    card = state.getP1City().get(2);
                    if(card != null)
                    {
                        //displays card info (name, value, color)
                        cardInfo.setText(state.getP1DistrictInfo(2));
                    }
                }
                catch(IndexOutOfBoundsException e)
                {
                }
            }
        });

        p1_D4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    card = state.getP1City().get(3);
                    if(card != null)
                    {
                        //displays card info (name, value, color)
                        cardInfo.setText(state.getP1DistrictInfo(3));
                    }
                }
                catch(IndexOutOfBoundsException e)
                {
                }
            }
        });

        p1_D5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    card = state.getP1City().get(4);
                    if(card != null)
                    {
                        //displays card info (name, value, color)
                        cardInfo.setText(state.getP1DistrictInfo(4));
                    }
                }
                catch(IndexOutOfBoundsException e)
                {
                }
            }
        });

        p1_D6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    card = state.getP1City().get(5);
                    if(card != null)
                    {
                        //displays card info (name, value, color)
                        cardInfo.setText(state.getP1DistrictInfo(5));
                    }
                }
                catch(IndexOutOfBoundsException e)
                {
                }
            }
        });

        p1_D7.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    card = state.getP1City().get(6);
                    if(card != null)
                    {
                        //displays card info (name, value, color)
                        cardInfo.setText(state.getP1DistrictInfo(6));
                    }
                }
                catch(IndexOutOfBoundsException e)
                {
                }
            }
        });

        p1_D8.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    card = state.getP1City().get(7);
                    if(card != null)
                    {
                        //displays card info (name, value, color)
                        cardInfo.setText(state.getP1DistrictInfo(7));
                    }
                }
                catch(IndexOutOfBoundsException e)
                {
                }
            }
        });

        p2_D1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    card = state.getP2City().get(0);
                    if(card != null)
                    {
                        //displays card info (name, value, color)
                        cardInfo.setText(state.getP2DistrictInfo(0));
                    }
                }
                catch(IndexOutOfBoundsException e)
                {
                }
            }
        });

        p2_D2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    card = state.getP2City().get(1);
                    if(card != null)
                    {
                        //displays card info (name, value, color)
                        cardInfo.setText(state.getP2DistrictInfo(1));
                    }
                }
                catch(IndexOutOfBoundsException e)
                {
                }

            }
        });

        p2_D3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    card = state.getP2City().get(2);
                    if(card != null)
                    {
                        //displays card info (name, value, color)
                        cardInfo.setText(state.getP2DistrictInfo(2));
                    }
                }
                catch(IndexOutOfBoundsException e)
                {
                }
            }
        });

        p2_D4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    card = state.getP2City().get(3);
                    if(card != null)
                    {
                        //displays card info (name, value, color)
                        cardInfo.setText(state.getP2DistrictInfo(3));
                    }
                }
                catch(IndexOutOfBoundsException e)
                {
                }
            }
        });

        p2_D5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    card = state.getP2City().get(4);
                    if(card != null)
                    {
                        //displays card info (name, value, color)
                        cardInfo.setText(state.getP2DistrictInfo(4));
                    }
                }
                catch(IndexOutOfBoundsException e)
                {
                }
            }
        });

        p2_D6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    card = state.getP2City().get(5);
                    if(card != null)
                    {
                        //displays card info (name, value, color)
                        cardInfo.setText(state.getP2DistrictInfo(5));
                    }
                }
                catch(IndexOutOfBoundsException e)
                {
                }
            }
        });

        p2_D7.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    card = state.getP2City().get(6);
                    if(card != null)
                    {
                        //displays card info (name, value, color)
                        cardInfo.setText(state.getP2DistrictInfo(6));
                    }
                }
                catch(IndexOutOfBoundsException e)
                {
                }
            }
        });

        p2_D8.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    card = state.getP2City().get(7);
                    if(card != null)
                    {
                        //displays card info (name, value, color)
                        cardInfo.setText(state.getP2DistrictInfo(7));
                    }
                }
                catch(IndexOutOfBoundsException e)
                {
                }
            }
        });

        p3_D1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    card = state.getP3City().get(0);
                    if(card != null)
                    {
                        //displays card info (name, value, color)
                        cardInfo.setText(state.getP3DistrictInfo(0));
                    }
                }
                catch(IndexOutOfBoundsException e)
                {
                }
            }
        });

        p3_D2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    card = state.getP3City().get(1);
                    if(card != null)
                    {
                        //displays card info (name, value, color)
                        cardInfo.setText(state.getP3DistrictInfo(1));
                    }
                }
                catch(IndexOutOfBoundsException e)
                {
                }

            }
        });

        p3_D3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    card = state.getP3City().get(2);
                    if(card != null)
                    {
                        //displays card info (name, value, color)
                        cardInfo.setText(state.getP3DistrictInfo(2));
                    }
                }
                catch(IndexOutOfBoundsException e)
                {
                }
            }
        });

        p3_D4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    card = state.getP3City().get(3);
                    if(card != null)
                    {
                        //displays card info (name, value, color)
                        cardInfo.setText(state.getP3DistrictInfo(3));
                    }
                }
                catch(IndexOutOfBoundsException e)
                {
                }
            }
        });

        p3_D5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    card = state.getP3City().get(4);
                    if(card != null)
                    {
                        //displays card info (name, value, color)
                        cardInfo.setText(state.getP3DistrictInfo(4));
                    }
                }
                catch(IndexOutOfBoundsException e)
                {
                }
            }
        });

        p3_D6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    card = state.getP3City().get(5);
                    if(card != null)
                    {
                        //displays card info (name, value, color)
                        cardInfo.setText(state.getP3DistrictInfo(5));
                    }
                }
                catch(IndexOutOfBoundsException e)
                {
                }
            }
        });

        p3_D7.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    card = state.getP3City().get(6);
                    if(card != null)
                    {
                        //displays card info (name, value, color)
                        cardInfo.setText(state.getP3DistrictInfo(6));
                    }
                }
                catch(IndexOutOfBoundsException e)
                {
                }
            }
        });

        p3_D8.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    card = state.getP3City().get(7);
                    if(card != null)
                    {
                        //displays card info (name, value, color)
                        cardInfo.setText(state.getP3DistrictInfo(7));
                    }
                }
                catch(IndexOutOfBoundsException e)
                {
                }
            }
        });

        face1_Up.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

        face2_Up.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

        if(state.getPlayerTurn() == 0)
        {
            //cardInfo.setText("It is your turn.");
            actionSpinner.setVisibility(View.VISIBLE);
            /*
            I will be putting a boolean in to check and see if we have received confirmation that it is our
            turn.

            Maybe add a counter at the bottom that will show which character's turn it is

            Make infoText display winner at game end

            Get checkIfGameOver to work
             */
        }else
        {
            actionSpinner.setVisibility(View.INVISIBLE);
        }
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

    //allows human player to build a district card, or at least try
    public void humanPlayerBuildDistrict(CitadelsDistrictCard cdc)
    {
        game.sendAction(new CitadelsBuildDistrictCard(this, cdc));
    }

    //this doesn't do anything yet- alpha sets characters automatically
    public void humanPlayerChooseCharacterCard1(int theCharacterCard1)
    {
        game.sendAction((new ChooseCharacterCard(this, theCharacterCard1)));
    }

    public void humanPlayerChooseCharacterCard2(int theCharacterCard2)
    {
        game.sendAction((new ChooseCharacterCard(this, theCharacterCard2)));
    }

    //gives us a reference to which district card is being built
    public void setWhichCard(CitadelsDistrictCard cdc)
    {
        this.cdc = cdc;
    }

    public boolean checkTurn()
    {
        if(state.getTurn() == 10 || state.getTurn() == 13)
        {
            isTurnChar = true;
        }
        return isTurnChar;
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
        //nothing
    }

    private class P1HandSpinnerListener implements AdapterView.OnItemSelectedListener
    {

        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                   int position, long id)
        {
            //this doesn't do anything except tell us which card is in the position of the spinner to be built
            selectedCard = position;
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
                //cardInfo.setText("");
            }else if(position == 1)
            {
                if (! hasGone)
                {
                    //take gold
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
                    //draw a card
                    CitadelsDistrictCard cdc = state.getTopCard();
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
            }
            else if(position == 3)
            {
                //make sure there is something in the district card array
                if (hasBuilt == false && state.getP1Hand().size() != 0)
                {
                    CitadelsDistrictCard cardToBuild = (CitadelsDistrictCard)state.getP1Hand().get(selectedCard);
                    if(state.getP1Gold() >= cardToBuild.getCost())
                   {
                       //if p1 has enough gold, build a district card
                       cardInfo.setText(cardToBuild.getName() + " Built.");
                       humanPlayerBuildDistrict(cardToBuild);
                       p1HandAdapter.remove(p1HandAdapter.getItem(selectedCard));
                       p1HandAdapter.notifyDataSetChanged();
                       hasBuilt = true;
                   }else
                    {
                        cardInfo.setText("Sorry, You Cannot Afford That.\nPlease Select Another Action.");
                        actionSpinner.setSelection(0);
                    }

                }else
                {
                    //do nothing, they aren't allowed to build more
                    cardInfo.setText("You have already built a district");
                }
            }
            else if(position == 4)
            {
                if(! hasGoneAbility)
                {
                    //use character ability
                    humanPlayerUseAbility();
                    hasGoneAbility = true;
                    cardInfo.setText("Character Ability Used.");
                }
                else
                {
                    //do nothing--- they cannot go
                    cardInfo.setText("You have already used your ability.");
                }
            }else if(position == 5)
            {
                if(hasGone)
                {
                    //end the turn
                    humanPlayerEndTurn();
                    hasGone = false;
                    hasBuilt = false;
                    hasGoneAbility = false;
                    cardInfo.setText("Turn Ended.");
                    actionSpinner.setSelection(0);
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
