package mbh.com.restlistrecyclerassignment6.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import mbh.com.restlistrecyclerassignment6.R;

public class RestListFragment extends Fragment implements RestRecyclerAdapter.OnAdapterItemInteraction{

    private RestListViewModel mViewModel;

    private int mPosition;   // the last clicked position
    private List<Rest> rest_data ;   //hold the planet data
    RestRecyclerAdapter restRecyclerAdapter;   //RecyclerViewAdapter
    public final static String TAG = " RestListFragment";     //
    private final static String KEY_POSITION = "key_position";    //Action item

    public static RestListFragment newInstance() {
        return new RestListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.rest_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Instantiate the button and the click listener.
        Button whatButton = getActivity().findViewById(R.id.restWhatIsItBtn);
        whatButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                clickWhatIsItButton();
            }
        });

        //Set up the data.
//        rest_data = setupPlanets();

//        RestListViewModel mViewModel = new RestListViewModel();
        rest_data = mViewModel.getPlanetData(getActivity());

        if (savedInstanceState != null){       //holding the position of the last clicked item.
            mPosition = savedInstanceState.getInt(KEY_POSITION, 0);
        }
        //Instantiate the recyclerView,
        RecyclerView recyclerView = getActivity().findViewById(R.id.planetRecyclerView);
        //Create a LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //Create a new recyclerViewAdapter
        restRecyclerAdapter = new RestRecyclerAdapter(rest_data, this);
        recyclerView.setAdapter(restRecyclerAdapter);   //Set the Layout Manager into the recyclerView
    }
    //display a toast when the button is clicked.
    private void clickWhatIsItButton() {
        String message = rest_data.get(mPosition).name + " "
                + getResources().getString(R.string.message_is_a) + " "
                + rest_data.get(mPosition).type;
        Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    //a method to setup up the data
    private List<Rest> setupPlanets() {
        List<Rest> rest_data_list ;
        String location = getString(R.string.Location).toString();
        Rest[] planets = new Rest[] {
                new Rest(getString(R.string.res_Subway), getString(R.string.type_Subway), location, getString(R.string.cost_Subway)),
                new Rest(getString(R.string.res_Starbucks), getString(R.string.type_Starbucks), location, getString(R.string.cost_Starbucks)),
                new Rest(getString(R.string.res_MC), getString(R.string.type_MC), location, getString(R.string.cost_MC)),
                new Rest(getString(R.string.res_ChickfilA), getString(R.string.type_ChickfilA), location, getString(R.string.cost_ChickfilA)),
                new Rest(getString(R.string.res_Panda), getString(R.string.type_Panda), location, getString(R.string.cost_Panda)),
                new Rest(getString(R.string.res_BCDTOFU), getString(R.string.type_BCDTOFU), location, getString(R.string.cost_BCDTOFU)),
                new Rest(getString(R.string.res_SunNongDan), getString(R.string.type_SunNongDan), location, getString(R.string.cost_SunNongDan)),
                new Rest(getString(R.string.res_InNOut), getString(R.string.type_InNOut), location, getString(R.string.cost_InNOut)),
        };
        // Convert array to List.
        rest_data_list= new ArrayList<>(Arrays.asList(planets));
        return rest_data_list;
    }

    @Override  //override Adapter, display the planet name in the toast
    public void onItemSelected(Rest planet, Integer position) {
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
        inflater.inflate(R.menu.rest_item, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_whatIsIt:
                Log.d(TAG," PlanetListActivityFragment onOptionsItemSelected: whatIsIt ");
                clickWhatIsItButton();
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    //save state
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_POSITION, mPosition);
    }

}