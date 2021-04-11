package mbh.com.wendylaptop.ui.main;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import mbh.com.wendylaptop.R;

public class DetailsFragment extends Fragment {

    private DetailsViewModel mViewModel;
    public static String PROCESSOR = "processor";
    public static String MEMORY = "memory";
    public static String STORAGE = "storage";
    public static String COLOR = "color";
    public static String PRICE = "price";
    public static String IMAGE = "image";

    public static String OPINION = "opinion";

    public static DetailsFragment newInstance() {
        return new DetailsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.details_fragment, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
        // TODO: Use the ViewModel

        TextView tvProcessor = getActivity().findViewById(R.id.laptopDetails_Processor);
        TextView tvMemory = getActivity().findViewById(R.id.laptopDetails_Memory);
        TextView tvStorage = getActivity().findViewById(R.id.laptopDetails_Storage);
        TextView tvPrice = getActivity().findViewById(R.id.laptopDetails_Price);
        TextView tvColor = getActivity().findViewById(R.id.laptopDetails_Color);
        ImageView ivImage = getActivity().findViewById(R.id.laptopDetails_LaptopImage);

        final CheckBox likeCB = getActivity().findViewById(R.id.laptopDetails_CheckBoxLike);  //check startActivityForResult
        Intent intent = getActivity().getIntent();  //get extras from the intent and these extras are a bundle.
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            // Get the info out of the bundle
            String processor = bundle.getString(PROCESSOR);
            String memory = bundle.getString(MEMORY);
            String storage = bundle.getString(STORAGE);
            String color = bundle.getString(COLOR);
            String price = bundle.getString(PRICE);

            int icon_id = bundle.getInt(IMAGE, -1);
            // Do something with the info
            tvProcessor.setText(!TextUtils.isEmpty(processor)? processor : "Unknown");
            tvMemory.setText(!TextUtils.isEmpty(memory) ? memory: "Unknown");
            tvStorage.setText(!TextUtils.isEmpty(storage) ? storage: "Unknown");
            tvColor.setText(!TextUtils.isEmpty(color) ? color : "Unknown");
            tvPrice.setText(!TextUtils.isEmpty(price) ? price : " 0");


            if (icon_id > -1) {
                // Set the drawable as the content of the imageView
                ivImage.setImageResource(icon_id);
                // Controls how the image should be resized or moved to match the size of the imageView
                ivImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }

            ivImage.setBackgroundResource(0);
            Button b = getActivity().findViewById(R.id.laptopDetails_ReturnBtn);    //button callback
            b.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    boolean like = likeCB.isChecked();
                    Intent data = new Intent();
                    data.putExtra(OPINION, like);
                    getActivity().setResult(Activity.RESULT_OK, data);
                    getActivity().finish();

                }
            });
        }
    }

}