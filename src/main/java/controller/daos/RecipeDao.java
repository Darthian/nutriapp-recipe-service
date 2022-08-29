package controller.daos;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import controller.entities.RecipeEntity;
import controller.requestModel.RecipeSchema;

import java.util.List;

public interface RecipeDao {
  void createRecipe(RecipeSchema recipe, LambdaLogger logger);
  List<RecipeEntity> gerRecipesByName(String name, LambdaLogger logger);
}
