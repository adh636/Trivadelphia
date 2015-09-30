package com.example.android.trivadelphia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView tv = (TextView) findViewById(R.id.ratingBar1);
        TextView tr = (TextView) findViewById(R.id.textResult);
        // ImageView rP = (ImageView) findViewById(R.id.resultPic);

        // retrieves Bundle from MainActivity
        Bundle b = getIntent().getExtras();
        // stores the score that was passed from MainActivity
        int score = b.getInt("score");
        // displays the score in the TextView for result
        tv.setText(Integer.toString(score) + " out of 5");
        // chooses which quote from the show to display based on user score
        switch (score) {
            case 1:
                tr.setText("Get your shit together dog!");
                // Drawable myDrawable = ContextCompat.getDrawable(this, R.drawable.charlieoops);
                // rP.setImageDrawable(myDrawable);
                break;
            case 2:
                tr.setText("Your illiteracy has screwed us again!");
                // Drawable myDrawable1 = ContextCompat.getDrawable(this, R.drawable.charlieroids);
                // rP.setImageDrawable(myDrawable1);
                break;
            case 3:
                tr.setText("Gotta separate the wheat from the chaff somehow");
                // Drawable myDrawable2 = ContextCompat.getDrawable(this, R.drawable.pepesilvia);
                // rP.setImageDrawable(myDrawable2);
                break;
            case 4:
                tr.setText("You're like a big round wizard");
                // Drawable myDrawable3 = ContextCompat.getDrawable(this, R.drawable.water);
                // rP.setImageDrawable(myDrawable3);
                break;
            case 5:
                tr.setText("Firing on all cylinders");
                // Drawable myDrawable4 = ContextCompat.getDrawable(this, R.drawable.cheers);
                // rP.setImageDrawable(myDrawable4);
                break;
        }
    }

    public void reset (View v) {
        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
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
}
