package edu.up.citadels;

import java.util.ArrayList;
import java.util.List;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private Spinner player1HandSpinner;
    List<String> player1DistrictsCards = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1HandSpinner = (Spinner) findViewById(R.id.player1HandSpinner);

    }

    private class SpinnerLister implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }

}
