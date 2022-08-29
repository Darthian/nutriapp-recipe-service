package controller.requestModel;

public class IngredientSchema {
  private String idProduct;
  private String quantity;
  private String unityMeasure;
  private Float measure;
  private String imageUrl;

  public String getIdProduct() {
    return idProduct;
  }

  public void setIdProduct(String idProduct) {
    this.idProduct = idProduct;
  }

  public String getQuantity() {
    return quantity;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  public String getUnityMeasure() {
    return unityMeasure;
  }

  public void setUnityMeasure(String unityMeasure) {
    this.unityMeasure = unityMeasure;
  }

  public Float getMeasure() {
    return measure;
  }

  public void setMeasure(Float measure) {
    this.measure = measure;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  @Override
  public String toString() {
    return "IngredientSchema{" +
        "idProduct='" + idProduct + '\'' +
        ", quantity='" + quantity + '\'' +
        ", unityMeasure='" + unityMeasure + '\'' +
        ", measure=" + measure +
        ", imageUrl='" + imageUrl + '\'' +
        '}';
  }
}
