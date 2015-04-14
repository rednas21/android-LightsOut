package com.sanderp.lightsout;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;


public class LightsOutActivity extends Activity implements View.OnClickListener {

    private int mNumButtons;
    private LightsOutGame mGame;
    private TextView mGameStatus;
    private ArrayList<Button> mButtons;
    private Button mNewGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lights_out);

        Intent data = getIntent();
        mNumButtons = data.getIntExtra(MainActivity.KEY_NUM_BUTTONS, 7);
        Log.d(MainActivity.LLOM, "Got " + mNumButtons + " buttons.");

        // Initialize the buttons
        mButtons = new ArrayList<Button>();
        TableRow buttonRow = new TableRow(this);

        for (int i = 0; i < mNumButtons; i++) {
            Button button = new Button(this);
            button.setTag(new Integer(i));
            mButtons.add(button);
            buttonRow.addView(button);
            button.setOnClickListener(this);
        }

        TableLayout buttonTable = (TableLayout) findViewById(R.id.button_table);
        buttonTable.addView(buttonRow);

        // Initialize the game status text view
        mGameStatus = (TextView) findViewById(R.id.game_state);

        // Initialize new game button
        mNewGame = (Button) findViewById(R.id.button_new_game);
        mNewGame.setOnClickListener(this);

        // Initialize the game
        mGame = new LightsOutGame(mNumButtons);
        updateView(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        boolean isWin = false;
        if (v.getId() == R.id.button_new_game) {
            Log.d(MainActivity.LLOM, "New game pressed");
            mGame = new LightsOutGame(mNumButtons);
        } else {
            Log.d(MainActivity.LLOM, "Button with tag " + v.getTag());
            isWin = mGame.pressedButtonAtIndex((Integer) v.getTag());
        }

        updateView(isWin);
    }

    private void updateView(boolean isWin) {
        for(int button = 0; button < mNumButtons; button++) {
            mButtons.get(button).setText("" + mGame.getValueAtIndex(button));
            mButtons.get(button).setEnabled(!isWin);
        }

        Resources r = this.getResources();
        String newGameString;
        int nPresses = mGame.getNumPresses();
        if (isWin) {
            if (nPresses == 1) {
                newGameString = r.getString(R.string.you_won_one_move);
            } else {
                newGameString = r.getString(R.string.you_won_format, nPresses);
            }
        } else {
            if (nPresses == 0) {
                newGameString = r.getString(R.string.game_start);
            } else if (nPresses == 1) {
                newGameString = r.getString(R.string.game_one_move);
            } else {
                newGameString = r.getString(R.string.game_format, nPresses);
            }
        }
        mGameStatus.setText(newGameString);
    }
}
