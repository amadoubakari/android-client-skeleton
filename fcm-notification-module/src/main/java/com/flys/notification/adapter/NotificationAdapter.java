package com.flys.notification.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.flys.notification.R;
import com.flys.notification.domain.Notification;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.Holder> {

    private List<Notification> notifications;
    private Context context;
    private NotificationOnclickListener onclickListener;
    private SimpleDateFormat formatter;

    public NotificationAdapter(Context context, List<Notification> notifications, NotificationOnclickListener notificationOnclickListener) {
        this.notifications = notifications;
        this.context = context;
        this.onclickListener = notificationOnclickListener;
        formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
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
        holder.content.setText(HtmlCompat.fromHtml(notification.getContent(), HtmlCompat.FROM_HTML_MODE_LEGACY));
        holder.date.setText(formatter.format(notification.getDate()));
        if (notification.getImage() != null) {
            holder.image.setImageBitmap(BitmapFactory.decodeByteArray(notification.getImage(), 0, notification.getImage().length));
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
        TextView date;
        NotificationOnclickListener notificationOnclickListener;

        public Holder(@NonNull View itemView, NotificationOnclickListener notificationOnclickListener) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            subTitle = itemView.findViewById(R.id.subtitle);
            content = itemView.findViewById(R.id.content);
            menu = itemView.findViewById(R.id.menu);
            button = itemView.findViewById(R.id.button);
            image = itemView.findViewById(R.id.image);
            date = itemView.findViewById(R.id.date);
            this.notificationOnclickListener = notificationOnclickListener;
            button.setOnClickListener(this);
            menu.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.menu) {
                notificationOnclickListener.onMenuListener(v, getAdapterPosition());
            }else if(v.getId()==R.id.button){
                notificationOnclickListener.onClickListener(getAdapterPosition());
            }
        }
    }

    public interface NotificationOnclickListener {
        void onClickListener(int position);

        void onMenuListener(View v, int position);
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
