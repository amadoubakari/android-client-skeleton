package client.android.fragments.behavior;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flys.notification.dialog.DialogStyle;
import com.flys.tools.dialog.AbstractDialogActivity;
import com.flys.tools.dialog.EditDialogFragment;
import com.flys.tools.dialog.MaterialNotificationDialog;
import com.flys.generictools.dao.daoException.DaoException;
import com.flys.notification.adapter.NotificationAdapter;
import com.flys.notification.domain.Notification;


import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import client.android.R;
import client.android.architecture.core.AbstractFragment;
import client.android.architecture.custom.CoreState;
import client.android.dao.db.User;
import client.android.dao.db.UserDao;
import client.android.dao.db.UserDaoImpl;

import com.flys.notification.dialog.NotificationDetailsDialogFragment;
import com.kyossi.firebase.tools.FirebaseCommonTools;

@EFragment(R.layout.fragment_dummy_layout)
@OptionsMenu(R.menu.menu_vide)
public class TestFragment extends AbstractFragment implements MaterialNotificationDialog.NotificationButtonOnclickListeneer, NotificationAdapter.NotificationOnclickListener {

    @ViewById(R.id.recyclerview)
    RecyclerView recyclerView;

    @Bean(UserDaoImpl.class)
    protected UserDao userDao;
    @ViewById(R.id.Splashscreen)
    protected TextView msg;

    MaterialNotificationDialog dialog;
    List<Notification> notifications;
    private NotificationAdapter notificationAdapter;

    @Click(R.id.Splashscreen)
    void splas() {
        //Bundle bundle = new Bundle();
        //bundle.putSerializable("notification", new Notification("title", "subtitle", "context", null, null));
        //NotificationDetailsDialogFragment configDialogFragment = NotificationDetailsDialogFragment.newInstance(new Notification("title", "subtitle", "context", null, null));
        //configDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme);
        //configDialogFragment.show(getActivity().getSupportFragmentManager(), "fragment_edit_name");
       /* dialog = new MaterialNotificationDialog(activity,
                new NotificationData("Dubun Guiziga", "Vvoudriez-vous quittez l'application?", "OUI", "NO", activity.getDrawable(R.drawable.ic_people_outline_24px)), this);
        dialog.show(getActivity().getSupportFragmentManager(), "data_notif");*/
        /*Bundle bundle=new Bundle();
         */
        /*List<Notification> notifications = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            notifications.add(new Notification("title", "Subtitle", "Descriptions"));
        }*/

        /*bundle.putSerializable("notifications", new Notification("title","Subtitle","Descriptions"));
        NotificationFragment newFragment = new NotificationFragment();
        newFragment.setArguments(bundle);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_c, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();*/
        //Utils.startNotification(getActivity(), R.id.main_c, notifications);
        //ConfigDialogFragment configDialogFragment = ConfigDialogFragment.newInstance(new Notification("title", "subtitle", "context", null, null),new DialogStyle(activity.getColor(R.color.blue_300)));
        //configDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme);
        //configDialogFragment.show(getActivity().getSupportFragmentManager(), "fragment_edit_name");
        FragmentManager fm = getParentFragmentManager();
        EditDialogFragment dialogActivity = new EditDialogFragment("Recommander", R.mipmap.ic_launcher,0);
        dialogActivity.show(fm, "fragment_edit_name");
    }

    @Override
    public CoreState saveFragment() {
        return new CoreState();
    }

    @Override
    protected int getNumView() {
        return 1;
    }

    @Override
    protected void initFragment(CoreState previousState) {
        ((AppCompatActivity) mainActivity).getSupportActionBar().show();

    }

    @Override
    protected void initView(CoreState previousState) {
        // Create new fragment and transaction
        try {
            User user = userDao.save(new User("AMADOU BAKARI"));
            msg.setText(userDao.findById(user.getId()).getNom());
        } catch (DaoException e) {
            e.printStackTrace();
        }
        notifications = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            notifications.add(new Notification("Dubun Guiziga", "Animaux en Guiziga", "<h2>Languages</h2>\n" +
                    "  <ul>\n" +
                    "    <li>English</li>\n" +
                    "    <li>Spanish</li>\n" +
                    "    <li>Japanese</li>\n" +
                    "  </ul>\n" +
                    "  <h2>Counting in English</h2>\n" +
                    "  <ol>\n" +
                    "    <li>one</li>\n" +
                    "    <li>two</li>\n" +
                    "    <li>three</li>\n" +
                    "  </ol>\n" +
                    "  <h2>Counting in Other Languages</h2>\n" +
                    "  <ul>\n" +
                    "    <li>Spanish\n" +
                    "      <ol>\n" +
                    "        <li>uno</li>\n" +
                    "        <li>dos</li>\n" +
                    "        <li>tres</li>\n" +
                    "      </ol>\n" +
                    "    </li>\n" +
                    "    <li>Japanese\n" +
                    "      <ol>\n" +
                    "        <li>ichi</li>\n" +
                    "        <li>ni</li>\n" +
                    "        <li>san</li>\n" +
                    "      </ol>      \n" +
                    "    </li>\n" +
                    "  </ul>", null, new Date(), "amadou.jpg",null));
        }
        notificationAdapter = new NotificationAdapter(activity, notifications, new DialogStyle(activity.getColor(R.color.blue_500),1,"fonts/Roboto-Thin.ttf"),this);
        notificationAdapter.refreshAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(notificationAdapter);


    }

    @Override
    protected void updateOnSubmit(CoreState previousState) {

    }

    @Override
    protected void updateOnRestore(CoreState previousState) {

    }

    @Override
    protected void notifyEndOfUpdates() {

    }

    @Override
    protected void notifyEndOfTasks(boolean runningTasksHaveBeenCanceled) {

    }

    @Override
    public void okButtonAction(DialogInterface dialog, int id) {

    }

    @Override
    public void noButtonAction(DialogInterface dialog, int id) {

    }

    public boolean showMenu(Context context, View anchor, int custom_menu) {
        PopupMenu popup = new PopupMenu(context, anchor);
        popup.getMenuInflater().inflate(custom_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.option_menu_share:
                    //Utils.shareText(context, "ƁIMUTOHO MIPAL", verset.getTitre().getChapitre().getNom().substring(0, 3) + " " + verset.getTitre().getChapitre().getNumero() + ":" + verset.getNumero() + "\n" + verset.getDescription(), "ƁIMUTOHO MIPAL");
                    break;
                case R.id.option_menu_mark:
                    Toast.makeText(context, "Marquer", Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
            return false;
        });
        popup.show();
        return true;
    }

    @Override
    public void onButtonClickListener(int position) {
        NotificationDetailsDialogFragment notificationDetailsDialogFragment = NotificationDetailsDialogFragment.newInstance(activity, notifications.get(position),new DialogStyle(activity.getColor(R.color.blue_300)));
        notificationDetailsDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme);
        notificationDetailsDialogFragment.show(getActivity().getSupportFragmentManager(), "fragment_edit_name");
    }

    @Override
    public void onMenuClickListener(View v, int position) {

    }
}
