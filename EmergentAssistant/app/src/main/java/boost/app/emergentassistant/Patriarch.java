package boost.app.emergentassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Patriarch extends AppCompatActivity {
    private static TextView ttPat;
    private static Button btloginPatriarch;
    private static Button btregisterPatriarch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patriarch);
        ttPat= (TextView)findViewById(R.id.textView6);
        btloginPatriarch= (Button)findViewById(R.id.button4);
        btregisterPatriarch= (Button)findViewById(R.id.button3);

        btloginPatriarch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Patriarch.this, LoginPatriarch.class));
            }
        });
        btregisterPatriarch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Patriarch.this, RegisterPatriarch.class));
            }
        });

    }
}
