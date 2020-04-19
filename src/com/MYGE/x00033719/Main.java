package com.MYGE.x00033719;

import javax.swing.*;

import static java.lang.Integer.parseInt;

public class Main {

    public static void main(String[] args) {

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
}
