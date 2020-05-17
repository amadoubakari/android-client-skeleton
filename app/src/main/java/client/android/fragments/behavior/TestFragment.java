package client.android.fragments.behavior;

import androidx.appcompat.app.AppCompatActivity;

import com.flys.common_tools.dialog.MaterialNotificationDialog;
import com.flys.common_tools.domain.NotificationData;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;

import client.android.R;
import client.android.architecture.core.AbstractFragment;
import client.android.architecture.custom.CoreState;

@EFragment(R.layout.fragment_dummy_layout)
@OptionsMenu(R.menu.menu_vide)
public class TestFragment extends AbstractFragment implements MaterialNotificationDialog.NotificationButtonOnclickListeneer {
    MaterialNotificationDialog dialog;

    @Click(R.id.Splashscreen)
    void splas() {
        dialog = new MaterialNotificationDialog(activity,
                new NotificationData("Dubun Guiziga", "Voudriez-vous quittez l'application?", "OUI", "NO", activity.getDrawable(R.drawable.ic_people_outline_24px)),this);
        dialog.show(getActivity().getSupportFragmentManager(), "data_notif");
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
