package com.flys.tools.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.flys.tools.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

/**
 * Created by User on 04/05/2018.
 */

public class AbstractDialogActivity extends DialogFragment {

    private EditText data;
    private String title;
    private int icon;
    private int theme;
    private int bodyStyleAppearance;
    MaterialAlertDialogBuilder builder;

    public AbstractDialogActivity(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public AbstractDialogActivity(String title, int icon, int theme) {
        this.title = title;
        this.icon = icon;
        this.theme = theme;
    }
    public AbstractDialogActivity(String title, int icon, int theme,int bodyStyleAppearance) {
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
                .setPositiveButton(getResources().getString(R.string.abstract_dialog_activity_button_send), (dialog, id) -> sendBackResult(data.getText().toString()))
                .setNegativeButton(getResources().getString(R.string.abstract_dialog_activity_button_cancel), (dialog, id) -> AbstractDialogActivity.this.getDialog().cancel());
        return builder.create();
    }


    public void sendBackResult(String nom) {
        // Notice the use of `getTargetFragment` which will be set when the dialog is displayed
        AbstractDialogFragmentInterface listener = (AbstractDialogFragmentInterface) getActivity();
        listener.receivedDate(nom);
        dismiss();
    }
}
