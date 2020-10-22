package edu.utep.cs.cs5390.mypricewatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

//Airam Martinez & Mildred Brito
//This activity is called whenever the User needs to modify an excisting Item within the list.

public class ModifyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
    }

    public void onSaveClick(View view){
        Intent data = new Intent("edu.utep.cs.cs5390.ModifyItem");

        EditText modifyItemName = findViewById(R.id.modify_item_name);
        EditText modifyItemUrl = findViewById(R.id.modify_item_URL);
        EditText modifyItemPrice = findViewById(R.id.modify_item_price);

        data.setData(Uri.parse(modifyItemName.getText().toString()));
        data.setData(Uri.parse(modifyItemUrl.getText().toString()));
        data.setData(Uri.parse(modifyItemPrice.getText().toString()));

        setResult(RESULT_OK,data);
        finish();
    }

}