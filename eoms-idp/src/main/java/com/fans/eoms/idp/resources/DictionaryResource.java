package com.fans.eoms.idp.resources;

import com.codahale.metrics.annotation.Timed;
import com.fans.eoms.idp.api.FunctionDictionary;
import com.fans.eoms.idp.api.RankDictionary;
import com.fans.eoms.idp.db.DictionaryDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/dictionary")
@Produces(MediaType.APPLICATION_JSON)
public class DictionaryResource {
    private final static Logger LOGGER = LoggerFactory.getLogger(DictionaryResource.class);
    private DictionaryDAO dictionaryDAO;

    public DictionaryResource() {
    }

    public DictionaryResource(DictionaryDAO dictionaryDAO) {
        this.dictionaryDAO = dictionaryDAO;
    }

    @GET
    @Path("/rank")
    @Timed
    public List<RankDictionary> getRankList() {
        return dictionaryDAO.rankDictionary();
    }

    @GET
    @Path("/function")
    @Timed
    public List<FunctionDictionary> getFunctionList() {
        return dictionaryDAO.functionDictionary();
    }
}
