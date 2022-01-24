package security;

import constants.Constants;
import org.apache.commons.codec.binary.Base64;
import utils.Exit;

import java.time.LocalDateTime;

public class KeyVerification {

    public void verify(){

        byte[] decodedBytes1 = Base64.decodeBase64(Constants.key);
        byte[] decodedBytes2 = Base64.decodeBase64(new String(decodedBytes1));

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime key = LocalDateTime.parse(new String(decodedBytes2));

        if (now.isAfter(key)){
            System.out.println("Время действия ключа истекло");
            new Exit().exitProgram();
        }
        System.out.println("Ключ действует до "+ key.getDayOfMonth()+" "+key.getMonth()+" "+key.getYear() );
    }
}
