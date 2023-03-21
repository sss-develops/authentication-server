package project.goorm.authentication.business.web.member.presentation;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CookieUtils {

    private static final String SESSION_ID = "SESSION_ID";
    private static final String INVALID_SESSION_ID = "INVALID_SESSION_ID";
    private static final long FIFTEEN_MINUTE = 60 * 15;

    public String createCookie(String session) {
        return ResponseCookie.from(SESSION_ID, session)
                .path("/")
                .domain("SSS")
                .httpOnly(true)
                .secure(true)
                .sameSite("Lax")
                .maxAge(FIFTEEN_MINUTE)
                .build()
                .toString();
    }

    public String createExpiredCookie() {
        return ResponseCookie.from(SESSION_ID, "")
                .path("/")
                .domain("SSS")
                .httpOnly(true)
                .secure(true)
                .sameSite("Lax")
                .maxAge(0)
                .build()
                .toString();
    }

    public static String createInvalidSessionCookie() {
        return ResponseCookie.from(INVALID_SESSION_ID, UUID.randomUUID().toString())
                .path("/")
                .domain("SSS")
                .httpOnly(true)
                .secure(true)
                .sameSite("Lax")
                .maxAge(1800)
                .build()
                .toString();
    }
}
