package me.abner.mp.custom;


import jakarta.annotation.security.DeclareRoles;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.auth.LoginConfig;

import java.util.Set;

@LoginConfig(authMethod = "MP-JWT")
@ApplicationScoped
@DeclareRoles({  "USERX" ,"DEVELOPERX","appuserroleX"}) //
                                                        // Para funcionar as roles, tive que alterar dentro do
                                                        // keycloak: Client scopes -> Client scope details -> Mapper details
                                                        // (User Realm Role: Campo Token Claim Name, trocado os valores de realms_acess.roles para groups )
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(HelloWorldEndpoint.class);
    }

}