package com.example.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service {
    IMyBarcodeCallback barcodeCallback;
    public MyService() {
    }

    private final IMyBarcode.Stub remoteBinder = new IMyBarcode.Stub(){
        @Override
        public void init(int anInt, IMyBarcodeCallback callback) throws RemoteException {
            barcodeCallback = callback;
        }

        public void start()  throws RemoteException {
            if (barcodeCallback != null)
                barcodeCallback.onComplete(0, "");
        }
    };
    @Override
    public IBinder onBind(Intent intent) {
        return remoteBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
