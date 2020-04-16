package web;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.KeyGenerator;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import model.LoginSelect;


@Path("/users")
@RequestScoped
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class UserEndpoint {
	@Context
    private UriInfo uriInfo;
	
    @Inject
    private KeyGenerator keyGenerator;
    
    @POST
    @Path("/login")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("Username") String Username,
                                     @FormParam("Password") String Password) {
        try {
            // Authenticate the user using the credentials provided
            //authenticate(Username, Password);
        	LoginSelect loginSelect = new LoginSelect();			
			if(loginSelect.loginVal(Username, Password) == 1){
	        }
            // Issue a token for the user
            String token = issueToken(Username);
            // Return the token on the response
            return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();
        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).build();
        }
    }
    private String issueToken(String Username) {
        Key key = keyGenerator.generateKey();
        String jwtToken = Jwts.builder()
                .setSubject(Username)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        return jwtToken;
    }
    
    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}