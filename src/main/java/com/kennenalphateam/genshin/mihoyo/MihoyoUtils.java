package com.kennenalphateam.genshin.mihoyo;

import com.kennenalphateam.genshin.mihoyo.exception.InvalidCookieException;
import com.kennenalphateam.genshin.mihoyo.exception.InvalidUidException;
import lombok.Getter;
import org.apache.tomcat.util.buf.HexUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class MihoyoUtils {

    private static String randomString() {
        return String.format("%d", 100000 + new Random().nextInt(99999));
    }

    public static String generateDSToken() {
        final String DS_SALT = "6cqshh5dhw73bzxn20oexa9k516chk7s";
        String randomString = randomString();
        long time = new Date().getTime() / 1000;
        String data = String.format("salt=%s&t=%d&r=%s", DS_SALT, time, randomString);
        String hash = getTokenHash(data);

        return String.format("%d,%s,%s", time, randomString, hash);
    }

    private static String getTokenHash(String data) {
        // data byte -> md5 -> hex
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(data.getBytes());
            return HexUtils.toHexString(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getServerNameFromUid(String uid) {
        int code;
        try {
            code = Integer.parseInt(uid.substring(0, 1));
        } catch (NumberFormatException e) {
            throw new InvalidUidException();
        }

        return MihoyoServer.getServerFromCode(code).getServerName();
    }

    public static String getMihoyoUidFromCookie(String cookie) {
        int startIdx = cookie.indexOf("ltuid=");
        if (startIdx == -1)
            throw new InvalidCookieException();
        startIdx += 6;
        int endIdx = cookie.indexOf(";", startIdx);
        if (endIdx == -1)
            endIdx = cookie.length();
        String uid = cookie.substring(startIdx, endIdx);
        if (uid.isEmpty())
            throw new InvalidCookieException();
        return uid;
    }

    @Getter
    public enum MihoyoServer {
        USA(6, "os_usa"),
        EURO(7, "os_euro"),
        ASIA(8, "os_asia"),
        CHT(9, "os_cht");
        int serverCode;
        String serverName;

        MihoyoServer(int serverCode, String serverName) {
            this.serverCode = serverCode;
            this.serverName = serverName;
        }

        public static MihoyoServer getServerFromCode(int serverCode) {
            return Arrays.stream(MihoyoServer.values()).filter(mihoyoServer ->
                    mihoyoServer.serverCode == serverCode
            ).findFirst().orElseThrow(InvalidUidException::new);
        }
    }
}
