package com.sslpinning;

import java.util.*;
import com.facebook.react.modules.network.OkHttpClientFactory;
import com.facebook.react.modules.network.OkHttpClientProvider;

import android.util.Log;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import android.util.Log;

public class SSLPinnerFactory implements OkHttpClientFactory {

    private static ArrayList<URLCertificate> certificates = new ArrayList<URLCertificate>();

    public OkHttpClient createNewNetworkModuleClient() {
        CertificatePinner.Builder builder = new CertificatePinner.Builder();
        for(int i = 0; i < certificates.size(); i++){
            builder = builder.add(certificates.get(i).url, "sha256/" + certificates.get(i).certificate);
        }
        CertificatePinner certificatePinner = builder.build();
        OkHttpClient.Builder clientBuilder = OkHttpClientProvider.createClientBuilder();
        return clientBuilder
                .certificatePinner(certificatePinner)
                .build();
    }

    public static boolean hasCertificates(){
        return certificates.size() > 0;
    }

    public static CertificatePinner createCertificatePinner(){
        CertificatePinner.Builder builder = new CertificatePinner.Builder();
        for(int i = 0; i < certificates.size(); i++){
            builder = builder.add(certificates.get(i).url, "sha256/" + certificates.get(i).certificate);
        }
        CertificatePinner certificatePinner = builder.build();
        return certificatePinner;
    }

    public static void addPublicKey(String hostname, String key){
        certificates.add(new URLCertificate(hostname,key));
    }

    private static class URLCertificate{
        private String url;
        private String certificate;

        public URLCertificate(String url, String certificate){
            this.url = url;
            this.certificate = certificate;
        }
    }
}