package mbh.com.hurestaurantslist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import mbh.com.hurestaurantslist.ui.main.RestaurantListFragment;

public class RestaurantListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_list_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, RestaurantListFragment.newInstance())
                    .commitNow();
        }
    }
}