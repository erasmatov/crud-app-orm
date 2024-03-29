package net.erasmatov.crudapp.utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.flywaydb.core.Flyway;

public class FlywayUtil {
    public static void flywayMigrate() {
        Config config = ConfigFactory.load();
        Flyway flyway = Flyway.configure().dataSource(
                config.getString("url"),
                config.getString("username"),
                config.getString("password")).load();
        flyway.repair();
        flyway.migrate();
    }

}
