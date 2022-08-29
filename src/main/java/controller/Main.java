package controller;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import controller.daos.RecipeDaoImpl;
import controller.service.RecipeService;
import controller.service.RecipeServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class Main implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

  private LambdaLogger logger;
  private RecipeService recipeService;


  @Override
  public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
    logger = context.getLogger();
    recipeService = new RecipeServiceImpl(logger, new RecipeDaoImpl(logger));
    Map<String, String> headers = new HashMap<>();
    headers.put("Content-Type", "application/json");
    headers.put("X-Custom-Header", "application/json");

    APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
        .withHeaders(headers);

    String output = String.format("{ \"message\": \"Todo Melo\" }");
    try {
      switch (input.getHttpMethod()) {
        case "POST":
          recipeService.createRecipe(input.getBody());
          break;
        case "GET":
          String nameParameter = input.getQueryStringParameters().get("name");
          output = recipeService.getRecipesByName(nameParameter);
          break;
      }

    } catch (Exception e) {
      response
          .withStatusCode(500)
          .withBody(e.getMessage());
    }

    return response
        .withStatusCode(200)
        .withBody(output);
  }
}
