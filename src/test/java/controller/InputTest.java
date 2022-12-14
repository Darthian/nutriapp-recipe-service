package controller;

import controller.requestModel.RecipeSchema;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import utils.Mapper;

public class InputTest {

  @Test
  public void testDecoder() {
    RecipeSchema recipe = Mapper.getRecipeSchema("ewogICJpZFJlY2lwZSI6ICIxMjMiLAogICJuYW1lUmVjaXBlIjogIlBvbGxvIGNvbiBhbGdvIiwKICAidGltZUZvclByZXBhcmF0aW9uIjogIm1lZGlhIGhvcml0YSIsCiAgInBvcnRpb25zIjogMywKICAidG90YWxJbmdyZWRpZW50cyI6IDEwLAogICJkZXNjcmlwdGlvbiI6ICJQb2xsaXRvIGNvbiBhbGd1aXRvIHBhIG5vIGFndWFudGFyIiwKICAiaW1hZ2VVcmwiOiAiaHR0cHM6Ly93d3cuZGVsaWNpb3NpLmNvbS9pbWFnZXMvMTcwMC8xNzYyL3N1ZGFkby1kZS1wb2xsby02NjUud2VicCIsCiAgInRhZ3MiOiBbCiAgICAiUG9sbG8iLAogICAgIlJhcGlkbyIKICBdLAogICJpbmdyZWRpZW50cyI6IFsKICAgIHsKICAgICAgImlkUHJvZHVjdCI6ICIxMjM0IiwKICAgICAgInF1YW50aXR5IjogIjIiLAogICAgICAidW5pdHlNZWFzdXJlIjogIktpbG8iLAogICAgICAibWVhc3VyZSI6IDEsCiAgICAgICJpbWFnZVVybCI6ICJodHRwczovL21lZGlhLmlzdG9ja3Bob3RvLmNvbS9waG90b3MvY2hpY2tlbi1icmVhc3RzLW9uLWN1dHRpbmctYm9hcmQtcGljdHVyZS1pZDQ5Mjc4NzA5OD9rPTIwJm09NDkyNzg3MDk4JnM9NjEyeDYxMiZ3PTAmaD12ckJVN3lPRFh6clJIZmxoNE5YN2ZhTjVlelNfRk5ZdC1TNEJHRXY4aHFNPSIKICAgIH0KICBdCn0=");
    Assertions.assertEquals("123", recipe.getIdRecipe());
    Assertions.assertEquals("Pollo con algo", recipe.getNameRecipe());
  }
}
