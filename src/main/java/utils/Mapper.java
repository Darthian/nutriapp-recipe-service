package utils;

import com.google.gson.Gson;
import controller.requestModel.RecipeSchema;

import java.util.Base64;

public class Mapper {

  static public RecipeSchema getRecipeSchema(String code) {
    byte[] decodedBytes = Base64.getDecoder().decode(code);
    String jsonDecoded = new String(decodedBytes);
    final Gson gson = new Gson();
    return gson.fromJson(jsonDecoded, RecipeSchema.class);
  }
}
