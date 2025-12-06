package body.SportActivity;

import org.json.JSONObject;
import org.json.JSONTokener;
import Utils.Utils;

import java.io.FileInputStream;
import java.io.IOException;

public class UpdateSportActivityBody {

    public JSONObject getBodyFromFile(String filePath, String activity_date) throws IOException {
        try (FileInputStream file = new FileInputStream(filePath)) {
            JSONObject body = new JSONObject(new JSONTokener(file));
            body.put("activity_date", activity_date);
            body.put("title", Utils.generateRandomTitle());
            body.put("city_id", 3172);
            return body;
        }
    }
}