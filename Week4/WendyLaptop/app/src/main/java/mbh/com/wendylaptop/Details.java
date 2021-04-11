package mbh.com.wendylaptop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import mbh.com.wendylaptop.ui.main.DetailsFragment;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, DetailsFragment.newInstance())
                    .commitNow();
        }
    }
}