package com.m2bdia.geoquiz;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class AideActivity extends AppCompatActivity {
    private boolean mReponseVraie;
    private TextView mReponseTextView;
    private Button mAfficheReponse;
    public static final String EXTRA_REPONSE_VRAIE = "com.m2bdia.geoquiz.reponse_vraie";
    public static final String EXTRA_REPONSE_AFFICHEE = "com.m2bdia.geoquiz.reponse_affichee";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aide);

        final boolean mReponseVraie = getIntent().getBooleanExtra(EXTRA_REPONSE_VRAIE,false);
        mReponseTextView=(TextView)findViewById(R.id.reponseTextView);
        mAfficheReponse = (Button)findViewById(R.id.boutonAfficheAide);
        mAfficheReponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mReponseVraie){
                    mReponseTextView.setText(R.string.toast_correct);
                }
                else{
                    mReponseTextView.setText(R.string.toast_faux);
                }
                setReponseAffichee(true);
            }
        });
    }
    private void  setReponseAffichee(boolean isReponseAffichee){
        Intent donnees = new Intent();
        donnees.putExtra(EXTRA_REPONSE_AFFICHEE,isReponseAffichee);
        setResult(RESULT_OK,donnees);
    }
}
