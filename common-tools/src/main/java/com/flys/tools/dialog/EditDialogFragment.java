package com.flys.tools.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.DialogFragment;

import com.flys.tools.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

/**
 * Created by User on 04/05/2018 modified 13/12/2020.
 *
 * @author AMADOU BAKARI
 * @version 1.0.1
 */

public class EditDialogFragment extends DialogFragment {

    private EditText data;
    private String title;
    private int icon;
    private int theme;
    private MaterialAlertDialogBuilder builder;
    private Context context;
    private int font ;

    public EditDialogFragment(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public EditDialogFragment(String title, int icon, int theme) {
        this.title = title;
        this.icon = icon;
        this.theme = theme;
    }

    public EditDialogFragment(Context context, String title, int icon, int theme) {
        this.title = title;
        this.icon = icon;
        this.theme = theme;
        this.context = context;
    }
    public EditDialogFragment(Context context, String title, int icon, int theme,int font) {
        this.title = title;
        this.icon = icon;
        this.theme = theme;
        this.context = context;
        this.font=font;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        builder = new MaterialAlertDialogBuilder(context, theme);
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        // Inflate and set the layout for the dialog
        View view = inflater.inflate(R.layout.dialog_fragment_layout, null);
        data = view.findViewById(R.id.data);
        data.setTypeface(ResourcesCompat.getFont(context,font));
        //Mise en forme de l'entete et de l'icone de l'application
        builder.setTitle(title);
        builder.setIcon(icon);
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton(getResources().getString(R.string.abstract_dialog_activity_button_send), (dialog, id) -> {
                    dialog.dismiss();
                    sendBackResult(data.getText().toString());
                })
                .setNegativeButton(getResources().getString(R.string.abstract_dialog_activity_button_cancel), (dialog, id) -> dialog.cancel());
        return builder.create();
    }


    public void sendBackResult(String nom) {
        // Notice the use of `getTargetFragment` which will be set when the dialog is displayed
        AbstractDialogFragmentInterface listener = (AbstractDialogFragmentInterface) getActivity();
        listener.receivedDate(nom);
        dismiss();
    }
}
