package evovital.uniquindio.edu.co.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import evovital.uniquindio.edu.co.dto.auxiliar.MensajeDTO;
import evovital.uniquindio.edu.co.util.JWTUtils;
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
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FiltroToken extends OncePerRequestFilter {

    private final JWTUtils jwtUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, Content-Type, Authorization");
                response.addHeader("Access-Control-Allow-Credentials", "true");
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        }else {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            String requestURI = req.getRequestURI();
            String token = getToken(req);
            boolean error = true;
            try {
                if (requestURI.startsWith("/api/paciente") || requestURI.startsWith("/api/medico")

                        || requestURI.startsWith("/api/administrador")) {

                    if (token != null) {
                        Jws<Claims> jws = jwtUtils.parseJwt(token);

                        if (
                                (requestURI.startsWith("/api/paciente") &&

                                        !jws.getBody().get("rol").equals("paciente")) ||

                                        (  requestURI.startsWith("/api/medico") &&

                                                !jws.getBody().get("rol").equals("medico")  ) ||

                                        (requestURI.startsWith("/api/administrador") &&

                                                !jws.getBody().get("rol").equals("admin"))) {

                            crearRespuestaError("No tiene los permisos para acceder a este recurso",

                                    HttpServletResponse.SC_FORBIDDEN, res);

                        } else {
                            error = false;
                        }
                    } else {
                        crearRespuestaError("No hay un Token", HttpServletResponse.SC_FORBIDDEN,

                                res);

                    }
                } else {
                    error = false;
                }
            } catch (MalformedJwtException | SignatureException e) {
                crearRespuestaError("El token es incorrecto",
                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR, res);
            } catch (ExpiredJwtException e) {
                crearRespuestaError("El token está vencido",
                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR, res);
            } catch (Exception e) {
                crearRespuestaError(e.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR,

                        res);
            }
            if (!error) {
                chain.doFilter(request, response);
            }
        }




    }

    private String getToken(HttpServletRequest req) {
        String header = req.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer "))
            return header.replace("Bearer ", "");
        return null;
    }

    private void crearRespuestaError(String mensaje, int codigoError, HttpServletResponse
            response) throws IOException {
        MensajeDTO<String> dto = new MensajeDTO<>(true, mensaje);
        response.setContentType("application/json");
        response.setStatus(codigoError);
        response.getWriter().write(new ObjectMapper().writeValueAsString(dto));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
