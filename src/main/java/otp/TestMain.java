package otp;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

public class TestMain {
    public static void main(String[] args) {
       TOTPManager totpManager = new TOTPManager(6, 30000);
        GoogleAuthenticatorKey key = totpManager.getgAuth().createCredentials();
        String secret = key.getKey();
        System.out.println(String.format("Generating key %s", secret));
        int ct = 4;
        while(ct > 0){
            System.out.println(String.format("Otp %d", totpManager.getgAuth().getTotpPassword(secret)));
            ct--;
            try {
                Thread.sleep(10000); // 1000 milliseconds = 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
