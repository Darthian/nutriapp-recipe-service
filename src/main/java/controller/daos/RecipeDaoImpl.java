package controller.daos;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import controller.entities.RecipeEntity;
import controller.requestModel.RecipeSchema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecipeDaoImpl implements RecipeDao {

  private LambdaLogger logger;
  private static AmazonDynamoDB client;
  private DynamoDBMapper mapper;

  public RecipeDaoImpl(LambdaLogger logger) {
    this.logger = logger;
    client = AmazonDynamoDBClientBuilder.standard().build();
    mapper = new DynamoDBMapper(client);
  }

  public void createRecipe(RecipeSchema recipe) {
    List<Map<String, String>> ingredients = new ArrayList<>();
    recipe.getIngredients().stream().forEach(i -> {
      Map<String, String> ingredientMap = new HashMap<>();
      ingredientMap.put("idProduct", i.getIdProduct());
      ingredientMap.put("quantity", i.getQuantity());
      ingredientMap.put("unityMeasure", i.getUnityMeasure());
      ingredientMap.put("measure", String.valueOf(i.getMeasure()));
      ingredientMap.put("imageUrl", i.getImageUrl());
      ingredients.add(ingredientMap);
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
      recipeEntity.setIngredients(ingredients);
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
  public List<RecipeEntity> gerRecipesByName(String name) {
    logger.log("nameParameter:" + name);
    Map<String, AttributeValue> eav = new HashMap<>();
    eav.put(":filterName", new AttributeValue().withS(name));
    logger.log(eav.entrySet().stream().map(x -> x.getKey() + "," + x.getValue()).collect(Collectors.joining(",")));
    DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
        .withFilterExpression("nameRecipe contains :filterName").withExpressionAttributeValues(eav);
    logger.log(scanExpression.getFilterExpression());
    logger.log(scanExpression.getExpressionAttributeValues().entrySet().stream().map(x -> x.getKey() + "," + x.getValue()).collect(Collectors.joining(",")));
    List<RecipeEntity> resultSet = mapper.scan(RecipeEntity.class, scanExpression);
    logger.log("Number of results: " + resultSet.size());
    return resultSet;
  }
}
