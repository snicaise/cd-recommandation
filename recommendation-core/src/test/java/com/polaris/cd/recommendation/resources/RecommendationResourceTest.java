package com.polaris.cd.recommendation.resources;

import com.polaris.cd.recommendation.api.Recommendations;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
public class RecommendationResourceTest {

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new RecommendationResource())
            .build();

    @Test
    public void recommendation_aDestination_returnRecommendations() throws Exception {
        Response response = resources.client()
            .target("/recommendation/Paris")
            .request().get();
        Recommendations recommendation = response.readEntity(Recommendations.class);

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(recommendation.getRecommendations())
            .isNotEmpty()
            .doesNotContainNull();
    }

    @Test
    public void recommendation_aDestination_returnSameDestination() throws Exception {
        Response response = resources.client()
            .target("/recommendation/Londres")
            .request().get();
        Recommendations recommendation = response.readEntity(Recommendations.class);

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(recommendation.getDestination()).isEqualTo("Londres");
    }

    @Test
    public void recommendation_aDestination_returnJson() throws Exception {
        Response response = resources.client()
            .target("/recommendation/Madrid")
            .request().get();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getMediaType()).isEqualTo(MediaType.APPLICATION_JSON_TYPE);
    }

}