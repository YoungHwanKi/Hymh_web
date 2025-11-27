package home.parns.Hymh_web.common.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class HDateUtil {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HHmmss");
    private static final DateTimeFormatter DATE_TIME_MINUTE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
    private static final DateTimeFormatter DATE_TIME_SECOND_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private static final ZoneId ZONE = ZoneId.of("Asia/Seoul");

    // 현재 날짜 yyyyMMdd
    public static String getDate() {
        return LocalDate.now(ZONE).format(DATE_FORMAT);
    }

    // 현재 시간 HHmmss
    public static String getTime() {
        return LocalTime.now(ZONE).format(TIME_FORMAT);
    }

    // 어제 날짜 yyyyMMdd
    public static String getYesterdayDate() {
        return LocalDate.now(ZONE).minusDays(1).format(DATE_FORMAT);
    }

    // 날짜 연산 yyyyMMdd -> yyyyMMdd
    public static String addDays(String date, int addDays) {
        return LocalDate.parse(date, DATE_FORMAT)
                .plusDays(addDays)
                .format(DATE_FORMAT);
    }

    // 두 날짜 diff
    public static long dateDiff(String fromDate, String toDate) {
        return ChronoUnit.DAYS.between(
                LocalDate.parse(fromDate, DATE_FORMAT),
                LocalDate.parse(toDate, DATE_FORMAT)
        );
    }

    // 두 날짜/시간 차이 (분)
    public static long gapMinutesFromNow(String dateTime) {
        LocalDateTime src = LocalDateTime.parse(dateTime, DATE_TIME_MINUTE_FORMAT);
        return ChronoUnit.MINUTES.between(src, LocalDateTime.now(ZONE));
    }

    // 두 날짜/시간 차이 (초)
    public static long gapSecondsFromNow(String dateTime) {
        LocalDateTime src = LocalDateTime.parse(dateTime, DATE_TIME_SECOND_FORMAT);
        return ChronoUnit.SECONDS.between(src, LocalDateTime.now(ZONE));
    }

    // 요일 반환 (일=1 ... 토=7)
    public static int getDayOfWeek(String date) {
        return LocalDate.parse(date, DATE_FORMAT).getDayOfWeek().getValue() + 1;
    }

    public static String getMonthAgoDate() {
        return LocalDate.now(ZONE).minusMonths(1).toString();
    }

    public static String get7DayAgoDate() {
        return LocalDate.now(ZONE).minusDays(7).toString();
    }

    public static String getLastDate(String date) {
        LocalDate localDate = LocalDate.parse(date, DATE_FORMAT);
        return localDate.withDayOfMonth(localDate.lengthOfMonth()).format(DATE_FORMAT);
    }


    /* ------------------------- 🔥 Custom Format Methods ------------------------- */

    // 현재 날짜/시간 format 적용
    public static String formatNow(String pattern) {
        return LocalDateTime.now(ZONE).format(DateTimeFormatter.ofPattern(pattern));
    }

    // yyyyMMdd -> formatted string
    public static String formatDate(String date, String pattern) {
        return LocalDate.parse(date, DATE_FORMAT)
                .format(DateTimeFormatter.ofPattern(pattern));
    }

    // yyyyMMddHHmm / yyyyMMddHHmmss -> formatted string (길이 자동판단)
    public static String formatDateTime(String dateTime, String pattern) {
        LocalDateTime parsed;

        if (dateTime.length() == 12) {
            parsed = LocalDateTime.parse(dateTime, DATE_TIME_MINUTE_FORMAT);
        } else if (dateTime.length() == 14) {
            parsed = LocalDateTime.parse(dateTime, DATE_TIME_SECOND_FORMAT);
        } else {
            throw new IllegalArgumentException("지원하지 않는 날짜 형식입니다: " + dateTime);
        }

        return parsed.format(DateTimeFormatter.ofPattern(pattern));
    }

    // 객체 기반 공통 타입
    public static String format(LocalDate date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String format(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }


    /**
     * 오늘 기준 날짜 + diff일 리턴 (yyyyMMdd)
     */
    public static String getDDay(int diff) {
        return LocalDate.now(ZONE)
                .plusDays(diff)
                .format(DATE_FORMAT);
    }

    /**
     * 오늘 기준 날짜 + diff일 리턴 (사용자 지정 포맷)
     */
    public static String getDDay(int diff, String pattern) {
        return LocalDate.now(ZONE)
                .plusDays(diff)
                .format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * D-Day 표현 반환
     * 예) 오늘: D-day, 내일: D-1, 어제: D+1
     */
    public static String getDDayLabel(String targetDate) {
        LocalDate target = LocalDate.parse(targetDate, DATE_FORMAT);
        long diff = ChronoUnit.DAYS.between(LocalDate.now(ZONE), target);

        if (diff == 0) {
            return "D-day";
        } else if (diff > 0) {
            return "D-" + diff;
        } else {
            return "D+" + Math.abs(diff);
        }
    }

    /**
     * D-Day 표현 + 형식 적용된 날짜 반환
     * 예시: "D-3 (2025-11-30)"
     */
    public static String getDDayFormatted(int diff, String pattern) {
        String date = getDDay(diff, pattern);
        String label = (diff == 0) ? "D-day" :
                (diff > 0 ? "D-" + diff : "D+" + Math.abs(diff));

        return label + " (" + date + ")";
    }

    public static void main(String[] args) {
        System.out.println("Default:" + getDate());
        System.out.println("Format Now: " + formatNow("yyyy-MM-dd HH:mm:ss"));
        System.out.println("Convert 20240215 -> " + formatDate("20240215", "yyyy-MM-dd"));
        System.out.println("Convert 202402151230 -> " + formatDateTime("202402151230", "yyyy/MM/dd HH:mm"));
    }
}