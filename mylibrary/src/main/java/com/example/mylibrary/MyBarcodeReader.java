package com.example.mylibrary;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.myservice.IMyBarcode;
import com.example.myservice.IMyBarcodeCallback;

/**
 * Created by steve on 2017/12/3.
 */

public class MyBarcodeReader {
    private MyBarcodeReaderCallback mCallback;
    private Context mContext;
    private IMyBarcode mService = null;
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            mService = IMyBarcode.Stub.asInterface(service);
            try {
                mService.init(0, new IMyBarcodeCallback.Stub(){
                    @Override
                    public void onComplete(int result, String message){
                        if (mCallback != null)
                            mCallback.onComplete(result, message);
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        public void onServiceDisconnected(ComponentName className) {
            mService = null;
        }
    };

    public void init(Context context){

        mContext = context;

        Intent intent = new Intent();
        intent.setAction("com.example.myservice");
        intent.setPackage("com.example.myservice");
        context.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

    }


    public void setCallback(MyBarcodeReader.MyBarcodeReaderCallback callback){
        mCallback = callback;
    }

    public void start(){
        if (mService == null)
            return;
        try {
            mService.start();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public interface MyBarcodeReaderCallback {
        void onComplete(int result, String message);
    }


}
