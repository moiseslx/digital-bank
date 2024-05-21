package br.com.cdb.digitalbank.service.authentication;

import br.com.cdb.digitalbank.model.Account;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiration}")
    private Long expiration;

    public DecodedJWT decode(String token) {
        return JWT.require(Algorithm.HMAC256(secret))
                .withIssuer(issuer)
                .build()
                .verify(token);
    }

    public String encode(Account account) {
        return String.format("%s %s", "Bearer", JWT.create()
                .withIssuer(issuer)
                .withSubject(account.getId().toString())
                .withClaim("password", account.getPassword())
                .withExpiresAt(Instant.now().plusSeconds(expiration))
                .sign(Algorithm.HMAC256(secret)));
    }
}
