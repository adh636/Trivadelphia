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
        TextView t = (TextView) findViewById(R.id.textResult);
        // ImageView rP = (ImageView) findViewById(R.id.resultPic);
        Bundle b = getIntent().getExtras();
        int score = b.getInt("score");
        tv.setText(Integer.toString(score));
        switch (score) {
            case 1:
                t.setText("Your illiteracy has screwed us again!");
                // Drawable myDrawable = ContextCompat.getDrawable(this, R.drawable.charlieoops);
                // rP.setImageDrawable(myDrawable);
                break;
            case 2:
                t.setText("Get your shit together dog!");
                // Drawable myDrawable1 = ContextCompat.getDrawable(this, R.drawable.charlieroids);
                // rP.setImageDrawable(myDrawable1);
                break;
            case 3:
                t.setText("Gotta separate the wheat from the chaff somehow");
                // Drawable myDrawable2 = ContextCompat.getDrawable(this, R.drawable.pepesilvia);
                // rP.setImageDrawable(myDrawable2);
                break;
            case 4:
                t.setText("You're like a big round wizard");
                // Drawable myDrawable3 = ContextCompat.getDrawable(this, R.drawable.water);
                // rP.setImageDrawable(myDrawable3);
                break;
            case 5:
                t.setText("Firing on all cylinders");
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
