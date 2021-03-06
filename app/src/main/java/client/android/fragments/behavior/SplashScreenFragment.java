package client.android.fragments.behavior;

import android.content.Intent;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;

import client.android.R;
import client.android.architecture.core.AbstractFragment;
import client.android.architecture.core.ISession;
import client.android.architecture.custom.CoreState;

@EFragment(R.layout.fragment_splash_screen)
@OptionsMenu(R.menu.menu_vide)
public class SplashScreenFragment extends AbstractFragment {
    @Override
    public CoreState saveFragment() {
        return new CoreState();
    }

    @Override
    protected int getNumView() {
        return 0;
    }

    @Override
    protected void initFragment(CoreState previousState) {
        ((AppCompatActivity) mainActivity).getSupportActionBar().hide();
    }

    @Override
    protected void initView(CoreState previousState) {
        new Handler().postDelayed(() -> {
            mainActivity.navigateToView(2, ISession.Action.SUBMIT);
        }, 3000);
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
}
