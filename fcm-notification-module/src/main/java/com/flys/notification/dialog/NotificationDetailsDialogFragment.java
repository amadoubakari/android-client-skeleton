package com.flys.notification.dialog;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.DialogFragment;

import com.flys.notification.R;
import com.flys.notification.domain.Notification;
import com.flys.notification.utils.Utils;

import java.text.SimpleDateFormat;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by User on 19/10/2018.
 */

public class NotificationDetailsDialogFragment extends DialogFragment {

    private ImageView back;
    private Notification notification;
    private DialogStyle dialogStyle;
    private TextView date;
    private TextView title;
    private TextView subTitle;
    private TextView content;
    private ImageView image;
    private static SimpleDateFormat formatter;
    private static Context context;
    private BitmapDrawable data;
    private CircleImageView icon;

    public static NotificationDetailsDialogFragment newInstance(Context context1, Notification notification, DialogStyle dialogStyle) {
        context = context1;
        formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        NotificationDetailsDialogFragment frag = new NotificationDetailsDialogFragment();
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
        return inflater.inflate(R.layout.dialog_notification_details_fragment, container);
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
        back = view.findViewById(R.id.quit);
        date = view.findViewById(R.id.date);
        title = view.findViewById(R.id.title);
        subTitle = view.findViewById(R.id.subtitle);
        content = view.findViewById(R.id.content);
        image = view.findViewById(R.id.image);
        icon = view.findViewById(R.id.icon);
        // Fetch arguments from bundle and set title
        notification = (Notification) getArguments().getSerializable("notification");
        dialogStyle = (DialogStyle) getArguments().getSerializable("dialogStyle");
        data = Utils.loadImageFromStorage("glearning", notification.getImageName(), context);
        date.setText(formatter.format(notification.getDate()));
        date.setTextColor(dialogStyle.getHeaderColor());
        title.setText(notification.getTitle());
        subTitle.setText(notification.getSubTitle());
        subTitle.setTextColor(dialogStyle.getHeaderColor());
        content.setText(HtmlCompat.fromHtml(notification.getContent(), HtmlCompat.FROM_HTML_MODE_LEGACY));
        getDialog().setTitle(notification.getTitle());
        // Show soft keyboard automatically and request focus to field
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        back.setOnClickListener(view1 -> dismiss());
        if (notification.getImageName() != null && !notification.getImageName().isEmpty()) {
            image.setBackground(data);
            icon.setImageDrawable(data);
        }


    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation;
    }
}
