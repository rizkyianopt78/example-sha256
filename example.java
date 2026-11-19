import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HexFormat;

public class Main {
    public static void main(String[] args) throws Exception {
        String clientId = "YOUR_CLIENT_ID";
        String secret = "YOUR_CLIENT_SECRET";

        // Generate 8 random bytes
        byte[] randomBytes = new byte[8];
        new SecureRandom().nextBytes(randomBytes);
        String externalId = HexFormat.of().formatHex(randomBytes);

        // Generate RFC3339 timestamp
        String timestamp = OffsetDateTime.now()
                .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        // HMAC-SHA256
        String signingString = String.format(
                "%s|%s|%s",
                clientId,
                externalId,
                timestamp
        );

        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec key = new SecretKeySpec(
                secret.getBytes(java.nio.charset.StandardCharsets.UTF_8),
                "HmacSHA256"
        );

        mac.init(key);
        byte[] signatureBytes = mac.doFinal(
                signingString.getBytes(java.nio.charset.StandardCharsets.UTF_8)
        );

        String signature = HexFormat.of().formatHex(signatureBytes);

        System.out.println("External ID: " + externalId);
        System.out.println("Timestamp: " + timestamp);
        System.out.println("Signature: " + signature);
    }
}