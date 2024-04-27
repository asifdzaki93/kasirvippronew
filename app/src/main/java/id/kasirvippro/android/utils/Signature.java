package id.kasirvippro.android.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Willyanto Wijaya Sulaiman on 2019-06-21.
 */
public class Signature {

//    private static final String secret = Resources.getSystem().getString(R.string.app_secret);

    public static String generateTimestamp() {
        // String = 2019-03-19T16:14:15.856+07:00
//        SimpleDateFormat fmtOut = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        SimpleDateFormat fmtOut;
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N){
            fmtOut = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ");
        }else{
            fmtOut = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        }
        return fmtOut.format(new Date());
    }

    private static String encodeHmacSha256(String key, String data) {
        try {
            Mac sha256_HMAC = null;
            sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            return byteArrayToHex(sha256_HMAC.doFinal(data.getBytes("UTF-8")));
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }

//    public static String getSignature(String method, String relativeUrl, String accessToken, String requestBody, String timestamp) {
//        String stringToSign = method + ":" + relativeUrl + ":" + accessToken + ":" +
//                stringToSha256(requestBody.replace(" ", "")) + ":" + timestamp;
//        String signature = encodeHmacSha256(secretKey, stringToSign);
//        return signature;
//    }

    public static String getSignature(String secret, String timestamp) {
        String signature = encodeHmacSha256(secret, timestamp);
        return signature;
    }
}