package com.flys.notification.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.text.HtmlCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.flys.google.ads.TemplateView;
import com.flys.notification.R;
import com.flys.notification.dialog.DialogStyle;
import com.flys.notification.domain.Notification;
import com.flys.notification.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdsSimpleNotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ADS_ITEM_VIEW = 0;
    private static final int SIMPLE_VIEW_ITEM = 1;
    private List<Notification> notifications;
    private Context context;
    private NotificationOnclickListener onclickListener;
    private SimpleDateFormat formatter;
    private DialogStyle dialogStyle;
    private Typeface typeface;
    private boolean isConnected;
    private String adsId;

    public AdsSimpleNotificationAdapter(Context context, List<Notification> notifications, DialogStyle dialogStyle, NotificationOnclickListener notificationOnclickListener) {
        this.notifications = notifications;
        this.context = context;
        this.onclickListener = notificationOnclickListener;
        formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        this.dialogStyle = dialogStyle;
        typeface = ResourcesCompat.getFont(context, dialogStyle.getFont());
    }

    public AdsSimpleNotificationAdapter(Context context, List<Notification> notifications, DialogStyle dialogStyle, boolean isConnected, NotificationOnclickListener notificationOnclickListener) {
        this.notifications = notifications;
        this.context = context;
        this.onclickListener = notificationOnclickListener;
        formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        this.dialogStyle = dialogStyle;
        typeface = ResourcesCompat.getFont(context, dialogStyle.getFont());
        this.isConnected = isConnected;
    }

    public AdsSimpleNotificationAdapter(Context context, List<Notification> notifications, DialogStyle dialogStyle, boolean isConnected, String adsId, NotificationOnclickListener notificationOnclickListener) {
        this.notifications = notifications;
        this.context = context;
        this.onclickListener = notificationOnclickListener;
        formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        this.dialogStyle = dialogStyle;
        typeface = ResourcesCompat.getFont(context, dialogStyle.getFont());
        this.isConnected = isConnected;
        this.adsId = adsId;
    }

    public AdsSimpleNotificationAdapter(Context context, List<Notification> notifications, DialogStyle dialogStyle, NotificationOnclickListener notificationOnclickListener, int font) {
        this.notifications = notifications;
        this.context = context;
        this.onclickListener = notificationOnclickListener;
        formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        this.dialogStyle = dialogStyle;
        typeface = ResourcesCompat.getFont(context, font);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case SIMPLE_VIEW_ITEM:
                view = LayoutInflater.from(context).inflate(R.layout.view_simple_notification_item, parent, false);
                return new Holder(view, this.onclickListener);
            case ADS_ITEM_VIEW:
                view = LayoutInflater.from(context).inflate(R.layout.view_simple_ads_notification_item, parent, false);
                return new AdsHolder(view);
        }

        return null;
    }


    /**
     * Return the view type of the item at <code>position</code> for the purposes
     * of view recycling.
     *
     * <p>The default implementation of this method returns 0, making the assumption of
     * a single view type for the adapter. Unlike ListView adapters, types need not
     * be contiguous. Consider using id resources to uniquely identify item view types.
     *
     * @param position position to query
     * @return integer value identifying the type of the view needed to represent the item at
     * <code>position</code>. Type codes need not be contiguous.
     */
    @Override
    public int getItemViewType(int position) {
        if (isConnected && notifications.get(position) == null) {
            return ADS_ITEM_VIEW;
        } else {
            return SIMPLE_VIEW_ITEM;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof Holder) {
            Holder holder = (Holder) viewHolder;
            Notification notification = notifications.get(position);
            if (Utils.fileExist("glearning", notification.getImageName(), context)) {
                holder.image.setVisibility(View.VISIBLE);
                holder.icon.setVisibility(View.VISIBLE);
                holder.image.setImageDrawable(Utils.loadImageFromStorage("glearning", notification.getImageName(), context));
                holder.icon.setImageDrawable(Utils.loadImageFromStorage("glearning", notification.getSourceIcon(), context));
            } else {
                holder.image.setVisibility(View.GONE);
                holder.icon.setVisibility(View.GONE);
            }
            holder.title.setText(notification.getTitle());
            holder.date.setText(formatter.format(notification.getDate()));
            holder.source.setText(notification.getSource());
            holder.contentPreview.setLines(3);
            holder.contentPreview.setText(HtmlCompat.fromHtml(notification.getContent(), HtmlCompat.FROM_HTML_MODE_LEGACY));
        } else {
            AdsHolder holder = (AdsHolder) viewHolder;
            holder.date.setText(formatter.format(new Date()));
            Utils.initializeAds(context, holder.templateView, this.adsId, holder.adsNotificationContainer);

        }
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        ImageView menu;
        ImageView share;
        ImageView image;
        ImageView icon;
        TextView date;
        TextView contentPreview;
        TextView source;
        NotificationOnclickListener notificationOnclickListener;

        public Holder(@NonNull View itemView, NotificationOnclickListener onclickListener) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            menu = itemView.findViewById(R.id.menu);
            image = itemView.findViewById(R.id.image);
            icon = itemView.findViewById(R.id.icon);
            date = itemView.findViewById(R.id.date);
            share = itemView.findViewById(R.id.share);
            contentPreview = itemView.findViewById(R.id.subtitle_for_content);
            source = itemView.findViewById(R.id.notification_src);
            notificationOnclickListener = onclickListener;
            contentPreview.setOnClickListener(this);
            share.setOnClickListener(this);
            menu.setOnClickListener(this);
            image.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.menu) {
                notificationOnclickListener.onMenuClickListener(v, getAdapterPosition());
            } else if (v.getId() == R.id.image || v.getId() == R.id.subtitle_for_content) {
                notificationOnclickListener.onShowMoreClickListener(getAdapterPosition());
            } else if (v.getId() == R.id.share) {
                notificationOnclickListener.onShareClickListener(getAdapterPosition());
            }
        }
    }

    class AdsHolder extends RecyclerView.ViewHolder {

        TemplateView templateView;
        TextView date;
        ConstraintLayout adsNotificationContainer;

        public AdsHolder(@NonNull View itemView) {
            super(itemView);
            templateView = itemView.findViewById(R.id.my_template);
            date = itemView.findViewById(R.id.date);
            adsNotificationContainer = itemView.findViewById(R.id.adsNotificationContainer);
        }
    }

    public interface NotificationOnclickListener {
        void onShowMoreClickListener(int position);

        void onMenuClickListener(View v, int position);

        void onShareClickListener(int adapterPosition);
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
