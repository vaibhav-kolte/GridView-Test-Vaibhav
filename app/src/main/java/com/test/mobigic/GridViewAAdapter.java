package com.test.mobigic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GridViewAAdapter extends RecyclerView.Adapter<GridViewAAdapter.ViewHolder> {

    private Context context;
    private ArrayList<GridModel> gridModelArrayList;

    public GridViewAAdapter(Context context, ArrayList<GridModel> gridModelArrayList) {
        this.context = context;
        this.gridModelArrayList = gridModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        try {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View convertView = layoutInflater.inflate(R.layout.card_item, parent, false);
            ViewHolder mPredictionHolder = new ViewHolder(convertView);
            return mPredictionHolder;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            holder.editText.setText(gridModelArrayList.get(position).getValue());

        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public int getItemCount() {
        return gridModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        EditText editText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            editText = itemView.findViewById(R.id.idAlphabet);
        }
    }
}
