package mbh.com.hulocal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import mbh.com.hulocal.ui.main.ResourcesFragment;

public class ResourcesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resources_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ResourcesFragment.newInstance())
                    .commitNow();
        }
    }
}