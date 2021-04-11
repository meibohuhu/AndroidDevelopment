package mbh.com.hurestaurantslist.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mbh.com.hurestaurantslist.R;

public class RestaurantListFragment extends Fragment {

    private RestaurantListViewModel mViewModel;
    private List<String> restuarant_data;


    public static RestaurantListFragment newInstance() {
        return new RestaurantListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.restaurant_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RestaurantListViewModel.class);
        // TODO: Use the ViewModel

        restuarant_data = setupRestuarants();
        ListView listView = getActivity().findViewById(R.id.restaurantList);

        //Create the ArrayAdapter.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.listview_layout, restuarant_data);
        // set the adapter (eg the data and the format) into the listView
        listView.setAdapter(adapter);

        //display the name of the item that was clicked in a Toast.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                doClick(view);
            }
        });
    }

    // return the planet data into ResaurantListActivityFragment.
    private List<String> setupRestuarants(){
        List<String> rest_data_list ;
        String[] rest = getResources().getStringArray(R.array.Restaurants);
    // convert array into List;
        rest_data_list= new ArrayList<>(Arrays.asList(rest));
        return rest_data_list;
    }

    public void doClick(View view){
        Toast.makeText(getActivity(),
            ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
    }

}