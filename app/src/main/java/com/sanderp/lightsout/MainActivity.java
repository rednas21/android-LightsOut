package com.sanderp.lightsout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener {

    public static final String LLOM = "LLOM";
    public static final String KEY_NUM_BUTTONS = "KEY_NUM_BUTTONS";
    private static final int REQUEST_CODE_CHANGE_BUTTON = 1;
    private int mNumButtons = 7;
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
                Intent playIntent = new Intent(this, LightsOutActivity.class);
                playIntent.putExtra(KEY_NUM_BUTTONS, mNumButtons);
                startActivity(playIntent);
                break;
            case R.id.change_num_buttons_button:
                Log.d(LLOM, "Change button clicked");
                Intent changeButtonsIntent = new Intent(this, ChangeButtons.class);
                changeButtonsIntent.putExtra(KEY_NUM_BUTTONS, mNumButtons);
                this.startActivityForResult(changeButtonsIntent, REQUEST_CODE_CHANGE_BUTTON);
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_CHANGE_BUTTON:
                if (resultCode == Activity.RESULT_OK) {
                    Log.d(LLOM, "Result ok!");

                    mNumButtons = data.getIntExtra(KEY_NUM_BUTTONS, 7);
                    Log.d(LLOM, "mNumButtons = " + mNumButtons);

                    String s = this.getString(R.string.play_format, mNumButtons);
                    mPlayButton.setText(s);
                } else {
                    Log.d(LLOM, "Result not okay. User hit back without a button");
                }
                break;
            default:
                Log.d(LLOM, "Unknown result code");
                break;
        }
    }
}
