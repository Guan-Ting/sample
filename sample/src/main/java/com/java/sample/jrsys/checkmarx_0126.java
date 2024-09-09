package com.java.sample.jrsys;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * ClassName: checkmarx
 * Package: com.java.sample.jrsys
 * Description:
 * 對應0126 第一次異動 戶役政需求
 *
 * @Author: Howard
 * @Create: 2024/1/18
 */
public class checkmarx_0126 {
    public static void main(String[] args) {
        String basePath = "D:\\工作用\\台北通後台集合\\弱掃源碼整理\\trunk\\";

        String[] pathsToDelete = {
                "lib",
                "web\\mgt\\resource",
                "web\\mgt\\WEB-INF\\web.xml",
                "src\\main\\resources",
                "web\\us\\WEB-INF\\sun-jaxws.xml",
                "web\\us\\WEB-INF\\web.xml",
                "web\\us\\META-INF\\context.xml",
                "web\\us\\demo.jsp",
                "web\\us",
                "web\\empdb\\META-INF\\MANIFEST.MF",
                "web\\empdb\\WEB-INF\\web.xml",
                "web\\empdb\\WEB-INF\\sun-jaxws.xml",
                "web\\empdb",
                "web\\mgt\\flow",
                "src\\main\\java\\empdb",
                "src\\main\\java\\mgt\\com\\jrsys\\tpcd\\mgt\\encryption",
                "src\\main\\java\\srv",
                "src\\main\\java\\mgt\\com\\jrsys\\tpcd\\mgt\\flow",
                "src\\main\\java\\mgt\\com\\jrsys\\tpcd\\mgt\\util",
                "src\\main\\java\\mgt\\com\\jrsys\\tpcd\\mgt\\control\\SSOAction.java",
//                "src\\main\\java\\household\\com\\jrsys\\tpcd\\srv\\household\\bso",
//                "src\\main\\java\\household\\com\\jrsys\\tpcd\\srv\\household\\cache",
//                "src\\main\\java\\mgt\\com\\jrsys\\tpcd\\mgt\\aspectj",
//                "src\\main\\java\\mgt\\com\\jrsys\\tpcd\\mgt\\exception",
//                "src\\main\\java\\mgt\\com\\jrsys\\tpcd\\mgt\\filter",
//                "src\\main\\java\\mgt\\com\\jrsys\\tpcd\\mgt\\flow",
//                "src\\main\\java\\mgt\\com\\jrsys\\tpcd\\mgt\\healthcheck",
//                "src\\main\\java\\mgt\\com\\jrsys\\tpcd\\mgt\\interceptor",
//                "src\\main\\java\\mgt\\com\\jrsys\\tpcd\\mgt\\listener",
//                "src\\main\\java\\mgt\\com\\jrsys\\tpcd\\mgt\\okhttps3",
//                "src\\main\\java\\mgt\\com\\jrsys\\tpcd\\mgt\\servlet",
//                "src\\main\\java\\mgt\\com\\jrsys\\tpcd\\mgt\\tpeAccount",
//                "src\\main\\java\\mgt\\com\\jrsys\\tpcd\\mgt\\trans",
//                "src\\main\\java\\mgt\\com\\jrsys\\tpcd\\mgt\\trigger",
//                "src\\main\\java\\mgt\\com\\jrsys\\tpcd\\mgt\\wsclient",
//                "src\\main\\java\\mgt\\com\\jrsys\\tpcd\\mgt\\AliasToBeanNestedResultTransformer.java",
//                "src\\main\\java\\mgt\\com\\jrsys\\tpcd\\mgt\\Email.java",
//                "src\\main\\java\\mgt\\com\\jrsys\\tpcd\\mgt\\Pagination.java",
//                "src\\main\\java\\mgt\\com\\jrsys\\tpcd\\mgt\\PlatformPasswordEncoder.java",
//                "src\\main\\java\\mgt\\com\\jrsys\\tpcd\\mgt\\ProjectInfo.java",
//                "src\\main\\java\\mgt\\com\\jrsys\\tpcd\\mgt\\SqlServerDialectWithNvarchar.java",
//                "src\\main\\java\\mgt\\com\\jrsys\\tpcd\\mgt\\SSOApServer.java",
//                "src\\main\\java\\mgt\\com\\jrsys\\tpcd\\mgt\\Status.java"
        };

        for (String path : pathsToDelete) {
            String fullPath = basePath + path;
            deletePath(fullPath);
        }
    }

    private static void deletePath(String fullPath) {
        Path path = Paths.get(fullPath);
        try {
            if (Files.exists(path)) {
                if (Files.isDirectory(path)) {
                    Files.walk(path)
                            .sorted((a, b) -> b.compareTo(a))
                            .forEach(p -> {
                                try {
                                    Files.delete(p);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                } else {
                    Files.delete(path);
                }
                System.out.println("刪除成功: " + fullPath);
            } else {
                System.out.println("查無此檔案/路徑: " + fullPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
