package controlador;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
/**
 * @author -Ismael Orellana Bello
 *         -Pablo Salvador Del Río Vergara
 *         -Ángel Acedo Moreno
 *         -Javier Tienda
 *         -Jorge Luis López
 * @version 1.0
 * @date 23/12/2022
 * That class Encrypt and decrypt passwords
 */
public class Encrypt {

    //Secret key
    private static String SECRET_KEY = "proyectoMultidisciplinar", SALT = "MultidisciplinarProyecto";

    /**
     *
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private static SecretKeySpec getSecretKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
        SecretKey tmp = factory.generateSecret(spec);
        return new SecretKeySpec(tmp.getEncoded(), "AES");
    }

    /**
     * Method that encrypt passwords
     *
     * @param strToEncrypt -String the password to encrypt
     * @return the password encrypted
     */
    public String encrypt(String strToEncrypt) {
        String response = null;
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            /**
             * Cipher
             */
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); //Cipher type
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(), ivspec);
            response =  Base64.getEncoder()
                    .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            response = "Error while encrypting: ";
        }
        return response;
    }

    /**
     * Method that decrypts passwords
     *
     * @param strToDecrypt -String the password encrypted
     * @return  -String the password decrypted
     */
    public String decrypt(String strToDecrypt) {
        String response;
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(), ivspec);
            response = new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            response = "Error while decrypting: ";
        }
        return response;
    }
}
