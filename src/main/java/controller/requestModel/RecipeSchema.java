package controller.requestModel;

import java.util.List;

public class RecipeSchema {
  private String idRecipe;
  private String nameRecipe;
  private String timeForPreparation;
  private Integer portions;
  private Integer totalIngredients;
  private String description;
  private String imageUrl;
  private List<String> tags;
  private List<IngredientSchema> ingredients;

  public String getIdRecipe() {
    return idRecipe;
  }

  public void setIdRecipe(String idRecipe) {
    this.idRecipe = idRecipe;
  }

  public String getNameRecipe() {
    return nameRecipe;
  }

  public void setNameRecipe(String nameRecipe) {
    this.nameRecipe = nameRecipe;
  }

  public String getTimeForPreparation() {
    return timeForPreparation;
  }

  public void setTimeForPreparation(String timeForPreparation) {
    this.timeForPreparation = timeForPreparation;
  }

  public Integer getPortions() {
    return portions;
  }

  public void setPortions(Integer portions) {
    this.portions = portions;
  }

  public Integer getTotalIngredients() {
    return totalIngredients;
  }

  public void setTotalIngredients(Integer totalIngredients) {
    this.totalIngredients = totalIngredients;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  public List<IngredientSchema> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<IngredientSchema> ingredients) {
    this.ingredients = ingredients;
  }

  @Override
  public String toString() {
    return "RecipeSchema{" +
        "idRecipe='" + idRecipe + '\'' +
        ", nameRecipe='" + nameRecipe + '\'' +
        ", timeForPreparation='" + timeForPreparation + '\'' +
        ", portions=" + portions +
        ", totalIngredients=" + totalIngredients +
        ", description='" + description + '\'' +
        ", imageUrl='" + imageUrl + '\'' +
        ", tags=" + tags +
        ", ingredients=" + ingredients +
        '}';
  }
}
