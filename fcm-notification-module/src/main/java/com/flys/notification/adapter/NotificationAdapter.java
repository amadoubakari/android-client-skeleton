package com.flys.notification.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.text.HtmlCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.flys.notification.R;
import com.flys.notification.dialog.DialogStyle;
import com.flys.notification.domain.Notification;
import com.flys.notification.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.Holder> {

    private List<Notification> notifications;
    private Context context;
    private NotificationOnclickListener onclickListener;
    private SimpleDateFormat formatter;
    private DialogStyle dialogStyle;
    private Typeface typeface;

    public NotificationAdapter(Context context, List<Notification> notifications, DialogStyle dialogStyle, NotificationOnclickListener notificationOnclickListener) {
        this.notifications = notifications;
        this.context = context;
        this.onclickListener = notificationOnclickListener;
        formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        this.dialogStyle = dialogStyle;
        typeface = ResourcesCompat.getFont(context,dialogStyle.getFont());
    }

    public NotificationAdapter(Context context, List<Notification> notifications, DialogStyle dialogStyle, NotificationOnclickListener notificationOnclickListener, int font) {
        this.notifications = notifications;
        this.context = context;
        this.onclickListener = notificationOnclickListener;
        formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        this.dialogStyle = dialogStyle;
        typeface = ResourcesCompat.getFont(context,font);
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_notification_item, parent, false);
        return new Holder(view, this.onclickListener);
    }


    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Notification notification = notifications.get(position);
        BitmapDrawable data;
        if (Utils.fileExist("glearning", notification.getImageName(), context)) {
            holder.image.setVisibility(View.VISIBLE);
            holder.icon.setVisibility(View.VISIBLE);
            data = Utils.loadImageFromStorage("glearning", notification.getImageName(), context);
            holder.image.setImageDrawable(data);
            holder.icon.setImageDrawable(data);
        } else {
            holder.image.setVisibility(View.GONE);
            holder.icon.setVisibility(View.GONE);
        }
        holder.title.setText(notification.getTitle());
        holder.title.setTypeface(this.typeface);
        holder.title.setTextColor(dialogStyle.getPrimaryTextColor());
        holder.subTitle.setText(String.valueOf(notification.getSubTitle()));
        holder.subTitle.setTextColor(dialogStyle.getSecondTextColor());
        holder.subTitle.setTypeface(this.typeface);
        holder.content.setText(HtmlCompat.fromHtml(notification.getContent(), HtmlCompat.FROM_HTML_MODE_LEGACY));
        holder.content.setTypeface(this.typeface);
        holder.content.setTextColor(dialogStyle.getPrimaryTextColor());
        holder.date.setText(formatter.format(notification.getDate()));
        holder.date.setTextColor(dialogStyle.getPrimaryTextColor());
        holder.date.setTypeface(this.typeface);
        holder.contentPreview.setText(notification.getSubTitle().concat(" ..."));
        holder.contentPreview.setTypeface(this.typeface);
        holder.contentPreview.setTextColor(dialogStyle.getPrimaryTextColor());
        holder.hideImage.setOnClickListener(v -> {
            toggleSectionText(holder.hideImage, holder.lytExpandText, holder.nestedScrollView);
        });
        holder.lytExpandText.setVisibility(View.GONE);
    }


    private void toggleSectionText(ImageView view, View lytExpandText, NestedScrollView nested_scroll_view) {
        boolean show = toggleArrow(view);
        if (show) {
            ViewAnimation.expand(lytExpandText, () -> Tools.nestedScrollTo(nested_scroll_view, lytExpandText));
        } else {
            ViewAnimation.collapse(lytExpandText);
        }
    }

    public boolean toggleArrow(View view) {
        if (view.getRotation() == 0) {
            view.animate().setDuration(200).rotation(180);
            return true;
        } else {
            view.animate().setDuration(200).rotation(0);
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        TextView subTitle;
        TextView content;
        ImageView menu;
        Button button;
        ImageView image;
        ImageView icon;
        TextView date;
        ImageView hideImage;
        View lytExpandText;
        TextView contentPreview;
        NestedScrollView nestedScrollView;
        NotificationOnclickListener notificationOnclickListener;

        public Holder(@NonNull View itemView, NotificationOnclickListener onclickListener) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            subTitle = itemView.findViewById(R.id.subtitle);
            content = itemView.findViewById(R.id.content);
            menu = itemView.findViewById(R.id.menu);
            button = itemView.findViewById(R.id.button);
            image = itemView.findViewById(R.id.image);
            icon = itemView.findViewById(R.id.icon);
            date = itemView.findViewById(R.id.date);
            hideImage = itemView.findViewById(R.id.bt_toggle_text);
            lytExpandText = itemView.findViewById(R.id.lyt_expand_text);
            nestedScrollView = itemView.findViewById(R.id.nested_scroll_view);
            contentPreview = itemView.findViewById(R.id.subtitle_for_content);
            notificationOnclickListener = onclickListener;
            button.setOnClickListener(this);
            menu.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.menu) {
                notificationOnclickListener.onMenuClickListener(v, getAdapterPosition());
            } else if (v.getId() == R.id.button) {
                notificationOnclickListener.onButtonClickListener(getAdapterPosition());
            }
        }
    }

    public interface NotificationOnclickListener {
        void onButtonClickListener(int position);

        void onMenuClickListener(View v, int position);
    }

    /**
     * @param listModelsTasks
     */
    public void setFilter(List<Notification> listModelsTasks) {
        notifications = new ArrayList<>();
        notifications.addAll(listModelsTasks);
        notifyDataSetChanged();
    }

    /**
     * Refreshing the adapter
     */
    public void refreshAdapter() {
        notifyDataSetChanged();
    }

    /**
     * Adding new notifications to recyclerview and refresh the adapter
     *
     * @param notifications
     */
    public void addAll(List<Notification> notifications) {
        this.notifications.addAll(notifications);
        notifyDataSetChanged();

    }
}
