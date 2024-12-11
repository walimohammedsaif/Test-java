package io.fastpix.Request;

import java.util.Map;

public class UpdateSimulcast {
    private UpdateSimulcastRequest simulcastRequest;

    public UpdateSimulcastRequest getSimulcastRequest() {
        return simulcastRequest;
    }

    public void setSimulcastRequest(UpdateSimulcastRequest simulcastRequest) {
        this.simulcastRequest = simulcastRequest;
    }

    public static class UpdateSimulcastRequest {
        private Boolean isEnabled;
        private Map<String, String> metadata;

        public Boolean getEnabled() {
            return isEnabled;
        }

        public void setEnabled(Boolean enabled) {
            isEnabled = enabled;
        }

        public Map<String, String> getMetadata() {
            return metadata;
        }

        public void setMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
        }
    }
}
