package mbh.com.meiboplanetlistrecycler.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mbh.com.meiboplanetlistrecycler.R;

public class PlanetListFragment extends Fragment implements PlanetRecyclerAdapter.OnAdapterItemInteraction{
    private int mPosition;   // the last clicked position
    private List<Planet> planet_data ;   //hold the planet data
    PlanetRecyclerAdapter planetRecyclerAdapter;   //RecyclerViewAdapter
    public final static String TAG = " PlanetListFragment";     //
    private final static String KEY_POSITION = "key_position";    //Action item

    public static PlanetListFragment newInstance() {
        return new PlanetListFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.planet_list_fragment, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        //Instantiate the button and the click listener.
        Button whatButton = getActivity().findViewById(R.id.planetWhatIsItBtn);
        whatButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                clickWhatIsItButton();
            }
        });

        //Set up the data.
        planet_data = setupPlanets();
//        PlanetListViewModel mViewModel = new PlanetListViewModel();
//        planet_data = mViewModel.getPlanetData(getActivity());
        if (savedInstanceState != null){       //holding the position of the last clicked item.
            mPosition = savedInstanceState.getInt(KEY_POSITION, 0);
        }
        //Instantiate the recyclerView,
        RecyclerView recyclerView = getActivity().findViewById(R.id.planetRecyclerView);
        //Create a LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //Create a new recyclerViewAdapter
        planetRecyclerAdapter = new PlanetRecyclerAdapter(planet_data, this);
        recyclerView.setAdapter(planetRecyclerAdapter);   //Set the Layout Manager into the recyclerView
    }
    //display a toast when the button is clicked.
    private void clickWhatIsItButton() {
        String message = planet_data.get(mPosition).name + " "
                + getResources().getString(R.string.message_is_a) + " "
                + planet_data.get(mPosition).type;
        Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    //a method to setup up the data
    private List<Planet> setupPlanets() {
//        String type_planet = getResources().getString(R.string.type_planet).toString();
//        String type_minor_planet =
//                getResources().getString(R.string.type_minor_planet).toString();
        List<Planet> planet_data_list ;
        String location = getString(R.string.Location).toString();
        Planet[] planets = new Planet[] {
                new Planet(getString(R.string.res_Subway), getString(R.string.type_Subway), location, getString(R.string.cost_Subway)),
                new Planet(getString(R.string.res_Starbucks), getString(R.string.type_Starbucks), location, getString(R.string.cost_Starbucks)),
                new Planet(getString(R.string.res_MC), getString(R.string.type_MC), location, getString(R.string.cost_MC)),
                new Planet(getString(R.string.res_ChickfilA), getString(R.string.type_ChickfilA), location, getString(R.string.cost_ChickfilA)),
                new Planet(getString(R.string.res_Panda), getString(R.string.type_Panda), location, getString(R.string.cost_Panda)),
                new Planet(getString(R.string.res_BCDTOFU), getString(R.string.type_BCDTOFU), location, getString(R.string.cost_BCDTOFU)),
                new Planet(getString(R.string.res_SunNongDan), getString(R.string.type_SunNongDan), location, getString(R.string.cost_SunNongDan)),
                new Planet(getString(R.string.res_InNOut), getString(R.string.type_InNOut), location, getString(R.string.cost_InNOut)),
        };
    // Convert array to List.
        planet_data_list= new ArrayList<>(Arrays.asList(planets));
        return planet_data_list;
    }

    @Override  //override Adapter, display the planet name in the toast
    public void onItemSelected(Planet planet, Integer position) {
        String item = planet.getName();
        Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
        mPosition = position;
    }

    //bar
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d(TAG," PlanetListActivityFragment onCreateOptionsMenu");
// Inflate the menu; this adds items to the app bar if it is present.
// The name of the layout is the name of the menu class (file)
        inflater.inflate(R.menu.planet_item, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_whatIsIt:
            Log.d(TAG," PlanetListActivityFragment onOptionsItemSelected: whatIsIt ");
            clickWhatIsItButton();
            return true; default:
            return super.onOptionsItemSelected(item);
        }
    }

    //save state
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_POSITION, mPosition);
    }

}