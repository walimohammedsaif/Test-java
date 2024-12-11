package io.fastpix.Service;

import com.google.gson.Gson;
import io.fastpix.AppResponse;
import io.fastpix.FastPixBaseClient;
import io.fastpix.FastPixConfigLoader;
import io.fastpix.Request.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FastPixClient extends FastPixBaseClient {
    private static final Gson gson = new Gson();
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    public FastPixClient() {
        super(
                FastPixConfigLoader.getProperty("fastpix.base-url", "https://v1.fastpix.io"),
                FastPixConfigLoader.getProperty("fastpix.username"),
                FastPixConfigLoader.getProperty("fastpix.password")
        );
    }

    public AppResponse<String> createStream(CreateLivestreamRequest request) throws Exception {
        CreateLivestreamRequest.InputMediaSettings inputMediaSettings = request.getInputMediaSettings();
        if (inputMediaSettings == null) {
            inputMediaSettings = new CreateLivestreamRequest.InputMediaSettings();
        }

        CreateLivestreamRequest.PlaybackSettings playbackSettings = request.getPlaybackSettings();
        if (playbackSettings == null) {
            playbackSettings = new CreateLivestreamRequest.PlaybackSettings();
        }

        request.setInputMediaSettings(inputMediaSettings);
        request.setPlaybackSettings(playbackSettings);

        String jsonRequestBody = gson.toJson(request);

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(buildUrl("/live/streams")))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Basic " + encodeCredentials())
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequestBody))
                .build();

        HttpResponse<String> response = HTTP_CLIENT.send(postRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return AppResponse.success(
                    response.statusCode(),
                    "livestream creation successful",
                    response.body()
            );
        } else {
            return AppResponse.error(
                    response.statusCode(),
                    "Failed to create livestream: " + response.body()
            );
        }
    }

    public AppResponse<String> getAllStreams(int limit, int offset, String orderBy) throws Exception {
        String url = String.format("/live/streams?limit=%d&offset=%d&orderBy=%s", limit, offset, orderBy);

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(buildUrl(url)))
                .header("Accept", "application/json")
                .header("Authorization", "Basic " + encodeCredentials())
                .GET()
                .build();

        HttpResponse<String> response = HTTP_CLIENT.send(getRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return AppResponse.success(
                    response.statusCode(),
                    "Retrieved livestream list successfully",
                    response.body()
            );
        } else {
            return AppResponse.error(
                    response.statusCode(),
                    "Failed to retrieve livestream list: " + response.body()
            );
        }
    }

    public AppResponse<String> getStreamById(String streamId) throws Exception {
        String url = String.format("/live/streams/%s/%s", buildUrl(""), streamId);

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("Authorization", "Basic " + encodeCredentials())
                .GET()
                .build();

        HttpResponse<String> response = HTTP_CLIENT.send(getRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return AppResponse.success(
                    response.statusCode(),
                    "Retrieved livestream successfully",
                    response.body()
            );
        } else {
            return AppResponse.error(
                    response.statusCode(),
                    "Failed to retrieve livestream: " + response.body()
            );
        }
    }

    public AppResponse<String> deleteStream(String streamId) throws Exception {
        String url = String.format("/live/streams/%s/%s", buildUrl(""), streamId);

        HttpRequest deleteRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("Authorization", "Basic " + encodeCredentials())
                .DELETE()
                .build();

        HttpResponse<String> response = HTTP_CLIENT.send(deleteRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return AppResponse.success(
                    response.statusCode(),
                    "livestream deleted successfully",
                    response.body()
            );
        } else {
            return AppResponse.error(
                    response.statusCode(),
                    "Failed to delete livestream: " + response.body()
            );
        }
    }

    public AppResponse<String> createStreamPlaybackId(CreatePlaybackIdRequest request, String streamId) throws Exception {
        String url = String.format("/live/streams/%s/%s/playback-ids", buildUrl(""), streamId);
        request.setAccessPolicy("public");

        String jsonRequestBody = gson.toJson(request);

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Basic " + encodeCredentials())
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequestBody))
                .build();

        HttpResponse<String> response = HTTP_CLIENT.send(postRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return AppResponse.success(
                    response.statusCode(),
                    "Playback ID created successfully",
                    response.body()
            );
        } else {
            return AppResponse.error(
                    response.statusCode(),
                    "Failed to create playback ID: " + response.body()
            );
        }
    }

    public AppResponse<String> getStreamPlaybackId(String streamId, String simulcastId) throws Exception {
        String url = String.format("/live/streams/%s/%s/playback-ids/%s", buildUrl(""), streamId, simulcastId);

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("Authorization", "Basic " + encodeCredentials())
                .GET()
                .build();

        HttpResponse<String> response = HTTP_CLIENT.send(getRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return AppResponse.success(
                    response.statusCode(),
                    "Retrieved livestream successfully",
                    response.body()
            );
        } else {
            return AppResponse.error(
                    response.statusCode(),
                    "Failed to retrieve livestream: " + response.body()
            );
        }
    }

    public AppResponse<String> deleteStreamPlaybackId(String streamId, String playbackId) throws Exception {
        String url = String.format("/live/streams/%s/%s/playback-ids?playbackId=%s", buildUrl(""), streamId, playbackId);

        HttpRequest deleteRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Basic " + encodeCredentials())
                .DELETE()
                .build();

        HttpResponse<String> response = HTTP_CLIENT.send(deleteRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return AppResponse.success(
                    response.statusCode(),
                    "Playback ID deleted successfully",
                    response.body()
            );
        } else {
            return AppResponse.error(
                    response.statusCode(),
                    "Failed to delete playback ID: " + response.body()
            );
        }
    }

    public AppResponse<String> createSimulcast(CreateSimulcastRequest request, String streamId) throws Exception {
        CreateSimulcastRequest.SimulcastRequest simulcastRequest = request.getSimulcastRequest();
        if (simulcastRequest == null) {
            simulcastRequest = new CreateSimulcastRequest.SimulcastRequest();
        }

        request.setSimulcastRequest(simulcastRequest);
        String jsonRequestBody = gson.toJson(request);

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(buildUrl("/live/streams/" + streamId + "/simulcast")))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Basic " + encodeCredentials())
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequestBody))
                .build();

        HttpResponse<String> response = HTTP_CLIENT.send(postRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return AppResponse.success(
                    response.statusCode(),
                    "simulcast creation successful",
                    response.body()
            );
        } else {
            return AppResponse.error(
                    response.statusCode(),
                    "Failed to create simulcast: " + response.body()
            );
        }
    }

    public AppResponse<String> updateSimulcast(UpdateSimulcast request, String streamId, String simulcastId) throws Exception {
        UpdateSimulcast.UpdateSimulcastRequest simulcastRequest = request.getSimulcastRequest();
        if (simulcastRequest == null) {
            simulcastRequest = new UpdateSimulcast.UpdateSimulcastRequest();
        }

        request.setSimulcastRequest(simulcastRequest);
        String jsonRequestBody = gson.toJson(request);

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(buildUrl("/live/streams/" + streamId + "/simulcast/" + simulcastId)))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Basic " + encodeCredentials())
                .PUT(HttpRequest.BodyPublishers.ofString(jsonRequestBody))
                .build();

        HttpResponse<String> response = HTTP_CLIENT.send(postRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return AppResponse.success(
                    response.statusCode(),
                    "Update simulcast successful",
                    response.body()
            );
        } else {
            return AppResponse.error(
                    response.statusCode(),
                    "Failed to update simulcast: " + response.body()
            );
        }
    }

    public AppResponse<String> getSimulcastById(String streamId, String simulcastId) throws Exception {
        String url = String.format("/live/streams/%s/%s/simulcast/%s", buildUrl(""), streamId, simulcastId);

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("Authorization", "Basic " + encodeCredentials())
                .GET()
                .build();

        HttpResponse<String> response = HTTP_CLIENT.send(getRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return AppResponse.success(
                    response.statusCode(),
                    "Retrieved simulcast successfully",
                    response.body()
            );
        } else {
            return AppResponse.error(
                    response.statusCode(),
                    "Failed to retrieve simulcast: " + response.body()
            );
        }
    }

    public AppResponse<String> deleteSimulcast(String streamId, String simulcastId) throws Exception {
        String url = String.format("/live/streams/%s/%s/simulcast/%s", buildUrl(""), streamId, simulcastId);

        HttpRequest deleteRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Basic " + encodeCredentials())
                .DELETE()
                .build();

        HttpResponse<String> response = HTTP_CLIENT.send(deleteRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return AppResponse.success(
                    response.statusCode(),
                    "simulcast ID deleted successfully",
                    response.body()
            );
        } else {
            return AppResponse.error(
                    response.statusCode(),
                    "Failed to delete simulcast ID: " + response.body()
            );
        }
    }

    public AppResponse<String> createMedia(CreateMediaRequest request) throws Exception {
        // Set default inputs and metadata if not provided
        if (request.getInputs() == null || request.getInputs().isEmpty()) {
            CreateMediaRequest.Input input = new CreateMediaRequest.Input();
            input.setType("video");
            input.setUrl("https://static.fastpix.io/sample.mp4");
            request.setInputs(List.of(input));
        }

        if (request.getMetadata() == null) {
            Map<String, String> metadata = new HashMap<>();
            metadata.put("user", "fastpix_admin");
            request.setMetadata(metadata);
        }

        request.setAccessPolicy("public");

        String jsonRequestBody = gson.toJson(request);

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(buildUrl("/on-demand")))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Basic " + encodeCredentials())
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequestBody))
                .build();

        HttpResponse<String> response = HTTP_CLIENT.send(postRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return AppResponse.success(
                    response.statusCode(),
                    "Media creation successful",
                    response.body()
            );
        } else {
            return AppResponse.error(
                    response.statusCode(),
                    "Failed to create media: " + response.body()
            );
        }
    }

    public AppResponse<String> uploadMedia(UploadRequest request) throws Exception {
        UploadRequest.pushMediaSettings pushMediaSettings = new UploadRequest.pushMediaSettings();
        UploadRequest.Input input = new UploadRequest.Input();
        input.setType("video");
        input.setUrl("https://static.fastpix.io/sample.mp4");

        Map<String, String> metadata = new HashMap<>();
        metadata.put("user", "fastpix_admin");

        pushMediaSettings.setInputs(List.of(input));
        pushMediaSettings.setMetadata(metadata);
        pushMediaSettings.setAccessPolicy("public");

        request.setPushMediaSettings(pushMediaSettings);
        request.setCorsOrigin("*");

        String jsonRequestBody = gson.toJson(request);
        System.out.println("POST upload media request. REQUEST BODY: " + jsonRequestBody);

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(buildUrl("/uploads")))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Basic " + encodeCredentials())
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequestBody))
                .build();

        HttpResponse<String> response = HTTP_CLIENT.send(postRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return AppResponse.success(
                    response.statusCode(),
                    "Media upload successful",
                    response.body()
            );
        } else {
            return AppResponse.error(
                    response.statusCode(),
                    "Failed to upload media: " + response.body()
            );
        }
    }

    public AppResponse<String> getAllMedia(int limit, int offset, String orderBy) throws Exception {
        String url = String.format("/on-demand?limit=%d&offset=%d&orderBy=%s", limit, offset, orderBy);

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(buildUrl(url)))
                .header("Accept", "application/json")
                .header("Authorization", "Basic " + encodeCredentials())
                .GET()
                .build();

        HttpResponse<String> response = HTTP_CLIENT.send(getRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return AppResponse.success(
                    response.statusCode(),
                    "Retrieved media list successfully",
                    response.body()
            );
        } else {
            return AppResponse.error(
                    response.statusCode(),
                    "Failed to retrieve media list: " + response.body()
            );
        }
    }

    public AppResponse<String> getMediaById(String mediaId) throws Exception {
        String url = String.format("%s/%s", buildUrl(""), mediaId);

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("Authorization", "Basic " + encodeCredentials())
                .GET()
                .build();

        HttpResponse<String> response = HTTP_CLIENT.send(getRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return AppResponse.success(
                    response.statusCode(),
                    "Retrieved media successfully",
                    response.body()
            );
        } else {
            return AppResponse.error(
                    response.statusCode(),
                    "Failed to retrieve media: " + response.body()
            );
        }
    }

    public AppResponse<String> getMediaInputInfo(String mediaId) throws Exception {
        String url = String.format("%s/%s/input-info", buildUrl(""), mediaId);

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("Authorization", "Basic " + encodeCredentials())
                .GET()
                .build();

        HttpResponse<String> response = HTTP_CLIENT.send(getRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return AppResponse.success(
                    response.statusCode(),
                    "Retrieved media input info successfully",
                    response.body()
            );
        } else {
            return AppResponse.error(
                    response.statusCode(),
                    "Failed to retrieve media input info: " + response.body()
            );
        }
    }

    public AppResponse<String> updateMedia(String mediaId) throws Exception {
        String url = String.format("%s/%s", buildUrl(""), mediaId);

        Map<String, String> metadata = new HashMap<>();
        metadata.put("user", "fastpix_admin");

        UpdateMediaRequest request = new UpdateMediaRequest();
        request.setMetadata(metadata);

        String jsonRequestBody = gson.toJson(request);

        HttpRequest patchRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic " + encodeCredentials())
                .method("PATCH", HttpRequest.BodyPublishers.ofString(jsonRequestBody))
                .build();

        HttpResponse<String> response = HTTP_CLIENT.send(patchRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return AppResponse.success(
                    response.statusCode(),
                    "Media updated successfully",
                    response.body()
            );
        } else {
            return AppResponse.error(
                    response.statusCode(),
                    "Failed to update media: " + response.body()
            );
        }
    }

    public AppResponse<String> deleteMedia(String mediaId) throws Exception {
        String url = String.format("%s/%s", buildUrl(""), mediaId);

        HttpRequest deleteRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("Authorization", "Basic " + encodeCredentials())
                .DELETE()
                .build();

        HttpResponse<String> response = HTTP_CLIENT.send(deleteRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return AppResponse.success(
                    response.statusCode(),
                    "Media deleted successfully",
                    response.body()
            );
        } else {
            return AppResponse.error(
                    response.statusCode(),
                    "Failed to delete media: " + response.body()
            );
        }
    }

    public AppResponse<String> createMediaPlaybackId(CreatePlaybackIdRequest request, String mediaId) throws Exception {
        String url = String.format("%s/%s/playback-ids", buildUrl(""), mediaId);
        request.setAccessPolicy("public");

        String jsonRequestBody = gson.toJson(request);

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Basic " + encodeCredentials())
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequestBody))
                .build();

        HttpResponse<String> response = HTTP_CLIENT.send(postRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return AppResponse.success(
                    response.statusCode(),
                    "Playback ID created successfully",
                    response.body()
            );
        } else {
            return AppResponse.error(
                    response.statusCode(),
                    "Failed to create playback ID: " + response.body()
            );
        }
    }

    public AppResponse<String> deleteMediaPlaybackId(String mediaId, String playbackId) throws Exception {
        String url = String.format("%s/%s/playback-ids?playbackId=%s", buildUrl(""), mediaId, playbackId);

        HttpRequest deleteRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Basic " + encodeCredentials())
                .DELETE()
                .build();

        HttpResponse<String> response = HTTP_CLIENT.send(deleteRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return AppResponse.success(
                    response.statusCode(),
                    "Playback ID deleted successfully",
                    response.body()
            );
        } else {
            return AppResponse.error(
                    response.statusCode(),
                    "Failed to delete playback ID: " + response.body()
            );
        }
    }

    protected String encodeCredentials() {
        String username = FastPixConfigLoader.getProperty("fastpix.username");
        String password = FastPixConfigLoader.getProperty("fastpix.password");
        String auth = username + ":" + password;
        return Base64.getEncoder().encodeToString(auth.getBytes());
    }
}
