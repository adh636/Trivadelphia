package com.example.android.trivadelphia;

/**
 * Created by Adam on 9/26/15.
 */
public class QuestionAnswer {

    private int id;
    private String question;
    private String answer;
    private String optionOne;
    private String optionTwo;
    private String optionThree;

    public QuestionAnswer() {
        id = 0;
        question = "";
        answer = "";
        optionOne = "";
        optionTwo = "";
        optionThree = "";
    }

    public QuestionAnswer(String dbQuestion, String dbOptionOne, String dbOptionTwo, String dbOptionThree, String dbAnswer) {
        question = dbQuestion;
        answer = dbAnswer;
        optionOne = dbOptionOne;
        optionTwo = dbOptionTwo;
        optionThree = dbOptionThree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getOptionOne() {
        return optionOne;
    }

    public void setOptionOne(String optionOne) {
        this.optionOne = optionOne;
    }

    public String getOptionTwo() {
        return optionTwo;
    }

    public void setOptionTwo(String optionTwo) {
        this.optionTwo = optionTwo;
    }

    public String getOptionThree() {
        return optionThree;
    }

    public void setOptionThree(String optionThree) {
        this.optionThree = optionThree;
    }

}
