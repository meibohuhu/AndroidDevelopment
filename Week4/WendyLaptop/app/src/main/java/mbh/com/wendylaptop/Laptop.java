package mbh.com.wendylaptop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import mbh.com.wendylaptop.ui.main.LaptopFragment;

public class Laptop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laptop_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, LaptopFragment.newInstance())
                    .commitNow();
        }
    }
}