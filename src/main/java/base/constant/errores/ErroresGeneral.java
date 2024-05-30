package base.constant.errores;

public class ErroresGeneral {

    private ErroresGeneral() {
        throw new IllegalStateException("Clase de utilidades");
    }

    public static final String GEN_0001 = "GEN_0001: Rol no autorizado para realizar la operación solicitada";
    public static final String GEN_0002 = "GEN_OOO2: Dni repetido";
    public static final String GEN_0003 = "GEN_OOO3: Usuario no encontrado";
    public static final String GEN_0004 = "GEN_0004: La máquina ya está en alquiler";
}
