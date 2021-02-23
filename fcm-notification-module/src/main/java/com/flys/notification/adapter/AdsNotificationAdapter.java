package com.flys.notification.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
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

public class AdsNotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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

    public AdsNotificationAdapter(Context context, List<Notification> notifications, DialogStyle dialogStyle, NotificationOnclickListener notificationOnclickListener) {
        this.notifications = notifications;
        this.context = context;
        this.onclickListener = notificationOnclickListener;
        formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        this.dialogStyle = dialogStyle;
        typeface = ResourcesCompat.getFont(context, dialogStyle.getFont());
    }

    public AdsNotificationAdapter(Context context, List<Notification> notifications, DialogStyle dialogStyle, boolean isConnected, NotificationOnclickListener notificationOnclickListener) {
        this.notifications = notifications;
        this.context = context;
        this.onclickListener = notificationOnclickListener;
        formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        this.dialogStyle = dialogStyle;
        typeface = ResourcesCompat.getFont(context, dialogStyle.getFont());
        this.isConnected = isConnected;
    }

    public AdsNotificationAdapter(Context context, List<Notification> notifications, DialogStyle dialogStyle, boolean isConnected, String adsId, NotificationOnclickListener notificationOnclickListener) {
        this.notifications = notifications;
        this.context = context;
        this.onclickListener = notificationOnclickListener;
        formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        this.dialogStyle = dialogStyle;
        typeface = ResourcesCompat.getFont(context, dialogStyle.getFont());
        this.isConnected = isConnected;
        this.adsId = adsId;
    }

    public AdsNotificationAdapter(Context context, List<Notification> notifications, DialogStyle dialogStyle, NotificationOnclickListener notificationOnclickListener, int font) {
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
                view = LayoutInflater.from(context).inflate(R.layout.view_notification_item, parent, false);
                return new Holder(view, this.onclickListener);
            case ADS_ITEM_VIEW:
                view = LayoutInflater.from(context).inflate(R.layout.view_ads_notification_item, parent, false);
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
        if (isConnected) {
            if (position % 2 == 0) {
                return SIMPLE_VIEW_ITEM;
            } else {
                return ADS_ITEM_VIEW;
            }
        } else {
            return SIMPLE_VIEW_ITEM;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        final boolean[] isClicked = {false};
        if (viewHolder instanceof Holder) {
            Holder holder = (Holder) viewHolder;
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
            //holder.title.setTypeface(this.typeface);
            holder.title.setTextColor(dialogStyle.getPrimaryTextColor());
            holder.subTitle.setText(String.valueOf(notification.getSubTitle()));
            //holder.subTitle.setTextColor(dialogStyle.getSecondTextColor());
            //holder.subTitle.setTypeface(this.typeface);
            holder.content.setText(HtmlCompat.fromHtml(notification.getContent(), HtmlCompat.FROM_HTML_MODE_LEGACY));
            //holder.content.setTypeface(this.typeface);
            holder.content.setTextColor(dialogStyle.getPrimaryTextColor());
            holder.date.setText(formatter.format(notification.getDate()));
            //holder.date.setTextColor(dialogStyle.getPrimaryTextColor());
            //holder.date.setTypeface(this.typeface);
            holder.contentPreview.setLines(3);
            holder.contentPreview.setText(notification.getContent());
            //holder.contentPreview.setTypeface(this.typeface);
            //holder.contentPreview.setTextColor(dialogStyle.getPrimaryTextColor());
            //holder.contentPreview.setOnClickListener(v -> toggleSectionText2(holder.contentPreview, holder.lytExpandText, holder.nestedScrollView));
            //holder.contentPreview.setOnClickListener(v -> expandTextView(holder.contentPreview));
            holder.contentPreview.setOnClickListener(v -> {
                        if (!isClicked[0]) {
                            holder.contentPreview.setMaxLines(Integer.MAX_VALUE);
                            expand(holder.contentPreview);
                            isClicked[0] = true;
                        } else {
                            //holder.contentPreview.setLines(3);
                            collapse(holder.contentPreview);
                            isClicked[0] = false;
                        }

                    }
            );


            holder.lytExpandText.setVisibility(View.GONE);
        } else {
            AdsHolder holder = (AdsHolder) viewHolder;
            holder.date.setText(formatter.format(new Date()));
            //holder.date.setTextColor(dialogStyle.getPrimaryTextColor());
            //holder.date.setTypeface(this.typeface);
            Utils.initializeAds(context, holder.templateView, this.adsId,holder.adsNotificationContainer);

        }
    }

    public static void expand(final View v) {
        int matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec(((View) v.getParent()).getWidth(), View.MeasureSpec.EXACTLY);
        int wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(matchParentMeasureSpec, wrapContentMeasureSpec);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        //v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // Expansion speed of 1dp/ms
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density) * 2);
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = initialHeight - (int) (initialHeight * 0.75);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // Collapse speed of 1dp/ms
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density) * 4);
        v.startAnimation(a);
    }

    private void toggleSectionText(ImageView view, View lytExpandText, NestedScrollView nested_scroll_view) {
        boolean show = toggleArrow(view);
        if (show) {
            ViewAnimation.expand(lytExpandText, () -> Tools.nestedScrollTo(nested_scroll_view, lytExpandText));
        } else {
            ViewAnimation.collapse(lytExpandText);
        }
    }

    private void toggleSectionText2(TextView view, View lytExpandText, NestedScrollView nested_scroll_view) {
        boolean show = isVisible(view);
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

    public boolean isVisible(View view) {
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.GONE);
            return true;
        }
        return false;
    }

    private void expandTextView(TextView tv) {
        ObjectAnimator animation = ObjectAnimator.ofInt(tv, "maxLines", tv.getLineCount());
        animation.setDuration(200).start();
    }

    private void collapseTextView(TextView tv, int numLines) {
        ObjectAnimator animation = ObjectAnimator.ofInt(tv, "maxLines", numLines);
        animation.setDuration(200).start();
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
