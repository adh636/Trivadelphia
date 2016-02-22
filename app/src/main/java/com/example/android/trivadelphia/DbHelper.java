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
    private static final String KEY_ANSWER = "answer";
    private static final String KEY_OPTA = "opta";
    private static final String KEY_OPTB = "optb";
    private static final String KEY_OPTC = "optc";
    private SQLiteDatabase dbase;

    // DbHelper constructor
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase =  db;
        // Set SQLite database parameters
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
                + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT)";
        db.execSQL(sql);
        addQuestions();
        //db.close();
    }

    // create the questions and use the addQuestion method to add them to the database
    private void addQuestions()
    {
        QuestionAnswer q1 = new QuestionAnswer("Why does Charlie need a second coat?",
                "To store his cheese", "To impress the waitress", "To protect his other coat", "To protect his other coat");
        this.addQuestion(q1);
        QuestionAnswer q2 = new QuestionAnswer("What animal does Sweet Dee most resemble?",
                "A hippo", "A bird", "A bear", "A bird");
        this.addQuestion(q2);
        QuestionAnswer q3 = new QuestionAnswer("In The Gang Gives Back, what does the gang do for community service?",
                "Interstate sanitation", "Mentor troubled youth",
                "Coach youth basketball","Coach youth basketball");
        this.addQuestion(q3);
        QuestionAnswer q4 = new QuestionAnswer("What does Frank dress up as for Halloween?",
                "Man-Spider", "The trash man", "An oompa loompa", "Man-Spider");
        this.addQuestion(q4);
        QuestionAnswer q5 = new QuestionAnswer("What is Mac's full name?",
                "Mackenzie Meehan","Macaulay Culkin","Ronald McDonald", "Ronald McDonald");
        this.addQuestion(q5);
        QuestionAnswer q6 = new QuestionAnswer("Why is there a tunnel between the stadium and the holiday inn?",
                "To smuggle in HGH", "Because Philly fans like to hammer the opposing team",
                "It's where the Philly Phanatic lives", "Because Philly fans like to hammer the opposing team");
        this.addQuestion(q6);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        onCreate(db);
    }

    // reads
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

    // gets all questions and adds them to an ArrayList to be used by MainActivity
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
    public int rowCount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }
}
