package kursigoyang.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Fajar Rianda on 18/08/2017.
 */
public class Baking implements Parcelable {

  @SerializedName("id") private int id;
  @SerializedName("name") private String name;
  @SerializedName("ingredients") private List<Ingredient> ingredients;
  @SerializedName("steps") private List<Step> steps;
  @SerializedName("servings") private String servings;
  @SerializedName("image") private String image;

  protected Baking(Parcel in) {
    id = in.readInt();
    name = in.readString();
    ingredients = in.createTypedArrayList(Ingredient.CREATOR);
    steps = in.createTypedArrayList(Step.CREATOR);
    servings = in.readString();
    image = in.readString();
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeString(name);
    dest.writeTypedList(ingredients);
    dest.writeTypedList(steps);
    dest.writeString(servings);
    dest.writeString(image);
  }

  @Override public int describeContents() {
    return 0;
  }

  public static final Creator<Baking> CREATOR = new Creator<Baking>() {
    @Override public Baking createFromParcel(Parcel in) {
      return new Baking(in);
    }

    @Override public Baking[] newArray(int size) {
      return new Baking[size];
    }
  };

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getServings() {
    return servings;
  }

  public void setServings(String servings) {
    this.servings = servings;
  }

  public List<Step> getSteps() {
    return steps;
  }

  public void setSteps(List<Step> steps) {
    this.steps = steps;
  }
}
