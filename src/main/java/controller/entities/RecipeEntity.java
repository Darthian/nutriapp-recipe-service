package controller.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.List;
import java.util.Map;

@DynamoDBTable(tableName = "recipe")
public class RecipeEntity {

  @DynamoDBGeneratedUuid(DynamoDBAutoGenerateStrategy.CREATE)
  private String idRecipe;
  private String nameRecipe;
  private String timeForPreparation;
  private Integer portions;
  private Integer totalIngredients;
  private String description;
  private String imageUrl;
  private List<String> tags;
  private List<Map<String, String>> ingredients;

  @DynamoDBHashKey(attributeName = "idRecipe")
  @DynamoDBAutoGeneratedKey
  public String getIdRecipe() {
    return idRecipe;
  }

  public void setIdRecipe(String idRecipe) {
    this.idRecipe = idRecipe;
  }

  @DynamoDBAttribute(attributeName = "nameRecipe")
  public String getNameRecipe() {
    return nameRecipe;
  }

  public void setNameRecipe(String nameRecipe) {
    this.nameRecipe = nameRecipe;
  }

  @DynamoDBAttribute(attributeName = "TimeForPreparation")
  public String getTimeForPreparation() {
    return timeForPreparation;
  }

  public void setTimeForPreparation(String timeForPreparation) {
    this.timeForPreparation = timeForPreparation;
  }

  @DynamoDBAttribute(attributeName = "portions")
  public Integer getPortions() {
    return portions;
  }

  public void setPortions(Integer portions) {
    this.portions = portions;
  }

  @DynamoDBAttribute(attributeName = "totalIngredients")
  public Integer getTotalIngredients() {
    return totalIngredients;
  }

  public void setTotalIngredients(Integer totalIngredients) {
    this.totalIngredients = totalIngredients;
  }

  @DynamoDBAttribute(attributeName = "description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @DynamoDBAttribute(attributeName = "imageUrl")
  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  @DynamoDBAttribute(attributeName = "tags")
  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  @DynamoDBAttribute(attributeName = "ingredients")
  public List<Map<String, String>> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<Map<String, String>> ingredients) {
    this.ingredients = ingredients;
  }

}
