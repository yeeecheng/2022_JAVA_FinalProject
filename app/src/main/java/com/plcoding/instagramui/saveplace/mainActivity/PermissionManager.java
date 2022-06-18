package com.plcoding.instagramui.saveplace.mainActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.plcoding.instagramui.saveplace.R;
import java.util.ArrayList;
import java.util.List;

public class PermissionManager {


    private  Boolean phoneCallPermissionGranted ;
    private Boolean sendMessagePermissionGrated ;
    private Boolean locationPermissionGrated;
    private Boolean readPhoneNumberPermissionGrated;

    private ArrayList<String>  permissionRequestList = new ArrayList();
    private int REQUEST_MUTILPLE_CODE;

    private List<String> permissionList = new ArrayList<String>(){
        {
            add(Manifest.permission.CALL_PHONE);
            add(Manifest.permission.SEND_SMS);
            add(Manifest.permission.ACCESS_FINE_LOCATION);
            add(Manifest.permission.READ_PHONE_NUMBERS);
        }
    };






    public PermissionManager(){
        phoneCallPermissionGranted =false;
        sendMessagePermissionGrated = false;
        locationPermissionGrated = false;
        readPhoneNumberPermissionGrated = false;
        REQUEST_MUTILPLE_CODE =1;
    }



    public void setPhoneCallPermissionGranted(Boolean state){
        phoneCallPermissionGranted=state;
    }
    public void setSendMessagePermissionGrated(Boolean state){
        sendMessagePermissionGrated=state;
    }

    public void  setLocationPermissionGrated(Boolean state){
        locationPermissionGrated=state;
    }

    public void setReadPhoneNumberPermissionGrated(Boolean state){
        readPhoneNumberPermissionGrated=state;
    }

    //get permission granted
    public Boolean getPhoneCallPermissionGranted(){
        return phoneCallPermissionGranted;
    }
    public Boolean getSendMessagePermissionGrated(){
        return sendMessagePermissionGrated;
    }

    public Boolean getLocationPermissionGrated(){
        return locationPermissionGrated;
    }

    public Boolean getReadPhoneNumberPermissionGrated(){
        return readPhoneNumberPermissionGrated;
    }

    public int getREQUEST_MUTILPLE_CODE(){
        return REQUEST_MUTILPLE_CODE;
    }

    private Boolean checkAllGrated(){



        if(phoneCallPermissionGranted&&sendMessagePermissionGrated&&readPhoneNumberPermissionGrated&&locationPermissionGrated){

            return true;
        }

        return false;
    }

    //check permission after click Button
    public Boolean checkPermissionAfterClickPhoneButton(Activity act,Context ctx){
        Log.d("phone","all grated " +checkAllGrated().toString());
        if(!checkAllGrated()){

            if(!phoneCallPermissionGranted){
                requestPhoneCallPermission(act,ctx);
            }

            if(!sendMessagePermissionGrated){
                requestSendMessagePermission(act,ctx);
            }

            if(!locationPermissionGrated){
                requestLocationPermission(act,ctx);
            }



//            if(!readPhoneNumberPermissionGrated){
//                requestReadPhoneNumberPermission(act,ctx);
//            }
            return false;
        }
        return true;
    }

