package com.test.mobigic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Context context;
    private Button btnCreateGrid;
    private EditText et_Row, et_Column;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            context = MainActivity.this;

            btnCreateGrid = findViewById(R.id.btnCreateGrid);
            et_Row = findViewById(R.id.editTextRows);
            et_Column = findViewById(R.id.et_SearchText);


            btnCreateGrid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,GridActivity.class);
                    intent.putExtra("ROW",et_Row.getText().toString());
                    intent.putExtra("COLUMN",et_Column.getText().toString());
                    startActivity(intent);

                }
            });



        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "onCreate: Exception : "+e.getMessage() );
        }


    }
}