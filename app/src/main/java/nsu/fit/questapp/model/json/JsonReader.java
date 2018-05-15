package nsu.fit.questapp.model.json;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Alena Drobot
 */
public class JsonReader {

    private final static String SPACE_JSON_PATH = "space_cards.json";
    private final static String DEBATES_JSON_PATH = "debates_cards.json";
    private final static String TEST_JSON_PATH = "test_cards.json";

    private String json = null;
    private InputStream in;
    private Context context;

    public JsonReader(Context context) {
        this.context = context;
    }

    @Nullable
    public String getSpaceJson() {
        return getJson(SPACE_JSON_PATH);
    }

    @Nullable
    public String getDebatesJson() {
        return getJson(DEBATES_JSON_PATH);
    }

    @Nullable
    public String getTestJson() {
        return getJson(TEST_JSON_PATH);
    }

    /**
     * @link https://developer.android.com/guide/topics/providers/document-provider
     */
    @Nullable
    public String getJsonFromStorage(@NonNull Uri uri) throws IOException{
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        inputStream.close();
        reader.close();
        return stringBuilder.toString();
    }

    @Nullable
    private String getJson(@NonNull String path) {
        try {
            in = context.getAssets().open(path);
            int bufferSize = in.available();
            byte[] buffer = new byte[bufferSize];
            in.read(buffer);
            in.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
