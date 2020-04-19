package com.MYGE.x00033719;

public final class CalculadoraImpuestos {

    private static double totalRenta;
    private static double totalISSS;
    private static double totalAFP;

    private CalculadoraImpuestos() { }


    public static double calcularPago(Empleado Contratado){
        double pagofinal = 0;

        if(Contratado  instanceof PlazaFija){
            totalAFP += (Contratado.getSalario() * 0.0625);
            totalISSS += (Contratado.getSalario() * 0.03);

            double AFP = (Contratado.getSalario() * 0.0625);
            double ISSS = (Contratado.getSalario() * 0.03);
            double restante = (Contratado.getSalario() - AFP - ISSS);

            double renta = 0;
            if(restante >= 0.01 && restante <= 472.00)
                totalRenta += 0;
            else if(restante >= 472.01 && restante <= 895.24){
                totalRenta += 0.1*(restante - 472) + 17.67;
                renta = 0.1*(restante - 472) + 17.67;
            }
            else if(restante >= 895.25 && restante <= 2038.10){
                totalRenta += 0.2*(restante - 895.24) + 60;
                renta = 0.2*(restante - 895.24) + 60;
            }
            else if(restante >= 2038.11){
                totalRenta += 0.3*(restante - 2038.10) + 288.57;
                renta = 0.3*(restante - 2038.10) + 288.57;
            }
            pagofinal = restante - renta;

        }else if(Contratado instanceof ServicioProfesional){
            totalRenta += (Contratado.getSalario() * 0.1);
            double renta = (Contratado.getSalario() * 0.1);

            pagofinal = (Contratado.getSalario() - renta);

        }
        return pagofinal;
    }

    
}
