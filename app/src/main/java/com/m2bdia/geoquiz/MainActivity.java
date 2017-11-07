package com.m2bdia.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private Button mBoutonVrai;
    private Button mBoutonFaux;
    private Button mBoutonSuivant;
    private TextView mQuestionTextView;
    private Button mBoutonAide;
    private boolean mEstTricheur;
    private VraiFaux[] mTabQuestions = new VraiFaux[]{
            new VraiFaux(R.string.question_oceans,true),
            new VraiFaux(R.string.question_africa,true),
            new VraiFaux(R.string.question_americas,false),
            new VraiFaux(R.string.question_asia,true),
            new VraiFaux(R.string.question_mideast,true)
    };
    private int mIndexActuel = 0;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            mEstTricheur = data.getBooleanExtra(AideActivity.EXTRA_REPONSE_AFFICHEE,false);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"OnSaveInstanceState");
        outState.putInt(KEY_INDEX,mIndexActuel);
    }

    private void majQuestion(){
        int question = mTabQuestions[mIndexActuel].getQuestion();
        mQuestionTextView.setText(question);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy appelee");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart appelee");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop appelee");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause appelee");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume appelee");
    }

    private void verifieReponse(boolean userVrai){
        boolean reponseVraie = mTabQuestions[mIndexActuel].isQuestionVraie();
        int messReponseId=0;
        if(mEstTricheur){
            messReponseId = R.string.toast_aide;
        }else {
            if (userVrai == reponseVraie) {
                messReponseId = R.string.toast_correct;
            } else {
                messReponseId = R.string.toast_faux;
            }
        }
        Toast.makeText(MainActivity.this,messReponseId,Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"OnCreate appelee");
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            mIndexActuel = savedInstanceState.getInt(KEY_INDEX,0);
        }
       //    Caused by: java.lang.NullPointerException: Attempt to invoke virtual method 'void android.widget.TextView.setText(int)' on a null object reference
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        int question = mTabQuestions[mIndexActuel].getQuestion();
        mQuestionTextView.setText(question);
        mBoutonVrai = (Button)findViewById(R.id.bouton_vrai);
        mBoutonVrai.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                verifieReponse(true);
                mIndexActuel = (mIndexActuel +1)%mTabQuestions.length;
                majQuestion();
            }
        });
        mBoutonFaux = (Button)findViewById(R.id.bouton_faux);
        mBoutonFaux.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                verifieReponse(false);
                mIndexActuel = (mIndexActuel +1)%mTabQuestions.length;
                majQuestion();
            }
        });
        mBoutonSuivant=(Button)findViewById(R.id.bouton_suivant);
        mBoutonSuivant.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mIndexActuel = (mIndexActuel +1)%mTabQuestions.length;
                majQuestion();
            }
        });
        mBoutonAide=(Button)findViewById(R.id.bouton_aide);
        mBoutonAide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intention = new Intent(MainActivity.this,AideActivity.class);
                boolean reponseVraie = mTabQuestions[mIndexActuel].isQuestionVraie();
                intention.putExtra(AideActivity.EXTRA_REPONSE_VRAIE,reponseVraie);
                //startActivity(intention);
                startActivityForResult(intention,2017);
            }
        });
        majQuestion();
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
}
