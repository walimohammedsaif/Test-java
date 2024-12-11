package io.fastpix;

import java.util.Base64;

public abstract class FastPixBaseClient {
    protected final String baseUrl;
    protected final String username;
    protected final String password;

    public FastPixBaseClient(String baseUrl, String username, String password) {
        this.baseUrl = baseUrl;
        this.username = username;
        this.password = password;
    }

    /**
     * Encodes credentials for Basic Authentication
     * @return Base64 encoded credentials
     */
    protected String encodeCredentials() {
        String auth = username + ":" + password;
        return Base64.getEncoder().encodeToString(auth.getBytes());
    }

    /**
     * Builds a complete URL with the base URL and additional path
     * @param path Additional path to append
     * @return Complete URL
     */
    protected String buildUrl(String path) {
        return baseUrl + path;
    }
}
