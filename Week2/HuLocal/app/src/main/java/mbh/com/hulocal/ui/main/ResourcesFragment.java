package mbh.com.hulocal.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import mbh.com.hulocal.R;

public class ResourcesFragment extends Fragment {

    private ResourcesViewModel mViewModel;

    public static ResourcesFragment newInstance() {
        return new ResourcesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.resources_fragment, container, false);
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = ViewModelProviders.of(this).get(ResourcesViewModel.class);
//        // TODO: Use the ViewModel
//    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button b = getActivity().findViewById(R.id.res_flag_button);
        // set click listener on the flag to show the toast
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), R.string.toast_text, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
