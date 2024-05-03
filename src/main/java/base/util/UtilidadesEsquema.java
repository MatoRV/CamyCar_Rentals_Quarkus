package base.util;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;

public class UtilidadesEsquema {

    private UtilidadesEsquema() {
        throw new IllegalStateException("Clase de utilidades");
    }

    public static boolean ejecutarMigracion(String[] locations, String jdbcUrl, String username, String password) {
        try {
            // Cambiamos la configuración del flyway para que apunte al nuevo esquema y realizamos la migración
            return Flyway.configure().cleanDisabled(true).dataSource(jdbcUrl, username, password).validateOnMigrate(false).locations(locations).load()
                    .migrate().migrationsExecuted > 0;
        } catch (FlywayException ex) {
            // En caso de excepción, se repara (eliminará la columna de versiones con success a 0) y volverá a intentar la migración
            Flyway.configure().cleanDisabled(true).dataSource(jdbcUrl, username, password).locations(locations).load().repair();
            return Flyway.configure().cleanDisabled(true).dataSource(jdbcUrl, username, password).locations(locations).load().migrate().migrationsExecuted > 0;
        }
    }
}
