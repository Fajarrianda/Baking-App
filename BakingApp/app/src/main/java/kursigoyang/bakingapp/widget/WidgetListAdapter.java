package kursigoyang.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import java.util.ArrayList;
import java.util.List;
import kursigoyang.bakingapp.Preferences;
import kursigoyang.bakingapp.model.Ingredient;

/**
 * Created by Fajar Rianda on 18/09/2017.
 */

public class WidgetListAdapter implements RemoteViewsService.RemoteViewsFactory {
  private List<Ingredient> listData = new ArrayList();
  private Context context = null;
  private int appWidgetId;

  public WidgetListAdapter(Context context, Intent intent) {
    this.context = context;
    appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
        AppWidgetManager.INVALID_APPWIDGET_ID);

    populateListItem();
  }

  private void populateListItem() {

    if (Preferences.getIngredient() != null) {
      List<Ingredient> ingredients = Preferences.getIngredient();
      listData.addAll(ingredients);
    }

    //for (int i = 0; i < 10; i++) {
    //  Ingredient listItem = new Ingredient();
    //  listItem.setIngredient(i + "Tes Data");
    //  listData.add(listItem);
    //}
  }

  @Override public void onCreate() {

  }

  @Override public void onDataSetChanged() {

  }

  @Override public void onDestroy() {

  }

  @Override public int getCount() {
    return listData.size();
  }

  @Override public RemoteViews getViewAt(int i) {
    final RemoteViews remoteViews =
        new RemoteViews(context.getPackageName(), android.R.layout.simple_list_item_1);
    Ingredient ingredient = listData.get(i);
    remoteViews.setTextViewText(android.R.id.text1, ingredient.getIngredient());
    return remoteViews;
  }

  @Override public RemoteViews getLoadingView() {
    return null;
  }

  @Override public int getViewTypeCount() {
    return 1;
  }

  @Override public long getItemId(int i) {
    return i;
  }

  @Override public boolean hasStableIds() {
    return false;
  }
}
