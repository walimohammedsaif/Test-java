package io.fastpix.Request;

import java.util.Map;

public class UpdateMediaRequest {
    private Map<String, String> metadata;

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
}
