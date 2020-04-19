package com.MYGE.x00033719;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Empresa {

        private String nombre;
        private List<Empleado> planilla;

        public Empresa(String nombre) {
            this.nombre = nombre;
            planilla = new ArrayList<>();
        }

        public String getNombre() {
            return nombre;
        }

        public List<Empleado> getPlanilla() {
            return planilla;
        }

        public void addEmpleado(Empleado agregar){

            planilla.add(agregar);

        }

        public void quitEmpleado(String nombre) throws NotExistingWorkerException {

            AtomicInteger one = new AtomicInteger(0);
            AtomicInteger two = new AtomicInteger(0);
            planilla.forEach(empleado-> {
                        if( empleado.getNombre().equals(nombre) ){
                            two.set(planilla.indexOf(empleado));
                            one.set(1);
                        }
                    }
            );
            int aux = one.get();

            if(aux != 1){
                throw new NotExistingWorkerException("Este empleado no esta registrado.");
            }
            planilla.remove(two.get());

        }

        @Override
        public String toString() {
            return "Empresa{" +
                    "nombre='" + nombre + '\'' +
                    ", planilla=" + planilla +
                    '}';
        }
    }


