package com.kennenalphateam.genshin.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CryptServiceTest {

    private final CryptService cryptService = new CryptService("secretsecretsecretsecretsecret12");

    @Test
    void encryptAndDecrypt() {
        String original = "test";
        String encrypted = cryptService.encrypt(original);
        String decrypted = cryptService.decrypt(encrypted);
        assertEquals(original, decrypted);
    }

    @Test
    void 빈_데이터가_들어오면_에러를_발생시킨다() {
        assertThrows(IllegalArgumentException.class, () -> {
            cryptService.encrypt("");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            cryptService.encrypt(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            cryptService.decrypt("");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            cryptService.decrypt(null);
        });
    }
}