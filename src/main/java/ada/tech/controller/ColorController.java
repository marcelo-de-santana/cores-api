package ada.tech.controller;

import ada.tech.controller.request.ColorRequest;
import ada.tech.controller.response.ColorResponse;
import ada.tech.controller.response.ErrorResponse;
import ada.tech.domain.entity.ColorEntity;
import ada.tech.mapper.ColorMapper;
import ada.tech.service.ColorService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import static ada.tech.mapper.ColorMapper.toResponse;

@Path("/colors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class ColorController {

    private final ColorService colorService;
    private final Validator validator;

    @GET
    public Response findAll() {
        return Response.ok(colorService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        ColorResponse colorResponse = colorService.findById(id);

        if (colorResponse == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(colorResponse).build();

    }

    @POST
    public Response save(@Valid ColorRequest request) {
        ColorResponse colorResponse = colorService.save(request);

        return Response.status(Response.Status.CREATED).entity(colorResponse).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, ColorRequest request) {
        Set<ConstraintViolation<ColorRequest>> violations = validator.validate(request);

        if (!violations.isEmpty()){
            ErrorResponse errorResponse = ErrorResponse.fromViolations(violations);

            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(errorResponse)
                    .build();
        }


        colorService.update(id, request);

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        if (colorService.delete(id)) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }


}
