package mbh.com.huplanetlist.ui.main;

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

import mbh.com.huplanetlist.R;

public class PlanetListFragment extends Fragment {

    //Declare a List of strings to hold your planet data
    private List<String> planet_data;

    private PlanetListViewModel mViewModel;

    public static PlanetListFragment newInstance() {
        return new PlanetListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.planet_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PlanetListViewModel.class);

        // TODO: Use the ViewModel
        planet_data = setupPlanets();
        ListView listView = getActivity().findViewById(R.id.planetList);

//        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, planet_data);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.listview_layout, planet_data);

    // set the adapter (eg the data and the format) into the listView
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                doClick(view);
            }
        });
    }

    private List<String> setupPlanets(){
        List<String> planet_data_list ;
        //fill in planets(from strings.xml)
        String[] planets = getResources().getStringArray(R.array.planets);
        // convert array into List;
        planet_data_list= new ArrayList<>(Arrays.asList(planets));
        return planet_data_list;
    }
    public void doClick(View view){
        Toast.makeText(getActivity(),
            ((TextView)view).getText(), Toast.LENGTH_SHORT).show();
    }




}