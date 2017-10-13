package kursigoyang.bakingapp;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import kursigoyang.bakingapp.model.Ingredient;

/**
 * Created by Fajar Rianda on 18/09/2017.
 */

public class Preferences {
  private static SharedPreferences getPreferences() {
    return PreferenceManager.getDefaultSharedPreferences(ContextProvider.get());
  }

  private static SharedPreferences.Editor getEditor() {
    return getPreferences().edit();
  }

  public static List<Ingredient> getIngredient() {
    return returnListOrNull(new TypeToken<List<Ingredient>>() {
    }.getType(), getPreferences().getString(Ingredient.class.getSimpleName(), null));
  }

  public static void setIngredients(List<Ingredient> ingredients) {
    Gson gson = new Gson();
    getEditor().putString(Ingredient.class.getSimpleName(), gson.toJson(ingredients)).apply();
  }

  public static <T> T returnListOrNull(Type type, String data) {
    try {
      Gson gson = new Gson();
      if (data == null) {
        return null;
      } else {
        try {
          return gson.fromJson(data, type);
        } catch (Exception e) {
          return null;
        }
      }
    } catch (Exception e) {
      return null;
    }
  }
}
