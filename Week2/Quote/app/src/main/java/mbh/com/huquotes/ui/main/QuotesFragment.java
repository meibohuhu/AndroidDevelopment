package mbh.com.huquotes.ui.main;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import mbh.com.huquotes.R;

public class QuotesFragment extends Fragment {

    private MainViewModel mViewModel;
    private String[] quotes;
    private int quotesNumber;
    private TextView quoteText;
    private Button getQuoteButton;

    public QuotesFragment () { }
    public static QuotesFragment newInstance() {
        return new QuotesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
//        // TODO: Use the ViewModel
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        quotes = getResources().getStringArray(R.array.quotes_array);
        quotesNumber = quotes.length;

        quoteText = getActivity().findViewById(R.id.quotes_quoteDisplay);
//        quoteText.setText(quotes[1]);
        getQuoteButton = getActivity().findViewById(R.id.quotes_getQuoteButton);
        //Attaching an onClickListener to a button
        getQuoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getQuote(); }
        });
    }
    public void getQuote() {
        Random r = new Random();
        int offset = r.nextInt(quotesNumber);
        if (offset > quotes.length) offset = quotes.length - 1;
        String quote = quotes[offset];
        quoteText.setText(quote);
    }
}