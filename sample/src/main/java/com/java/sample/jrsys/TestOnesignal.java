package com.java.sample.jrsys;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * ClassName: TestOnesignal
 * Package: com.java.sample.jrsys
 * Description:
 *
 * @Author: Howard
 * @Create: 2025/3/10
 */
public class TestOnesignal {

    public static void main(String[] args) {
        ObjectMapper objectMapper =new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.putArray("extra_fields").add("external_user_id").add("onesignal_id");
        String requestBody = objectNode.toString();
        System.out.println("requestBody:"+requestBody);

    }
}
