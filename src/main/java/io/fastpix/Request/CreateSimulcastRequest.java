package io.fastpix.Request;

import java.util.Map;

public class CreateSimulcastRequest {
    private SimulcastRequest simulcastRequest;

    public SimulcastRequest getSimulcastRequest() {
        return simulcastRequest;
    }

    public void setSimulcastRequest(SimulcastRequest simulcastRequest) {
        this.simulcastRequest = simulcastRequest;
    }

    public static class SimulcastRequest {
        private String url;

        private String streamKey;

        private Map<String, String> metadata;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getStreamKey() {
            return streamKey;
        }

        public void setStreamKey(String streamKey) {
            this.streamKey = streamKey;
        }

        public Map<String, String> getMetadata() {
            return metadata;
        }

        public void setMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
        }
    }
}