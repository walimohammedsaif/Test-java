package io.fastpix.Request;

import java.util.List;
import java.util.Map;

public class UploadRequest {
    private String corsOrigin;
    private pushMediaSettings pushMediaSettings;

    public UploadRequest.pushMediaSettings getPushMediaSettings() {
        return pushMediaSettings;
    }

    public void setPushMediaSettings(UploadRequest.pushMediaSettings pushMediaSettings) {
        this.pushMediaSettings = pushMediaSettings;
    }

    public String getCorsOrigin() {
        return corsOrigin;
    }

    public void setCorsOrigin(String corsOrigin) {
        this.corsOrigin = corsOrigin;
    }

    public static class pushMediaSettings{
        private List<Input> inputs;

        private Map<String, String> metadata;

        private Subtitles subtitles;

        private String accessPolicy;

        public List<Input> getInputs() {
            return inputs;
        }

        private String mp4Support;

        private boolean optimizeAudio;

        private String maxResolution;

        public void setInputs(List<Input> inputs) {
            this.inputs = inputs;
        }

        public Map<String, String> getMetadata() {
            return metadata;
        }

        public void setMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
        }

        public Subtitles getSubtitles() {
            return subtitles;
        }

        public void setSubtitles(Subtitles subtitles) {
            this.subtitles = subtitles;
        }

        public String getAccessPolicy() {
            return accessPolicy;
        }

        public void setAccessPolicy(String accessPolicy) {
            this.accessPolicy = accessPolicy;
        }

        public String getMp4Support() {
            return mp4Support;
        }

        public void setMp4Support(String mp4Support) {
            this.mp4Support = mp4Support;
        }

        public boolean isOptimizeAudio() {
            return optimizeAudio;
        }

        public void setOptimizeAudio(boolean optimizeAudio) {
            this.optimizeAudio = optimizeAudio;
        }

        public String getMaxResolution() {
            return maxResolution;
        }

        public void setMaxResolution(String maxResolution) {
            this.maxResolution = maxResolution;
        }
    }

    // Inner classes for Input, Metadata, Subtitles, etc.
    public static class Input {
        private String type;
        private String url;
        private Integer startTime;
        private Integer endTime;
        private String introUrl;
        private String outroUrl;
        private List<String> expungeSegments;
        private CreateMediaRequest.Input.Placement placement;
        private String width;
        private String height;
        private String opacity;
        private String swapTrackUrl;
        private List<CreateMediaRequest.Input.ImposeTrack> imposeTracks;

        // Getters and setters
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Integer getStartTime() {
            return startTime;
        }

        public void setStartTime(Integer startTime) {
            this.startTime = startTime;
        }

        public Integer getEndTime() {
            return endTime;
        }

        public void setEndTime(Integer endTime) {
            this.endTime = endTime;
        }

        public String getIntroUrl() {
            return introUrl;
        }

        public void setIntroUrl(String introUrl) {
            this.introUrl = introUrl;
        }

        public String getOutroUrl() {
            return outroUrl;
        }

        public void setOutroUrl(String outroUrl) {
            this.outroUrl = outroUrl;
        }

        public List<String> getExpungeSegments() {
            return expungeSegments;
        }

        public void setExpungeSegments(List<String> expungeSegments) {
            this.expungeSegments = expungeSegments;
        }

        public CreateMediaRequest.Input.Placement getPlacement() {
            return placement;
        }

        public void setPlacement(CreateMediaRequest.Input.Placement placement) {
            this.placement = placement;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getOpacity() {
            return opacity;
        }

        public void setOpacity(String opacity) {
            this.opacity = opacity;
        }

        public String getSwapTrackUrl() {
            return swapTrackUrl;
        }

        public void setSwapTrackUrl(String swapTrackUrl) {
            this.swapTrackUrl = swapTrackUrl;
        }

        public List<CreateMediaRequest.Input.ImposeTrack> getImposeTracks() {
            return imposeTracks;
        }

        public void setImposeTracks(List<CreateMediaRequest.Input.ImposeTrack> imposeTracks) {
            this.imposeTracks = imposeTracks;
        }

        public static class Placement {
            private String xAlign;
            private String xMargin;
            private String yAlign;
            private String yMargin;

            // Getters and setters
            public String getxAlign() {
                return xAlign;
            }

            public void setxAlign(String xAlign) {
                this.xAlign = xAlign;
            }

            public String getxMargin() {
                return xMargin;
            }

            public void setxMargin(String xMargin) {
                this.xMargin = xMargin;
            }

            public String getyAlign() {
                return yAlign;
            }

            public void setyAlign(String yAlign) {
                this.yAlign = yAlign;
            }

            public String getyMargin() {
                return yMargin;
            }

            public void setyMargin(String yMargin) {
                this.yMargin = yMargin;
            }
        }

        public static class ImposeTrack {
            private String url;
            private Integer startTime;
            private Integer endTime;
            private Integer fadeInLevel;
            private Integer fadeOutLevel;

            // Getters and setters
            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public Integer getStartTime() {
                return startTime;
            }

            public void setStartTime(Integer startTime) {
                this.startTime = startTime;
            }

            public Integer getEndTime() {
                return endTime;
            }

            public void setEndTime(Integer endTime) {
                this.endTime = endTime;
            }

            public Integer getFadeInLevel() {
                return fadeInLevel;
            }

            public void setFadeInLevel(Integer fadeInLevel) {
                this.fadeInLevel = fadeInLevel;
            }

            public Integer getFadeOutLevel() {
                return fadeOutLevel;
            }

            public void setFadeOutLevel(Integer fadeOutLevel) {
                this.fadeOutLevel = fadeOutLevel;
            }
        }
    }


    public static class Subtitles {
        private String name;
        private Map<String, String> metadata;
        private String languageCode;

        // Getters and setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Map<String, String> getMetadata() {
            return metadata;
        }

        public void setMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
        }

        public String getLanguageCode() {
            return languageCode;
        }

        public void setLanguageCode(String languageCode) {
            this.languageCode = languageCode;
        }
    }
}

