package mbh.com.webview.ui.main;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RadioGroup;

import mbh.com.webview.R;

public class WebViewFragment extends Fragment {

    private WebViewViewModel mViewModel;

    private String[] urls;


    public static WebViewFragment newInstance() {
        return new WebViewFragment();
    }

    @Nullable
    @Override    //onCreateView is called when the fragment is being created.
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.web_view_fragment, container, false);
    }

    @Override   //In onActivityCreated, the ViewModel is instantiated.
    public void onActivityCreated(Bundle savedInstanceState){

        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WebViewViewModel.class);

        urls = getResources().getStringArray(R.array.url_array);
        final RadioGroup radioUrlGroup = getActivity().findViewById(R.id.web_radioGroup1);
        radioUrlGroup.check(R.id.web_radio0);

        final WebView myWebView = getActivity().findViewById(R.id.web_webView);
        myWebView.loadUrl(urls[0]);
        myWebView.setWebViewClient(new MyWebViewClient());

        Button showWebSiteBtn = getActivity().findViewById(R.id.web_showButton);
        showWebSiteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayWebSite(radioUrlGroup, myWebView); }
        }); }

    void displayWebSite(RadioGroup radioUrlGroup, WebView myWebView){
        int selectedButtonId = radioUrlGroup.getCheckedRadioButtonId();
        View radioUrlButton = radioUrlGroup.findViewById(selectedButtonId);
        int index = radioUrlGroup.indexOfChild(radioUrlButton);
        if (index > urls.length) index = urls.length - 1;
        if (index < 0) index = 0;
        myWebView.loadUrl(urls[index]);
        myWebView.setWebViewClient(new MyWebViewClient());

    }
    static class MyWebViewClient extends WebViewClient {
        @TargetApi(Build.VERSION_CODES.N)
        public boolean shouldOverrideUrlLoading(WebView view,
                                                WebResourceRequest request) {
            String url = request.getUrl().toString();
            view.loadUrl(url);
            return true;
        }
    }


}















