package com.flys.common_tools.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.flys.common_tools.R;
import com.flys.common_tools.domain.NotificationData;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MaterialNotificationDialog extends DialogFragment {

    private Context context;
    private NotificationData notificationData;
    private NotificationButtonOnclickListeneer buttonOnclickListeneer;

    public MaterialNotificationDialog() {
    }

    public MaterialNotificationDialog(Context context) {
        this.context = context;
    }

    public MaterialNotificationDialog(Context context, NotificationData notificationData) {
        this.context = context;
        this.notificationData = notificationData;
    }

    public MaterialNotificationDialog(Context context, NotificationData notificationData, NotificationButtonOnclickListeneer buttonOnclickListeneer) {
        this.context = context;
        this.notificationData = notificationData;
        this.buttonOnclickListeneer = buttonOnclickListeneer;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.notification_dialog_fragment, container, false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(this.context, notificationData.getResourcesThemes());
        //Mise en forme de l'entete et de l'icone de l'application
        dialogBuilder.setIcon(notificationData.getIcon());
        dialogBuilder.setTitle(notificationData.getTitle());
        dialogBuilder.setMessage(notificationData.getBody());
        dialogBuilder.setPositiveButton(notificationData.getOkMsg(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                buttonOnclickListeneer.okButtonAction();
            }
        });
        dialogBuilder.setNegativeButton(notificationData.getNoMsg(), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                buttonOnclickListeneer.noButtonAction();
            }
        });

        return dialogBuilder.create();
    }

    public interface NotificationButtonOnclickListeneer {
        public void okButtonAction();

        public void noButtonAction();
    }

}
