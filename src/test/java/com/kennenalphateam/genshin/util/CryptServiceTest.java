package com.kennenalphateam.genshin.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CryptServiceTest {

    private final CryptService cryptService = new CryptService("secretsecretsecretsecretsecret12");

    @Test
    void encryptAndDecrypt() {
        String original = "testtest;testtest;";
        String encrypted = cryptService.encrypt(original);
        String decrypted = cryptService.decrypt(encrypted);
        assertEquals(original, decrypted);
    }
}