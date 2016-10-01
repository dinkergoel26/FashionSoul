

package com.ibm.mobileappbuilder.storecatalog20150911132549;

import android.app.Application;
import ibmmobileappbuilder.injectors.ApplicationInjector;
import android.app.Activity;
import android.os.Bundle;
import ibmmobileappbuilder.util.SecurePreferences;
import com.ibm.mobileappbuilder.storecatalog20150911132549.ui.LoginActivity;
import ibmmobileappbuilder.util.LoginUtils;


/**
 * You can use this as a global place to keep application-level resources
 * such as singletons, services, etc.
 */
public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks {
    private SecurePreferences mSharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationInjector.setApplicationContext(this);
        mSharedPreferences = new SecurePreferences(this);
        registerActivityLifecycleCallbacks(this);

        mSharedPreferences.edit().putLong(LoginUtils.EXPIRATION_TIME, LoginUtils.SESSION_EXPIRED).apply();
    }

    public SecurePreferences getSecureSharedPreferences() {
        return mSharedPreferences;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        boolean splashShown = false;
        if(!splashShown && !(activity instanceof LoginActivity) ){
            LoginUtils.checkLoggedStatus(mSharedPreferences, LoginActivity.class, activity);
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
        if(!(activity instanceof LoginActivity) ) {
            LoginUtils.storeLastActiveStatus(mSharedPreferences);
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }


}

