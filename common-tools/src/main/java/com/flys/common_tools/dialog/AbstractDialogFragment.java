package com.flys.common_tools.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.flys.common_tools.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

/**
 * Created by User on 04/05/2018.
 */

public class AbstractDialogFragment extends DialogFragment {

    private EditText data;
    private String title;
    private int icon;
    private int theme;
    private int bodyStyleAppearance;
    MaterialAlertDialogBuilder builder;

    public AbstractDialogFragment(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public AbstractDialogFragment(String title, int icon, int theme) {
        this.title = title;
        this.icon = icon;
        this.theme = theme;
    }
    public AbstractDialogFragment(String title, int icon, int theme, int bodyStyleAppearance) {
        this.title = title;
        this.icon = icon;
        this.theme = theme;
        this.bodyStyleAppearance=bodyStyleAppearance;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (theme > 0) {
            builder = new MaterialAlertDialogBuilder(getActivity(), theme);
        } else {
            builder = new MaterialAlertDialogBuilder(getActivity());
        }
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        // Inflate and set the layout for the dialog
        View view = inflater.inflate(R.layout.dialog_fragment_layout, null);
        data = view.findViewById(R.id.data);

        if(bodyStyleAppearance>0){
            data.setTextAppearance(bodyStyleAppearance);
        }
        //Mise en forme de l'entete et de l'icone de l'application
        builder.setTitle(title);
        builder.setIcon(icon);
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("ENVOYER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        sendBackResult(data.getText().toString());
                    }
                })
                .setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AbstractDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }


    public void sendBackResult(String nom) {
        // Notice the use of `getTargetFragment` which will be set when the dialog is displayed
        AbstractDialogFragmentInterface listener = (AbstractDialogFragmentInterface) getActivity();
        listener.receivedDate(nom);
        dismiss();
    }
}
