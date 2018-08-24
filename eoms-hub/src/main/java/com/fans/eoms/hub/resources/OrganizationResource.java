package com.fans.eoms.hub.resources;

import com.codahale.metrics.annotation.Timed;
import com.fans.eoms.hub.core.Organization;
import com.fans.eoms.hub.db.OrganizationDAO;
import org.skife.jdbi.v2.exceptions.DBIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/organization")
@Produces(MediaType.APPLICATION_JSON)
public class OrganizationResource {
    private final static Logger LOGGER = LoggerFactory.getLogger(OrganizationResource.class);
    private OrganizationDAO organizationDAO;

    public OrganizationResource() {
    }

    public OrganizationResource(OrganizationDAO organizationDAO) {
        this.organizationDAO = organizationDAO;
    }

    @GET
    @Timed
    public List list() {
        return organizationDAO.list();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Timed
    public Response create(@NotNull @Valid Organization organization) {
        LOGGER.debug(organization.toString());

        try {
            organizationDAO.insert(
                    organization.getRoot_id(),
                    organization.getParent_id(),
                    organization.getName(),
                    organization.getProvince(),
                    organization.getCity(),
                    organization.getDistrict(),
                    organization.getAddress(),
                    organization.getOwner(),
                    organization.getPhone());

            LOGGER.debug("create organization is done !");
            return Response.noContent().status(Response.Status.CREATED).build();
        } catch (DBIException ex) {
            LOGGER.error("create organization error: {}", ex);
            return Response.noContent().status(Response.Status.EXPECTATION_FAILED).build();
        }
    }

    @GET
    @Path("/{id}")
    @Timed
    public Response detail(@PathParam("id") int id) {
        LOGGER.debug("organization id is {}", id);
        LOGGER.debug("result: {}", organizationDAO.get(id).isPresent());

        Optional<Organization> result = organizationDAO.get(id);

        if (result.isPresent()) {
            return Response.ok(result.get()).build();
        }

        return Response.noContent().status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Timed
    public Response update(@PathParam("id") int id, @NotNull @Valid Organization organization) {
        LOGGER.debug("organization id is {}", id);
        LOGGER.debug(organization.toString());

        try {
            organizationDAO.update(
                    id,
                    organization.getName(),
                    organization.getProvince(),
                    organization.getCity(),
                    organization.getDistrict(),
                    organization.getAddress(),
                    organization.getOwner(),
                    organization.getPhone());

            LOGGER.debug("update organization is done !");
            return Response.noContent().status(Response.Status.NO_CONTENT).build();
        } catch (DBIException ex) {
            LOGGER.error("update organization error: {}", ex);
            return Response.noContent().status(Response.Status.EXPECTATION_FAILED).build();
        }
    }

    ;

    @DELETE
    @Path("/{id}")
    @Timed
    public Response delete(@PathParam("id") int id) {
        LOGGER.debug("organization id is {}", id);

        try {
            int childrenCount = organizationDAO.findChilrenCount(id);

            if (childrenCount == 0) {
                organizationDAO.delete(id);
                return Response.noContent().status(Response.Status.OK).build();
            }
            
            throw new WebApplicationException("This organization has chilren, Do not allow deletion .", Response.Status.METHOD_NOT_ALLOWED);
        } catch (DBIException ex) {
            LOGGER.error("update organization error: {}", ex);
            return Response.noContent().status(Response.Status.EXPECTATION_FAILED).build();
        }
    }
}
