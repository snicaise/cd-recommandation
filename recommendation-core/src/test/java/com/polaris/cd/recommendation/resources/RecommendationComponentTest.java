package com.polaris.cd.recommendation.resources;

import com.jayway.restassured.http.ContentType;
import com.polaris.cd.ComponentTests;
import com.polaris.cd.recommendation.RecommendationApplication;
import com.polaris.cd.recommendation.RecommendationConfiguration;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

/**
 *
 */
@Category(ComponentTests.class)
public class RecommendationComponentTest {

    @ClassRule
    public static final DropwizardAppRule<RecommendationConfiguration> RULE =
            new DropwizardAppRule<>(
                    RecommendationApplication.class,
                    ResourceHelpers.resourceFilePath("test-server.yml")
            );

    @Test
    public void recommendation_anUser_returnRecommendation() {
        given()
            .port(RULE.getLocalPort())
            .pathParam("destination", "paris")
        .when()
            .get("/recommendation/{destination}")
        .then()
            .log().all().and()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("destination", equalTo("paris"))
            .body("recommendations.size()", greaterThan(0));
    }

}