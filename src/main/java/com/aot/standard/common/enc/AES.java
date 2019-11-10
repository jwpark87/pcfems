package com.aot.standard.common.enc;

import com.aot.standard.common.exception.CommonException;
import com.aot.standard.common.exception.CommonExceptionCode;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AES {
    private static final Logger LOGGER = LoggerFactory.getLogger(AES.class);

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String KEY = "SkMnS";

    private AES() {
        throw new UnsupportedOperationException();
    }

    public static String decrypt(final String text) throws CommonException {
        return decrypt(text, KEY);
    }

    public static String decrypt(final String text, final String key) throws CommonException {
        byte[] results;
        try {
            final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            final byte[] keyBytes = new byte[16];
            final byte[] getKeyBytes = key.getBytes(StandardCharsets.UTF_8);
            int len = getKeyBytes.length;
            if (len > keyBytes.length) {
                len = keyBytes.length;
            }
            System.arraycopy(getKeyBytes, 0, keyBytes, 0, len);
            final SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITHM);
            final IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            results = cipher.doFinal(Base64.decodeBase64(text));
        } catch (final IllegalBlockSizeException e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new CommonException(CommonExceptionCode.ERROR_ILLEGAL_BLOCK_SIZE_AES);
        } catch (BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new CommonException(e);
        }
        return new String(results, StandardCharsets.UTF_8);
    }

    public static String encrypt(final String text) throws CommonException {
        return encrypt(text, KEY);
    }

    public static String encrypt(final String text, final String key) throws CommonException {
        String results;
        try {
            final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            final byte[] keyBytes = new byte[16];
            final byte[] getKeyBytes = key.getBytes(StandardCharsets.UTF_8);
            int len = getKeyBytes.length;
            if (len > keyBytes.length) {
                len = keyBytes.length;
            }
            System.arraycopy(getKeyBytes, 0, keyBytes, 0, len);
            final SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITHM);
            final IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

            results = Base64.encodeBase64String(cipher.doFinal(text.getBytes(StandardCharsets.UTF_8)));
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new CommonException(e);
        }
        return results;
    }
}
