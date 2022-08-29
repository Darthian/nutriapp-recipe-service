package controller.service;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.google.gson.Gson;
import controller.daos.RecipeDao;
import controller.daos.RecipeDaoImpl;
import controller.entities.RecipeEntity;
import controller.requestModel.RecipeSchema;
import utils.Mapper;

import java.util.List;
import java.util.stream.Collectors;

public class RecipeServiceImpl implements RecipeService {

  private LambdaLogger logger;
  private RecipeDao recipeDao;
  private Gson gsonMapper;

  public RecipeServiceImpl(LambdaLogger logger, RecipeDao recipeDao) {
    this.logger = logger;
    this.recipeDao = recipeDao;
    gsonMapper = new Gson();
  }

  public void createRecipe(String body) {
    RecipeSchema recipeSchema = Mapper.getRecipeSchema(body);
    logger.log("Recipe: " + recipeSchema);
    recipeDao.createRecipe(recipeSchema);
  }

  public String getRecipesByName(String name) {
    List<RecipeEntity> result = recipeDao.gerRecipesByName(name);
    return gsonMapper.toJson(result);
  }
}
