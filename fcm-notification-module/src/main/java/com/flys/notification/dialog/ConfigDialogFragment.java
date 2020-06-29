package com.flys.notification.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.DialogFragment;

import com.flys.notification.R;
import com.flys.notification.domain.Notification;

import java.text.SimpleDateFormat;

/**
 * Created by User on 19/10/2018.
 */

public class ConfigDialogFragment extends DialogFragment {

    private TextView mEditText;
    private ImageView save;
    private Notification notification;
    private LinearLayout notificationDetailsHeader;
    private DialogStyle dialogStyle;
    private ImageView back;
    private TextView time;
    private TextView content;
    private static SimpleDateFormat formatter;

    public static ConfigDialogFragment newInstance(Notification notification, DialogStyle dialogStyle) {
        formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        ConfigDialogFragment frag = new ConfigDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("notification", notification);
        bundle.putSerializable("dialogStyle", dialogStyle);
        //Put sérialization
        frag.setArguments(bundle);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_config_fragment_layout_2, container);
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
        mEditText = view.findViewById(R.id.title);
        back = view.findViewById(R.id.iv_notification_back);
        save = view.findViewById(R.id.save);
        time = view.findViewById(R.id.time);
        content = view.findViewById(R.id.content);
        notificationDetailsHeader = view.findViewById(R.id.lyt_notification_details_header);
        // Fetch arguments from bundle and set title
        notification = (Notification) getArguments().getSerializable("notification");
        dialogStyle = (DialogStyle) getArguments().getSerializable("dialogStyle");
        mEditText.setText(notification.getTitle());
        time.setText(formatter.format(notification.getDate()));
        time.setTextColor(dialogStyle.getHeaderColor());
        content.setText(HtmlCompat.fromHtml(notification.getContent(), HtmlCompat.FROM_HTML_MODE_LEGACY));
        notificationDetailsHeader.setBackgroundColor(dialogStyle.getHeaderColor());
        getDialog().setTitle(notification.getTitle());
        // Show soft keyboard automatically and request focus to field
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        save.setOnClickListener(view1 -> dismiss());
        back.setOnClickListener(view1 -> dismiss());

    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation;
    }
}
