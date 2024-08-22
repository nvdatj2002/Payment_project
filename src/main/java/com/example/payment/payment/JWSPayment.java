package com.example.payment.payment;

import com.example.payment.Entity.Bank;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.text.ParseException;
import java.util.*;

public class JWSPayment {

    private static final String SECRET_KEY_ALGORITHM = "HS256";

    public String createJWS(int orderCode, Bank bank, double totalMoney) {
        // Tạo khóa bí mật ngẫu nhiên
        String secretKey = "day_la_khoa_bi_mat_cua_toi_nhe__";
        byte[] signSecret = secretKey.getBytes(StandardCharsets.UTF_8);

        // Tạo header
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

        // tao payload
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject("xác minh thông tin thanh toán")
                .issuer("Nông Văn Đạt")
                .claim("Mã hoá đơn",orderCode)
                .claim("Số tiền",totalMoney)
                .claim("Nội dung chuyển khoán","Thanh toán đơn hàng "+orderCode)
                .claim("Ngân hàng",bank.getNameBank())
                .claim("Số tài khoản nhận tiền",bank.getNumberBank())
                .claim("Tên người nhận",bank.getName())
                .expirationTime(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Hết hạn sau 1 giờ
                .build();

        JWSObject jwsObject = new JWSObject(header, claimsSet.toPayload());

        // Tạo JWS
        JWSSigner jwsSigner;
        try {
            jwsSigner = new MACSigner(signSecret);
            jwsObject.sign(jwsSigner);
            return jwsObject.serialize();
        } catch (JOSEException e) {
            // Xử lý lỗi cụ thể
            System.err.println("Error creating JWS: " + e.getMessage());
            return null; // hoặc throw một ngoại lệ tùy theo yêu cầu
        }
    }


    public boolean verifyJWS(String jws) {

        // khoá bí mật
        String privateSecret = "day_la_khoa_bi_mat_cua_toi_nhe__";
        byte[] signSecret = privateSecret.getBytes(StandardCharsets.UTF_8);

        JWSObject jwsObject;
        JWSVerifier jwsVerifier;

        try {
            jwsObject = JWSObject.parse(jws);
        } catch (ParseException e) {
            System.out.println("lỗi trong khi parse jws token sang jws object");
            throw new RuntimeException(e);
        }

        try {
            jwsVerifier = new MACVerifier(signSecret);
        } catch (JOSEException e) {
            System.out.println("lỗi trong khi tạo xác minh jws");
            throw new RuntimeException(e);
        }
        try {
            if (jwsObject.verify(jwsVerifier)) {
                return true;
            }
        } catch (JOSEException e) {
            System.out.println("lỗi trong khi xác minh jws ");
            throw new RuntimeException(e);
        }
        return false;
    }
}
