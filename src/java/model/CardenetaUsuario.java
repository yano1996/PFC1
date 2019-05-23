/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author nelson_amaral
 */
public class CardenetaUsuario {
    private Usuario usuario;
    private CalendarioObrigatorio calendarioObrigatorio;
  //private Campanha campanha;
    private Funcionario funcionario;
    private Vacina vacina;
  //private  Posto posto;
    
    private Date dateCadastro;
    private Date horaCadastro;
    
    
    public CardenetaUsuario() {}

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public CalendarioObrigatorio getCalendarioObrigatorio() {
        return calendarioObrigatorio;
    }

    public void setCalendarioObrigatorio(CalendarioObrigatorio calendarioObrigatorio) {
        this.calendarioObrigatorio = calendarioObrigatorio;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Vacina getVacina() {
        return vacina;
    }

    public void setVacina(Vacina vacina) {
        this.vacina = vacina;
    }

    public Date getDateCadastro() {
        return dateCadastro;
    }

    public void setDateCadastro(Date dateCadastro) {
        this.dateCadastro = dateCadastro;
    }

    public Date getHoraCadastro() {
        return horaCadastro;
    }

    public void setHoraCadastro(Date horaCadastro) {
        this.horaCadastro = horaCadastro;
    }

    
}
