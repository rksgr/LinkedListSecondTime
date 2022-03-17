package com.example.bookstorebackend.util;

import org.springframework.stereotype.Component;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Component
public class OtpGenerationUtil {

    private static final Integer OTP_VALIDITY_TIME = 400;
    private LoadingCache<String, Integer> otpCache;

    public OtpGenerationUtil() {

        otpCache = CacheBuilder.newBuilder().
                expireAfterWrite(OTP_VALIDITY_TIME,
                        TimeUnit.SECONDS).build(new CacheLoader<String, Integer>(){
                    public Integer load(String key) {
                        return 0;
                    }
                });
    }

    // Generate a random otp number and store it in the cache.
    // Rewrite the OTP if it exists Using user id  as key
    public int generateOTP(String key) {
        Random random = new Random();
        int otp = 100000 + (int) (Math.random() * 900000);
        otpCache.put(key, otp);
        return otp;
    }

    // Returns the OTP number against Key->Key value is username
    public int getOTP(String key) {
        try {
            return otpCache.get(key);
        } catch (Exception e) {
            return 0;
        }
    }
    // Clear the pre-existing otp present in the cache
    public void clearOTP(String key){
        otpCache.invalidate(key);
    }
}

