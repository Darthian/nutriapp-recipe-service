package controller.daos;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import controller.entities.RecipeEntity;
import controller.requestModel.RecipeSchema;

import java.util.List;

public interface RecipeDao {
  void createRecipe(RecipeSchema recipe);
  List<RecipeEntity> gerRecipesByName(String name);
}
