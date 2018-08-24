package com.fans.eoms.hub;

import com.fans.eoms.hub.api.CrossDomainFilter;
import com.fans.eoms.hub.db.OrganizationDAO;
import com.fans.eoms.hub.resources.DictionaryResource;
import com.fans.eoms.hub.resources.OrganizationResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.jdbi.bundles.DBIExceptionsBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class EomsHubApplication extends Application<EomsHubConfiguration> {
    public static void main(final String[] args) throws Exception {
        new EomsHubApplication().run(args);
    }

    @Override
    public String getName() {
        return "E-OMS HUB";
    }

    @Override
    public void initialize(Bootstrap<EomsHubConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<EomsHubConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(EomsHubConfiguration eomsIdpConfiguration) {
                return eomsIdpConfiguration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(new DBIExceptionsBundle());
    }

    @Override
    public void run(EomsHubConfiguration eomsIdpConfiguration, Environment environment) throws Exception {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, eomsIdpConfiguration.getDataSourceFactory(), "postgresql");
        final OrganizationDAO organizationDAO = jdbi.onDemand(OrganizationDAO.class);

        environment.jersey().register(CrossDomainFilter.class);
        environment.jersey().register(new DictionaryResource(organizationDAO));
        environment.jersey().register(new OrganizationResource(organizationDAO));
    }
}
