package com.sanderp.lightsout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String LLOM = "LLOM";
    private static int NUM_BUTTONS = 7;
    private Button mPlayButton;
    private Button mChangeButton;
    private Button mAboutButton;
    private Button mExitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayButton = (Button) findViewById(R.id.play_button);
        mPlayButton.setOnClickListener(this);
        mChangeButton = (Button) findViewById(R.id.change_num_buttons_button);
        mChangeButton.setOnClickListener(this);
        mAboutButton = (Button) findViewById(R.id.about_button);
        mAboutButton.setOnClickListener(this);
        mExitButton = (Button) findViewById(R.id.exit_button);
        mExitButton.setOnClickListener(this);
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
        switch (v.getId()) {
            case R.id.play_button:
                Log.d(LLOM, "Play button clicked");
                break;
            case R.id.change_num_buttons_button:
                Log.d(LLOM, "Change button clicked");
                break;
            case R.id.about_button:
                Log.d(LLOM, "You clicked about");
                Intent aboutIntent = new Intent(this, AboutActivity.class);
                this.startActivity(aboutIntent);
                break;
            case R.id.exit_button:
                Log.d(LLOM, "Exit button clicked");
                break;
        }
    }
}
