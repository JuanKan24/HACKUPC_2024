import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.widget.RemoteViews
import com.hackupc.travelperk.R

class MainActivity : FlutterActivity() {
    private val CHANNEL = "com.hackupc.travelpert/channel"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            if (call.method == "updateWidget") {
                val data = call.argument<List<String>>("data")
                data?.let {
                    updateWidget(it)
                    result.success("Data received and widget updated")
                } ?: result.error("ERROR", "Failed to receive data", null)
            } else {
                result.notImplemented()
            }
        }
    }

    private fun updateWidget(data: List<String>) {
        val appWidgetManager = AppWidgetManager.getInstance(applicationContext)
        val views = RemoteViews(packageName, R.layout.widget_travel_info)
        views.setTextViewText(R.id.text_departure_city, data[4])
        views.setTextViewText(R.id.text_destination_city, data[5])
        views.setTextViewText(R.id.text_departure_date, data[2])
        views.setTextViewText(R.id.text_arrival_date, data[3])
        val widgetId = ComponentName(applicationContext, AppWidgetProvider::class.java)
        appWidgetManager.updateAppWidget(widgetId, views)
    }
}