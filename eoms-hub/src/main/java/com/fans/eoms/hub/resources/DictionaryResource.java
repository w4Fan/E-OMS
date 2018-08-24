package com.fans.eoms.hub.resources;

import com.codahale.metrics.annotation.Timed;
import com.fans.eoms.hub.db.OrganizationDAO;
import com.fans.eoms.hub.api.OrganizationDictionary;
import com.fans.eoms.hub.core.Organization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Path("/dictionary")
@Produces(MediaType.APPLICATION_JSON)
public class DictionaryResource {
    private final static Logger LOGGER = LoggerFactory.getLogger(DictionaryResource.class);
    private OrganizationDAO organizationDAO;

    public DictionaryResource() {
    }

    public DictionaryResource(OrganizationDAO organizationDAO) {
        this.organizationDAO = organizationDAO;
    }

    @GET
    @Path("/organization")
    @Timed
    public List getOrganizationList() {
        final Iterator<Organization> listIterator = organizationDAO.list().iterator();
        List<OrganizationDictionary> list = new ArrayList<OrganizationDictionary>();

        LOGGER.debug("result: {}", organizationDAO.list().size());

        while (listIterator.hasNext()) {
            OrganizationDictionary organizationDictionary = new OrganizationDictionary(listIterator.next());
            list.add(organizationDictionary);
        }

        return list;
    }
}
