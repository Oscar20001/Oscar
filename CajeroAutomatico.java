import java.util.Scanner;

public class CajeroAutomatico {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Inicializamos el cajero automático
        CajeroAutomatico cajero = new CajeroAutomatico();

        // Creamos una cuenta de ejemplo con un titular y un saldo inicial
        Cuenta cuenta = new Cuenta("Juan Pérez", 1000);

        // Mostramos el menú principal y pedimos al usuario que inicie sesión
        boolean autenticado = false;
        while (!autenticado) {
            System.out.println("¡Bienvenido al Cajero Automático!");
            System.out.println("Por favor, introduzca su número de cuenta:");
            String numeroCuenta = scanner.nextLine();
            System.out.println("Introduzca su código PIN:");
            String pin = scanner.nextLine();

            autenticado = cajero.iniciarSesion(numeroCuenta, pin, cuenta);
            if (!autenticado) {
                System.out.println("Número de cuenta o PIN incorrecto. Por favor, inténtelo de nuevo.");
            }
        }

        // Una vez autenticado el usuario, mostramos el menú principal
        int opcion = 0;
        while (opcion != 5) {
            System.out.println("\nMenú principal:");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Recargar cuenta");
            System.out.println("3. Retirar efectivo");
            System.out.println("4. Realizar transferencia");
            System.out.println("5. Salir");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiamos el buffer de entrada

            switch (opcion) {
                case 1:
                    System.out.println("\nSaldo actual: $" + cuenta.getSaldo());
                    cajero.mostrarExtracto(cuenta);
                    break;
                case 2:
                    System.out.println("\nPor favor, introduzca la cantidad que desea recargar:");
                    int cantidadRecarga = scanner.nextInt();
                    scanner.nextLine(); // Limpiamos el buffer de entrada

                    cajero.recargarCuenta(cantidadRecarga, cuenta);
                    break;
                case 3:
                    System.out.println("\nPor favor, introduzca la cantidad que desea retirar:");
                    int cantidadRetiro = scanner.nextInt();
                    scanner.nextLine(); // Limpiamos el buffer de entrada

                    cajero.retirarEfectivo(cantidadRetiro, cuenta);
                    break;
                case 4:
                    System.out.println("\nPor favor, introduzca el número de cuenta a la que desea transferir:");
                    String numeroCuentaDestino = scanner.nextLine();
                    System.out.println("Por favor, introduzca la cantidad que desea transferir:");
                    int cantidadTransferencia = scanner.nextInt();
                    scanner.nextLine(); // Limpiamos el buffer de entrada

                    cajero.realizarTransferencia(cantidadTransferencia, cuenta, numeroCuentaDestino);
                    break;
                case 5:
                    System.out.println("Gracias por usar el Cajero Automático");
                    break;
                default:
                    System.out.println("Opción inválida, por favor seleccione una opción válida.");
                    break;
            }
        }
    }

    // Método para iniciar sesión en la cuenta
    public boolean iniciarSesion(String numeroCuenta, String pin, Cuenta cuenta) {
        return cuenta.getNumeroCuenta().equals(numeroCuenta) && cuenta.getPin().equals(pin
}

// Método para mostrar el extracto de transacciones de la cuenta
public void mostrarExtracto(Cuenta cuenta) {
    System.out.println("\nExtracto de transacciones:");
    for (Transaccion transaccion : cuenta.getTransacciones()) {
        System.out.println(transaccion);
    }
}

// Método para recargar la cuenta
public void recargarCuenta(int cantidadRecarga, Cuenta cuenta) {
    cuenta.setSaldo(cuenta.getSaldo() + cantidadRecarga);

    Transaccion transaccion = new Transaccion(cantidadRecarga, "Recarga de cuenta");
    cuenta.agregarTransaccion(transaccion);

    System.out.println("\nRecarga realizada con éxito. Saldo actual: $" + cuenta.getSaldo());
}

// Método para retirar efectivo de la cuenta
public void retirarEfectivo(int cantidadRetiro, Cuenta cuenta) {
    if (cantidadRetiro <= cuenta.getSaldo()) {
        cuenta.setSaldo(cuenta.getSaldo() - cantidadRetiro);

        Transaccion transaccion = new Transaccion(cantidadRetiro, "Retiro de efectivo");
        cuenta.agregarTransaccion(transaccion);

        System.out.println("\nRetiro realizado con éxito. Saldo actual: $" + cuenta.getSaldo());
    } else {
        System.out.println("\nNo se puede retirar más de lo que hay en la cuenta.");
    }
}

// Método para realizar una transferencia a otra cuenta
public void realizarTransferencia(int cantidadTransferencia, Cuenta cuentaOrigen, String numeroCuentaDestino) {
    // Buscamos la cuenta destino en la base de datos (en este ejemplo, asumimos que solo hay una cuenta)
    Cuenta cuentaDestino = new Cuenta("Ana García", 0);
    if (cuentaDestino.getNumeroCuenta().equals(numeroCuentaDestino)) {
        if (cantidadTransferencia <= cuentaOrigen.getSaldo()) {
            cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - cantidadTransferencia);
            cuentaDestino.setSaldo(cuentaDestino.getSaldo() + cantidadTransferencia);

            Transaccion transaccionOrigen = new Transaccion(cantidadTransferencia, "Transferencia a cuenta " + numeroCuentaDestino);
            Transaccion transaccionDestino = new Transaccion(cantidadTransferencia, "Transferencia recibida de cuenta " + cuentaOrigen.getNumeroCuenta());

            cuentaOrigen.agregarTransaccion(transaccionOrigen);
            cuentaDestino.agregarTransaccion(transaccionDestino);

            System.out.println("\nTransferencia realizada con éxito. Saldo actual: $" + cuentaOrigen.getSaldo());
        } else {
            System.out.println("\nNo se puede transferir más de lo que hay en la cuenta.");
        }
    } else {
        System.out.println("\nNo se encontró la cuenta destino.");
    }
}
}

class Cuenta {
private String numeroCuenta;
private String pin;
private String titular;
private int saldo;
private List<Transaccion> transacciones;