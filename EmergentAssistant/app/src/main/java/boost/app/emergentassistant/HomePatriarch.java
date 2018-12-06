package boost.app.emergentassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.google.firebase.auth.FirebaseAuth;
import android.speech.RecognizerIntent;
import android.content.ActivityNotFoundException;
import android.widget.Button;
import android.view.Menu;
import java.util.ArrayList;
import java.util.Locale;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class HomePatriarch extends AppCompatActivity {
    private static TextView tv;
    private static Button logout;
    private static TextView txtSpeechInput;
    private static ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_patriarch);
        tv = (TextView) findViewById(R.id.textView);
        logout = (Button) findViewById(R.id.button7);
        txtSpeechInput = (TextView) findViewById(R.id.textView7);
        btnSpeak = (ImageButton) findViewById(R.id.imageButton);


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
                    for(int i =0; i<result.size();i++){
                    txtSpeechInput.setText(result.get(i));
                    }
                }
                break;
            }
        }
    }
}