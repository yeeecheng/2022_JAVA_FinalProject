package com.plcoding.instagramui.saveplace.mainActivity;

import com.google.android.gms.common.util.Hex;

import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;

public class AESCrypt {


    public String toHex(byte[] byteArray){
        StringBuilder sb = new StringBuilder(byteArray.length * 2);
        for(byte item : byteArray){
            sb.append(String.format("%02x",item));
        }
        return sb.toString();
    }

    public String generateKey(){

        byte[] result = new byte[128/8];

        SecureRandom sr = new SecureRandom();
        sr.nextBytes(result);

        return  toHex(result);
    }

    public String generateIv(){

        byte[] result = new byte[128/8];

        SecureRandom sr = new SecureRandom();
        sr.nextBytes(result);

        return  toHex(result);
    }



    public String encrypt(String key,String initVector,String value){

        try{

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            
            
            
            IvParameterSpec iv = new IvParameterSpec(stringToByte(initVector));
            SecretKeySpec skeySpec =new SecretKeySpec(stringToByte(key), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());

            return new String(Base64.getEncoder().encode(encrypted));
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }

    public String decrypt(String key,String initVector,String encrypted){

        try{
            IvParameterSpec iv = new IvParameterSpec(stringToByte(initVector));
            SecretKeySpec skeySpec =new SecretKeySpec(stringToByte(key), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE,skeySpec,iv);

            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
            return new String(original);

        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }
    
    public byte[] stringToByte(String x){
        
        byte[] r=new byte[16];
        
        for(int i=0;i<32;i+=2)
        {
            Integer num=Integer.parseInt(x.substring(i,i+2), 16);
            
            r[i/2]=num.byteValue();
        }
        
        return r;
        
    }
    
}