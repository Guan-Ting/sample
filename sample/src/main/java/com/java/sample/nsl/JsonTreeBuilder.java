package com.java.sample.nsl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.StreamSupport;

public class JsonTreeBuilder {

    public static void main(String[] args) throws JsonProcessingException {
        String jsonData = "["
                + "{ \"parentId\": \"\", \"parentOId\": \"\", \"id\": \"dd2c2c4b-659f-d7df-cb06-3a106a94e6ad\", \"oId\": \"_Root\", \"displayName\": \"GSS\", \"email\": null, \"managers\": [], \"isEnabled\": true, \"startDate\": \"2024-01-30T11:27:11.2577581Z\", \"endDate\": null, \"children\": [], \"users\": [], \"extraProperties\": {} },"
                + "{ \"parentId\": \"4bf44920-a368-fc9d-05da-3a107b576cce\", \"parentOId\": \"NANSHANROOT\", \"id\": \"0a951172-2bc4-6dd0-c3a1-3a107b579eca\", \"oId\": \"164VN00001\", \"displayName\": \"越南辦事處\", \"email\": null, \"managers\": [], \"isEnabled\": true, \"startDate\": \"2022-02-02T00:00:00Z\", \"endDate\": null, \"children\": [], \"users\": [], \"extraProperties\": {} },"
                + "{ \"parentId\": \"4bf44920-a368-fc9d-05da-3a107b576cce\", \"parentOId\": \"NANSHANROOT\", \"id\": \"0b5c822d-09b5-801f-b06f-3a107b579297\", \"oId\": \"164PJ00000\", \"displayName\": \"專案企劃部\", \"email\": null, \"managers\": [], \"isEnabled\": true, \"startDate\": \"2022-02-02T00:00:00Z\", \"endDate\": null, \"children\": [], \"users\": [], \"extraProperties\": {} },"
                + "{ \"parentId\": \"0b5c822d-09b5-801f-b06f-3a107b579297\", \"parentOId\": \"164PJ00000\", \"id\": \"eeb0c224-25f9-467f-9964-3a107b5792fb\", \"oId\": \"164PJ00004\", \"displayName\": \"綜合企劃二處\", \"email\": null, \"managers\": [], \"isEnabled\": true, \"startDate\": \"2022-02-02T00:00:00Z\", \"endDate\": null, \"children\": [], \"users\": [], \"extraProperties\": {} },"
                + "{ \"parentId\": \"0b5c822d-09b5-801f-b06f-3a107b579297\", \"parentOId\": \"164PJ00000\", \"id\": \"3b56072b-2f68-acb5-8d68-3a107b57934d\", \"oId\": \"164PJ00005\", \"displayName\": \"整合企劃處\", \"email\": null, \"managers\": [], \"isEnabled\": true, \"startDate\": \"2022-02-02T00:00:00Z\", \"endDate\": null, \"children\": [], \"users\": [], \"extraProperties\": {} },"
                + "{ \"parentId\": \"0b5c822d-09b5-801f-b06f-3a107b579297\", \"parentOId\": \"164PJ00000\", \"id\": \"2f8bdbc7-68d4-8a21-f601-3a107b5792c9\", \"oId\": \"164PJ00002\", \"displayName\": \"專題企劃處\", \"email\": null, \"managers\": [], \"isEnabled\": true, \"startDate\": \"2022-02-02T00:00:00Z\", \"endDate\": null, \"children\": [], \"users\": [], \"extraProperties\": {} },"
                + "{ \"parentId\": \"0b5c822d-09b5-801f-b06f-3a107b579297\", \"parentOId\": \"164PJ00000\", \"id\": \"7dc327ad-c4e5-28eb-3940-3a107b5792b2\", \"oId\": \"164PJ00001\", \"displayName\": \"經營企劃處\", \"email\": null, \"managers\": [], \"isEnabled\": true, \"startDate\": \"2022-02-02T00:00:00Z\", \"endDate\": null, \"children\": [], \"users\": [], \"extraProperties\": {} },"
                + "{ \"parentId\": \"4bf44920-a368-fc9d-05da-3a107b576cce\", \"parentOId\": \"NANSHANROOT\", \"id\": \"4d3ba865-f260-f229-1750-3a107b576d1c\", \"oId\": \"164AG00000\", \"displayName\": \"業務通路\", \"email\": null, \"managers\": [], \"isEnabled\": true, \"startDate\": \"2022-02-02T00:00:00Z\", \"endDate\": null, \"children\": [], \"users\": [], \"extraProperties\": {} },"
                + "{ \"parentId\": \"4d3ba865-f260-f229-1750-3a107b576d1c\", \"parentOId\": \"164AG00000\", \"id\": \"df085f48-5bb7-712b-6419-3a107b577230\", \"oId\": \"164AO00000\", \"displayName\": \"優質增員暨培訓發展部\", \"email\": null, \"managers\": [], \"isEnabled\": true, \"startDate\": \"2022-02-02T00:00:00Z\", \"endDate\": null, \"children\": [], \"users\": [], \"extraProperties\": {} },"
                + "{ \"parentId\": \"4d3ba865-f260-f229-1750-3a107b576d1c\", \"parentOId\": \"164AG00000\", \"id\": \"dd8dd14a-7a8c-1ace-d3e0-3a107b579459\", \"oId\": \"164PP00000\", \"displayName\": \"高資暨企業行銷部\", \"email\": null, \"managers\": [], \"isEnabled\": true, \"startDate\": \"2022-02-02T00:00:00Z\", \"endDate\": null, \"children\": [], \"users\": [], \"extraProperties\": {} }"
                + "]";

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode originalArray = objectMapper.readTree(jsonData);

        Map<String, ObjectNode> nodes = new ConcurrentHashMap<>();
        StreamSupport.stream(originalArray.spliterator(), true).forEach(node -> {
            String oId = node.get("oId").asText();
            ObjectNode currentNode = nodes.computeIfAbsent(oId, k -> objectMapper.createObjectNode());
            currentNode.put("parentOId", node.get("parentOId").asText());
            currentNode.put("oId", oId);
            currentNode.put("displayName", node.get("displayName").asText());
            currentNode.putArray("children");
        });

        StreamSupport.stream(originalArray.spliterator(), true).forEach(node -> {
            String parentOId = node.get("parentOId").asText();
            String oId = node.get("oId").asText();
            if (!parentOId.equals("NANSHANROOT") && !parentOId.isEmpty()) {
                ObjectNode parentNode = nodes.get(parentOId);
                ObjectNode currentNode = nodes.get(oId);
                parentNode.withArray("children").add(currentNode);
            }
        });

        // 根節點
        ObjectNode root = objectMapper.createObjectNode();
        nodes.values().stream()
                .filter(node -> node.get("parentOId").asText().equals("NANSHANROOT") || node.get("parentOId").asText().isEmpty())
                .forEach(node -> root.withArray("children").add(node));

        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(root));
    }
}
