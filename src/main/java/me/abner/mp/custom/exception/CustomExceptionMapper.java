package me.abner.mp.custom.exception;


import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;


@Provider
public class CustomExceptionMapper implements ExceptionMapper<CustomExceptionEntity> {
    public CustomExceptionMapper() {
        super();
    }

    public Response toResponse(CustomExceptionEntity customException) {

        System.out.println("abner0001:" +  customException.getMessage() );
        String ret = "{\n" +
                "    \"message\": \"" + customException.getMessage() + "\"";

        if(customException.getFlexValues() != null && customException.getFlexValues().length > 0) {
            ret += ",\n    \"flexValues\": {";
            int i = 0;

            for(String flex : customException.getFlexValues()) {
                i++;

                if(flex == null) {
                    flex = "";
                }

                String s = (i <= 9) ? "0" + i : "" + i;

                if(i > 1) {
                    ret += ",";
                }

                ret += "\n        \"flex_" + s + "\": \"" + flex.replace("\"", "\\\"").replace("\n", "<br>") + "\"";
            }

            ret += "\n    }";
        }

        ret += "\n}";

        return Response.status(560)
                .entity(ret)
                .type("application/json")
                .build();

      ///  return Response.status(500).entity("teste "+customException.getMessage()).type("text/plain").build();
    }
}



