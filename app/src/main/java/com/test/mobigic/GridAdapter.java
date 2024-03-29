package com.test.mobigic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class GridAdapter extends ArrayAdapter<GridModel> {
    private static final String TAG = "GridAdapter";

    private ArrayList<Integer> pos;


    public GridAdapter(@NonNull Context context, ArrayList<GridModel> gridModelArrayList) {
        super(context,0, gridModelArrayList);
    }

    public GridAdapter(@NonNull Context context, ArrayList<GridModel> gridModelArrayList,ArrayList<Integer> pos) {
        super(context,0, gridModelArrayList);
        this.pos = pos;

    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.card_item, parent, false);
        }
        GridModel gridModel = getItem(position);
        EditText textView = listItemView.findViewById(R.id.idAlphabet);

        try{
            if(pos != null){
                for(int i = 0;i<pos.size();i++){
                    if(pos.get(i) == position){
                        textView.setTypeface(null, Typeface.BOLD);
                        textView.setTextColor(Color.parseColor("#E80C7505"));
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "getView: position Exception : "+e.getMessage() );
        }
        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e(TAG, "onTextChanged: Position : "+(position+1) + " Value : "+textView.getText() );
                gridModel.setValue(String.valueOf(textView.getText()));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        Log.e(TAG, "getView: You enter : " +textView.getText() );
        textView.setText(gridModel.getValue());
        return listItemView;
    }
}
