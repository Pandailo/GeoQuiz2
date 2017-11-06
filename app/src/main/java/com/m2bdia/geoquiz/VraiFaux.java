package com.m2bdia.geoquiz;

/**
 * Created by user on 06/11/2017.
 */

public class VraiFaux {
    private int mQuestion;
    private boolean mQuestionVraie;

    public VraiFaux(int question, boolean questionVrai){
        mQuestion  = question;
        mQuestionVraie = questionVrai;
    }

    public boolean isQuestionVraie() {
        return mQuestionVraie;
    }

    public void setQuestionVraie(boolean questionVraie) {
        mQuestionVraie = questionVraie;
    }

    public int getQuestion() {

        return mQuestion;
    }

    public void setQuestion(int question) {
        mQuestion = question;
    }
}
