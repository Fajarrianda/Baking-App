package kursigoyang.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by Fajar Rianda on 18/09/2017.
 */

public class WidgetService extends RemoteViewsService {
  @Override public RemoteViewsFactory onGetViewFactory(Intent intent) {
    int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
        AppWidgetManager.INVALID_APPWIDGET_ID);

    return (new WidgetListAdapter(this.getApplicationContext(), intent));
  }
}