package com.flys.notification.dialog;

import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.flys.notification.R;
import com.flys.notification.domain.Notification;


/**
 * Created by User on 19/10/2018.
 */

public class NotificationDetailsDialogFragment extends DialogFragment{

    //private ImageView save;
    private Notification notification;
    //private ImageView image;
    //private TextView title;

    public static NotificationDetailsDialogFragment newInstance(Notification data) {
        NotificationDetailsDialogFragment frag = new NotificationDetailsDialogFragment();
        //Put sérialization
        Bundle bundle = new Bundle();
        bundle.putSerializable("notification", data);
        frag.setArguments(bundle);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_exemple, container);
    }

    @Override
    public void onResume() {
        // Get existing layout params for the window
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        // Assign window properties to fill the parent
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme);
        getDialog().getWindow().setAttributes((WindowManager.LayoutParams) params);
        // Call super onResume after sizing
        super.onResume();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        ImageView save = view.findViewById(R.id.notificiation_dialog_skip);
        ImageView image = view.findViewById(R.id.image);
        TextView title = view.findViewById(R.id.title);
        // Fetch arguments from bundle and set title
        notification = (Notification) getArguments().getSerializable("notification");
        getDialog().setTitle(notification.getTitle());
        title.setText(notification.getTitle());
        if (notification.getImage() != null) {
            image.setImageBitmap(BitmapFactory.decodeByteArray(notification.getImage(), 0, notification.getImage().length));
        }
        // Show soft keyboard automatically and request focus to field
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        save.setOnClickListener(v->Log.e(getClass().getCanonicalName(),"bonjour"));

    }
}
