package client.android.fragments.behavior;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flys.notification.adapter.AdsNotificationAdapter;
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
import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.kyossi.firebase.tools.FirebaseCommonTools;

@EFragment(R.layout.fragment_notif_layout)
@OptionsMenu(R.menu.menu_vide)
public class TestFragment extends AbstractFragment implements MaterialNotificationDialog.NotificationButtonOnclickListeneer, NotificationAdapter.NotificationOnclickListener {

    @ViewById(R.id.recyclerview)
    RecyclerView recyclerView;

    @Bean(UserDaoImpl.class)
    protected UserDao userDao;
    //@ViewById(R.id.Splashscreen)
    //protected TextView msg;

    MaterialNotificationDialog dialog;
    List<Notification> notifications;
    private AdsNotificationAdapter notificationAdapter;
    //@ViewById(R.id.my_template)
    //protected TemplateView myTemplateView;

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
        EditDialogFragment dialogActivity = new EditDialogFragment("Recommander", R.mipmap.ic_launcher, 0);
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
       /* try {
            User user = userDao.save(new User("AMADOU BAKARI"));
            msg.setText(userDao.findById(user.getId()).getNom());
        } catch (DaoException e) {
            e.printStackTrace();
        }
       */
        notifications = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            notifications.add(new Notification("Dubun Guiziga", "Animaux en Guiziga", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", null, new Date(), "amadou.jpg", null));
        }
        notificationAdapter = new AdsNotificationAdapter(activity, notifications, new DialogStyle(activity.getColor(R.color.blue_500), activity.getColor(R.color.amber_400), R.font.google_sans),true, "ca-app-pub-3940256099942544/2247696110", new AdsNotificationAdapter.NotificationOnclickListener() {
            @Override
            public void onButtonClickListener(int position) {

            }

            @Override
            public void onMenuClickListener(View v, int position) {

            }
        }
        );


        notificationAdapter.refreshAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(notificationAdapter);

        //initializeAds();
    }

    private void initializeAds2() {
        AdLoader adLoader = new AdLoader.Builder(activity, "ca-app-pub-3940256099942544/2247696110")
                .forNativeAd(nativeAd -> {
                    NativeTemplateStyle styles = new
                            NativeTemplateStyle.Builder()
                            .withMainBackgroundColor(new ColorDrawable(activity.getColor(R.color.gnt_white)))
                            .withPrimaryTextTypeface(ResourcesCompat.getFont(activity, R.font.google_sans))
                            .build();

                    //TemplateView template = findViewById(R.id.my_template);
                    //myTemplateView.setStyles(styles);
                    //myTemplateView.setNativeAd(nativeAd);
                })
                .withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(LoadAdError adError) {
                        // Handle the failure by logging, altering the UI, and so on.
                    }
                })
                .withNativeAdOptions(new NativeAdOptions.Builder()
                        // Methods in the NativeAdOptions.Builder class can be
                        // used here to specify individual options settings.
                        .build())
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());
    }

    @Override
    protected void updateOnSubmit(CoreState previousState) {
        //initializeAds();
    }

    @Override
    protected void updateOnRestore(CoreState previousState) {
        //initializeAds();

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
        NotificationDetailsDialogFragment notificationDetailsDialogFragment = NotificationDetailsDialogFragment.newInstance(activity, notifications.get(position), new DialogStyle(activity.getColor(R.color.blue_300)));
        notificationDetailsDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme);
        notificationDetailsDialogFragment.show(getActivity().getSupportFragmentManager(), "fragment_edit_name");
    }

    @Override
    public void onMenuClickListener(View v, int position) {

    }
}
