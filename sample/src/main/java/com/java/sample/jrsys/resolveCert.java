package com.java.sample.jrsys;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.x509.Attribute;
import org.bouncycastle.asn1.x509.SubjectDirectoryAttributes;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.x509.SubjectDirectoryAttributes;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * ClassName: resolveCert
 * Package: com.java.sample.jrsys
 * Description:
 *
 * @Author: Howard
 * @Create: 2024/12/24
 */
public class resolveCert {
    public static void main(String[] args) {
        try {
            // 更改路徑替換成您的 .der 檔案路徑
            String certFilePath = "D:\\\\1212.cer";

            // 加載 DER 憑證
            InputStream inputStream = new FileInputStream(certFilePath);
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            X509Certificate certificate = (X509Certificate) certificateFactory.generateCertificate(inputStream);

            // 提取 certType
            String certType = getCertTypeFromSubjectDirectoryAttributes(certificate);
            if (certType != null) {
                System.out.println("CertType:" +certType);
            } else {
                System.out.println("無法從憑證中提取 CertType");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 從 Subject Directory Attributes 中取得 certType
     */
    public static String getCertTypeFromSubjectDirectoryAttributes(X509Certificate cert) {
        String certUid = getUIDFromSubjectDirectoryAttributes(cert);
        System.out.println("certUid:"+certUid);
        if ("2.16.886.1.100.3.1.1".equals(certUid)) {
            return "01";
        } else {
            return null;
        }
    }

    /**
     * 從憑證的 Subject Directory Attributes 提取 UID
     */
    public static String getUIDFromSubjectDirectoryAttributes(X509Certificate cert) {
        Map<String, ASN1Set> subjectDirectoryAttributes = getSubjectDirectoryAttributes(cert);
        if (subjectDirectoryAttributes != null) {
            // OID: 2.16.886.1.100.2.202 表示 UID
            ASN1Set set = subjectDirectoryAttributes.get("2.16.886.1.100.2.1");
            if (set != null && set.size() > 0) {
                return set.getObjectAt(0).toString();
            }
        }
        return null;
    }

    /**
     * 提取 Subject Directory Attributes 擴展 (OID: 2.5.29.9) 的內容
     */
    public static Map<String, ASN1Set> getSubjectDirectoryAttributes(X509Certificate cert) {
        try {
            // 取得憑證中 Extension (OID: 2.5.29.9)
            byte[] extensionBytes = cert.getExtensionValue("2.5.29.9");
            if (extensionBytes == null) {
                return null;
            }

            ASN1OctetString octetString = ASN1OctetString.getInstance(extensionBytes);

            try (ASN1InputStream asn1InputStream = new ASN1InputStream(octetString.getOctets())) {
                ASN1Sequence sequence = (ASN1Sequence) asn1InputStream.readObject();
                SubjectDirectoryAttributes attributes = SubjectDirectoryAttributes.getInstance(sequence);

                Map<String, ASN1Set> attributesMap = new HashMap<>();
                for (Object obj : attributes.getAttributes()) {
                    Attribute attribute = (Attribute) obj;
                    attributesMap.put(attribute.getAttrType().getId(), attribute.getAttrValues());
                    // 輸出屬性資訊 (僅供調試使用)
                    System.out.println(attribute.getAttrType() + ": " + attribute.getAttrValues());
                }
                return attributesMap;
            }
        } catch (Exception e) {
            System.out.println("exception");
        }
        return null;
    }

}
