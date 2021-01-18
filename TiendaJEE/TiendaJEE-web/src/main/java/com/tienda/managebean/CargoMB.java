/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tienda.managebean;

import com.tienda.entidades.Cargo;
import com.tienda.session.CargoFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author BYRONTOSH
 */
@Named(value = "cargoMB")
@ViewScoped
public class CargoMB implements Serializable, ManagedBeanInterface<Cargo>{
    // PASO 1.- INSTANCIAR AL SESSION BEAN
    @EJB
    private CargoFacadeLocal cargoFacadeLocal;

    // PASO 2.- INICIALIZAR EL SESSION
    @PostConstruct
    public void init() {
        listaCargos = cargoFacadeLocal.findAll();
    }

    private List<Cargo> listaCargos;

    private Cargo cargo;
    
    
    
    public CargoMB() {
    }

    @Override
    public void nuevo() {
     cargo = new Cargo();   
    }

    @Override
    public void grabar() {
        try {
            if (cargo.getCodigo() == null) {
                cargoFacadeLocal.create(cargo);
            } else {
                cargoFacadeLocal.edit(cargo);
            }
            cargo = null;
            listaCargos = cargoFacadeLocal.findAll();
            mostrarMnesajeTry("INFORMACIÓN OK", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            mostrarMnesajeTry("OCURRIO UN ERROR", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void seleccionar(Cargo c) {
     cargo = c;   
    }

    @Override
    public void eliminar(Cargo c) {
        try {
            cargoFacadeLocal.remove(c);
            listaCargos = cargoFacadeLocal.findAll();
            mostrarMnesajeTry("INFORMACIÓN OK", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            mostrarMnesajeTry("OCURRIO UN ERROR", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void cancelar() {
        cargo=null;
    }

    public CargoFacadeLocal getCargoFacadeLocal() {
        return cargoFacadeLocal;
    }

    public void setCargoFacadeLocal(CargoFacadeLocal cargoFacadeLocal) {
        this.cargoFacadeLocal = cargoFacadeLocal;
    }

    public List<Cargo> getListaCargos() {
        return listaCargos;
    }

    public void setListaCargos(List<Cargo> listaCargos) {
        this.listaCargos = listaCargos;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
    
    
}
