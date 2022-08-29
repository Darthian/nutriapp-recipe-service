package controller.daos;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import controller.entities.RecipeEntity;
import controller.requestModel.RecipeSchema;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeDaoImpl implements RecipeDao {
  static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
  DynamoDBMapper mapper = new DynamoDBMapper(client);

  public void createRecipe(RecipeSchema recipe, LambdaLogger logger) {
    Map<String, String> ingredientsMap = new HashMap<>();
    recipe.getIngredients().stream().forEach(i -> {
      ingredientsMap.put("idProduct", i.getIdProduct());
      ingredientsMap.put("quantity", i.getQuantity());
      ingredientsMap.put("unityMeasure", i.getUnityMeasure());
      ingredientsMap.put("measure", String.valueOf(i.getMeasure()));
      ingredientsMap.put("imageUrl", i.getImageUrl());
    });
    try {
      RecipeEntity recipeEntity = new RecipeEntity();
      recipeEntity.setNameRecipe(recipe.getNameRecipe());
      recipeEntity.setTimeForPreparation(recipe.getTimeForPreparation());
      recipeEntity.setPortions(recipe.getPortions());
      recipeEntity.setTotalIngredients(recipe.getTotalIngredients());
      recipeEntity.setDescription(recipe.getDescription());
      recipeEntity.setImageUrl(recipe.getImageUrl());
      recipeEntity.setTags(recipe.getTags());
      recipeEntity.setIngredients(ingredientsMap);
      logger.log("Recipe Entity: " + recipeEntity);
      logger.log("Gonna save the data");
      mapper.save(recipeEntity);

    } catch (AmazonServiceException ase) {
      logger.log("Could not complete operation");
      logger.log("Error Message:  " + ase.getMessage());
      logger.log("HTTP Status:    " + ase.getStatusCode());
      logger.log("AWS Error Code: " + ase.getErrorCode());
      logger.log("Error Type:     " + ase.getErrorType());
      logger.log("Request ID:     " + ase.getRequestId());
      throw ase;

    } catch (AmazonClientException ace) {
      logger.log("Internal error occurred communicating with DynamoDB");
      logger.log("Error Message:  " + ace.getMessage());
      throw ace;
    }
  }

  @Override
  public List<RecipeEntity> gerRecipesByName(String name, LambdaLogger logger) {
    Map<String, AttributeValue> eav = new HashMap<>();
    eav.put(":nameRecipe", new AttributeValue().withS(name));

    DynamoDBQueryExpression<RecipeEntity> queryExpression = new DynamoDBQueryExpression<RecipeEntity>()
        .withKeyConditionExpression("nameRecipe contains :nameRecipe").withExpressionAttributeValues(eav);

    return mapper.query(RecipeEntity.class, queryExpression);
  }
}
