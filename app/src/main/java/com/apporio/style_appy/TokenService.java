package com.apporio.style_appy;

import android.preference.PreferenceManager;
import android.util.Log;

import com.apporio.style_appy.Parsing.parsingforNotification;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by apporioinfolabs on 16-09-2016.
 */
public class TokenService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e("notification", refreshedToken);
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {
        parsingforNotification.parsingfornoti(TokenService.this,refreshedToken);
    }


}
