package mbh.com.restlistrecyclerassignment6.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import mbh.com.restlistrecyclerassignment6.R;

import java.util.List;

public class RestRecyclerAdapter extends RecyclerView.Adapter<RestRecyclerAdapter.ViewHolder> {

    public interface OnAdapterItemInteraction {      //an interface for callbacks to the app: listener
        //2. The callback will return the planet data (corresponding to the clicked view) and the position of this view.
        void onItemSelected(Rest planet, Integer position);
    }
    //3. Declare variables and the constructor.
    private final List<Rest> mValues;
    final OnAdapterItemInteraction mListener;
    public RestRecyclerAdapter(List<Rest> items, OnAdapterItemInteraction listener) {
        mValues = items;
        mListener = listener;
    }

    @NonNull
    @Override   // 4. inflate the layout, returns a matching ViewHolder, the row format that is instantiated.
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rest_item, parent, false);
        return new ViewHolder(view);
    }


    @Override  //5. bind data to the holder
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.restNameView.setText(mValues.get(position).getName());
        holder.restTypeView.setText(mValues.get(position).getType());
        holder.restCostView.setText(mValues.get(position).getCost());
        holder.restLocaView.setText(mValues.get(position).getLocation());
        final Rest selectedPlanet = mValues.get(position);
        final int pos = holder.getAdapterPosition();
        // The onClick listener can be used to send the callback to the component using the adapter.
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onItemSelected(selectedPlanet, pos);
                }
            }
        });
    }
    @Override  //give the number of elements in the data.
    public int getItemCount() {
        return mValues.size();
    }

    //add an item (in this case a planet) to the data used by the adapter.
    public void add(Rest item){
        mValues.add(item);
        notifyItemInserted(mValues.size() - 1);
    }

    //1. there is a view for each item that will be displayed in the row format.
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
//        final ImageView planetLogoIv;
//        final TextView planetNameTv;
//        final TextView planetTypeTv;

        final TextView restNameView;
        final TextView restTypeView;
        final TextView restCostView;
        final TextView restLocaView;
        public ViewHolder(View view) {
            super(view);
            mView = view;
            restNameView = view.findViewById(R.id.restuarant_name);
            restTypeView = view.findViewById(R.id.restuarant_type);
            restCostView = view.findViewById(R.id.restuarant_cost);
            restLocaView = view.findViewById(R.id.restuarant_location);
        }
        @Override
        public String toString() {
            return super.toString() ;
        }
    }
}
