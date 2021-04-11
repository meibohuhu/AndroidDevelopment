package mbh.com.intentimplicit.ui.intentimplicit;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import mbh.com.intentimplicit.R;

public class IntentImplicitFragment extends Fragment {

    private static final String TAG = "2323r";
    private IntentImplicitViewModel mViewModel;

    public static IntentImplicitFragment newInstance() {
        return new IntentImplicitFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.implicit_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        final RadioGroup radioUrlGroup = getActivity().findViewById(R.id.radioGroup1);
        radioUrlGroup.check(R.id.implicit_radio0);

        Button startActivityBtn = getActivity().findViewById(R.id.implicit_button1);
        startActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedButtonId = radioUrlGroup.getCheckedRadioButtonId();
                RadioButton radioUrlButton =
                        radioUrlGroup.findViewById(selectedButtonId);
                String radioButtonValue = (String) radioUrlButton.getText();
                Toast.makeText(getActivity(),
                        radioButtonValue, Toast.LENGTH_SHORT).show();
                callActivity(radioButtonValue);
            }
        });

    }
    private void callActivity(String opt) {
        Intent myIntent;
        if (opt.equals(getResources().getString(R.string.list_browser).toString())){
            myIntent = new Intent();
            String url = "http:/www.google.com";
            myIntent.setAction(Intent.ACTION_VIEW);
            myIntent.setData(Uri.parse(url)); startActivity(myIntent);
        }else if (opt.equals(getResources().getString(R.string.list_dialer).toString())) {
            myIntent = new Intent();
            String numberToDial = "tel:800-555-0100";
            myIntent.setAction(Intent.ACTION_DIAL);
            myIntent.setData(Uri.parse(numberToDial));
            startActivity(myIntent);
        }else if (opt.equals(getResources().getString(R.string.list_contacts).toString())) {

            myIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            try {
                startActivity(myIntent);
            } catch (Exception e) {
                Log.d(TAG, "Error: " + e);
            }
        }else if (opt.equals(getResources().getString(R.string.list_call).toString())) {
            myIntent = new Intent(Intent.ACTION_CALL);
            String numberToDial = "tel:800-555-0100";
            myIntent.setData(Uri.parse(numberToDial));
            startActivity(myIntent);
        }
    }

}