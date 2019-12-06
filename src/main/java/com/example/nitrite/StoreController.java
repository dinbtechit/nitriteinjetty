package com.example.nitrite;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/db")
public class StoreController {

    @GET
    @Path("/memory/insert")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertIntoMemory() {
        DatabaseConnection db = DatabaseConnection.getMemoryInstance();
        Employee employee = new Employee( UUID.randomUUID().toString(), "From Memory. 123, North Pole.");
        return  Response.ok(db.insertIntoMemory(employee)).build();
    }


    @GET
    @Path("/file/insert")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertIntoFile() {
        DatabaseConnection db = DatabaseConnection.getFileInstance();
        Employee employee = new Employee( UUID.randomUUID().toString(), "From file. 123, North Pole.");
        return  Response.ok(db.insertIntoFile(employee)).build();
    }


    @GET
    @Path("/memory/find/{offset}/{size}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findFromMemory(@PathParam("offset") int offset, @PathParam("size") int size) {
        DatabaseConnection db = DatabaseConnection.getMemoryInstance();
        return Response.ok(db.findFromMemory(offset, size)).build();
    }

    @GET
    @Path("/file/find/{offset}/{size}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findFromFile(@PathParam("offset") int offset, @PathParam("size") int size) {
        DatabaseConnection db = DatabaseConnection.getFileInstance();
        return Response.ok(db.findFromFile(offset, size)).build();
    }

}
