import android.appwidget.AppWidgetProvider
import android.appwidget.AppWidgetManager
import android.content.Context
import android.widget.RemoteViews
import com.hackupc.travelperk.R


class MyWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // update all widgets instances
        appWidgetIds.forEach { appWidgetId ->
            // instantiation RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.widget_travel_info)
            // update widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}


