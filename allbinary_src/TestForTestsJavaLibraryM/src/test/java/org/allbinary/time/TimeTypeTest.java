package org.allbinary.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TimeTypeTest {

    private static long millisForHourLocal(int hour) {
        LocalDate today = LocalDate.now();
        LocalDateTime ldt = LocalDateTime.of(today, LocalTime.of(hour, 0));
        return ldt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    private static class TestableTimeTypeUtil extends TimeTypeUtil {
        private final int hour;
        TestableTimeTypeUtil(int hour) {
            this.hour = hour;
        }
        @Override
        public int getHourOfDay() {
            return hour;
        }
    }

    @Test
    @DisplayName("isNight returns true for hours < 6")
    void isNight_returnsTrue_for_hours_less_than_6() {
        for (int h : new int[]{0, 1, 2, 3, 4, 5}) {
            TimeTypeUtil util = new TestableTimeTypeUtil(h);
            Assertions.assertTrue(util.isNight(), "Expected night for hour=" + h);
        }
    }

    @Test
    @DisplayName("isNight returns true for hours > 18")
    void isNight_returnsTrue_for_hours_greater_than_18() {
        for (int h : new int[]{19, 20, 21, 22, 23}) {
            TimeTypeUtil util = new TestableTimeTypeUtil(h);
            Assertions.assertTrue(util.isNight(), "Expected night for hour=" + h);
        }
    }

    @Test
    @DisplayName("isNight returns false for boundary hours 6 and 18")
    void isNight_returnsFalse_for_boundary_hours_6_and_18() {
        for (int h : new int[]{6, 18}) {
            TimeTypeUtil util = new TestableTimeTypeUtil(h);
            Assertions.assertFalse(util.isNight(), "Expected day for boundary hour=" + h);
        }
    }

    @Test
    @DisplayName("getNightOrDay returns labels consistent with isNight")
    void getNightOrDay_returns_expected_label() {
        TimeTypeUtil nightUtil = new TestableTimeTypeUtil(0);
        Assertions.assertEquals(nightUtil.NIGHT, nightUtil.getNightOrDay());

        TimeTypeUtil dayUtil = new TestableTimeTypeUtil(12);
        Assertions.assertEquals(dayUtil.DAY, dayUtil.getNightOrDay());
    }

    @Test
    @DisplayName("getHourOfDay(long) converts epoch millis to local hour correctly")
    void getHourOfDay_long_converts_epoch_to_local_hour() {
        TimeTypeUtil util = new TimeTypeUtil();
        for (int h : new int[]{0, 6, 12, 18, 23}) {
            long millis = millisForHourLocal(h);
            Assertions.assertEquals(h, util.getHourOfDay(millis), "Hour mismatch for h=" + h);
        }
    }

    @Test
    @DisplayName("isNight(long) returns true for <6 and >18 hours and false otherwise")
    void isNight_long_expected_ranges() {
        TimeTypeUtil util = new TimeTypeUtil();
        // true cases
        for (int h : new int[]{0, 1, 2, 3, 4, 5, 19, 20, 21, 22, 23}) {
            long millis = millisForHourLocal(h);
            Assertions.assertTrue(util.isNight(millis), "Expected night for hour=" + h);
        }
        // false cases including boundaries
        for (int h : new int[]{6, 7, 12, 18}) {
            long millis = millisForHourLocal(h);
            Assertions.assertFalse(util.isNight(millis), "Expected day for hour=" + h);
        }
    }

    @Test
    @DisplayName("getNightOrDay(long) returns DAY/NIGHT consistent with isNight(long)")
    void getNightOrDay_long_consistency() {
        TimeTypeUtil util = new TimeTypeUtil();
        long nightMillis = millisForHourLocal(0);
        long dayMillis = millisForHourLocal(12);
        Assertions.assertEquals(util.NIGHT, util.getNightOrDay(nightMillis));
        Assertions.assertEquals(util.DAY, util.getNightOrDay(dayMillis));
    }

    @Test
    @DisplayName("Boundary checks: 6 and 18 should be DAY for long-based API")
    void boundaries_day_for_long_based_api() {
        TimeTypeUtil util = new TimeTypeUtil();
        for (int h : new int[]{6, 18}) {
            long millis = millisForHourLocal(h);
            Assertions.assertFalse(util.isNight(millis));
            Assertions.assertEquals(util.DAY, util.getNightOrDay(millis));
        }
    }

    @Test
    @DisplayName("Public constants NIGHT and DAY are accessible and stable")
    void constants_accessible_and_stable() {
        TimeTypeUtil util = new TimeTypeUtil();
        Assertions.assertEquals("Night", util.NIGHT);
        Assertions.assertEquals("Day", util.DAY);
    }

    @Test
    @DisplayName("getInstance returns the same singleton instance")
    void getInstance_returns_same_singleton() {
        TimeTypeUtil a = TimeTypeUtil.getInstance();
        TimeTypeUtil b = TimeTypeUtil.getInstance();
        Assertions.assertSame(a, b);
    }
}
