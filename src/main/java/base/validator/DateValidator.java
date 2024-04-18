package base.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import base.constant.FormatoFechas;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<Date, String> {

    @Override
    public boolean isValid(String fecha, ConstraintValidatorContext context) {
        // Si viene la fecha, se pasará la validación
        if (fecha!=null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FormatoFechas.PATTERN_DATE_NOTIMEZONE);
            try {
                LocalDate.parse(fecha,formatter);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}
