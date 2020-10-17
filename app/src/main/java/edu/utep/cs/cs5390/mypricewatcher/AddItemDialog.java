package edu.utep.cs.cs5390.mypricewatcher;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AddItemDialog extends AppCompatDialogFragment {

    private EditText editTextItemName;
    private EditText editTextItemURL;
    private EditText editTextItemPrice;
    private AddItemDialogListener listener;
    private WatchListAdapter adapter;
    private Item item;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view)
                .setTitle("Add Item")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String itemName = editTextItemName.getText().toString();
                        String itemURL = editTextItemURL.getText().toString();
                        if(!itemName.isEmpty() || !itemURL.isEmpty() || !editTextItemPrice.getText().toString().isEmpty()) {
                            double itemPrice = Double.parseDouble(editTextItemPrice.getText().toString());
                            listener.applyTexts(itemName, itemURL, itemPrice);
                        }
                        addItem();
                    }
                });

        editTextItemName = view.findViewById(R.id.edit_item_name);
        editTextItemURL = view.findViewById(R.id.edit_item_URL);
        editTextItemPrice = view.findViewById(R.id.edit_item_price);

        return builder.create();
    }

    private void addItem() {
        String itemName = editTextItemName.getText().toString();
        String itemURL = editTextItemURL.getText().toString();
        double itemPriceD = Double.parseDouble(editTextItemPrice.getText().toString());
        item = new Item(itemName, itemPriceD, itemURL);
        adapter.add(item);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (AddItemDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement AddItemDialog");
        }
    }

    public interface AddItemDialogListener {
        void applyTexts(String itemName, String itemURL, double itemPrice);
    }
}
