package boost.app.emergentassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Assistant extends AppCompatActivity {
private static TextView tt;
    private static Button btloginAssistant;
    private static Button btregisterAssistant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistant);
        tt = (TextView)findViewById(R.id.textView1);
        btloginAssistant= (Button)findViewById(R.id.button6);
        btregisterAssistant= (Button)findViewById(R.id.button5);

        btloginAssistant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Assistant.this, LoginAssitants.class));
            }
        });


        btregisterAssistant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Assistant.this, RegisterAssistant.class));
            }
        });
    }
}
