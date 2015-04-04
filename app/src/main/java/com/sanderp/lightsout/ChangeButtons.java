package com.sanderp.lightsout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class ChangeButtons extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_buttons);

        Intent intent = this.getIntent();
        int nButtons = intent.getIntExtra(MainActivity.KEY_NUM_BUTTONS, -1);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);

        switch (nButtons) {
            case 3:
                radioGroup.check(R.id.radio3);
                break;
            case 5:
                radioGroup.check(R.id.radio5);
                break;
            case 7:
                radioGroup.check(R.id.radio7);
                break;
            case 9:
                radioGroup.check(R.id.radio9);
                break;
        }

        ((RadioButton) findViewById(R.id.radio3)).setOnClickListener(this);
        ((RadioButton) findViewById(R.id.radio5)).setOnClickListener(this);
        ((RadioButton) findViewById(R.id.radio7)).setOnClickListener(this);
        ((RadioButton) findViewById(R.id.radio9)).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_change_buttons, menu);
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
        Intent result = new Intent();
        switch (v.getId()) {
            case R.id.radio3:
                Log.d(MainActivity.LLOM, "You clicked radio button 3");
                result.putExtra(MainActivity.KEY_NUM_BUTTONS, 3);
                break;
            case R.id.radio5:
                Log.d(MainActivity.LLOM, "You clicked radio button 5");
                result.putExtra(MainActivity.KEY_NUM_BUTTONS, 5);
                break;
            case R.id.radio7:
                Log.d(MainActivity.LLOM, "You clicked radio button 7");
                result.putExtra(MainActivity.KEY_NUM_BUTTONS, 7);
                break;
            case R.id.radio9:
                Log.d(MainActivity.LLOM, "You clicked radio button 9");
                result.putExtra(MainActivity.KEY_NUM_BUTTONS, 9);
                break;
        }
        setResult(Activity.RESULT_OK, result);
        finish();
    }
}
