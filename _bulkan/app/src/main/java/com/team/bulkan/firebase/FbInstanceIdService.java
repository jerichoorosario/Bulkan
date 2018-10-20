package com.team.bulkan.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.team.bulkan.AppController;
import com.team.bulkan.helpers.PrefManager;
import com.team.bulkan.network.Consts;

public class FbInstanceIdService extends FirebaseInstanceIdService {
    private static final String TAG = "FbInstanceIdService";
    private PrefManager prefManager;

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        sendRegistrationToServer(refreshedToken, "");
    }

    private void sendRegistrationToServer(final String token, final String deviceChannel) {
        prefManager = PrefManager.getInstance(this);
        boolean isTracked = prefManager.getBoolean(PrefManager.IS_TRACKED);
        final String deviceToken = FirebaseInstanceId.getInstance().getToken();

        if (!isTracked && (!deviceToken.isEmpty())) {
            AppController.getFBDatabaseReference().child("devices").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //System.out.println("PUSH DEVICE TOKEN: " + token);
                    AppController.getFBDatabaseReference().child("tbl_devices/" + token + "/platform").setValue(Consts.PLATFORM);
                    prefManager.save(PrefManager.IS_TRACKED, true);
                    prefManager.save(PrefManager.FB_DEVICE_TOKEN, deviceToken);
                    FirebaseMessaging.getInstance().subscribeToTopic("bulkan-android-develop");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }
}
