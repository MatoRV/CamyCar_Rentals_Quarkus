package camycar_rentals.domain.enumerados;

import base.converter.AbstractEnumConverter;
import base.converter.PersistableEnum;

public enum EstadoEnum implements PersistableEnum<String> {

    DISPONIBLE ("D"), NO_DISPONIBLE ("N"), ALQUILADO ("A"), MANTENIMIENTO ("M");

    private final String value;

    EstadoEnum(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static class Converter extends AbstractEnumConverter<EstadoEnum, String> {

        public Converter() {
            super(EstadoEnum.class);
        }
    }
}
