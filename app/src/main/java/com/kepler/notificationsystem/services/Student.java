package com.kepler.notificationsystem.services;

import android.content.Context;

import com.kepler.notificationsystem.notification.Config;
import com.kepler.notificationsystem.support.GenerateHashKey;
import com.kepler.notificationsystem.support.Params;
import com.loopj.android.http.RequestParams;

/**
 * Created by Amit on 08-04-2017.
 */

public class Student {
    private static final int UPDATE_EMAIL = 0;
    private static final int UPDATE_PASS = 1;
    private static final String UPDATE = "update";
    private static final String SELECT = "select";
    private static final String REGISTER = "register";
    public static final int OFFSET = 30;

    public static void register(Context context, com.kepler.notificationsystem.support.Student student, SimpleNetworkHandler simpleNetworkHandler) {
        RequestParams requestParams = new RequestParams();
        requestParams.add(Params.NAME, student.getName());
        requestParams.add(Params.USERNAME, student.getUsername());
        requestParams.add(Params.RN, student.getRn());
        requestParams.add(Params.CN, student.getCn());
        requestParams.add(Params.BATCH, student.getBatch());
//        requestParams.add(Params.PASSWORD, user.getPassword());
        requestParams.add(Params.DEVICE_ID, GenerateHashKey.getHashedDeivceId(context));
        requestParams.add(Params.ACTION, REGISTER);
        load(null, requestParams, simpleNetworkHandler);
    }

//    public static void serviceRequest(@NonNull Context context, String msg, String value, SimpleNetworkHandler simpleNetworkHandler) {
//        RequestParams requestParams = new RequestParams();
//        requestParams.add(Params.VALUE, value);
//        requestParams.add(Params.MESSAGE, msg);
//        requestParams.add(Params.ACTION, "service_request");
//        requestParams.add(Params.DEVICE_ID, GenerateHashKey.getHashedDeivceId(context));
//        load(null, requestParams, simpleNetworkHandler);
//    }

    public static void updateEmailId(Context context, String new_email, SimpleNetworkHandler simpleNetworkHandler) {
        RequestParams requestParams = new RequestParams();
        requestParams.add(Params.USERNAME, (context.getSharedPreferences(Config.SHARED_PREF_USER, 0)).getString(Params.USER, null));
        requestParams.add(Params.NEW_USERNAME, new_email);
        requestParams.add(Params.ACTION_TYPE, String.valueOf(UPDATE_EMAIL));
        requestParams.add(Params.DEVICE_ID, GenerateHashKey.getHashedDeivceId(context));
        requestParams.add(Params.ACTION, UPDATE);
        load(null, requestParams, simpleNetworkHandler);
    }

    public static void updateContactNumber(Context context, String email, String contact, SimpleNetworkHandler simpleNetworkHandler) {
        RequestParams requestParams = new RequestParams();
        requestParams.add(Params.USERNAME, email);
        requestParams.add(Params.CN, contact);
        requestParams.add(Params.ACTION_TYPE, String.valueOf(UPDATE_PASS));
        requestParams.add(Params.DEVICE_ID, GenerateHashKey.getHashedDeivceId(context));
        requestParams.add(Params.ACTION, UPDATE);
        load(null, requestParams, simpleNetworkHandler);
    }

    public static void select(Context context, com.kepler.notificationsystem.support.Student student, SimpleNetworkHandler simpleNetworkHandler,int page) {
        RequestParams requestParams = new RequestParams();
        if (student.getName() != null) {
            requestParams.add(Params.NAME, student.getName());
        }
        if (student.getBatch() != null)
            requestParams.add(Params.BATCH, student.getBatch());
        requestParams.add(Params.OFFSET, String.valueOf(OFFSET));
        requestParams.add(Params.PAGE,String.valueOf(page));
        requestParams.add(Params.DEVICE_ID, GenerateHashKey.getHashedDeivceId(context));
        requestParams.add(Params.ACTION, SELECT);
        load(null, requestParams, simpleNetworkHandler);
    }

    private static void load(Context context, RequestParams params, SimpleNetworkHandler simpleNetworkHandler) {
        SimpleNetworkHandler.callPostRequest(context, SimpleNetworkHandler.BASE_URL, params, simpleNetworkHandler);
    }
}