    //set state of all permission  when APP start
    public void  checkAllPermission(Context ctx){


        phoneCallPermissionGranted = ContextCompat.checkSelfPermission(ctx, Manifest.permission.CALL_PHONE)==0;
        sendMessagePermissionGrated = ContextCompat.checkSelfPermission(ctx, Manifest.permission.SEND_SMS)==0;
        locationPermissionGrated = ContextCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION)==0;
        readPhoneNumberPermissionGrated = ContextCompat.checkSelfPermission(ctx, Manifest.permission.READ_PHONE_NUMBERS)==0;
        Log.d("per",phoneCallPermissionGranted.toString());
        Log.d("per",sendMessagePermissionGrated.toString());
        Log.d("per",locationPermissionGrated.toString());
        Log.d("per",readPhoneNumberPermissionGrated.toString());


    }


    //get permission
    public void  getPermission(Activity act){


        int count=0;

        if(phoneCallPermissionGranted){

            permissionRequestList.add("");

        }
        else {

            permissionRequestList.add(Manifest.permission.CALL_PHONE);
            count++;

        }

        if(sendMessagePermissionGrated){

            permissionRequestList.add("");

        }
        else {

            permissionRequestList.add(Manifest.permission.SEND_SMS);
            count++;

        }

        if(locationPermissionGrated){

            permissionRequestList.add("");

        }
        else {

            permissionRequestList.add(Manifest.permission.ACCESS_FINE_LOCATION);
            count++;

        }




        if(readPhoneNumberPermissionGrated){

            permissionRequestList.add("");

        }
        else {

            permissionRequestList.add(Manifest.permission.READ_PHONE_NUMBERS);
            count++;

        }


        if(!permissionRequestList.isEmpty()&&count!=0){
            Log.d("per", String.valueOf(count));
            ActivityCompat.requestPermissions(act,permissionList.toArray(new String[permissionList.size()]) ,REQUEST_MUTILPLE_CODE);

        }

    }

    //phone call permission request
    public void  requestPhoneCallPermission(Activity act,Context ctx){
        if(ActivityCompat.shouldShowRequestPermissionRationale(act,Manifest.permission.CALL_PHONE)){//因為第一次拒絕，所以這次要跑這個，第三
            new AlertDialog.Builder(ctx)
                    .setMessage(R.string.phone_call_permission_title)
                    .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            ActivityCompat.requestPermissions(act, new String[] { Manifest.permission.CALL_PHONE },REQUEST_MUTILPLE_CODE);
                        }
                    }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    requestPhoneCallPermission(act,ctx);
                }
            }).show();
        }
        else {
            ActivityCompat.requestPermissions(act, new String[] {Manifest.permission.CALL_PHONE},REQUEST_MUTILPLE_CODE);
        }
    }

    //send message permission request
    public void requestSendMessagePermission(Activity act,Context ctx) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(act, Manifest.permission.SEND_SMS)) {//因為第一次拒絕，所以這次要跑這個，第三
            new AlertDialog.Builder(ctx)
                    .setMessage(R.string.send_message_permission_title)
                    .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            ActivityCompat.requestPermissions(act, new String[] { Manifest.permission.SEND_SMS },REQUEST_MUTILPLE_CODE);
                        }
                    }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    requestSendMessagePermission(act,ctx);
                }
            }).show();
        }
        else {
            ActivityCompat.requestPermissions(act, new String[] {Manifest.permission.SEND_SMS},REQUEST_MUTILPLE_CODE);
        }
    }

    //location permission request
    public void requestLocationPermission(Activity act,Context ctx){
        if(ActivityCompat.shouldShowRequestPermissionRationale(act,Manifest.permission.ACCESS_FINE_LOCATION)){//因為第一次拒絕，所以這次要跑這個，第三
            new AlertDialog.Builder(ctx)
                    .setMessage(R.string.location_permission_title)
                    .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            ActivityCompat.requestPermissions(act, new String[] { Manifest.permission.ACCESS_FINE_LOCATION },REQUEST_MUTILPLE_CODE);
                        }
                    }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    requestLocationPermission(act,ctx);
                }
            }).show();
        }
        else {
            ActivityCompat.requestPermissions(act, new String[] {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_MUTILPLE_CODE);
        }
    }




    //read phone number permission request
    public void requestReadPhoneNumberPermission(Activity act,Context ctx) {

        if (ActivityCompat.shouldShowRequestPermissionRationale(act, Manifest.permission.READ_PHONE_NUMBERS)) {//因為第一次拒絕，所以這次要跑這個，第三

            new AlertDialog.Builder(ctx)
                    .setMessage(R.string.read_phone_number_permission_title)
                    .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            ActivityCompat.requestPermissions(act, new String[] { Manifest.permission.READ_PHONE_NUMBERS },REQUEST_MUTILPLE_CODE);
                        }
                    }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    requestReadPhoneNumberPermission(act,ctx);
                }
            }).show();
        }
        else {
            ActivityCompat.requestPermissions(act, new String[] {Manifest.permission.READ_PHONE_NUMBERS},REQUEST_MUTILPLE_CODE);
        }
    }
}
