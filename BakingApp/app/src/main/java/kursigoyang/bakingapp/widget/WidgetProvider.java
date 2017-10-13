package kursigoyang.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import kursigoyang.bakingapp.R;

/**
 * Implementation of App Widget functionality.
 */
public class WidgetProvider extends AppWidgetProvider {

  static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

    // Construct the RemoteViews object
    RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget);

    // Create an Intent to launch MainActivity when clicked

    Intent intent = new Intent(context, WidgetService.class);
    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
    intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

    remoteViews.setRemoteAdapter(R.id.lvContent, intent);
    remoteViews.setEmptyView(R.id.lvContent, R.id.txtNoContent);


    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
  }

  @Override
  public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    // There may be multiple widgets active, so update all of them
    for (int appWidgetId : appWidgetIds) {
      updateAppWidget(context, appWidgetManager, appWidgetId);
    }
  }

  @Override public void onEnabled(Context context) {
    // Enter relevant functionality for when the first widget is created
  }

  @Override public void onDisabled(Context context) {
    // Enter relevant functionality for when the last widget is disabled
  }
}

