package mbh.com.restlistrecyclerassignment6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import mbh.com.restlistrecyclerassignment6.ui.main.RestListFragment;

public class RestListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rest_list_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, RestListFragment.newInstance())
                    .commitNow();
        }
    }
}