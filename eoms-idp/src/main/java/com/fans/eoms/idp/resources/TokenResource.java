package com.fans.eoms.idp.resources;

import com.codahale.metrics.annotation.Timed;
import com.fans.eoms.idp.api.Signature;
import com.fans.eoms.idp.api.SignatureVerification;
import com.fans.eoms.idp.utils.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.WebApplicationException;

@Path("/token")
@Produces(MediaType.APPLICATION_JSON)
public class TokenResource {
    private final static Logger LOGGER = LoggerFactory.getLogger(TokenResource.class);

    @GET
    @Timed
    public Signature getToken() {
        Signature signature = Token.create("1");

        if (signature.getCode() == 0) {
            return signature;
        }

        throw new WebApplicationException(signature.getToken(), Response.Status.EXPECTATION_FAILED);
    }

    @POST
    @Timed
    public SignatureVerification verifyToken() {
        String temp = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiIxIiwiZXhwIjoxNTMzNjM2OTMzLCJpYXQiOjE1MzM2MzY2MzN9.7YsQiUCP4eGNj_JnvIKYjfK8n7xqCG_3slHpqgOyUu0";
        SignatureVerification signatureVerification = Token.verify(temp);

        if (signatureVerification.getCode() == 0) {
            return signatureVerification;
        }

        throw new WebApplicationException(signatureVerification.getResult(), Response.Status.UNAUTHORIZED);
    }
}
