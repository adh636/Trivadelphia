package com.example.android.trivadelphia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 9/26/15.
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "triviaQuiz";
    private static final String TABLE_QUEST = "quest";
    private static final String KEY_ID = "id";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer"; //correct option
    private static final String KEY_OPTA= "opta"; //option a
    private static final String KEY_OPTB= "optb"; //option b
    private static final String KEY_OPTC= "optc"; //option c
    private SQLiteDatabase dbase;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase =  db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
                +KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT)";
        db.execSQL(sql);
        addQuestions();
        //db.close();
    }
    private void addQuestions()
    {
        QuestionAnswer q1 = new QuestionAnswer("What is the first name of Dennis and Dee's real father?","Francis", "Frank", "Bruce", "Bruce");
        this.addQuestion(q1);
        QuestionAnswer q2 = new QuestionAnswer("What does the first N in the D.E.N.N.I.S. system stand for?", "Neglect Emotionally", "Nurture Dependence", "Negate Value", "Nurture Dependence");
        this.addQuestion(q2);
        QuestionAnswer q3 = new QuestionAnswer("Dennis is asshole. Why Charlie Hate?","Because Dennis banged the waitress", "Dennis stole my grilled cheese","Because Dennis is a bastard man","Because Dennis is a bastard man");
        this.addQuestion(q3);
        QuestionAnswer q4 = new QuestionAnswer("In Lethal Weapon 6, who plays Murtaugh?", "Dennis and Mac", "Danny Glover", "Mac", "Dennis and Mac");
        this.addQuestion(q4);
        QuestionAnswer q5 = new QuestionAnswer("Who does Rick sell an anti-matter gun to?","Plemtonion Steve","Swatilligan James","Krombopulous Michael", "Krombopulous Michael");
        this.addQuestion(q5);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        onCreate(db);
    }

    public void addQuestion(QuestionAnswer quest) {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQuestion());
        values.put(KEY_ANSWER, quest.getAnswer());
        values.put(KEY_OPTA, quest.getOptionOne());
        values.put(KEY_OPTB, quest.getOptionTwo());
        values.put(KEY_OPTC, quest.getOptionThree());

        dbase.insert(TABLE_QUEST, null, values);
    }
    public List<QuestionAnswer> getAllQuestions() {
        List<QuestionAnswer> qaList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                QuestionAnswer quest = new QuestionAnswer();
                quest.setId(cursor.getInt(0));
                quest.setQuestion(cursor.getString(1));
                quest.setAnswer(cursor.getString(2));
                quest.setOptionOne(cursor.getString(3));
                quest.setOptionTwo(cursor.getString(4));
                quest.setOptionThree(cursor.getString(5));
                qaList.add(quest);
            } while (cursor.moveToNext());
        }

        return qaList;
    }

    // planning to use to determine number of questions for ResultActivity
    public int rowcount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }
}