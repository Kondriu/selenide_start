package org.selenide.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.concurrent.TimeUnit;
import org.awaitility.core.ConditionFactory;
import lombok.extern.slf4j.Slf4j;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.awaitility.Awaitility.await;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WaitUtil {
    private static final long TIMEOUT = PropertiesController.timeoutConfig().webdriverWaitTimeout();
    private static final long INTERVAL = 2000L;
    private static final String LOG_MESSAGE = "{} (elapsed time {}, remaining time {})" + System.lineSeparator();

    public static ConditionFactory doWait() {
        return doWait(INTERVAL, MILLISECONDS, TIMEOUT, MILLISECONDS);
    }

    public static ConditionFactory doWaitWithTimeout(long timeout, TimeUnit timeUnit) {
        return doWait(INTERVAL, MILLISECONDS, timeout, timeUnit);
    }

    public static ConditionFactory doWait(long interval,
                                          TimeUnit timeUnitInterval,
                                          long timeout,
                                          TimeUnit timeUnitTimeout) {
        return await().ignoreException(StaleElementReferenceException.class)
                .pollInSameThread()
                .conditionEvaluationListener(condition -> log.info(
                        LOG_MESSAGE,
                        condition.getDescription(),
                        toDuration(condition.getElapsedTimeInMS()),
                        toDuration(condition.getRemainingTimeInMS())))
                .await().atMost(timeout, timeUnitTimeout)
                .with().pollInterval(interval, timeUnitInterval);
    }

    private static String toDuration(long timeInMS) {
        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(timeInMS),
                TimeUnit.MILLISECONDS.toMinutes(timeInMS) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeInMS)),
                TimeUnit.MILLISECONDS.toSeconds(timeInMS) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeInMS)));
    }
}
