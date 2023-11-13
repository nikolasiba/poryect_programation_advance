package co.edu.uniquindio.proyecto.Utils;

import co.edu.uniquindio.proyecto.Dto.MessageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FilterToken implements Filter {
    private final JWTUtils jwtUtils;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String token = getToken(req);
        String requestURI = req.getRequestURI();
        boolean error = true;

        try {

            if (
//                    requestURI.startsWith("/api/patient") ||
                    requestURI.startsWith("/api/doctor") ||
                    requestURI.startsWith("/api/admin")) {

                if (token != null) {

                    Jws<Claims> jws = jwtUtils.parseJwt(token);
                    if (
                            (requestURI.startsWith("/api/patient") &&
                                    !jws.getBody().get("rol").equals("patient")) ||
                            (requestURI.startsWith("/api/doctor") &&
                                            !jws.getBody().get("rol").equals("doctor")) ||
                            (requestURI.startsWith("api/admin") &&
                                            !jws.getBody().get("rol").equals("admin"))) {

                        createErrorAnswer("you don't have the permissions",
                                HttpServletResponse.SC_FORBIDDEN, res);

                    } else {
                        error = false;
                    }

                } else {
                    createErrorAnswer("There are not token",
                            HttpServletResponse.SC_FORBIDDEN, res);
                }

            } else {
                error = false;
            }

        }catch (MalformedJwtException | SignatureException exception) {
            createErrorAnswer("Token is incorrect",
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR, res);
        } catch (ExpiredJwtException e) {
            createErrorAnswer("Token is expired",
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR, res);
        } catch (Exception e) {
            createErrorAnswer(e.getMessage(),
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR, res);
        }
        if (!error) {
            chain.doFilter(request, response);
        }
    }

    private void createErrorAnswer(String message, int errorCode, HttpServletResponse response) throws IOException {
        MessageDTO<String> dto = new MessageDTO<>(true, message);
        response.setContentType("application/json");
        response.setStatus(errorCode);
        response.getWriter().write(new ObjectMapper().writeValueAsString(dto));
        response.getWriter().flush();
        response.getWriter().close();

    }

    private String getToken(HttpServletRequest req) {
        String header = req.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer "))
            return header.replace("Bearer ", "");
        return null;
    }
}