package com.flys.notification.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flys.notification.R;
import com.flys.notification.adapter.NotificationAdapter;
import com.flys.notification.domain.Notifications;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */

public class NotificationFragment extends Fragment implements NotificationAdapter.NotificationOnclickListener {

    public NotificationFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.notification_layout, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerview);
        Notifications notifications = (Notifications) getArguments().getSerializable("notifications");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new NotificationAdapter(getContext(), notifications.getNotifications(), this));
    }

    @Override
    public void onClickListener(int position) {
        Toast.makeText(getContext(), "notificiation " + position, Toast.LENGTH_LONG).show();
    }


}
