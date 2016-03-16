package homeWork5.service;

import org.apache.logging.log4j.*;

/**
 * Created by marva on 16.03.16.
 */
public class Logging {
    private static Logger LOGG = LogManager.getLogger("userActivity");

    public static void log(String text) {
        LOGG.info(text);
    }
}
