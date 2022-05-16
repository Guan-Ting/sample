package com.java.sample.Cert;
import java.io.File;
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Locale;
import org.apache.commons.codec.*;

public class TestForOCSP {
    public static void main(String[] args) {
        try {
            String path="D:\\jrsys_workspace\\jrsysva2\\jrsysva\\JrsysVA\\test\\test\\cert\\GRCA.cer";
            path = path.replace("\\", "/");
            X509Certificate cert = CertManager.getX509Certificate(new File(path));
            char[] encHex =org.apache.commons.codec.binary.Hex.encodeHex(cert.getIssuerX500Principal().getEncoded());
            String x=new String(encHex).toUpperCase();



            String issuerHex = new String(encodeHex(cert.getIssuerX500Principal().getEncoded()));
            System.out.println("issuerHex:"+issuerHex);
            String sn1=cert.getSerialNumber().toString();
            System.out.println("sn1:"+sn1);



    }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static String encodeHex(byte[] data){
        char[] encHex = org.apache.commons.codec.binary.Hex.encodeHex(data);
        return new String(encHex).toUpperCase();
    }



}
