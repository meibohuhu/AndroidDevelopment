package mbh.com.hufasteddy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import mbh.com.hufasteddy.ui.main.CarDetailsFragment;

public class CarDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_details_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, CarDetailsFragment.newInstance())
                    .commitNow();
        }
    }
}