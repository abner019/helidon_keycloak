package me.abner.mp.custom;


import io.helidon.microprofile.cors.CrossOrigin;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import me.abner.mp.custom.exception.CustomExceptionEntity;
import org.eclipse.microprofile.jwt.Claim;

import java.security.Principal;
import java.util.Collections;
import java.util.logging.Logger;


@ApplicationScoped
@Path("/")

public class HelloWorldEndpoint {

    @Inject
    @Claim("email")
    private String gEmail;

    @Inject
    @Claim("preferred_username")
    private String preferred_username;

    @Inject
    @Claim("realm_access")
    private JsonObject realm_access;

    @Inject
    @Claim("domain")
    private Long domain;

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());
    Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * The greeting message provider.
     */

    /**
     * Return a worldly greeting message.
     *
     * @return {@link JsonObject}
     */
    @GET
    @Path("/hello")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"USER" ,"DEVELOPER","appuserrole"})
    public Response getDefaultMessage(@HeaderParam("Host") String host) {
        logger.info("Incoming from host " + host + " ! ");
        JsonObject retJ = JSON.createObjectBuilder()
                .add("hello", "world")
                .build();
        return Response
                .status(200)
                .entity(retJ)
                .build();
    }

    @OPTIONS
    @CrossOrigin(
            //value = {"http://localhost:8000","http://127.0.0.1:8000"},
            //allowHeaders = {"Accept","Accept-Encoding"},
            value = {"*"},
            allowMethods = {HttpMethod.GET})
    public void optionsForGetDefaultMessage() {}


    private JsonObject createResponse(String who) {
        String msg = String.format("%s %s!", "yada", who);

        return JSON.createObjectBuilder()
                .add("message", msg)
                .build();
    }

    /*@GET
    @Path("/jwtroles")
    @Produces(MediaType.APPLICATION_JSON)
    //@RolesAllowed({ "admin", "user" })
    public String getRoles() {
        System.out.println("abner_bessi");
        System.out.println(realm_access);
        return roles.toString();
    }*/

    @GET
    @Path("/getPrincipal")
    @Produces(MediaType.APPLICATION_JSON)
    //@RolesAllowed({ "admin", "user" })
    public Principal getPrincipal(@Context SecurityContext sec) {
        Principal user = sec.getUserPrincipal();
        return user;
    }

    @GET
    @Path("/SecurityContext")
    @Produces(MediaType.APPLICATION_JSON)
    //@RolesAllowed({ "admin", "user" })
    public SecurityContext getInfo(@Context SecurityContext sec) {
        //Principal user = sec.getUserPrincipal();
        return sec;
    }

    @GET
    @Path("/globalInfo")
    @Produces(MediaType.APPLICATION_JSON)
    //@RolesAllowed({ "admin", "user" })
    public String getInfoConcat(@Context SecurityContext sec) {
        System.out.println(realm_access);
        return "email:" + gEmail + " preferred_username:" + preferred_username +  " domain:" + domain ;
    }


    @GET
    @Path("/getCustomException")
    @Produces(MediaType.APPLICATION_JSON)
    //@RolesAllowed({ "admin", "user" })
    public String getCustomException() throws CustomExceptionEntity {


            throw new CustomExceptionEntity("SSMA_PERMISSIONS_GROUPS.MSG.GROUP_USED");

    }


}