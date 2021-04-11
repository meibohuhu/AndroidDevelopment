package mbh.com.hufasteddy.ui.main;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import mbh.com.hufasteddy.CarDetails;
import mbh.com.hufasteddy.R;

public class FastEddyFragment extends Fragment {

    private FastEddyViewModel mViewModel;
    public static final String TAG = "FastEddy_Fragment";
    TextView likeTv;
    //Define the RequestCode.
    static final int REQUEST_CAR_DETAILS = 1;

    public static FastEddyFragment newInstance() {
        return new FastEddyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fast_eddy_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FastEddyViewModel.class);
        Button showDetails = getActivity().findViewById(R.id.fastEddy_button);

        //Hide the TextView when the app starts.
        likeTv = getActivity().findViewById(R.id.fastEddy_like);
        likeTv.setVisibility(View.INVISIBLE);

        showDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetails();
            } });
    }

//    void showDetails() {                           //one activity called CarDetails
//        Intent intent = new Intent(getActivity(), CarDetails.class);
//        startActivity(intent);
//    }
    private void showDetails() {
        Intent intent = new Intent(getActivity(), CarDetails.class);
        intent.putExtra(CarDetailsFragment.MODEL, "Skoda");
        intent.putExtra(CarDetailsFragment.MANUFACTURE, "Grand Touring");
        intent.putExtra(CarDetailsFragment.COLOR, "Executive Silver");
        intent.putExtra(CarDetailsFragment.PRICE, "16,000");
        intent.putExtra(CarDetailsFragment.TYPE, "Sedan");
        intent.putExtra(CarDetailsFragment.IMAGE, R.drawable.greatcar);

//        startActivity(intent);
        //One parameter is an intent specifying which activity to start and can contain information
        startActivityForResult(intent, REQUEST_CAR_DETAILS);
    }

    @Override   //when the recipient finishes
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    // Check which request we're responding to
        boolean like = false;
        if (requestCode == REQUEST_CAR_DETAILS) { // Make sure the request was successful
            if (resultCode == Activity.RESULT_OK) {
    // Show/hide "like it" textView depending on intent extra info
                if (data.hasExtra(CarDetailsFragment.OPINION)) {
                    like = data.getBooleanExtra(CarDetailsFragment.OPINION, false);
                    Log.d(TAG," onActivityResult RESULT_OK REQUEST_CAR_DETAILS like: " + like);
                    if (like)
                        likeTv.setVisibility(View.VISIBLE);
                    else
                        likeTv.setVisibility(View.INVISIBLE);
            }
        } else {
            Log.d(TAG,"onActivityResult result is not RESULT_NOT_OK");
        }
    }
}


}