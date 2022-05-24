package com.rydzwr.sharedShoppingList.service;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class DeviceAuthorization
{
    public static DeviceAuthorization instance;

    public String deviceIdFromAuthHeader(String authHeader)
    {
        String deviceCodeBase64 = authHeader.split(" ")[1];
        byte[] decodedBytes = Base64.getDecoder().decode(deviceCodeBase64);

        String deviceId = "";
        try
        {
            deviceId = new String(decodedBytes, "US-ASCII");
        }
        catch (UnsupportedEncodingException e)
        {

        }

        return deviceId;
    }

    public static DeviceAuthorization getInstance()
    {
        if (instance == null)
            instance = new DeviceAuthorization();

        return instance;
    }
}
