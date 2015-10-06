package com.polaris.cd.recommendation;

import com.codahale.metrics.health.HealthCheck;
import com.polaris.cd.recommendation.resources.RecommendationResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 *
 */
public class RecommendationApplication extends Application<RecommendationConfiguration> {

    public static void main(String[] args) throws Exception {
        new RecommendationApplication().run(args);
    }

    @Override
    public String getName() {
        return "recommendation";
    }

    @Override
    public void initialize(Bootstrap<RecommendationConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );
    }

    @Override
    public void run(RecommendationConfiguration configuration, Environment environment) {
        environment.healthChecks().register("recommendation", new HealthCheck() {
            @Override
            protected Result check() throws Exception {
                return Result.healthy();
            }
        });

        environment.jersey().register(new RecommendationResource());
    }
}
