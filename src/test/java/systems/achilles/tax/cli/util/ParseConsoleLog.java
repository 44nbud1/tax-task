package systems.achilles.tax.cli.util;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ParseConsoleLog {

    public static boolean consoleLogToBoolean(List<String> log, String expected) {
        AtomicBoolean status = new AtomicBoolean(false);
        log.forEach(r -> {
            if (r.contains(expected)) {
                status.set(true);
            }
        });

        return status.get();
    }
}
