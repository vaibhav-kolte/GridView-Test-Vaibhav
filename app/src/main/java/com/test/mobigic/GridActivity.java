package com.test.mobigic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridActivity extends AppCompatActivity {

    private static final String TAG = "GridActivity";
    private GridView gridView;
    private Context context;
    private EditText et_SearchText;
    private TextView tvResult;
    private Button btnSearch;

    private int row, col;
    private String searchString;
    private boolean found;
    private GridAdapter adapter;
    private GridAdapter updateAdapter;
    ArrayList<GridModel> gridModelArrayList;
    ArrayList<Integer> charPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        try {
            context = GridActivity.this;

            gridView = findViewById(R.id.idGridView);
            et_SearchText = findViewById(R.id.et_SearchText);
            btnSearch = findViewById(R.id.btnSearch);
            tvResult = findViewById(R.id.tvResult);

            charPos = new ArrayList<Integer>();

            Intent intent = getIntent();

            row = Integer.parseInt(intent.getStringExtra("ROW"));
            col = Integer.parseInt(intent.getStringExtra("COLUMN"));

            gridView.setNumColumns(col);


            gridModelArrayList = new ArrayList<GridModel>();
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    gridModelArrayList.add(new GridModel(""));

                }
            }
            adapter = new GridAdapter(this, gridModelArrayList);

            gridView.setAdapter(adapter);


            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        searchString = et_SearchText.getText().toString();
                        Log.e(TAG, "onClick: Search text : " + et_SearchText.getText().toString());

                        found = true;
                        findLeftToRight();

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(TAG, "onClick: btnSearch Exception : " + e.getMessage());
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "onCreate: Exception : " + e.getMessage());
        }
    }

    private void findLeftToRight() {
        try {
            int counter = -1;
            int letterCounter = 0;
            if (!TextUtils.isEmpty(searchString)) {

                char[] letters = searchString.toCharArray();

                if (letters.length <= col) {

                    for (int i = 0; i < row; i++) {
                        letterCounter = 0;
                        charPos.clear();
                        found = true;
                        // Change
                        for (int j = 0; j < col; j++) {
                            counter++;
                            if (letters.length > letterCounter) {
                                Log.e(TAG, "findLeftToRight: " + gridModelArrayList.get(counter).getValue() + " = " + String.valueOf(letters[letterCounter]));
                                if (!TextUtils.isEmpty(gridModelArrayList.get(counter).getValue())) {
                                    if (!gridModelArrayList.get(counter).getValue().equalsIgnoreCase(String.valueOf(letters[letterCounter]))) {
                                        found = false;
                                        if(!gridModelArrayList.get(counter).getValue().equalsIgnoreCase(String.valueOf(letters[letterCounter-1]))){
                                            letterCounter = 0;
                                        }

                                    } else {
                                        found = true;
                                        charPos.add(counter);
                                        letterCounter++;
                                    }
                                } else {
                                    found = false;
                                }
                            } else {
                                if (found) {
                                    break;
                                }
                            }
                        }
                        if (found && letters.length == letterCounter) {
                            break;
                        }
                    }

                    if (found && letters.length == letterCounter) {
                        Log.e(TAG, "findLeftToRight: Text is found");
                        tvResult.setText(R.string.found_left_to_right);
                        updateAdapter = new GridAdapter(context,gridModelArrayList,charPos);
                        gridView.setAdapter(updateAdapter);
                    } else {
                        findTopToBottom();
                    }
                }
                else{
                    findTopToBottom();
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "findLeftToRight: Exception : " + e.getMessage());
        }
    }

    private void findTopToBottom() {
        try {
            int counter = -1;
            int letterCounter = 0;
            if (!TextUtils.isEmpty(searchString)) {

                char[] letters = searchString.toCharArray();

                if (letters.length <= row) {

                    for (int i = 0; i < col; i++) {
                        counter = i;
                        letterCounter = 0;
                        found = true;
                        charPos.clear();
                        for (int j = 0; j < row; j++) {
                            Log.e(TAG, "findTopToBottom: grid value : " + gridModelArrayList.get(counter).getValue());
                            if (letters.length > letterCounter) {
                                Log.e(TAG, "findTopToBottom: " + gridModelArrayList.get(counter).getValue() + " = " + String.valueOf(letters[letterCounter]));
                                if (!TextUtils.isEmpty(gridModelArrayList.get(counter).getValue())) {
                                    if (!gridModelArrayList.get(counter).getValue().equalsIgnoreCase(String.valueOf(letters[letterCounter]))) {
                                        found = false;
                                        letterCounter = 0;
                                    } else {
                                        found = true;
                                        charPos.add(counter);
                                        letterCounter++;
                                    }
                                } else {
                                    found = false;
                                }
                            }
                            counter = counter + col;

                        }
                        if (found && letters.length == letterCounter) {
                            break;
                        }
                    }

                    if (found && letters.length == letterCounter) {
                        Log.e(TAG, "findTopToBottom: Text is found");
                        tvResult.setText(R.string.found_top_to_bottom);
                        updateAdapter = new GridAdapter(context,gridModelArrayList,charPos);
                        gridView.setAdapter(updateAdapter);
                    } else {
                        findDiagonal();
                    }
                }
                else
                {
                    findDiagonal();
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "findTopToBottom: Exception : " + e.getMessage());
        }
    }

    private void findDiagonal() {
        try {
            int counter = 0;
            int letterCounter = 0;
            if (!TextUtils.isEmpty(searchString)) {

                char[] letters = searchString.toCharArray();
                for(int i =0 ;i<row;i++){
                    for(int j=0;j<col;j++){
                        if(i==j && letters.length > letterCounter){
                            Log.e(TAG, "findDiagonal: value : "+gridModelArrayList.get(counter).getValue() );
                            if (!TextUtils.isEmpty(gridModelArrayList.get(counter).getValue())) {
                                if (!gridModelArrayList.get(counter).getValue().equalsIgnoreCase(String.valueOf(letters[letterCounter]))) {
                                    found = false;
                                    letterCounter = 0;
                                } else {
                                    found = true;
                                    charPos.add(counter);
                                    letterCounter++;
                                }
                            }
                            else{
                                found = false;
                                letterCounter = 0;
                            }

                            counter = counter +col +1;
                        }
                    }
                }
                if(found && letters.length == letterCounter){
                    tvResult.setText(R.string.found_in_diagonal);
                    updateAdapter = new GridAdapter(context,gridModelArrayList,charPos);
                    gridView.setAdapter(updateAdapter);
                }
                else{
                    tvResult.setText(R.string.not_found);
                    updateAdapter = new GridAdapter(context,gridModelArrayList,null);
                    gridView.setAdapter(updateAdapter);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "findDiagonal: Exception : " + e.getMessage());
        }
    }
}