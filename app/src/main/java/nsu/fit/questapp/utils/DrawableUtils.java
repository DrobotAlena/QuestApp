package nsu.fit.questapp.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Alena Drobot
 */
public class DrawableUtils {
    //возвращает положит. ресурс или 0 если не нашел

    public static int getDrawableId(@NonNull Context context, @Nullable String drawableName) {
        return StringUtils.isEmpty(drawableName) ? 0 : context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName());
    }
}
