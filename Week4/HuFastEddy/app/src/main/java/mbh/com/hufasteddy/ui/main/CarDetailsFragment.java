package mbh.com.hufasteddy.ui.main;

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

import mbh.com.hufasteddy.R;

public class CarDetailsFragment extends Fragment {

    private CarDetailsViewModel mViewModel;

    public static String MODEL = "model";
    public static String MANUFACTURE = "manufacturer";
    public static String COLOR = "color";
    public static String PRICE = "price";
    public static String TYPE = "type";
    public static String IMAGE = "image";

    public static String OPINION = "opinion";

    public static CarDetailsFragment newInstance() {
        return new CarDetailsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.car_details_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CarDetailsViewModel.class);
        TextView tvManufacturer = getActivity().findViewById(R.id.carDetails_Manufacturer);

        TextView tvModel = getActivity().findViewById(R.id.carDetails_Model);
        TextView tvColor = getActivity().findViewById(R.id.carDetails_Color);
        TextView tvType = getActivity().findViewById(R.id.carDetails_Type);
        TextView tvPrice = getActivity().findViewById(R.id.carDetails_Price);
        ImageView ivImage = getActivity().findViewById(R.id.carDetails_CarImage);

        final CheckBox likeCB = getActivity().findViewById(R.id.carDetails_CheckBoxLike);   //check startActivityForResult

        Intent intent = getActivity().getIntent();
        //get extras from the intent and these extras are a bundle.
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            // Get the info out of the bundle
            String manufacturer = bundle.getString(MANUFACTURE);
            String model = bundle.getString(MODEL);
            String color = bundle.getString(COLOR);
            String price = bundle.getString(PRICE);
            String type = bundle.getString(TYPE);
            int icon_id = bundle.getInt(IMAGE, -1);
            // Do something with the info
            tvManufacturer.setText(!TextUtils.isEmpty(manufacturer)? manufacturer:"Unknown");
            tvModel.setText(!TextUtils.isEmpty(model) ? model:"Unknown");
            tvColor.setText(!TextUtils.isEmpty(color) ? color:"Unknown");
            tvPrice.setText(!TextUtils.isEmpty(price) ? price:" 0");
            tvType.setText(!TextUtils.isEmpty(type) ? type:"Unknown");

            if (icon_id > -1) {
        // Set the drawable as the content of the imageView
                ivImage.setImageResource(icon_id);
        // Controls how the image should be resized or moved to match the size of the imageView
                ivImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }
        }
        
        // Set the background to a given resource. 0 removes the background.
        ivImage.setBackgroundResource(0);

        Button b = getActivity().findViewById(R.id.carDetails_ReturnBtn);    //button callback
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