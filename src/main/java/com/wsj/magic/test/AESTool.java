package com.wsj.magic.test;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;
import java.util.Base64;

/**
 * @version V2.0
 * @title: AESTool
 * @package: com.zielsmart.web.basic.tool
 * @description: AES加密工具
 * @author: 李耀华
 * @date: 2021-02-2610:21
 * @Copyright: 2019 www.zielsmart.com Inc. All rights reserved.
 * 注意：本内容仅限于郑州致欧网络科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class AESTool {

    private String key;
    private String iv;


    static {
        try {
            Security.addProvider(new BouncyCastleProvider());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AESTool(String key, String iv) {
        this.key = key;
        this.iv = iv;
    }

    /**
     * 以PKCS7Padding 方式加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public String encrypt7(String data) throws Exception {

        byte[] dataByte = data.getBytes();
        byte[] keyByte = key.getBytes();
        byte[] ivByte = iv.getBytes();

        String encryptedData = null;

        //指定算法，模式，填充方式，创建一个Cipher
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");

        //生成Key对象
        Key sKeySpec = new SecretKeySpec(keyByte, "AES");

        //把向量初始化到算法参数
        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
        params.init(new IvParameterSpec(ivByte));

        //指定用途，密钥，参数 初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, params);

        //指定加密
        byte[] result = cipher.doFinal(dataByte);

        //对结果进行Base64编码，否则会得到一串乱码，不便于后续操作
        Base64.Encoder encoder = Base64.getEncoder();
        encryptedData = encoder.encodeToString(result);
        return encryptedData;
    }

    public String decrypt7(String encryptedData) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        byte[] dataByte = Base64.getDecoder().decode(encryptedData);
        byte[] keyByte = key.getBytes();
        byte[] ivByte = iv.getBytes();

        String data = null;

        //指定算法，模式，填充方法 创建一个Cipher实例
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");

        //生成Key对象
        Key sKeySpec = new SecretKeySpec(keyByte, "AES");

        //把向量初始化到算法参数
        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
        params.init(new IvParameterSpec(ivByte));

        //指定用途，密钥，参数 初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, sKeySpec, params);

        //执行解密
        byte[] result = cipher.doFinal(dataByte);

        //解密后转成字符串
        data = new String(result);

        return data;
    }

}

