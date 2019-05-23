/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author nelson_amaral
 */
public class Vacina_has_restricao {
    private int vacina_id;
    private int restricao_id;
    private boolean status;
    
    public Vacina_has_restricao(){}

    public int getVacina_id() {
        return vacina_id;
    }

    public void setVacina_id(int vacina_id) {
        this.vacina_id = vacina_id;
    }

    public int getRestricao_id() {
        return restricao_id;
    }

    public void setRestricao_id(int restricao_id) {
        this.restricao_id = restricao_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    
}
