package kursigoyang.bakingapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import kursigoyang.bakingapp.model.Ingredient;
import kursigoyang.bakingapp.model.Step;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailFragment extends Fragment {

  @Bind(R.id.ingredientsContainer) LinearLayout ingredientsContainer;
  @Bind(R.id.stepsContainer) LinearLayout stepsContainer;
  Context context;
  List<Step> steps;
  List<Ingredient> ingredients;
  OnStepSelectedListener onStepSelectedListener;


  public interface OnStepSelectedListener{
    public void onStepSelected(int position);
  }

  public RecipeDetailFragment() {
    // Required empty public constructor
  }

  public static RecipeDetailFragment newInstance(String steps, String ingredients) {
    Bundle bundle = new Bundle();
    bundle.putString(Step.class.getSimpleName(), steps);
    bundle.putString(Ingredient.class.getSimpleName(), ingredients);
    RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
    recipeDetailFragment.setArguments(bundle);
    return recipeDetailFragment;
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    this.context = context;
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
    setHasOptionsMenu(true);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    init();
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.recipe_detail_menu, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.add_widget) {
      if (!ingredients.isEmpty()) {
        Preferences.setIngredients(ingredients);
      }
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  private void init() {

    Type stepListType = new TypeToken<ArrayList<Step>>() {
    }.getType();
    steps = new Gson().fromJson(getArguments().getString(Step.class.getSimpleName()), stepListType);

    Type ingredientListType = new TypeToken<ArrayList<Ingredient>>() {
    }.getType();
    ingredients = new Gson().fromJson(getArguments().getString(Ingredient.class.getSimpleName()),
        ingredientListType);

    for (int i = 0; i < steps.size(); i++) {
      TextView txtSteps = (TextView) LayoutInflater.from(context)
          .inflate(android.R.layout.simple_list_item_1, stepsContainer, false);
      ButterKnife.findById(txtSteps, R.id.text);
      txtSteps.setText(steps.get(i).getDescription());

      final int position = i;
      txtSteps.setOnClickListener(view -> {
        RecipeStepDetailActivity.start(context, steps, position);
      });
      stepsContainer.addView(txtSteps);
    }

    for (Ingredient ingredient : ingredients) {
      TextView txtIngredients = (TextView) LayoutInflater.from(getContext())
          .inflate(android.R.layout.simple_list_item_1, ingredientsContainer, false);
      ButterKnife.findById(txtIngredients, R.id.text);
      txtIngredients.setText(ingredient.getIngredient());
      ingredientsContainer.addView(txtIngredients);
    }
  }

  //@Override public void onListItemClick(ListView l, View v, int position, long id) {
  //  onStepSelectedListener.onStepSelected(position);
  //  l.setItemChecked(position,true);
  //  super.onListItemClick(l, v, position, id);
  //}
}
