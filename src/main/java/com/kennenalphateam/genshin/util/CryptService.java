package com.kennenalphateam.genshin.util;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

@Component
public class CryptService {

    public static final String alg = "AES/GCM/NoPadding";
    public static final int GCM_IV_LENGTH = 12;
    public static final int GCM_TLENGTH = 128;
    private final SecretKeySpec secret;
    private final SecureRandom secureRandom = new SecureRandom();

    @Autowired
    public CryptService(
            @Value("crypt.secret") String secret) {
        this.secret = new SecretKeySpec(secret.getBytes(), "AES");
    }

    @SneakyThrows
    public String encrypt(String data) {
        if (StringUtils.isBlank(data))
            throw new IllegalArgumentException("Data must not empty");

        Cipher cipher = Cipher.getInstance(alg);
        byte[] iv = new byte[GCM_IV_LENGTH];
        secureRandom.nextBytes(iv);

        GCMParameterSpec spec = new GCMParameterSpec(GCM_TLENGTH, iv);
        cipher.init(Cipher.ENCRYPT_MODE, secret, spec);
        byte[] encryptedByte = cipher.doFinal(data.getBytes());
        byte[] cryptData = ByteBuffer.allocate(iv.length + encryptedByte.length)
                .put(iv)
                .put(encryptedByte)
                .array();
        return Base64.getEncoder().encodeToString(cryptData);
    }

    @SneakyThrows
    public String decrypt(String encryptedData) {
        if (StringUtils.isBlank(encryptedData))
            throw new IllegalArgumentException("Data must not empty");

        Cipher cipher = Cipher.getInstance(alg);
        byte[] data = Base64.getDecoder().decode(encryptedData);
        byte[] iv = Arrays.copyOf(data, GCM_IV_LENGTH);
        byte[] encryptedByte = Arrays.copyOfRange(data, GCM_IV_LENGTH, data.length);

        GCMParameterSpec spec = new GCMParameterSpec(GCM_TLENGTH, iv);
        cipher.init(Cipher.DECRYPT_MODE, secret, spec);
        return new String(cipher.doFinal(encryptedByte));
    }
}
