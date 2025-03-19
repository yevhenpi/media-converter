package ua.pidopryhora.mediaconverter.requestmanager.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ua.pidopryhora.mediaconverter.requestmanager.model.RequestDTO;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
@Slf4j
@Component
public class HashUtil {

    public String getHash(RequestDTO requestDTO){

        byte[] hashBytes = null;
        try {
            String data = requestDTO.toString();
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hashBytes = digest.digest(data.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            log.error("UNABLE TO GET HASH");
        }

        return Base64.getEncoder().encodeToString(hashBytes);
    }
}
