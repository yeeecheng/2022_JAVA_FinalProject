package com.plcoding.instagramui.saveplace.mainActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.plcoding.instagramui.saveplace.R;

public class CheckBackgroundSetting {

    private boolean hasShowGpsAlert =false;

    public void checkGPS(Context ctx){
        Boolean state = getGpsStatus(ctx);
        if(hasShowGpsAlert && state){
            hasShowGpsAlert = false;
        }
        else if(!state && !hasShowGpsAlert){
            hasShowGpsAlert= true;
            new AlertDialog.Builder(ctx)
                    .setMessage(R.string.location_not_open)
                    .setPositiveButton(R.string.setting, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            goToOpenGps(ctx);
                        }
                    }).show();
        }
    }

    //confirm Internet
    public void checkInternet(Activity act , Context ctx){

        Boolean state = haveInternet(ctx);
        if(!state){

            new AlertDialog.Builder(ctx)
                    .setMessage("網路沒開啟將無法運行!!!")
                    .setPositiveButton("OK!!", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            act.finish();
                        }
                    }).show();

        }
    }


    private Boolean haveInternet(Context ctx){
        ConnectivityManager connManager = (ConnectivityManager) ctx.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE);

        NetworkInfo info = connManager.getActiveNetworkInfo();


        if(info == null || !info.isConnected()){
            return false;
        }
        else {
            return info.isAvailable();
        }

    }

    private Boolean getGpsStatus(Context ctx) {
        //从系统服务中获取定位管理器
        LocationManager lm = (LocationManager) ctx.getSystemService(AppCompatActivity.LOCATION_SERVICE);
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private  void goToOpenGps(Context ctx) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        ctx.startActivity(intent);

    }

}
