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
    int qid = 0;

    QuestionAnswer currentQuestion;
    TextView txtQuestion;
    RadioButton rbOne, rbTwo, rbThree;
    Button nextButton;

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

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup1);
                RadioButton answer = (RadioButton) findViewById(grp.getCheckedRadioButtonId());
                if (currentQuestion.getAnswer().equals(answer.getText())) {
                    score++;
                    Log.d("score", "Your score" + score);
                }
                if (qid < 5) {
                    currentQuestion = qaList.get(qid);
                    setQAView();
                } else {
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("score", score);
                    intent.putExtras(b);
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

    private void setQAView() {
        txtQuestion.setText(currentQuestion.getQuestion());
        rbOne.setText(currentQuestion.getOptionOne());
        rbTwo.setText(currentQuestion.getOptionTwo());
        rbThree.setText(currentQuestion.getOptionThree());
        qid++;
    }

    // possible use for ResultActivity?
    public int getScore() {
        return score;
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
