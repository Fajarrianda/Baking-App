package kursigoyang.bakingapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Fajar Rianda on 17/09/2017.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityScreenTest {

  @Rule
  public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

  @Test
  public void scrollRecycleItem_MainActivity(){
    onView(withId(R.id.rvContent)).perform(RecyclerViewActions.scrollToPosition(3));
  }
}
