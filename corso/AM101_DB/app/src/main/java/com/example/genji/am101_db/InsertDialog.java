package com.example.genji.am101_db;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by genji on 3/26/16.
 */
public class InsertDialog extends DialogFragment {

    private OnInsertListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Context ctx = getActivity();
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View rootView = inflater.inflate(R.layout.insert_dialog, null, false);
        final EditText editName = (EditText) rootView.findViewById(R.id.insert_name);
        final TextView editDescription = (EditText) rootView.findViewById(R.id.insert_description);

        return new AlertDialog.Builder(ctx)
                //.setTitle("Add Item")
                .setView(rootView)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String name = editName.getText().toString();
                        String description = editDescription.getText().toString();
                        Product ins = new Product(name, description);
                        MainActivity main = (MainActivity)InsertDialog.this.getActivity();
                        main.add(ins);

                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
    }

    public interface OnInsertListener {
        public void onProductInsert(Product ins);
    }



}
