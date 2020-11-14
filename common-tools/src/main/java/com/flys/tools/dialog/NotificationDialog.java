package com.flys.tools.dialog;

import androidx.appcompat.app.AlertDialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.flys.tools.R;
import com.flys.tools.domain.NotificationData;

/**
 * Res: https://guides.codepath.com/android/using-dialogfragment#build-dialog
 */
public class NotificationDialog extends DialogFragment {

    private NotificationData notificationData;
    private NotificationButtonOnclickListeneer notificationButtonOnclickListeneer;

    public NotificationDialog(NotificationData notificationData) {
        this.notificationData = notificationData;
    }

    public NotificationDialog(NotificationData notificationData, NotificationButtonOnclickListeneer notificationButtonOnclickListeneer) {
        this.notificationData = notificationData;
        this.notificationButtonOnclickListeneer = notificationButtonOnclickListeneer;
    }

    interface NotificationButtonOnclickListeneer {
        public void okButtonAction();

        public void noButtonAction();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.notification_dialog_fragment, container, false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity(), notificationData.getResourcesThemes());
        alertDialogBuilder.setTitle(notificationData.getTitle());
        alertDialogBuilder.setMessage(notificationData.getBody());
        alertDialogBuilder.setIcon(notificationData.getIcon());
        alertDialogBuilder.setPositiveButton("OK", (dialog, which) -> getActivity().finish());
        alertDialogBuilder.setNegativeButton("No", (dialog, which) -> {
            if (dialog != null) {
                dialog.dismiss();
            }
        });
        return alertDialogBuilder.create();
    }
}
