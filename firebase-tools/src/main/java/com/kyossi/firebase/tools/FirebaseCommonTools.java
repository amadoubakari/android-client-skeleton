/*
 * Copyright (C) 2020
 * Kyossi alights reserved.
 *
 *
 *
 *
 *
 *
 *
 */
package com.kyossi.firebase.tools;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.listener.StateUpdatedListener;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.review.testing.FakeReviewManager;
import com.google.android.play.core.tasks.Task;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 */
public class FirebaseCommonTools implements Serializable {
    /**
     * @param activity
     */
    public static void rateApplication(Activity activity) {
        ReviewManager manager = ReviewManagerFactory.create(activity);
        Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // We can get the ReviewInfo object
                ReviewInfo reviewInfo = task.getResult();
                Task<Void> flow = manager.launchReviewFlow(activity, reviewInfo);
                flow.addOnCompleteListener(task1 -> {
                    // The flow has finished. The API does not indicate whether the user
                    // reviewed or not, or even whether the review dialog was shown. Thus, no
                    // matter the result, we continue our app flow.
                    if (task1.isSuccessful()) {

                    }
                });
            } else {
                // There was some problem, continue regardless of the result.
            }
        });
    }

    /**
     * Review app
     *
     * @param activity
     * @param fakeMODE true if launch in fake mode the review app
     */
    public static void rateApplication(Activity activity, boolean fakeMODE) {
        if (fakeMODE) {
            FakeReviewManager manager = new FakeReviewManager(activity);
            Task<ReviewInfo> request = manager.requestReviewFlow();
            request.addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // We can get the ReviewInfo object
                    ReviewInfo reviewInfo = task.getResult();
                    Task<Void> flow = manager.launchReviewFlow(activity, reviewInfo);
                    flow.addOnCompleteListener(task1 -> {
                        // The flow has finished. The API does not indicate whether the user
                        // reviewed or not, or even whether the review dialog was shown. Thus, no
                        // matter the result, we continue our app flow.
                        if (task1.isSuccessful()) {

                        }
                    });
                } else {
                    // There was some problem, continue regardless of the result.
                }
            });
        } else {
            ReviewManager manager = ReviewManagerFactory.create(activity);
            Task<ReviewInfo> request = manager.requestReviewFlow();
            request.addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // We can get the ReviewInfo object
                    ReviewInfo reviewInfo = task.getResult();
                    Task<Void> flow = manager.launchReviewFlow(activity, reviewInfo);
                    flow.addOnCompleteListener(task1 -> {
                        // The flow has finished. The API does not indicate whether the user
                        // reviewed or not, or even whether the review dialog was shown. Thus, no
                        // matter the result, we continue our app flow.
                        if (task1.isSuccessful()) {

                        }
                    });
                } else {
                    // There was some problem, continue regardless of the result.
                }
            });
        }
    }

    /**
     * check updates from play store.
     *
     * @param activity             the context
     * @param updateProcess        actions when updating
     * @param stateUpdatedListener the class implement StateUpdatedListener
     */
    public static void checkUpdatesAvailable(Activity activity,AppUpdateManager appUpdateManager,
                                             UpdateProcess updateProcess,
                                             StateUpdatedListener<InstallState> stateUpdatedListener) {
        // Create a listener to track request state updates.
        InstallStateUpdatedListener listener = state -> {
            // (Optional) Provide a download progress bar.
            if (state.installStatus() == InstallStatus.DOWNLOADING) {
                long bytesDownloaded = state.bytesDownloaded();
                long totalBytesToDownload = state.totalBytesToDownload();
                // Implement progress bar.
                //launch the waiting loader
                updateProcess.beginUpdateProcess();
            }
            if (state.installStatus() == InstallStatus.DOWNLOADED) {
                //cancel loading waiting
                updateProcess.endUpdateProcess();
                // When status updates are no longer needed, unregister the listener.
                appUpdateManager.unregisterListener(stateUpdatedListener::onStateUpdate);
            }
        };

        // Returns an intent object that you use to check for an update.
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
        // Checks that the platform will allow the specified type of update.
        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE) {
                // Request the update.
                try {
                    // Before starting an update, register a listener for updates.
                    appUpdateManager.registerListener(listener);
                    //Start download updates
                    appUpdateManager.startUpdateFlowForResult(
                            // Pass the intent that is returned by 'getAppUpdateInfo()'.
                            appUpdateInfo,
                            // Or 'AppUpdateType.FLEXIBLE' for flexible updates.
                            AppUpdateType.FLEXIBLE,
                            // The current activity making the update request.
                            activity,
                            // Include a request code to later monitor this update request.
                            FirebaseConstants.PLAY_STORE_UPDATE_REQUEST_CODE);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }
            } else {
                Log.d(activity.getClass().getSimpleName(), "No Update available");
            }
        });
    }

    public static void popupSnackBarForCompleteUpdate(Activity activity, AppUpdateManager appUpdateManager, View ancestorView, FirebaseAction firebaseAction, int primaryColor) {
        Snackbar snackbar =
                Snackbar.make(
                        ancestorView,
                        activity.getString(R.string.main_activity_completed_download),
                        Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(activity.getString(R.string.main_activity_download_completed_restart), view -> {
            appUpdateManager.completeUpdate();
        });
        firebaseAction.call();
        snackbar.setAnchorView(ancestorView);
        snackbar.setActionTextColor(primaryColor);
        snackbar.show();
    }

    /**
     * @param firebaseAction
     */
    public static void checkForUpdates(FirebaseAction firebaseAction) {
        Observable<Boolean> observable = Observable.create(subscriber -> {
            subscriber.onNext(Boolean.TRUE);
            subscriber.onCompleted();
        });
        observable
                .delay(10, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> {
                    firebaseAction.call();
                });
    }

    /**
     *
     */
    public static void checkIfUpdatesDownloaded(AppUpdateManager appUpdateManager,FirebaseAction firebaseAction) {
        appUpdateManager
                .getAppUpdateInfo()
                .addOnSuccessListener(appUpdateInfo -> {
                    // If the update is downloaded but not installed,
                    // notify the user to complete the update.
                    if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                        firebaseAction.call();
                    }
                });
    }

}
