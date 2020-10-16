package mbh.com.huquotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import mbh.com.huquotes.ui.main.QuotesFragment;

public class QuotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quotes_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, QuotesFragment.newInstance())
                    .commitNow();
        }
    }
}