package com.fans.eoms.idp.resources;

import com.codahale.metrics.annotation.Timed;
import com.fans.eoms.idp.api.FunctionDictionary;
import com.fans.eoms.idp.api.RoleList;
import com.fans.eoms.idp.core.Menu;
import com.fans.eoms.idp.core.Role;
import com.fans.eoms.idp.db.DictionaryDAO;
import com.fans.eoms.idp.db.MenuDAO;
import com.fans.eoms.idp.db.RoleDAO;
import org.skife.jdbi.v2.exceptions.DBIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("/role")
@Produces(MediaType.APPLICATION_JSON)
public class RoleResource {
    private final static Logger LOGGER = LoggerFactory.getLogger(DictionaryResource.class);
    private RoleDAO roleDAO;
    private DictionaryDAO dictionaryDAO;
    private MenuDAO menuDAO;

    public RoleResource() {
    }

    public RoleResource(RoleDAO roleDAO, DictionaryDAO dictionaryDAO, MenuDAO menuDAO) {
        this.roleDAO = roleDAO;
        this.dictionaryDAO = dictionaryDAO;
        this.menuDAO = menuDAO;
    }

    @GET
    @Timed
    public Response list() {
        Map<String, Object> result = new HashMap<String, Object>();

        try {
            List<RoleList> roles = roleDAO.list();
            List<FunctionDictionary> functionDictionary = dictionaryDAO.functionDictionary();
            result.put("roles", roles);
            result.put("functionDictionary", functionDictionary);

            return Response.status(Response.Status.OK).entity(result).build();

        } catch (DBIException ex) {
            LOGGER.debug("Exception: {}", ex);
            throw new WebApplicationException(Response.Status.EXPECTATION_FAILED);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Timed
    public Response create(@NotNull @Valid Role role) {
        LOGGER.debug("role: {}", role);
        String[] menuRootIds = role.getFunctionIds().split(",");
        List<String> menuIds = new ArrayList<String>();
        List<String> operatingIds = new ArrayList<String>();

        try {
            for (String menuRootId : menuRootIds) {
                List<Menu> menus = menuDAO.findMenuByRootId(menuRootId);

                menus.forEach(menu -> {
                    menuIds.add(menu.getId());
                    operatingIds.add(menu.getOperatingIds());
                });
            }

            LOGGER.debug("menuIds: {}", menuIds);
            LOGGER.debug("operatingIds: {}", operatingIds);

            roleDAO.insert(
                    role.getOrganizationId(),
                    role.getName(),
                    role.getAdmin(),
                    role.getFunctionIds(),
                    role.getRankId(),
                    role.getDescription(),
                    menuIds, operatingIds);

            return Response.noContent().status(Response.Status.CREATED).build();
        } catch (DBIException ex) {
            LOGGER.debug("Exception: {}", ex);
            throw new WebApplicationException(Response.Status.EXPECTATION_FAILED);
        }
    }

    @GET
    @Path("/{id}")
    @Timed
    public Response getRole(@PathParam("id") String id){
//        Role role = roleDAO.get(id);
//        return Response.ok(role).build();
        Optional<Role> role = roleDAO.get(id);

        if (role.isPresent()) {
            return Response.ok(role.get()).build();
        }

        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
}
