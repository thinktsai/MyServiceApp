// IMyBarcode.aidl
package com.example.myservice;

import com.example.myservice.IMyBarcodeCallback;

// Declare any non-default types here with import statements

interface IMyBarcode {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void init(int anInt, IMyBarcodeCallback callback);
    void start();
}
