package com.example.genji.am016_sqlite;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by genji on 3/19/17.
 */

public class InputDialog extends DialogFragment {

    AddListener mCallback;

    // Container Activity must implement this interface
    public interface AddListener {
        void addProduct(Product product);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (AddListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View viewAlert = inflater.inflate(R.layout.dialog_add, null);
        builder.setView(viewAlert);
        final EditText editName = (EditText) viewAlert.findViewById(R.id.product_name);
        final EditText editDescription = (EditText) viewAlert.findViewById(R.id.product_description);
        // set title
        builder.setMessage(R.string.add_product);
        builder.setPositiveButton("add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String name = editName.getText().toString();
                String description = editDescription.getText().toString();
                Product product = new Product(name, description);
                mCallback.addProduct(product);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
