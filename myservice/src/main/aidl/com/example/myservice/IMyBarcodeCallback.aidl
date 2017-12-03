// IMyBarcodeCallback.aidl
package com.example.myservice;

// Declare any non-default types here with import statements

interface IMyBarcodeCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onComplete(int result, String message);
}
