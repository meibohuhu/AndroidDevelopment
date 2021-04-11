package mbh.com.intentimplicit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import mbh.com.intentimplicit.ui.intentimplicit.IntentImplicitFragment;

public class IntentImplicitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.implicit_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, IntentImplicitFragment.newInstance())
                    .commitNow();
        }
    }
}