package com.sslpinning;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactContext;

import android.util.Log;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import com.facebook.react.modules.network.OkHttpClientProvider;
import com.facebook.react.modules.network.NetworkingModule;

@ReactModule(name = SslPinningModule.NAME)
public class SslPinningModule extends ReactContextBaseJavaModule {
  public static final String NAME = "SslPinning";

  public SslPinningModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }

  @ReactMethod
    public void addPublicKey(String hostname, String key){
        SSLPinnerFactory.addPublicKey(hostname,key);
    }
    
  @ReactMethod
  public void setup(){}

  @ReactMethod
  public void save(){
      NetworkingModule.setCustomClientBuilder(new CustomSSLPinningClientBuilder());
  }

}