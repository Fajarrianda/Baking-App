package kursigoyang.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Fajar Rianda on 18/08/2017.
 */
public class Ingredient implements Parcelable {

  public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
    @Override public Ingredient createFromParcel(Parcel in) {
      return new Ingredient(in);
    }

    @Override public Ingredient[] newArray(int size) {
      return new Ingredient[size];
    }
  };
  @SerializedName("quantity") private float quantity;
  @SerializedName("measure") private String measure;
  @SerializedName("ingredient") private String ingredient;

  public Ingredient() {

  }

  protected Ingredient(Parcel in) {
    quantity = in.readFloat();
    measure = in.readString();
    ingredient = in.readString();
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeFloat(quantity);
    dest.writeString(measure);
    dest.writeString(ingredient);
  }

  @Override public int describeContents() {
    return 0;
  }

  public String getIngredient() {
    return ingredient;
  }

  public void setIngredient(String ingredient) {
    this.ingredient = ingredient;
  }

  public String getMeasure() {
    return measure;
  }

  public void setMeasure(String measure) {
    this.measure = measure;
  }

  public float getQuantity() {
    return quantity;
  }

  public void setQuantity(float quantity) {
    this.quantity = quantity;
  }
}
