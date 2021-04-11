package mbh.com.meiboplanetlistrecycler.ui.main;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import mbh.com.meiboplanetlistrecycler.R;

public class PlanetListViewModel extends ViewModel {

    // TODO: Implement the ViewModel
//    public final static String TAG = " PlanetListViewModel";
//    private List<Planet> planet_data_list ;
//    private Planet[] planets;
//
//    private List<Planet> setupPlanets(Context context) {
//        String type_planet = context.getString(R.string.type_planet).toString();
//        String type_minor_planet = context.getString(R.string.type_minor_planet).toString();
//        planets = new Planet[]{
//                new Planet(R.drawable.mercury_symbol, context.getString(R.string.planet_mercury), type_planet),
//                new Planet(R.drawable.venus_symbol, context.getString(R.string.planet_venus), type_planet),
//                new Planet(R.drawable.earth_symbol, context.getString(R.string.planet_earth), type_planet),
//                new Planet(R.drawable.mars_symbol, context.getString(R.string.planet_mars), type_planet),
//                new Planet(R.drawable.jupiter_symbol, context.getString(R.string.planet_jupiter), type_planet),
//                new Planet(R.drawable.saturn_symbol, context.getString(R.string.planet_saturn), type_planet),
//                new Planet(R.drawable.uranus_symbol, context.getString(R.string.planet_uranus), type_planet),
//                new Planet(R.drawable.neptune_symbol, context.getString(R.string.planet_neptune), type_planet),
//                new Planet(R.drawable.ceres_symbol, context.getString(R.string.planet_ceres), type_minor_planet),
//                new Planet(R.drawable.pluto_symbol, context.getString(R.string.planet_pluto), type_minor_planet),
//                new Planet(R.drawable.eris_symbol, context.getString(R.string.planet_eris), type_minor_planet)
//        };
//// Convert array to List.
//
//        planet_data_list= new ArrayList<>(Arrays.asList(planets));
//        return planet_data_list;
//    }
//    public List<Planet> getPlanetData(Context context){
//        if (planet_data_list == null || planet_data_list.isEmpty()){
//            Log.d(TAG,"planet_data_list empty, create new data");
//            return setupPlanets(context);
//        } else {
//            Log.d(TAG,"planet_data_list exists, return it");
//            return planet_data_list;
//        }
//    }
}