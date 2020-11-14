package com.flys.tools.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.flys.tools.domain.NotificationData;
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

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(this.context, notificationData.getResourcesThemes());
        dialogBuilder.setIcon(notificationData.getIcon());
        dialogBuilder.setTitle(notificationData.getTitle());
        dialogBuilder.setMessage(notificationData.getBody());
        dialogBuilder.setPositiveButton(notificationData.getOkMsg(), (dialog, id) -> buttonOnclickListeneer.okButtonAction(dialog,id));
        dialogBuilder.setNegativeButton(notificationData.getNoMsg(), (dialog, id) -> buttonOnclickListeneer.noButtonAction(dialog,id));

        return dialogBuilder.create();
    }

    public interface NotificationButtonOnclickListeneer {
        void okButtonAction(DialogInterface dialog, int id);

        void noButtonAction(DialogInterface dialog, int id);
    }

}
