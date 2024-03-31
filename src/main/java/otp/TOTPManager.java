package otp;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig;
import com.warrenstrange.googleauth.HmacHashFunction;
import com.warrenstrange.googleauth.KeyRepresentation;

public class TOTPManager {
    public static final HmacHashFunction DEFAULT_ALGORITHM = HmacHashFunction.HmacSHA1;
    public static final int DEFUALT_DIGIT_CODE = 6;
    public static final int DEFAULT_TIMEOUT = 30;
    private GoogleAuthenticator gAuth;

    public TOTPManager(){
        this.gAuth = new GoogleAuthenticator(this.getDefaultConfig());
    }

    public TOTPManager(HmacHashFunction hashfunc, int digitCode, int timeout){
        this.gAuth = new GoogleAuthenticator(new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder()
                .setCodeDigits(digitCode)
                .setHmacHashFunction(hashfunc)
                .setTimeStepSizeInMillis(timeout)
                .build());
    }

    public TOTPManager(int digitCode, int timeout){
        this.gAuth = new GoogleAuthenticator(new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder()
                .setCodeDigits(digitCode)
                .setTimeStepSizeInMillis(timeout)
                .build());
    }

    public GoogleAuthenticatorConfig getDefaultConfig(){
        return new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder()
                .setCodeDigits(DEFUALT_DIGIT_CODE)
                .setHmacHashFunction(DEFAULT_ALGORITHM)
                .setTimeStepSizeInMillis(DEFAULT_TIMEOUT)
                .build();
    }

    public GoogleAuthenticator getgAuth() {
        return gAuth;
    }

    public void setgAuth(GoogleAuthenticator gAuth) {
        this.gAuth = gAuth;
    }
}
