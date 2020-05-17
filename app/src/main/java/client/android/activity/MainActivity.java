package client.android.activity;

import android.util.Log;

import com.flys.common_tools.dialog.MaterialNotificationDialog;
import com.flys.common_tools.domain.NotificationData;

import client.android.R;
import client.android.architecture.core.AbstractActivity;
import client.android.architecture.core.AbstractFragment;
import client.android.architecture.custom.Session;
import client.android.dao.service.Dao;
import client.android.dao.service.IDao;
import client.android.fragments.behavior.SplashScreenFragment_;
import client.android.fragments.behavior.TestFragment_;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;

@EActivity
@OptionsMenu(R.menu.menu_main)
public class MainActivity extends AbstractActivity implements MaterialNotificationDialog.NotificationButtonOnclickListeneer {

    // couche [DAO]
    @Bean(Dao.class)
    protected IDao dao;
    // session
    private Session session;
    //Notification
    private MaterialNotificationDialog dialog;

    // méthodes classe parent -----------------------
    @Override
    protected void onCreateActivity() {
        // log
        if (IS_DEBUG_ENABLED) {
            Log.d(className, "onCreateActivity");
        }
        // session
        this.session = (Session) super.session;
        // todo : on continue les initialisations commencées par la classe parent
    }

    @Override
    protected IDao getDao() {
        return dao;
    }

    @Override
    protected AbstractFragment[] getFragments() {
        // todo : définir les fragments ici
        return new AbstractFragment[]{new SplashScreenFragment_(), new TestFragment_()};
    }


    @Override
    protected CharSequence getFragmentTitle(int position) {
        // todo : définir les titres des fragments ici
        return null;
    }

    @Override
    protected void navigateOnTabSelected(int position) {
        // todo : navigation par onglets - définir la vue à afficher lorsque l'onglet n° [position] est sélectionné
    }

    @Override
    protected int getFirstView() {
        // todo : définir le n° de la première vue (fragment) à afficher
        return 0;
    }

    @Override
    public void onBackPressed() {
        if (mViewPager.getCurrentItem() == 0 || mViewPager.getCurrentItem() == 1) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            this.dialog = new MaterialNotificationDialog(this, new NotificationData("Title","Voudriez-vous quitter l'application?","OUI","NON",getDrawable(R.drawable.ic_people_outline_24px),R.style.Theme_MaterialComponents_DayNight_Dialog_Alert), this);
            this.dialog.show(getSupportFragmentManager(), "material_notification_alert_dialog");
        } else {
            // Otherwise, select the previous step.
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
        }
    }

    @Override
    public void okButtonAction() {
        super.onBackPressed();
    }

    @Override
    public void noButtonAction() {
        this.dialog.dismiss();
    }
}
