package kursigoyang.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import kursigoyang.bakingapp.model.Ingredient;
import kursigoyang.bakingapp.model.Step;

public class RecipeDetailActivity extends AppCompatActivity
    implements AdapterView.OnItemSelectedListener {

  String stepsJson;
  String ingredientsJson;
  private boolean isTwoPane = false;

  public static void start(Context context, String steps, String ingredients) {
    Intent intent = new Intent(context, RecipeDetailActivity.class);
    intent.putExtra(Step.class.getSimpleName(), steps);
    intent.putExtra(Ingredient.class.getSimpleName(), ingredients);
    context.startActivity(intent);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recipe_detail);
    ButterKnife.bind(this);

    stepsJson = getIntent().getStringExtra(Step.class.getSimpleName());
    ingredientsJson = getIntent().getStringExtra(Ingredient.class.getSimpleName());

    if (findViewById(R.id.frameContainer) != null) {

      if (savedInstanceState != null) {
        return;
      }

      getSupportFragmentManager().beginTransaction()
          .replace(R.id.frameContainer,
              RecipeDetailFragment.newInstance(stepsJson, ingredientsJson))
          .commit();
    }

    determinePaneLayout();
  }

  private void determinePaneLayout() {
    if (findViewById(R.id.recipe_step_detail_fragment) != null) {
      isTwoPane = true;
    }
  }

  @Override public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    if (!TextUtils.isEmpty(stepsJson)) {
      Type stepListType = new TypeToken<ArrayList<Step>>() {
      }.getType();
      List<Step> stepList = new Gson().fromJson(stepsJson, stepListType);

      if (isTwoPane) {
        RecipeStepDetailFragment fragment = RecipeStepDetailFragment.newInstance(stepList.get(0));
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.recipe_step_detail_fragment, fragment)
            .commit();
      } else {
        RecipeStepDetailActivity.start(this, stepList, 0);
      }
    } else {
      Toast.makeText(this,"json ksong",Toast.LENGTH_LONG);
    }
  }

  @Override public void onNothingSelected(AdapterView<?> adapterView) {

  }
  //
  //@Override public void onStepSelected(int index) {
  //  RecipeStepDetailFragment recipeStepDetailFragment =
  //      (RecipeStepDetailFragment) getSupportFragmentManager().findFragmentById(
  //          R.id.recipe_step_detail_fragment);
  //
  //  if (recipeStepDetailFragment != null) {
  //
  //  } else {
  //
  //    Type stepListType = new TypeToken<ArrayList<Step>>() {
  //    }.getType();
  //    List<Step> stepList = new Gson().fromJson(stepsJson, stepListType);
  //
  //    getSupportFragmentManager().beginTransaction()
  //        .replace(R.id.frameContainer, RecipeStepDetailFragment.newInstance(stepList.get(0)))
  //        .addToBackStack(null)
  //        .commit();
  //  }
  //}
}
