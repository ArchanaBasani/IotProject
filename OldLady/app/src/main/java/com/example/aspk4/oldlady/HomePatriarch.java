package com.example.aspk4.oldlady;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomePatriarch extends AppCompatActivity {
    private static TextView tv;
    private static Button logout;
    private TextView txtSpeechInput;
    private static ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private static ImageView iv;
    private static EditText  et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_patriarch);
        tv = (TextView) findViewById(R.id.textView);
        logout = (Button) findViewById(R.id.button7);
        txtSpeechInput = (TextView) findViewById(R.id.textView7);
        btnSpeak = (ImageButton) findViewById(R.id.imageButton);
        iv = (ImageView) findViewById(R.id.submit_button);
        et = (EditText) findViewById(R.id.editText3);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePatriarch.this, LoginPatriarch.class);
                startActivity(intent);
                finish();
                FirebaseAuth.getInstance().signOut();

            }
        });

        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });



        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().push().setValue(new ChatMessage(et.getText().toString(),
                        FirebaseAuth.getInstance().getCurrentUser().getEmail()));
                et.setText(txtSpeechInput.getText());
                et.requestFocus();
            }
        });
    }


    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_not_supported));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);

        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result= data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    List<String> ealert = new ArrayList<String>();
                    // :::: To add the words for emergency alert::::
                    //::: ealert.add("sample");
                    ealert.add("food");
                    ealert.add("hungry");
                    ealert.add("help");
                    ealert.add("water");
                    ealert.add("shopping");
                    ealert.add("medicine");
                    for(int j=0; j<ealert.size();j++){
                    if(result.get(0).contains(ealert.get(j))) {
                        et.setText(result.get(0));
                        FirebaseDatabase.getInstance().getReference().push().setValue(new ChatMessage(et.getText().toString(),
                                FirebaseAuth.getInstance().getCurrentUser().getEmail()));
                    }}

                }
                break;
            }
        }
    }
}