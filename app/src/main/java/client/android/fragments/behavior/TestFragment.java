package client.android.fragments.behavior;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.flys.common_tools.dialog.MaterialNotificationDialog;
import com.flys.common_tools.domain.NotificationData;
import com.flys.generictools.dao.daoException.DaoException;
import com.flys.notification.domain.Notification;
import com.flys.notification.utils.Utils;


import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import client.android.R;
import client.android.architecture.core.AbstractFragment;
import client.android.architecture.custom.CoreState;
import client.android.dao.db.User;
import client.android.dao.db.UserDao;
import client.android.dao.db.UserDaoImpl;

@EFragment(R.layout.fragment_dummy_layout)
@OptionsMenu(R.menu.menu_vide)
public class TestFragment extends AbstractFragment implements MaterialNotificationDialog.NotificationButtonOnclickListeneer {

    @ViewById(R.id.recyclerview)
    RecyclerView recyclerView;

    @Bean(UserDaoImpl.class)
    protected UserDao userDao;

    MaterialNotificationDialog dialog;
    List<Notification> notifications;

    @Click(R.id.Splashscreen)
    void splas() {
        dialog = new MaterialNotificationDialog(activity,
                new NotificationData("Dubun Guiziga", "Vvoudriez-vous quittez l'application?", "OUI", "NO", activity.getDrawable(R.drawable.ic_people_outline_24px)), this);
        dialog.show(getActivity().getSupportFragmentManager(), "data_notif");
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
            userDao.save(new User("AMADOU BAKARI"));
        } catch (DaoException e) {
            e.printStackTrace();
        }

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
    public void okButtonAction() {
        activity.finish();
    }

    @Override
    public void noButtonAction() {
        dialog.dismiss();
    }


}
