package edu.utep.cs.cs5390.mypricewatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ModifyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
    }

    public void onSaveClick(View view){
        Intent data = new Intent("edu.utep.cs.cs5390.ModifyItem");

        EditText modifyItemName = (EditText)findViewById(R.id.modify_item_name);
        EditText modifyItemUrl = (EditText)findViewById(R.id.modify_item_URL);
        EditText modifyItemPrice = (EditText)findViewById(R.id.modify_item_price);

        data.setData(Uri.parse(modifyItemName.getText().toString()));
        data.setData(Uri.parse(modifyItemUrl.getText().toString()));
        data.setData(Uri.parse(modifyItemPrice.getText().toString()));

        setResult(RESULT_OK,data);
        finish();
    }

}