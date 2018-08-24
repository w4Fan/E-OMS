package com.fans.eoms.idp.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fans.eoms.idp.api.Signature;
import com.fans.eoms.idp.api.SignatureVerification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;


public class Token {
    private final static String ALG = "HS256";
    private final static String TYP = "JWT";
    private final static int EXPIRES_INTERVAL = 1000 * 60 * 5;
    private final static Algorithm ALGORITHM = Algorithm.HMAC256("eoms-idp");
    private final static Logger LOGGER = LoggerFactory.getLogger(Token.class);

    private Token() {
    }

    private static class TokenHoder {
        private static Token token = new Token();
    }

    public static Token getInstance() {
        return TokenHoder.token;
    }

    public static Signature create(String userId) {
        Long issuedAt = System.currentTimeMillis();
        Long expiresAt = issuedAt + EXPIRES_INTERVAL;
        String tokenString = "";
        int signCode = 0;

        LOGGER.debug("===============================");
        LOGGER.debug("issuedAt: {}", new Date(issuedAt));
        LOGGER.debug("expiresAt: {}", new Date(expiresAt));
        LOGGER.debug("===============================");

        try {
            tokenString = JWT.create()
                    .withHeader(getHeaderClaims())
                    .withClaim("uid", userId)
                    .withIssuedAt(new Date(issuedAt))
                    .withExpiresAt(new Date(expiresAt))
                    .sign(ALGORITHM);
        } catch (JWTCreationException ex) {
            signCode = -1;
            tokenString = "Invalid Signing configuration / Couldn't convert Claims.";
        }

        return new Signature(signCode, tokenString);
    }

    public static SignatureVerification verify(String tokenString) {
        int code = 0;
        String result = "";

        try {
            JWTVerifier verifier = JWT.require(ALGORITHM).build();
            DecodedJWT jwt = verifier.verify(tokenString);

            result = jwt.getClaim("uid").asString();
        } catch (JWTVerificationException ex) {
            code = -1;
            result = ex.toString();
        }

        return new SignatureVerification(code, result);
    }

    private static Map<String, Object> getHeaderClaims() {
        Map<String, Object> headerClaims = new HashMap<String, Object>();

        headerClaims.put("alg", ALG);
        headerClaims.put("typ", TYP);

        return headerClaims;
    }
}
