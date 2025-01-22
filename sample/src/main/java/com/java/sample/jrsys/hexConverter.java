package com.java.sample.jrsys;

import org.apache.commons.codec.binary.Hex;

import java.util.Base64;

/**
 * ClassName: hexConverter
 * Package: com.java.sample.jrsys
 * Description:
 *
 * @Author: Howard
 * @Create: 2024/12/25
 */
public class hexConverter {

    public static void main(String[] args) {
        // 示例 Base64 字串
        String base64String = "MIIEtjCCA56gAwIBAgIRALEfot9GVnP6FK5sQwpXoVAwDQYJKoZIhvcNAQELBQAwRzELMAkGA1UEBhMCVFcxEjAQBgNVBAoMCeihjOaUv+mZojEkMCIGA1UECwwb5YWn5pS/6YOo5oaR6K2J566h55CG5Lit5b+DMB4XDTI0MTAxNDA4MzcxMloXDTI5MTAxNDE1NTk1OVowPDELMAkGA1UEBhMCVFcxEjAQBgNVBAMMCeWRguWGoOWruzEZMBcGA1UEBRMQMTAxMDA3MDIyMTExMTMyMzCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBALPzifEEwfbqvWvdeMh+8EpVWxN5Uy1MYC0Udg3Q8lfcI9HjtghMHYG1DEZnrFDaNQ9ybusAURaxITL7F1geiE6j4QeRuZas3ZDTC2BeRo8R8I3LwEkM208bT7uWixoqGBD6VXdG5dgbPfTwKsIUI6Gv7i/+rQ5/my1v5svKA+IfwkxQIHOXJsm4w945GB/txXt10ml2VbssO1NQwwWcivgxZkun7Ek7Eu20KJEt/VNMB8oU4mhwEM9m8bRZlPMy3Amsii6qWcUFDrFp+GjSUdLu0nt8BNdZlqIX6i3ShuLAJQ9SR8NjZ96GehraKU0oyeJDh9H+sGistcFTUOxTObUCAwEAAaOCAaYwggGiMB8GA1UdIwQYMBaAFPqbNGcJCpgi92JIi4ImpkXFwyKkMB0GA1UdDgQWBBQaiU/fk7zR4wKhxRRlvCzamNgTGTAOBgNVHQ8BAf8EBAMCB4AwFAYDVR0gBA0wCzAJBgdghnZlAAMDMDMGA1UdCQQsMCowFQYHYIZ2AWQCATEKBghghnYBZAMBATARBgdghnYBZAIzMQYMBDA0ODUwfAYDVR0fBHUwczA2oDSgMoYwaHR0cDovL29jc3AtbW9pY2EubW9pLmdvdi50dy9jcmwvTU9JQ0EtMTAtMzEuY3JsMDmgN6A1hjNodHRwOi8vb2NzcC1tb2ljYS5tb2kuZ292LnR3L2NybC9NT0lDQS1jb21wbGV0ZS5jcmwwgYYGCCsGAQUFBwEBBHoweDBHBggrBgEFBQcwAoY7aHR0cDovL21vaWNhLm5hdC5nb3YudHcvcmVwb3NpdG9yeS9DZXJ0cy9Jc3N1ZWRUb1RoaXNDQS5wN2IwLQYIKwYBBQUHMAGGIWh0dHA6Ly9vY3NwLW1vaWNhLm1vaS5nb3YudHcvT0NTUDANBgkqhkiG9w0BAQsFAAOCAQEAMUUZVVdGMG3EPqC5G7861UAhVmup362Y77YjHDbjlx+BHSyG34+lCmD9tJTrr4d6re7AoUai9Aii/nEjJGl/LlQklmvBl9pBvZNqEfmOJC4Y/D7DWye8MfCnGVTv/r498LKi8uy6toRMuUqojDljnX5tsVD9mmHa740cQM7cBvuw0gYonoG4R+t5VsoFsbQ0P7YPshn0G6nKM9brDG7ic7nmXLD8B3OvO69N1MO1d/pVVbbnMoy7v1Bd6gv4zFyRkYWJYC+F6OuaERTfeRLSkK9DOKNsUQJD88hTlDvoBY4sDfdbI+8AaU9MzSC5/Z9fp50Yxo6OhntmrWU660F4GA==";

        // 1. 將 Base64 字串解碼為 byte 陣列
        byte[] decodedBytes = Base64.getDecoder().decode(base64String);
        // 2. 將解碼後的 byte 陣列轉換為十六進位字串
        String hexResult = Hex.encodeHexString(decodedBytes);

        // 3. 輸出結果
        System.out.println("Base64 Input: " + base64String);
        System.out.println("Hex Output: " + hexResult);
    }

    /**
     * 將 byte 陣列轉換為十六進位字串 (Hex)
     *
     * @param bytes byte 陣列
     * @return 十六進位格式的字串
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            // 將每個 byte 轉為兩位十六進位格式並追加
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}
