package kursigoyang.dateutils;

import android.text.format.DateUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Fajar Rianda on 12/7/2017.
 */

public class Dateutil {

  /* milisecond */
  public static long getTimeZone() {
    return TimeZone.getDefault().getRawOffset();
  }

  public static long getTimeZoneInSecond() {
    return TimeZone.getDefault().getRawOffset() / DateUtils.SECOND_IN_MILLIS;
  }

  private static long getCurrentTimeSecondUTC() {
    return (getCurrentTimeSecond() + getTimeZoneInSecond());
  }

  public static long getCurrentTimeSecond() {
    return Calendar.getInstance().getTimeInMillis() / DateUtils.SECOND_IN_MILLIS;
  }

  /* Include Timezone*/
  public static String getDateFromEpoch(long date, String formatDate) {
    SimpleDateFormat format = new SimpleDateFormat(formatDate);
    return format.format(new Date(date * 1000));
  }
}
