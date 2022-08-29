package service;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.google.gson.Gson;
import controller.daos.RecipeDao;
import controller.entities.RecipeEntity;
import controller.service.RecipeService;
import controller.service.RecipeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {

  @Mock
  private RecipeDao recipeDao;

  @Mock
  private LambdaLogger logger;
  private RecipeService recipeService;

  @BeforeEach
  public void setup() {
    recipeService = new RecipeServiceImpl(logger, recipeDao);
  }

  @Test
  public void testGetRecipes() throws IOException {
    Mockito.when(recipeDao.gerRecipesByName(Mockito.anyString())).thenReturn(getMock());
    Assertions.assertEquals("[{\"idRecipe\":\"test\",\"nameRecipe\":\"testName\"," +
            "\"timeForPreparation\":\"20 minutos\",\"portions\":2,\"totalIngredients\":4," +
            "\"description\":\"testDescription\",\"imageUrl\":\"addressToSomeImageResource\"," +
            "\"tags\":[\"rapido\",\"barato\"],\"ingredients\":[{\"idProduct\":\"1234\",\"unityMeasure\":\"kilo\"}]}]",
        recipeService.getRecipesByName("test"));
  }

  private List<RecipeEntity> getMock() throws IOException {
    RecipeEntity recipeEntityMock;
    URL url = getClass().getResource("/mocks/recipe-mock.json");
    Reader reader = Files.newBufferedReader(Paths.get(url.getPath()));
    recipeEntityMock = new Gson().fromJson(reader, RecipeEntity.class);
    return Arrays.asList(recipeEntityMock);
  }

}
