package controller.service;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;

public interface RecipeService {
  void createRecipe(String body);

  String getRecipesByName(String name);
}
