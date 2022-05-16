package com.java.sample.Cert;

import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

public class CertificateFactoryFactory {
    private static CertificateFactoryFactory instance;
    private CertificateFactoryFactory(){}
    public static CertificateFactoryFactory getInstance(){
        if(instance==null){
            instance = new CertificateFactoryFactory();
        }
        return instance;
    }

    public CertificateFactory getCertificateFactory() throws CertificateException {
        /*
        try {
            return CertificateFactory.getInstance("X509", "JSM");
        } catch (NoSuchProviderException e) {
            return CertificateFactory.getInstance("X509");
        }
        */
        return CertificateFactory.getInstance("X509");
    }

    public CertificateFactory getCertificateFactory(String type) throws CertificateException{
        return CertificateFactory.getInstance(type);
    }

    public CertificateFactory getCertificateFactory(String type, String provider) throws CertificateException, NoSuchProviderException {
        return CertificateFactory.getInstance(type, provider);
    }

    public CertificateFactory getCertificateFactory(String type, Provider provider) throws CertificateException, NoSuchProviderException{
        return CertificateFactory.getInstance(type, provider);
    }


}
