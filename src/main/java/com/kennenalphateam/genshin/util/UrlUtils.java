package com.kennenalphateam.genshin.util;

import java.net.URL;

public class UrlUtils {
    public static String createRedirectUrl(URL serverRequestUrl, String targetEndpoint) {
        StringBuilder redirectUrl = new StringBuilder();
        redirectUrl.append(serverRequestUrl.getProtocol());
        redirectUrl.append("://");
        redirectUrl.append(serverRequestUrl.getHost());
        if (serverRequestUrl.getPort() != -1) {
            redirectUrl.append(":");
            redirectUrl.append(serverRequestUrl.getPort());
        }
        if (!targetEndpoint.startsWith("/"))
            redirectUrl.append("/");
        redirectUrl.append(targetEndpoint);
        return redirectUrl.toString();
    }
}
