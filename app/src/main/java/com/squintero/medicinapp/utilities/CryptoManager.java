package com.squintero.medicinapp.utilities;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class CryptoManager {

    private static final String CRYPTO_PASS = "cr1pT0Mana6er";

    public static String encrypt(String value) {

        if (value == null || value.isEmpty())
            return value;

        try {
            DESKeySpec keySpec = new DESKeySpec(CRYPTO_PASS.getBytes("UTF8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(keySpec);

            byte[] clearText = value.getBytes("UTF8");
            // Cipher is not thread safe
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            return Base64.encodeToString(cipher.doFinal(clearText), Base64.DEFAULT);

        } catch (InvalidKeyException | UnsupportedEncodingException | InvalidKeySpecException |
                BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException |
                IllegalBlockSizeException e)
        {
            e.printStackTrace();
        }

        return value;
    }

    public static String decrypt(String value) {

        if (value == null || value.isEmpty())
            return value;

        try {
            DESKeySpec keySpec = new DESKeySpec(CRYPTO_PASS.getBytes("UTF8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(keySpec);

            byte[] encrypedPwdBytes = Base64.decode(value, Base64.DEFAULT);
            // cipher is not thread safe
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decrypedValueBytes = (cipher.doFinal(encrypedPwdBytes));

            return new String(decrypedValueBytes);

        } catch (InvalidKeyException | IllegalBlockSizeException | NoSuchPaddingException |
                BadPaddingException | NoSuchAlgorithmException | InvalidKeySpecException |
                UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        return value;
    }
}
