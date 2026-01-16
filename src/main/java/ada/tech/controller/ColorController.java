package ada.tech.controller;

import ada.tech.controller.request.ColorRequest;
import ada.tech.mapper.ColorMapper;
import ada.tech.service.ColorService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/colors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class ColorController {

    private ColorService colorService;

    @GET
    public Response findAll() {
        return Response.ok(
                        colorService.findAll()
                                .stream().map(ColorMapper::toResponse)
                                .toList())
                .build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        var color = colorService.findById(id);

        if (color == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(color).build();

    }

    @POST
    public Response save(ColorRequest request) {

        var entity = colorService.save(request);
        return Response
                .status(Response.Status.CREATED)
                .entity(request)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        colorService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, ColorRequest dto) {
        colorService.update(id, dto);
        return Response.ok().build();
    }

}
