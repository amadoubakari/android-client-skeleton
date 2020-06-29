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
import androidx.core.widget.NestedScrollView;
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
        holder.contentPreview.setText(notification.getSubTitle().concat(" ..."));
        if (notification.getImage() != null) {
            holder.image.setImageBitmap(BitmapFactory.decodeByteArray(notification.getImage(), 0, notification.getImage().length));
        }
        holder.hideImage.setOnClickListener(v -> {
            toggleSectionText(holder.hideImage, holder.lytExpandText, holder.nestedScrollView);
        });
        holder.lytExpandText.setVisibility(View.GONE);
    }

    private void toggleSectionText(ImageView view, View lytExpandText,NestedScrollView nested_scroll_view) {
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
        TextView date;
        ImageView hideImage;
        View lytExpandText;
        TextView contentPreview;
        NestedScrollView nestedScrollView;
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
            hideImage = itemView.findViewById(R.id.bt_toggle_text);
            lytExpandText = itemView.findViewById(R.id.lyt_expand_text);
            nestedScrollView= itemView.findViewById(R.id.nested_scroll_view);
            contentPreview= itemView.findViewById(R.id.subtitle_for_content);
            this.notificationOnclickListener = notificationOnclickListener;
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
}
