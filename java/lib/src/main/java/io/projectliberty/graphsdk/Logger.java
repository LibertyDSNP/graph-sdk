package io.projectliberty.graphsdk;

import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import io.projectliberty.graphsdk.Native;

public class Logger {
    public static void initialize(Level level) {
        org.slf4j.Logger logger = LoggerFactory.getLogger("dsnp-graph-sdk");
        Native.loggerInitialize(level.toInt(), logger);
    }

    public static void initialize() {
        initialize(Level.INFO);
    }
}
