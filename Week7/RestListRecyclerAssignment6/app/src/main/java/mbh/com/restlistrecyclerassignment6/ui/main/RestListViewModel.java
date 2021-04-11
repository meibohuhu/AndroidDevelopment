package mbh.com.restlistrecyclerassignment6.ui.main;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mbh.com.restlistrecyclerassignment6.R;

public class RestListViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public final static String TAG = " PlanetListViewModel";
    private List<Rest> rest_data_list ;
    private Rest[] planets;


    private List<Rest> setupPlanets(Context context) {
//        List<Rest> rest_data_list ;
        String location = context.getString(R.string.Location).toString();
        planets = new Rest[] {
                new Rest(context.getString(R.string.res_Subway), context.getString(R.string.type_Subway), location, context.getString(R.string.cost_Subway)),
                new Rest(context.getString(R.string.res_Starbucks), context.getString(R.string.type_Starbucks), location, context.getString(R.string.cost_Starbucks)),
                new Rest(context.getString(R.string.res_MC), context.getString(R.string.type_MC), location, context.getString(R.string.cost_MC)),
                new Rest(context.getString(R.string.res_ChickfilA), context.getString(R.string.type_ChickfilA), location, context.getString(R.string.cost_ChickfilA)),
                new Rest(context.getString(R.string.res_Panda), context.getString(R.string.type_Panda), location, context.getString(R.string.cost_Panda)),
                new Rest(context.getString(R.string.res_BCDTOFU), context.getString(R.string.type_BCDTOFU), location, context.getString(R.string.cost_BCDTOFU)),
                new Rest(context.getString(R.string.res_SunNongDan), context.getString(R.string.type_SunNongDan), location, context.getString(R.string.cost_SunNongDan)),
                new Rest(context.getString(R.string.res_InNOut), context.getString(R.string.type_InNOut), location, context.getString(R.string.cost_InNOut)),
        };
        // Convert array to List.
        rest_data_list = new ArrayList<>(Arrays.asList(planets));
        return rest_data_list;
    }

    public List<Rest> getPlanetData(Context context){
        if (rest_data_list == null || rest_data_list.isEmpty()){
            Log.d(TAG,"planet_data_list empty, create new data");
            return setupPlanets(context);
        } else {
            Log.d(TAG,"planet_data_list exists, return it");
            return rest_data_list;
        }
    }
}