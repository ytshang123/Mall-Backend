package project.linkortech.test.utils;


import mizuki.project.core.restserver.util.ByteUtil;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 简单加密
 * key = key_base ^ id; data^key 逐字
 * data为一个字节 则不需要
 */
public class SecretUtil {
    private static final byte[] BASE_KEY= "lTT-mDc-coLlEctOr".getBytes();
    private static final String password = "lkt-Mdc-1";
    private static final String DEFAULT_CIPHER_ALGORITHM_AES = "AES/ECB/PKCS5Padding";
//    private static final byte[] BASE_KEY= "lTT-mDc-".getBytes();

    private static byte[] genKey(int id){
        byte[] ids = new byte[2];
        ids[0]=(byte)(id>>8 & 0xff);
        ids[1]=(byte)(id & 0xff);
        byte[] key = new byte[BASE_KEY.length];
        for(int i=0;i<BASE_KEY.length;i++){
            key[i]=(byte)(BASE_KEY[i]^ids[i%ids.length]);
        }
        return key;
    }

    public static byte[] convert(byte[] in, int id){
        byte[] key = genKey(id);
        byte[] out = new byte[in.length];
        for(int i=0;i<in.length;i++){
            out[i] = (byte)(in[i]^key[i%key.length]);
        }
        return out;
    }

    private static byte[] desEnc(byte[] in, int id) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        //初始化向量参数，AES 为16bytes. DES 为8bytes.
        String iv = "aabbccdd";
        byte[] key = genKey(id);
        Key keySpec = new SecretKeySpec(key, "DES");
        cipher.init(Cipher.ENCRYPT_MODE,  keySpec);
        return cipher.doFinal(in);
    }

    public static byte[] encDES(byte[] content) {
        try {
            byte[] key = password.getBytes();
            // 初始化向量
            IvParameterSpec iv = new IvParameterSpec(key);
            DESKeySpec desKey = new DESKeySpec(key);
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成securekey
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);
            // 现在，获取数据并加密
            // 正式执行加密操作
            return cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] decDES(byte[] content){
        try {
            byte[] key = password.getBytes();
            // 初始化向量
            IvParameterSpec iv = new IvParameterSpec(key);
            // 创建一个DESKeySpec对象
            DESKeySpec desKey = new DESKeySpec(key);
            // 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            // 将DESKeySpec对象转换成SecretKey对象
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, iv);
            // 真正开始解密操作
            return cipher.doFinal(content);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] encAES(byte[] content) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM_AES);// 创建密码器
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            //AES 要求密钥长度为 128
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());
            kg.init(128, random);
            //生成一个密钥
            SecretKey secretKey = kg.generateKey();
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(secretKey.getEncoded(), "AES"));// 初始化为加密模式的密码器
            return cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] decAES(byte[] content){
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM_AES);
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            //AES 要求密钥长度为 128
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());
            kg.init(128, random);
            //生成一个密钥
            SecretKey secretKey = kg.generateKey();
            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(secretKey.getEncoded(), "AES"));
            return cipher.doFinal(content);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
//        long a = System.currentTimeMillis();
//        byte[] r = desEnc("{\"params\": {\"axis_rate\": 110, \"fast_rate\": 100, \"feed_rate\": 140}, \"status\": [\"editing\"], \"program\": {\"no\": \"1234\"}, \"quantity\": {\"add\": 0, \"sum\": 226}}".getBytes(),12);
//        System.out.println(r.length);
//        System.out.println(System.currentTimeMillis()-a);
//        byte[] data = new byte[]{0,0,0,0,1,0,0};
//        System.out.println(ByteUtil.byte2hex(convert(data,9)));
//        System.out.println(ByteUtil.byte2hex(convert(data,3)));
//
//        String[] strs = "84".split(" ");
//        byte[] bytes = new byte[strs.length];
//        for(int i=0;i<strs.length;i++){
//            bytes[i] = (byte)Integer.parseInt(strs[i],16);
//        }
//        System.out.println(ByteUtil.byte2hex(convert(bytes,60002)));
        Long a = System.currentTimeMillis();
        byte[] aa = encAES("1234567890123456".getBytes());
        System.out.println(System.currentTimeMillis() - a);
        System.out.println(aa.length);
        System.out.println(ByteUtil.byteArrayToHexString(aa));
        a = System.currentTimeMillis();
        byte[] bb = decAES(aa);
        System.out.println(System.currentTimeMillis() - a);
        System.out.println(ByteUtil.byteArrayToHexString(bb));

    }


}
