package mbh.com.hufasteddy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import mbh.com.hufasteddy.ui.main.FastEddyFragment;

public class FastEddy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fast_eddy_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, FastEddyFragment.newInstance())
                    .commitNow();
        }
    }
}