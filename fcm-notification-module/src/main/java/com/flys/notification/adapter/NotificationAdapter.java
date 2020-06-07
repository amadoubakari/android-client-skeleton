package com.flys.notification.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flys.notification.R;
import com.flys.notification.domain.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.Holder> {

    private List<Notification> notifications;
    private Context context;
    private NotificationOnclickListener onclickListener;

    public NotificationAdapter(Context context, List<Notification> notifications, NotificationOnclickListener notificationOnclickListener) {
        this.notifications = notifications;
        this.context = context;
        this.onclickListener = notificationOnclickListener;
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
        holder.title.setText(notification.getTitle());
        holder.subTitle.setText(String.valueOf(notification.getSubTitle()));
        holder.content.setText(String.valueOf(notification.getContent()));
    }


    @Override
    public int getItemCount() {
        return notifications.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        TextView subTitle;
        TextView content;
        NotificationOnclickListener notificationOnclickListener;

        public Holder(@NonNull View itemView, NotificationOnclickListener notificationOnclickListener) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            subTitle = itemView.findViewById(R.id.subtitle);
            content = itemView.findViewById(R.id.content);
            this.notificationOnclickListener = notificationOnclickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            notificationOnclickListener.onClickListener(getAdapterPosition());
        }
    }

    public interface NotificationOnclickListener {
        void onClickListener(int position);
    }

    /**
     * @param listModelsTasks
     */
    public void setFilter(List<Notification> listModelsTasks) {
        notifications = new ArrayList<>();
        notifications.addAll(listModelsTasks);
        notifyDataSetChanged();
    }
}
