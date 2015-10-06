package com.polaris.cd.recommendation.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Strings;
import com.polaris.cd.recommendation.api.ErrorMessage;
import com.polaris.cd.recommendation.api.Recommendations;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

/**
 *
 */
@Path("recommendation")
@Produces(MediaType.APPLICATION_JSON)
public class RecommendationResource {


    private static final List<String> HOTELS = Arrays.asList(
            "Hotel 1",
            "Hotel 2",
            "Hotel 3",
            "Hotel A",
            "Hotel B",
            "Hotel C");

    @GET
    @Path("{destination}")
    @Timed(name = "recommendation.recommended", absolute = true)
    public Response recommended(@PathParam("destination") String destination) {
        if (Strings.isNullOrEmpty(destination)) {
            return Response.status(BAD_REQUEST)
                .entity(new ErrorMessage("param 'destination' is mandatory"))
                .build();
        }

        Random random = new Random();

        List<String> randomList = new ArrayList<>(HOTELS.size());
        randomList.addAll(HOTELS);
        Collections.shuffle(randomList);

        Recommendations recommendations = new Recommendations(destination, randomList.subList(0, random.nextInt(3) + 1));
        return Response.ok()
            .entity(recommendations)
            .build();
    }

}
