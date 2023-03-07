public Cuenta(String titular, int saldo) {
    Random rnd = new Random();
    this.numeroCuenta = String.format("%09d", rnd.nextInt(1000000000));
    this.pin = String.format("%04d", rnd.nextInt(10000));
    this.titular = titular;
    this.saldo = saldo;
    this.transacciones = new ArrayList<>();
}

public String getNumeroCuenta() {
    return numeroCuenta;
}

public String getPin() {
    return pin;
}
public String getTitular() {
    return titular;
}

public int getSaldo() {
    return saldo;
}

public void setSaldo(int saldo) {
    this.saldo = saldo;
}

public List<Transaccion> getTransacciones() {
    return transacciones;
}

public void agregarTransaccion(Transaccion transaccion) {
    transacciones.add(transaccion);
}
}

class Transaccion {
private int cantidad;
private String descripcion;
private Date fecha;
public Transaccion(int cantidad, String descripcion) {
    this.cantidad = cantidad;
    this.descripcion = descripcion;
    this.fecha = new Date();
}

@Override
public String toString() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    return sdf.format(fecha) + " - " + descripcion + ": $" + cantidad;
}
}

class Main {
public static void main(String[] args) {
// Creamos una cuenta de ejemplo
Cuenta cuenta = new Cuenta("Juan Pérez", 1000);
    // Creamos un cajero automático
    CajeroAutomatico cajero = new CajeroAutomatico("Banco X");

    // Simulamos una sesión de usuario
    Scanner scanner = new Scanner(System.in);

    System.out.print("Introduzca su número de cuenta: ");
    String numeroCuenta = scanner.nextLine();

    System.out.print("Introduzca su código pin: ");
    String pin = scanner.nextLine();

    if (numeroCuenta.equals(cuenta.getNumeroCuenta()) && pin.equals(cuenta.getPin())) {
        // Mostramos las opciones disponibles
        boolean salir = false;
        while (!salir) {
            System.out.println("\nBienvenido, " + cuenta.getTitular
ccione una opción:");
System.out.println("1. Consultar saldo");
System.out.println("2. Recargar cuenta");
System.out.println("3. Sacar dinero");
System.out.println("4. Realizar transferencia");
System.out.println("5. Ver extracto de transacciones");
System.out.println("6. Salir");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    // Consultar saldo
                    System.out.println("\nSaldo actual: $" + cuenta.getSaldo());
                    break;
                case "2":
                    // Recargar cuenta
                    System.out.print("\nIntroduzca la cantidad a recargar: ");
                    int cantidadRecargar = scanner.nextInt();
                    scanner.nextLine();

                    cuenta.setSaldo(cuenta.getSaldo() + cantidadRecargar);
                    cuenta.agregarTransaccion(new Transaccion(cantidadRecargar, "Recarga de cuenta"));
                    System.out.println("\nRecarga realizada con éxito. Saldo actual: $" + cuenta.getSaldo());
                    break;
                case "3":
                    // Sacar dinero
                    System.out.print("\nIntroduzca la cantidad a sacar: ");
                    int cantidadSacar = scanner.nextInt();
                    scanner.nextLine();

                    if (cantidadSacar <= cuenta.getSaldo()) {
                        cuenta.setSaldo(cuenta.getSaldo() - cantidadSacar);
                        cuenta.agregarTransaccion(new Transaccion(cantidadSacar, "Retiro de cuenta"));
                        System.out.println("\nRetiro realizado con éxito. Saldo actual: $" + cuenta.getSaldo());
                    } else {
                        System.out.println("\nNo tiene suficiente saldo para realizar esta operación.");
                    }
                    break;
                case "4":
                    // Realizar transferencia
                    System.out.print("\nIntroduzca el número de cuenta destino: ");
                    String numeroCuentaDestino = scanner.nextLine();

                    System.out.print("Introduzca la cantidad a transferir: ");
                    int cantidadTransferir = scanner.nextInt();
                    scanner.nextLine();

                    if (cantidadTransferir <= cuenta.getSaldo()) {
                        cuenta.setSaldo(cuenta.getSaldo() - cantidadTransferir);
                        cuenta.agregarTransaccion(new Transaccion(cantidadTransferir, "Transferencia a cuenta " + numeroCuentaDestino));

                        // Simulamos la transferencia a la cuenta destino
                        System.out.println("\nTransferencia realizada con éxito.");
                        System.out.println("Se han transferido $" + cantidadTransferir + " a la cuenta " + numeroCuentaDestino + ".");
                        System.out.println("Comisión de la operación: $" + (cantidadTransferir * 0.02));
                        System.out.println("Saldo actual: $" + cuenta.getSaldo());
                    } else {
                        System.out.println("\nNo tiene suficiente saldo para realizar esta operación.");
                    }
                    break;
                case "5":
                    // Ver extracto de transacciones
                    List<Transaccion> transacciones = cuenta.getTransacciones();

                    if (transacciones.size() > 0) {
                        System.out.println("\nTransacciones realizadas:");
                        for (Transaccion transaccion : transacciones) {
                            System.out.println(transaccion.toString());
                        }
                    } else {
                        System.out.println("\nNo hay transacciones que mostrar.");
                    }
                    break;
                case "6":
                    // Salir
                    salir = true;
                    break;
                default:
                    System.out.println("\nOpción inválida.");
                    break;
            }
        }
    } else {
        System.out.println("\nNúmero de cuenta o código pin incorrectos.");
    
}
}
}