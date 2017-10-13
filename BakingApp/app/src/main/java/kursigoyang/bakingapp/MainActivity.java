package kursigoyang.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.gson.Gson;
import kursigoyang.bakingapp.adapter.BakingAdapter;
import kursigoyang.bakingapp.model.Baking;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements ListItemClickListener<Baking> {

  BakingAdapter adapter;
  @Bind(R.id.rvContent) RecyclerView rvContent;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    init();
  }

  private void init() {

    rvContent.setHasFixedSize(true);
    isTabletDevice();

    if (isTabletDevice()) {
      rvContent.setLayoutManager(new GridLayoutManager(this, 3));
    } else {
      rvContent.setLayoutManager(new LinearLayoutManager(this));
      rvContent.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    adapter = new BakingAdapter(this);
    rvContent.setAdapter(adapter);
    adapter.setOnListItemClickListener(this);
    loadData();
  }

  private void loadData() {
    AppClient.createService(ApiService.class)
        .getBaking()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object -> {
          adapter.pushData(object);
        }, error -> {
          error.printStackTrace();
        });
  }

  @Override public void onListItemClick(int position, Baking data) {
    RecipeDetailActivity.start(this, new Gson().toJson(adapter.getItem(position).getSteps()),
        new Gson().toJson(adapter.getItem(position).getIngredients()));
  }

  public boolean isTabletDevice() {
    DisplayMetrics metrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(metrics);
    int widthPixels = metrics.widthPixels;
    int heightPixels = metrics.heightPixels;
    float scaleFactor = metrics.density;
    float widthDp = widthPixels / scaleFactor;
    float heightDp = heightPixels / scaleFactor;
    float smallestWidth = Math.min(widthDp, heightDp);
    if (smallestWidth >= 600) {
      return true;
    } else {
      return false;
    }
  }
}
