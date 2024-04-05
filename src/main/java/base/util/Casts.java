package base.util;

import base.exception.CamycarException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Casts {

    private Casts() {
        throw new IllegalStateException("Clase de utilidades");
    }

    public static <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        if (obj instanceof List<?>) {
            for (Object o: (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        throw new CamycarException("Se esperaba una lista, pero el objeto recibido no lo es");
    }

    public static <T> T castOptional(Class<T> clazz, Optional<?> optional) {
        if (optional.isEmpty()) {
            return null;
        } else {
            return clazz.cast(optional.get());
        }
    }

    public static <T> T castObject(Object objecto, Class<T> clazz) {
        if (objecto == null) {
            return null;
        } else {
            return clazz.cast(objecto);
        }
    }
}