package austeretony.oxygen_dailyrewards.server.test.time;

import java.time.Clock;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import austeretony.oxygen_core.common.main.OxygenMain;
import austeretony.oxygen_dailyrewards.common.config.DailyRewardsConfig;
import austeretony.oxygen_dailyrewards.server.DailyRewardsManagerServer;

public class TimeManagerServer {

    private final DailyRewardsManagerServer manager;

    private final ZoneId zoneId;

    private final Clock clock;

    public TimeManagerServer(DailyRewardsManagerServer manager) {
        this.manager = manager;
        this.zoneId = initZoneId();
        this.clock = Clock.system(this.zoneId);
    }

    private static ZoneId initZoneId() {
        ZoneId zoneId = ZoneId.systemDefault();
        if (!DailyRewardsConfig.SERVER_REGION_ID.asString().isEmpty()) {
            try {
                zoneId = ZoneId.of(DailyRewardsConfig.SERVER_REGION_ID.asString());
            } catch (DateTimeException exception) {
                OxygenMain.LOGGER.error("Server ZoneId parse failure! System default ZoneId will be used.", exception);
            }
        }
        return zoneId;
    }

    public ZoneId getZoneId() {
        return this.zoneId;
    }

    public Clock getClock() {
        return this.clock;
    }

    public Instant getInstant() {
        return this.clock.instant();
    }

    public ZonedDateTime getZonedDateTime() {
        return ZonedDateTime.now(this.clock);
    }
}
