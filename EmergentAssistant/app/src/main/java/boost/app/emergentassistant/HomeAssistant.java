package boost.app.emergentassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.google.firebase.auth.FirebaseAuth;
import android.widget.Button;

public class HomeAssistant extends AppCompatActivity {
    private static Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_assistant);

        logout= (Button)findViewById(R.id.button8);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeAssistant.this, LoginAssitants.class);
                startActivity(intent);
                finish();
                FirebaseAuth.getInstance().signOut();

            }
        });
    }
}
