package com.example.android.trivadelphia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<QuestionAnswer> qaList;
    int score = 0;
    int qid = 0; // reference to question id in the database (eventually qaList)

    QuestionAnswer currentQuestion;
    TextView txtQuestion; // used to pass the question to the UI
    RadioButton rbOne, rbTwo, rbThree; // used to pass the answers to the UI
    Button nextButton; // button to submit answer and move to next question

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper db = new DbHelper(this);
        qaList = db.getAllQuestions();
        currentQuestion = qaList.get(qid);
        txtQuestion = (TextView) findViewById(R.id.qaView);
        rbOne = (RadioButton) findViewById(R.id.radio1);
        rbTwo = (RadioButton) findViewById(R.id.radio2);
        rbThree = (RadioButton) findViewById(R.id.radio3);
        nextButton = (Button) findViewById(R.id.nextButton);
        setQAView();

        // handles app behavior when the nextButton is pressed
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup1);
                RadioButton answer = (RadioButton) findViewById(grp.getCheckedRadioButtonId());
                if (currentQuestion.getAnswer().equals(answer.getText())) {
                    score++;
                    Log.d("score", "Your score" + score); // log statement for testing score
                }
                // change when more questions are added (use rowCount())
                if (qid < 6) {
                    currentQuestion = qaList.get(qid);
                    setQAView();
                } else {
                    // creates intent for launching the result screen
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    Bundle b = new Bundle();
                    // puts the score in a Bundle so it can be passed to the ResultActivity
                    b.putInt("score", score);
                    // puts the Bundle into the Intent
                    intent.putExtras(b);
                    // starts the ResultActivity with score values stored
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // passes the questions and answers to the UI Views
    private void setQAView() {
        txtQuestion.setText(currentQuestion.getQuestion());
        rbOne.setText(currentQuestion.getOptionOne());
        rbTwo.setText(currentQuestion.getOptionTwo());
        rbThree.setText(currentQuestion.getOptionThree());
        qid++;
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