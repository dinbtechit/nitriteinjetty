package com.example.nitrite;


import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;


@ApplicationPath("/api")
public class RestApplication extends ResourceConfig {

    public RestApplication() {
        // Register resources and providers using package-scanning.
        packages(this.getClass().getPackage().getName());
    }

}