package com.MYGE.x00033719;

import javax.swing.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Integer.parseInt;

public class Main {

    public static void main(String[] args) {

        boolean on = true;
        Empresa empresa = new Empresa(JOptionPane.showInputDialog(null, "Ingrese el nombre de su empresa: "));


        do {
            switch (printMenu(empresa)) {

                case 1:
                    Empleado emp = añadirEmpleado(empresa);
                    if (emp == null) {
                        break;
                    }

                    empresa.addEmpleado(emp);
                    JOptionPane.showMessageDialog(null, "Empleado ingresado exitosamente.");

                    break;

                case 2:

                    String name = JOptionPane.showInputDialog(null, "Ingrese el nombre del empleado a despedir: ");
                    try {
                        empresa.quitEmpleado(name);
                    } catch (NotExistingWorkerException ex) {
                        System.out.println(ex.getMessage());
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                        break;
                    }
                    JOptionPane.showMessageDialog(null, "Lo sentimos, usted ha sido despedido :( F");
                    break;

                case 3:

                    String mostrando = mostrandoEmpleados(empresa);
                    JOptionPane.showMessageDialog(null, mostrando);
                    break;

                case 4:
                    double salary = 0;
                    String nameEmp = JOptionPane.showInputDialog(null, "Ingrese el nombre del empleado: ");
                    try {
                        salary = calculandoSalario(nameEmp, empresa.getPlanilla());
                    } catch (NotExistingWorkerException ex) {
                        System.out.println(ex.getMessage());
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                    JOptionPane.showMessageDialog(null, "El salario del empleado " + nameEmp
                            + " luego de aplicar descuentos es de: \n $" + salary);
                    break;


                case 5:

                    JOptionPane.showMessageDialog(null, CalculadoraImpuestos.mostrarTotales());
                    break;

                case 6:
                    JOptionPane.showMessageDialog(null, "ADIOS, VUELVA PRONTO.");
                    on = false;
                    break;
                default:
                    break;

            }

        } while (on);

    }

    public static int printMenu(Empresa empresa) {

        int x = 0;
        try{
            x = Integer.parseInt(JOptionPane.showInputDialog(null, "MENU DE LA EMPRESA: " + empresa.getNombre() +
                    "\n 1- Agregar Empleado.\n" +
                    " 2- Despedir Empleado.\n" +
                    " 3- Ver lista de Empleados.\n" +
                    " 4- Calcular Sueldo.\n" +
                    " 5- Mostrar Totales.\n" +
                    " 6- SALIR.\n" +
                    " Su opcion es: "));
            if (x < 1 || x > 6) {
                throw new WrongNumberException("ERROR,opcion invalida");
            }

        } catch (WrongNumberException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return 0;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "ERROR, ingrese una opcion valida");

            return 0;
        }

        return x;
    }

    public static Empleado añadirEmpleado(Empresa emp) {
        Empleado empleado = null;
        Documento d ;
        boolean continuar = true;
        int numero = 0;
        int opc;
        try {
            opc = parseInt(JOptionPane.showInputDialog(null, "Que clase de empleo tiene: \n1-Servicio Profesional \n2-Plaza Fija"));
            if (opc < 1 || opc > 2) {
                throw new WrongNumberException("Digite una opcion valida");
            }
        } catch (WrongNumberException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return null;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error again, vuelva a digitar");
            return null;
        }
        String nombre = JOptionPane.showInputDialog(null, "Digite el nombre del empleado");
        String puesto = JOptionPane.showInputDialog(null, "Digite el puesto del empleado");
        d = añadirDocumento();
        JOptionPane.showMessageDialog(null, "El Documento se ha registrado exitosamente.");



        double salario = 0;
        try {
            salario = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el Salario del empleado: "));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "ERROR, Digite lo que se le pide");
            return null;
        }


        switch (opc) {

            case 1:
                int meses = 0;
                try {
                    meses = parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de meses a contratar el servicio profesional: "));
                    empleado = new ServicioProfesional(nombre, puesto, salario, meses);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, "ERROR, Digite correctamente el numero de meses");
                    return null;
                }
                break;

            case 2:
                int extension = 0;
                try {
                    extension = parseInt(JOptionPane.showInputDialog(null,
                            "Ingrese la extension (Numero telefonico) del nuevo empleado: "));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, "Ocurrio un error al ingresar el numero telefonico, intentelo de nuevo!");
                    return null;

                }
                empleado = new PlazaFija(nombre, puesto, salario, extension);
                break;


        }
        return empleado;

    }
    public static String mostrandoEmpleados(Empresa empresa) {

        List<Empleado> print = empresa.getPlanilla();

        AtomicReference<String> empleados = new AtomicReference<>("Lista empleados de la Empresa: \n");

        print.forEach(e -> {
            if (e instanceof PlazaFija) {
                empleados.set(empleados + "\nNOMBRE: " + e.getNombre() +
                        "\nPUESTO: " + e.getPuesto() +
                        "\nSALARIO: " + e.getSalario() +
                        "\nEXTENSION: " + ((PlazaFija) e).getExtension() +
                        "\n(PLAZA FIJA)");
            } else if (e instanceof ServicioProfesional) {
                empleados.set(empleados + "NOMBRE: " + e.getNombre() +
                        "\nPUESTO: " + e.getPuesto() +
                        "\nSALARIO: " + e.getSalario() +
                        "\nDURACION DE CONTRATO: " + ((ServicioProfesional) e).getMesesContrato() +
                        "\n(SERVICIO PROFESIONAL)");
            }
        });
        return empleados.get();
    }
    public static double calculandoSalario(String name, List<Empleado> emp) throws NotExistingWorkerException {
        AtomicReference<Double> total = new AtomicReference<>((double) 0);
        AtomicInteger calculadora = new AtomicInteger(0);
        emp.forEach(e -> {
                    if (e.getNombre().equals(name)) {
                        total.set(CalculadoraImpuestos.calcularPago(e));
                        calculadora.set(1);
                    }
                }
        );
        int aux = calculadora.get();

        if (aux != 1) {
            throw new NotExistingWorkerException("El nombre del empleado no se encuentra en la base de datos!");
        }

        return total.get();
    }
    public static Documento añadirDocumento(){
        Empleado employee = null;
        Documento info = null;
        boolean continuar = true;
        int numero=0;

        try{
            numero = parseInt(JOptionPane.showInputDialog(null, "Cuando documentos desea agregar (MINIMO 1): "));
            if (numero < 1) {
                throw new WrongNumberException("Digite una opcion valida");
            }
        } catch (WrongNumberException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return null;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error again, vuelva a digitar");
            return null;
        }
        do {
            if (numero < 1) {
                JOptionPane.showInputDialog(null, "Ingrese un numero mayor que uno.");
            } else if(numero >= 1) {
                for(int i = 0; i< numero; i++ ){
                    String name = JOptionPane.showInputDialog(null, "Ingrese el nombre del documento: ");
                    String number = JOptionPane.showInputDialog(null, "Ingrese el numero del documento: ");
                    info = new Documento(name, number);
                    if (employee != null) {
                        employee.addDocumento(info);
                    }
                }
                continuar = false;
            }
        }while(continuar);


        return info;
    }
}
