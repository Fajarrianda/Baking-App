package kursigoyang.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import java.util.List;
import kursigoyang.bakingapp.adapter.RecipeStepDetailPagerAdapter;
import kursigoyang.bakingapp.model.Step;

public class RecipeStepDetailActivity extends AppCompatActivity {


  RecipeStepDetailPagerAdapter recipeStepDetailPagerAdapter;
  @Bind(R.id.pager) ViewPager pager;

  public static void start(Context context, List<Step> steps, int position) {
    Intent intent = new Intent(context, RecipeStepDetailActivity.class);
    intent.putExtra(int.class.getSimpleName(), position);
    intent.putParcelableArrayListExtra(Step.class.getSimpleName(),
        (java.util.ArrayList<? extends android.os.Parcelable>) steps);
    context.startActivity(intent);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recipe_step_detail);
    ButterKnife.bind(this);
    setupViewPager();
    setupDataPager();
  }

  private void setupViewPager() {

    Bundle extra = getIntent().getExtras();
    int position = extra.getInt(int.class.getSimpleName(), 0);
    recipeStepDetailPagerAdapter =
        new RecipeStepDetailPagerAdapter(getSupportFragmentManager(), position);

    pager.setAdapter(recipeStepDetailPagerAdapter);
  }

  private void setupDataPager() {

    Bundle bundle = getIntent().getExtras();
    List<Step> steps = bundle.getParcelableArrayList(Step.class.getSimpleName());
    int position = bundle.getInt(int.class.getSimpleName(), 0);
    recipeStepDetailPagerAdapter.pushData(steps);
    pager.setCurrentItem(position);
  }
}
