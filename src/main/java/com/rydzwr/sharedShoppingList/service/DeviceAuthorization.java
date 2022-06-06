package com.rydzwr.sharedShoppingList.service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class DeviceAuthorization
{
    public static DeviceAuthorization instance;

    public String deviceIdFromAuthHeader(String authHeader)
    {
        String deviceCodeBase64 = authHeader.split(" ")[1];
        byte[] decodedBytes = Base64.getDecoder().decode(deviceCodeBase64);

        String deviceId = "";

        deviceId = new String(decodedBytes, StandardCharsets.US_ASCII);

        return deviceId;
    }

    public static DeviceAuthorization getInstance()
    {
        if (instance == null)
            instance = new DeviceAuthorization();

        return instance;
    }
}
