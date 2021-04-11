package mbh.com.huzoolist.ui.main;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import mbh.com.huzoolist.R;

import java.util.List;

public class AnimalRecyclerViewAdapter extends RecyclerView.Adapter<AnimalRecyclerViewAdapter.ViewHolder>{
    public final static String TAG = "ZooListAdapter";

    //Create the interface that will be used for callbacks.
    public interface OnAdapterItemInteraction {
        void onItemSelected(Animal animal);
    }

    //Declare variables and the constructor.
    private final List<Animal> mValues;
    final OnAdapterItemInteraction mListener;
    //The constructor takes two parameters, the data and the listener.
    public AnimalRecyclerViewAdapter(List<Animal> items, OnAdapterItemInteraction listener) { mValues = items;
        mListener = listener;
    }

    @Override
    public AnimalRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_linear, parent, false);
        return new ViewHolder(view);
    }

    //bind data to the holder.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(mValues.get(position).getName());
        holder.txtLocation.setText(mValues.get(position).getLocation());

        if (TextUtils.equals(mValues.get(position).getType(), Animal.MAMMAL))
            holder.imgSpecies.setImageResource(R.mipmap.ic_lion);
        else if (TextUtils.equals(mValues.get(position).getType(),Animal.BIRD))
            holder.imgSpecies.setImageResource(R.mipmap.ic_bird);
        else
            holder.imgSpecies.setImageResource(R.mipmap.ic_lizard);

        final Animal animal = mValues.get(position);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the fragment is attached to one) that an item has been selected.
                    //delete
                    mListener.onItemSelected(animal);
                }
            }
        });
    }
    //     return the number of elements in the data.
    @Override
    public int getItemCount() {
        return mValues.size();
    }

   // add an item (in this case an animal) to the data used by the adapter.
   // This method is intended as demonstration of a way of directly adding an item to mValues.
    public void add(Animal item){
        Log.d(TAG,"Add " + item.toString());
        mValues.add(item);
        notifyItemInserted(mValues.size()-1);
    }
    //remove the data.
    public void remove(Animal item) {
        int position = mValues.indexOf(item);
        mValues.remove(position);
        notifyItemRemoved(position);
    }

    //Create the ViewHolder, Note that there is a view for each item that will be displayed in the row format.
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView imgSpecies;
        public final TextView txtName;
        public final TextView txtLocation;
        public ViewHolder(View view) {
            super(view);
            mView = view;
            txtName = view.findViewById(R.id.name);
            txtLocation = view.findViewById(R.id.location);
            imgSpecies = view.findViewById(R.id.icon);
        }
        @Override
        public String toString() {
            return super.toString() + " '" + txtLocation.getText() + "'";
        }
    }
}
