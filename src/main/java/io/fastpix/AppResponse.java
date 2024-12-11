package io.fastpix;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Represents a standardized response structure for FastPix API calls.
 */
public class AppResponse<T> {
    @SerializedName("status_code")
    private int statusCode;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private T data;

    // Constructors
    public AppResponse() {}

    public AppResponse(int statusCode, boolean success, String message, T data) {
        this.statusCode = statusCode;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // Static factory method to create successful responses
    public static <T> AppResponse<T> success(int statusCode, String message, T data) {
        return new AppResponse<>(statusCode, true, message, data);
    }

    // Static factory method to create error responses
    public static <T> AppResponse<T> error(int statusCode, String message) {
        return new AppResponse<>(statusCode, false, message, null);
    }

    // Getters and Setters
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * Converts the response to a JSON string
     * @return JSON representation of the response
     */
    public String toJson() {
        return new Gson().toJson(this);
    }

    /**
     * Parses a JSON string to an AppResponse
     * @param json JSON string to parse
     * @param clazz Class of the data type
     * @return Parsed AppResponse
     */
    public static <T> AppResponse<T> fromJson(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, AppResponse.class);
    }
}