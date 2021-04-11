package mbh.com.hutodolist.ui.main;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mbh.com.hutodolist.R;

public class TodoRecyclerViewAdapter extends RecyclerView.Adapter<TodoRecyclerViewAdapter.ViewHolder>{
    public final static String TAG = "TodoListAdapter";

    public interface OnAdapterItemInteraction {
        void onItemSelected(Todo todo);       //display
        void onItemLongClick(Todo todo);      //delete
    }
    private final List<Todo> mValues;
    final OnAdapterItemInteraction mListener;
    public TodoRecyclerViewAdapter(List<Todo> items, OnAdapterItemInteraction listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public TodoRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_linear, parent, false);
        return new ViewHolder(view);
    }

    //bind data to the holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtTitle.setText(mValues.get(position).getTitle());
        holder.txtDescription.setText(mValues.get(position).getDescription());
        holder.txtDue.setText(mValues.get(position).getDueDate());

        if (TextUtils.equals(mValues.get(position).getType(), Todo.LIFE))
            holder.imgSpecies.setImageResource(R.drawable.life);
        else if (TextUtils.equals(mValues.get(position).getType(),Todo.ENTERTAINMENT))
            holder.imgSpecies.setImageResource(R.drawable.entertainment);
        else
            holder.imgSpecies.setImageResource(R.drawable.study);

        final Todo todo = mValues.get(position);

        //Attaching a long click listener
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(null != mListener){
                    mListener.onItemLongClick(todo);
                }
                return true;
            }
        });
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the fragment is attached to one) that an item has been selected.
                    //display
                    mListener.onItemSelected(todo);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return mValues.size();
    }

    // add an item (in this case an animal) to the data used by the adapter.
    // This method is intended as demonstration of a way of directly adding an item to mValues.
    public void add(Todo item){
        Log.d(TAG,"Add " + item.toString());
        mValues.add(item);
        notifyItemInserted(mValues.size() - 1);
    }

    //remove the data.
    public void remove(Todo item) {
        int position = mValues.indexOf(item);
        mValues.remove(position);
        notifyItemRemoved(position);
    }


    //Create the ViewHolder,
    // Note that there is a view for each item that will be displayed in the row format.
    //no additional information
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public final ImageView imgSpecies;
        public final TextView txtTitle;
        public final TextView txtDescription;
        public final TextView txtDue;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            txtTitle = view.findViewById(R.id.title);
            txtDescription = view.findViewById(R.id.description);
            txtDue = view.findViewById(R.id.dueDate);
            imgSpecies = view.findViewById(R.id.icon);
        }
        @Override
        public String toString() {
            return super.toString() + " '" + txtDescription.getText() + "'";
        }
    }

}
