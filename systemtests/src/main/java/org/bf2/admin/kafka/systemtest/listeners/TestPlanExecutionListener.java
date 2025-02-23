package org.bf2.admin.kafka.systemtest.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bf2.admin.kafka.systemtest.Environment;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.TestPlan;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class TestPlanExecutionListener implements TestExecutionListener {
    private static final Logger LOGGER = LogManager.getLogger(TestPlanExecutionListener.class);

    public void testPlanExecutionStarted(TestPlan testPlan) {
        LOGGER.info("=======================================================================");
        LOGGER.info("=======================================================================");
        LOGGER.info("                        Test run started");
        LOGGER.info("=======================================================================");
        LOGGER.info("=======================================================================");
        printSelectedTestClasses(testPlan);
        try {
            Files.createDirectories(Environment.LOG_DIR);
        } catch (IOException e) {
            LOGGER.warn("Test suite cannot create log dirs");
            throw new RuntimeException("Log folders cannot be created: ", e.getCause());
        }
    }

    public void testPlanExecutionFinished(TestPlan testPlan) {
        LOGGER.info("=======================================================================");
        LOGGER.info("=======================================================================");
        LOGGER.info("                        Test run finished");
        LOGGER.info("=======================================================================");
        LOGGER.info("=======================================================================");
    }

    private void printSelectedTestClasses(TestPlan plan) {
        LOGGER.info("Following testclasses are selected for run:");
        Arrays.asList(plan.getChildren(plan.getRoots()
                .toArray(new TestIdentifier[0])[0])
                .toArray(new TestIdentifier[0])).forEach(testIdentifier -> LOGGER.info("-> {}", testIdentifier.getLegacyReportingName()));
        LOGGER.info("=======================================================================");
        LOGGER.info("=======================================================================");
    }
}
