/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Victor Aguiar
 */
public class Restricao {

    public int restricao_id;
    public String restricao_nome;
    public String restricao_tipo;
    public boolean restricao_status;

    //Construtor Vazio
    public Restricao() {

    }

    public Restricao(int restricao_id, String restricao_nome, String restricao_tipo, boolean restricao_status) {
        this.restricao_id = restricao_id;
        this.restricao_nome = restricao_nome;
        this.restricao_tipo = restricao_tipo;
        this.restricao_status = restricao_status;
    }

    public int getRestricao_id() {
        return restricao_id;
    }

    public void setRestricao_id(int restricao_id) {
        this.restricao_id = restricao_id;
    }

    public String getRestricao_nome() {
        return restricao_nome;
    }

    public void setRestricao_nome(String restricao_nome) {
        this.restricao_nome = restricao_nome;
    }

    public String getRestricao_tipo() {
        return restricao_tipo;
    }

    public void setRestricao_tipo(String restricao_tipo) {
        this.restricao_tipo = restricao_tipo;
    }

    public boolean isRestricao_status() {
        return restricao_status;
    }

    public void setRestricao_status(boolean restricao_status) {
        this.restricao_status = restricao_status;
    }

}
