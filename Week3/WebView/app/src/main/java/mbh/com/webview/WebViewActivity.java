package mbh.com.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import mbh.com.webview.ui.main.WebViewFragment;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, WebViewFragment.newInstance())
                    .commitNow();
        }
    }
}