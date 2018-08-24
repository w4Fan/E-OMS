package com.fans.eoms.idp;

import com.fans.eoms.idp.api.CrossDomainFilter;
import com.fans.eoms.idp.db.DictionaryDAO;
import com.fans.eoms.idp.db.MenuDAO;
import com.fans.eoms.idp.db.RoleDAO;
import com.fans.eoms.idp.resources.DictionaryResource;
import com.fans.eoms.idp.resources.RoleResource;
import com.fans.eoms.idp.resources.TokenResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.jdbi.bundles.DBIExceptionsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class EomsIdpApplication extends Application<EomsIdpConfiguration> {
    public static void main(final String[] args) throws Exception {
        new EomsIdpApplication().run(args);
    }

    @Override
    public String getName() {
        return "E-OMS IDP";
    }

    @Override
    public void initialize(Bootstrap<EomsIdpConfiguration> bootstrap) {
        bootstrap.addBundle(new DBIExceptionsBundle());
    }

    @Override
    public void run(EomsIdpConfiguration eomsIdpConfiguration, Environment environment) throws Exception {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, eomsIdpConfiguration.getDataSourceFactory(), "postgresql");
        final DictionaryDAO dictionaryDAO = jdbi.onDemand(DictionaryDAO.class);
        final RoleDAO roleDAO = jdbi.onDemand(RoleDAO.class);
        final MenuDAO menuDAO = jdbi.onDemand(MenuDAO.class);

        environment.jersey().register(CrossDomainFilter.class);
        environment.jersey().register(new TokenResource());
        environment.jersey().register(new DictionaryResource(dictionaryDAO));
        environment.jersey().register(new RoleResource(roleDAO, dictionaryDAO, menuDAO));
    }
}
