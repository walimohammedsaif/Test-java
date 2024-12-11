package io.fastpix.Request;

import java.util.Map;

public class CreateLivestreamRequest {
    private InputMediaSettings inputMediaSettings;

    private PlaybackSettings playbackSettings;

    public InputMediaSettings getInputMediaSettings() {
        return inputMediaSettings;
    }

    public void setInputMediaSettings(InputMediaSettings inputMediaSettings) {
        this.inputMediaSettings = inputMediaSettings;
    }

    public PlaybackSettings getPlaybackSettings() {
        return playbackSettings;
    }

    public void setPlaybackSettings(PlaybackSettings playbackSettings) {
        this.playbackSettings = playbackSettings;
    }

    public static class InputMediaSettings {
        private String reconnectWindow;

        private String maxResolution;

        private Map<String, String> metadata;

        private String mediaPolicy;

        private Boolean enableDvrMode;

        private Boolean enableRecording;

        public String getReconnectWindow() {
            return reconnectWindow;
        }

        public void setReconnectWindow(String reconnectWindow) {
            this.reconnectWindow = reconnectWindow;
        }

        public String getMaxResolution() {
            return maxResolution;
        }

        public void setMaxResolution(String maxResolution) {
            this.maxResolution = maxResolution;
        }

        public Map<String, String> getMetadata() {
            return metadata;
        }

        public void setMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
        }

        public String getMediaPolicy() {
            return mediaPolicy;
        }

        public void setMediaPolicy(String mediaPolicy) {
            this.mediaPolicy = mediaPolicy;
        }

        public Boolean getEnableDvrMode() {
            return enableDvrMode;
        }

        public void setEnableDvrMode(Boolean enableDvrMode) {
            this.enableDvrMode = enableDvrMode;
        }

        public Boolean getEnableRecording() {
            return enableRecording;
        }

        public void setEnableRecording(Boolean enableRecording) {
            this.enableRecording = enableRecording;
        }
    }

    public static class PlaybackSettings {
        private String accessPolicy;

        public String getAccessPolicy() {
            return accessPolicy;
        }

        public void setAccessPolicy(String accessPolicy) {
            this.accessPolicy = accessPolicy;
        }
    }
}
