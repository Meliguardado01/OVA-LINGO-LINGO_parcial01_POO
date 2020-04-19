package com.MYGE.x00033719;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

abstract class Empleado {


    protected String nombre;
    protected String puesto;
    protected List<Documento> documentos;
    protected double salario;

    public Empleado(String nombre, String puesto,double salario) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
        documentos = new ArrayList<>();
    }


    public String getNombre() {
        return nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void addDocumento(Documento agregar){

        documentos.add(agregar);
    }

    public void removeDocumento(String nombre) throws NotExistingDocumentException {

        AtomicInteger one = new AtomicInteger(0);
        AtomicInteger two = new AtomicInteger(0);
        documentos.forEach(empleado-> {
                    if( empleado.getNombre().equals(nombre) ){
                        two.set(documentos.indexOf(empleado));
                        one.set(1);
                    }
                }
        );
        int aux = one.get();

        if(aux != 1){
            throw new NotExistingDocumentException("Este empleado no esta registrado.");
        }
        documentos.remove(two.get());

    }



    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", puesto='" + puesto + '\'' +
                ", documentos=" + documentos +
                ", salario=" + salario +
                '}';
    }
}
