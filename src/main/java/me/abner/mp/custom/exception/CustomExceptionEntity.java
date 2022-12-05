package me.abner.mp.custom.exception;

import jakarta.ws.rs.WebApplicationException;

public class CustomExceptionEntity extends WebApplicationException   {
    private String[] flexValues;

    public CustomExceptionEntity() {
        this.flexValues = new String[]{ };
    }

    public CustomExceptionEntity(String message) {
        this.flexValues = new String[]{ };
    }

    public CustomExceptionEntity(String message, String ... flexValues) {
        this.flexValues = flexValues;
    }

    public void setFlexValues(String[] flexValues) {
        this.flexValues = flexValues;
    }

    public String[] getFlexValues() {
        return flexValues;
    }
}
