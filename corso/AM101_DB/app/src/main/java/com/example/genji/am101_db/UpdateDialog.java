package com.example.genji.am101_db;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.style.UpdateAppearance;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by genji on 3/26/16.
 */
public class UpdateDialog  extends DialogFragment {

    String name;
    String description;
    int position;
    Context mContext;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        // inflate layout
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.update_dialog, null, false);

        name = getArguments().getString("name");
        position = getArguments().getInt("position");

        final TextView textName = (TextView) rootView.findViewById(R.id.update_name);
        textName.setText(name);
        final TextView editDescription = (EditText) rootView.findViewById(R.id.update_description);
        final Button buttonUpdate = (Button) rootView.findViewById(R.id.update);
        final Button buttonDelete = (Button) rootView.findViewById(R.id.delete);
        final Button buttonExit = (Button) rootView.findViewById(R.id.exit);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                description = editDescription.getText().toString();
                Toast.makeText(mContext, name + " " + description, Toast.LENGTH_SHORT).show();
                MainActivity main = (MainActivity)mContext;
                main.update(position, description);
                dismiss();
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity main = (MainActivity)mContext;
                main.delete(UpdateDialog.this.position);
                dismiss();
            }
        });
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return new AlertDialog.Builder(mContext)
                //.setTitle("Add Item")
                .setView(rootView)
                .create();
    }
}


