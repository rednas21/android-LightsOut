package com.sanderp.lightsout;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class LightsOutActivity extends ActionBarActivity implements View.OnClickListener {

    private static int NUM_BUTTONS = 7;
    private LightsOutGame mGame;
    private TextView mGameStatus;
    private Button[] mGameButtons;
    private Button mNewGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lights_out);

        // Initialize the game status text view
        mGameStatus = (TextView) findViewById(R.id.game_status);

        // Initialize the game buttons
        mGameButtons = new Button[NUM_BUTTONS];

        mGameButtons[0] = (Button) findViewById(R.id.button0);
        mGameButtons[1] = (Button) findViewById(R.id.button1);
        mGameButtons[2] = (Button) findViewById(R.id.button2);
        mGameButtons[3] = (Button) findViewById(R.id.button3);
        mGameButtons[4] = (Button) findViewById(R.id.button4);
        mGameButtons[5] = (Button) findViewById(R.id.button5);
        mGameButtons[6] = (Button) findViewById(R.id.button6);

        for(int button = 0; button < NUM_BUTTONS; button++) {
            mGameButtons[button].setOnClickListener(this);
        }

        // Initialize new game button
        mNewGame = (Button) findViewById(R.id.button_new_game);
        mNewGame.setOnClickListener(this);

        // Initialize the game
        mGame = new LightsOutGame(NUM_BUTTONS);
        mGame.newGame();
        updateView();
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
        if(v.getId() == R.id.button_new_game) {
            mGame.newGame();
        } else {
            for(int button = 0; button < NUM_BUTTONS; button++) {
                if(v.getId() == mGameButtons[button].getId()) {
                    mGame.pressedButtonAtIndex(button);
                    break;
                }
            }
        }

        updateView();

        if (mGame.gameOver()) {
            mGameStatus.setText(R.string.game_win);
            for(int button = 0; button < NUM_BUTTONS; button++) {
                mGameButtons[button].setEnabled(false);
            }
        } else {
            mGameStatus.setText(R.string.game_win);
            for(int button = 0; button < NUM_BUTTONS; button++) {
                mGameButtons[button].setEnabled(true);
            }
        }
    }

    private void updateView() {
        for(int button = 0; button < NUM_BUTTONS; button++) {
            String stringValue = "" + mGame.getButtonValue(button);
            mGameButtons[button].setText(stringValue);
        }
    }
}
