package Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {

    private static final Logger logger = LoggerFactory.getLogger(Utils.class);

    // Generate random title dengan prefix "Title-"
    public static String generateRandomTitle() {
        String title = "Title-" + UUID.randomUUID().toString().substring(0, 8);
        logger.info("Generated random title: {}", title);
        return title;
    }

    // Ambil tanggal hari ini + 7 hari (format yyyy-MM-dd)
    public static String getDateAfterSevenDays() {
        LocalDate currentDate = LocalDate.now();
        LocalDate dateAfter7Days = currentDate.plusDays(7);
        String formatted = dateAfter7Days.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        logger.info("Generated date after 7 days: {}", formatted);
        return formatted;
    }

    // Ambil tanggal hari ini + 4 hari (format yyyy-MM-dd)
    public static String getDateAfterFourDays() {
        LocalDate currentDate = LocalDate.now();
        LocalDate dateAfter4Days = currentDate.plusDays(4);
        String formatted = dateAfter4Days.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        logger.info("Generated date after 4 days: {}", formatted);
        return formatted;
    }

    // Ambil tanggal hari ini + 4 hari dengan format full timestamp (yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z')
    public static String getDateAfterFourDaysWithTimestamp() {
        LocalDateTime dateTime = LocalDateTime.now().plusDays(4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
        String formatted = dateTime.format(formatter);
        logger.info("Generated date after 4 days (timestamp): {}", formatted);
        return formatted;
    }

    // Generate random email untuk testing
    public static String generateRandomEmail() {
        String email = "user_" + UUID.randomUUID().toString().substring(0, 6) + "@example.com";
        logger.info("Generated random email: {}", email);
        return email;
    }

    // Generate random password sederhana
    public static String generateRandomPassword() {
        String password = "Pass@" + UUID.randomUUID().toString().substring(0, 6);
        logger.info("Generated random password: {}", password);
        return password;
    }

    // Simpan response body ke file JSON di folder reports-output/screenshots
    public static void saveResponseToFile(String response, String fileName) {
        String path = "reports-output/screenshots/" + fileName + ".json";
        try (FileWriter file = new FileWriter(path)) {
            file.write(response);
            logger.info("Response saved to file: {}", path);
        } catch (IOException e) {
            logger.error("Failed to save response to file: {}", path, e);
        }
    }

    // Utility tambahan: format tanggal custom
    public static String formatDate(LocalDate date, String pattern) {
        String formatted = date.format(DateTimeFormatter.ofPattern(pattern));
        logger.info("Formatted date {} with pattern {}: {}", date, pattern, formatted);
        return formatted;
    }

    // Utility tambahan: generate random string dengan panjang tertentu
    public static String generateRandomString(int length) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String randomString = uuid.substring(0, Math.min(length, uuid.length()));
        logger.info("Generated random string of length {}: {}", length, randomString);
        return randomString;
    }
}