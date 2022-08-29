package controller;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import controller.daos.RecipeDao;
import controller.daos.RecipeDaoImpl;
import controller.entities.RecipeEntity;
import controller.requestModel.RecipeSchema;
import utils.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

  private LambdaLogger logger;
  private RecipeDao recipeDao = new RecipeDaoImpl();

  @Override
  public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
    logger = context.getLogger();
    Map<String, String> headers = new HashMap<>();
    headers.put("Content-Type", "application/json");
    headers.put("X-Custom-Header", "application/json");

    APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
        .withHeaders(headers);

    String output = String.format("{ \"message\": \"Todo Melo\" }");
    try {
      switch (input.getHttpMethod()) {
        case "POST":
          createRecipe(input);
          break;
        case "GET":
          output = getRecipesByName(input);
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

  private void createRecipe(APIGatewayProxyRequestEvent input) {
    RecipeSchema recipeSchema = Mapper.getRecipeSchema(input.getBody());
    logger.log("Recipe: " + recipeSchema);
    recipeDao.createRecipe(recipeSchema, logger);
  }

  private String getRecipesByName(APIGatewayProxyRequestEvent input) {
    List<RecipeEntity> result = recipeDao.gerRecipesByName(input.getQueryStringParameters().get("name"), logger);
    return result.stream().map(Object::toString).collect(Collectors.joining(","));
  }
}
