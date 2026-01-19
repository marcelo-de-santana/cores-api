package ada.tech.controller;

import ada.tech.domain.entity.ColorEntity;
import ada.tech.domain.entity.ProductEntity;
import ada.tech.domain.repository.ColorRepository;
import ada.tech.domain.repository.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Path("/colors/{colorId}/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final ColorRepository colorRepository;

    @GET
    public Response findAll(@PathParam("colorId") Long colorId) {

        ColorEntity colorEntity = colorRepository.findById(colorId);
        if (colorEntity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        var query = productRepository.find("mainColor", colorEntity);
        List<ProductEntity> products = query.list();
        return Response.ok(products).build();

    }

    @POST
    @Transactional
    private Response save(@PathParam("colorId") Long colorId, ProductEntity product) {
        ColorEntity colorEntity = colorRepository.findById(colorId);
        if (colorEntity == null) {
            return Response.status((Response.Status.NOT_FOUND)).build();
        }
        productRepository.persist(product);
        return Response.ok(product).build(); //TODO CREATED instead of OK
    }

    @PUT
    public Response update() {
        var product = productRepository.findAll().list().get(0);
        if (product != null) {
            System.out.println(product.getName());
            System.out.println(product.getMainColor());
        }
        return Response.ok(product).build();
    }
}