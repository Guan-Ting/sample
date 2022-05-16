package com.java.sample.Cert;

import java.io.File;
import java.io.FileInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class CertManager {
    public static X509Certificate getX509Certificate(File certFile) throws Exception {
        try{
            CertificateFactory cf =CertificateFactoryFactory.getInstance().getCertificateFactory();
            X509Certificate cert = (X509Certificate) cf.generateCertificate(new FileInputStream(certFile));
            return cert;
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }

    public static void main(String[] args) {
        String x ="va_pkcs7_attachment_";
         x=x.substring(0,x.length()-1);
        System.out.println("x:"+x);

    }

}
