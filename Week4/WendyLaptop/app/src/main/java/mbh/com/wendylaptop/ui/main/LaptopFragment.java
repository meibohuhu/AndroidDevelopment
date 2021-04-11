package mbh.com.wendylaptop.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import mbh.com.wendylaptop.Details;
import mbh.com.wendylaptop.R;

public class LaptopFragment extends Fragment {

    private LaptopViewModel mViewModel;
    public static final String TAG = "LapTop_Fragment";
    static final int REQUEST_LAPTOP_DETAILS = 1;
    TextView likeTv;
    //Define the RequestCode.


    public static LaptopFragment newInstance() {
        return new LaptopFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.laptop_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LaptopViewModel.class);
        // TODO: Use the ViewModel
        Button showDetails = getActivity().findViewById(R.id.laptop_button);

        //Hide the TextView when the app starts.
        likeTv = getActivity().findViewById(R.id.Laptop_like);
        likeTv.setVisibility(View.INVISIBLE);

        showDetails.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showDetails();
            }
        });

    }

    private void showDetails() {
        Intent intent = new Intent(getActivity(), Details.class);       //from Details
        intent.putExtra(DetailsFragment.STORAGE, "512GB");
        intent.putExtra(DetailsFragment.PROCESSOR, "Intel Core i5");
        intent.putExtra(DetailsFragment.MEMORY, "8GB");
        intent.putExtra(DetailsFragment.COLOR, "Space Gray");
        intent.putExtra(DetailsFragment.PRICE, "1399.00");
        intent.putExtra(DetailsFragment.IMAGE, R.drawable.laptop);

//        startActivity(intent);
        //One parameter is an intent specifying which activity to start and can contain information
        startActivityForResult(intent, REQUEST_LAPTOP_DETAILS);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        boolean like = false;
        if (requestCode == REQUEST_LAPTOP_DETAILS) { // Make sure the request was successful
            if (resultCode == Activity.RESULT_OK) {
                // Show/hide "like it" textView depending on intent extra info
                if (data.hasExtra(DetailsFragment.OPINION)) {
                    like = data.getBooleanExtra(DetailsFragment.OPINION, false);
                    Log.d(TAG, " onActivityResult RESULT_OK REQUEST_CAR_DETAILS like: " + like);
                    if (like)
                        likeTv.setVisibility(View.VISIBLE);
                    else
                        likeTv.setVisibility(View.INVISIBLE);
                }
            } else {
                Log.d(TAG, "onActivityResult result is not RESULT_NOT_OK");
            }
        }
    }
}