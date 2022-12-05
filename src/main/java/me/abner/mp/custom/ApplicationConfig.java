package me.abner.mp.custom;


import jakarta.annotation.security.DeclareRoles;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
//import me.abner.mp.custom.exception.CustomExceptionMapper;
import me.abner.mp.custom.exception.CustomExceptionMapper;
import org.eclipse.microprofile.auth.LoginConfig;

import java.util.HashSet;
import java.util.Set;

@LoginConfig(authMethod = "MP-JWT")
@ApplicationScoped
@DeclareRoles({  "USER" ,"DEVELOPER","appuserrole"}) //adsfasd
                                                        // Para funcionar as roles, tive que alterar dentro do
                                                        // keycloak: Client scopes -> Client scope details -> Mapper details
                                                        // (User Realm Role: Campo Token Claim Name, trocado os valores de realms_acess.roles para groups )
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        //Set<Class<?>> classes =  Set.of(HelloWorldEndpoint.class);
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(HelloWorldEndpoint.class);
        classes.add(CustomExceptionMapper.class);
        return classes;
    }

}