package com.sslpinning;

import java.util.*;
import com.facebook.react.modules.network.OkHttpClientFactory;
import com.facebook.react.modules.network.OkHttpClientProvider;

import android.util.Log;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import android.util.Log;
import com.facebook.react.modules.network.NetworkingModule;

public class CustomSSLPinningClientBuilder implements NetworkingModule.CustomClientBuilder {
    
    public void apply(OkHttpClient.Builder builder){
        if(SSLPinnerFactory.hasCertificates()){
            builder = builder.certificatePinner(SSLPinnerFactory.createCertificatePinner());
        }
    }
}
