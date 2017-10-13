package kursigoyang.bakingapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import java.util.List;
import kursigoyang.bakingapp.RecipeStepDetailFragment;
import kursigoyang.bakingapp.model.Step;

/**
 * Created by Fajar Rianda on 16/09/2017.
 */

public class RecipeStepDetailPagerAdapter extends FragmentStatePagerAdapter {

  private List<Step> steps = new ArrayList<>();
  private int stepPosition = -1;

  public RecipeStepDetailPagerAdapter(FragmentManager fm, int stepPosition) {
    super(fm);
    this.stepPosition = stepPosition;
  }

  @Override public Fragment getItem(int position) {
    return RecipeStepDetailFragment.newInstance(steps.get(position));
  }

  @Override public int getCount() {
    return steps.size();
  }

  public void pushData(List<Step> steps) {
    this.steps.clear();
    this.steps.addAll(steps);
    notifyDataSetChanged();
  }
}
