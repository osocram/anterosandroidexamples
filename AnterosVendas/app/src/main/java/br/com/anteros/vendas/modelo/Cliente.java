/*
 * Copyright 2016 Anteros Tecnologia
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.anteros.vendas.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.anteros.bean.validation.constraints.Email;
import br.com.anteros.bean.validation.constraints.Length;
import br.com.anteros.bean.validation.constraints.Required;
import br.com.anteros.persistence.metadata.annotation.Cascade;
import br.com.anteros.persistence.metadata.annotation.Column;
import br.com.anteros.persistence.metadata.annotation.Entity;
import br.com.anteros.persistence.metadata.annotation.Enumerated;
import br.com.anteros.persistence.metadata.annotation.Fetch;
import br.com.anteros.persistence.metadata.annotation.GeneratedValue;
import br.com.anteros.persistence.metadata.annotation.Id;
import br.com.anteros.persistence.metadata.annotation.Table;
import br.com.anteros.persistence.metadata.annotation.TableGenerator;
import br.com.anteros.persistence.metadata.annotation.Temporal;
import br.com.anteros.persistence.metadata.annotation.type.CascadeType;
import br.com.anteros.persistence.metadata.annotation.type.EnumType;
import br.com.anteros.persistence.metadata.annotation.type.FetchMode;
import br.com.anteros.persistence.metadata.annotation.type.FetchType;
import br.com.anteros.persistence.metadata.annotation.type.GeneratedType;
import br.com.anteros.persistence.metadata.annotation.type.TemporalType;
import br.com.anteros.validation.api.constraints.Past;
import br.com.anteros.validation.api.constraints.Pattern;
import br.com.anteros.validation.api.constraints.Size;
import br.com.anteros.validation.api.groups.Default;

/**
 * Created by edson on 09/05/16.
 */

@Entity
@Table(name = "CLIENTE")
public class Cliente implements Serializable {

    /*
     * Id do Cliente
     */
    @Id
    @GeneratedValue(strategy = GeneratedType.TABLE)
    @TableGenerator(value = "SEQ_CLIENTE", name = "SEQUENCIA", initialValue = 1, pkColumnName = "ID_SEQUENCIA", valueColumnName = "NR_SEQUENCIA")
    @Column(name = "ID_CLIENTE", required = true, length = 8)
    private Long id;

    /*
     * Razão social do cliente
     */
    @Required(groups = {Default.class, ValidacaoPadrao.class})
    @Column(name = "RAZAO_SOCIAL", required = true, length = 50, label = "Razão social")
    private String razaoSocial;

    /*
     * Nome fantasia do cliente
     */
    @Required(groups = {Default.class, ValidacaoPadrao.class})
    @Column(name = "FANTASIA", required = true, length = 40, label = "Nome fantasia")
    private String nomeFantasia;

    /*
     * Tipo de logradouro: RUA, AVENIDA, etc
     */
    @Required(groups = {Default.class, ValidacaoPadrao.class})
    @Enumerated(EnumType.STRING)
    @Column(name = "TP_LOGRADOURO", length = 20, required = true, label = "Tipo de logradouro")
    private TipoLogradouro tpLogradouro;

    /*
     * Logradouro do cliente
     */
    @Required(groups = {Default.class, ValidacaoPadrao.class})
    @Column(name = "LOGRADOURO", length = 40, required = true, label = "Logradouro")
    private String logradouro;

    /*
     * Número do logradouro do cliente
     */
    @Required(groups = {Default.class, ValidacaoPadrao.class})
    @Column(name = "NR_LOGRADOURO", length = 20, required = true, label = "Número")
    private String nrLogradouro;

    /*
     * Cep do cliente
     */
    @Length(min = 8, max = 8)
    @Required(groups = {Default.class, ValidacaoPadrao.class})
    @Column(name = "CEP", length = 8, required = true, label = "CEP")
    private String cep;

    /*
     * Bairro do cliente
     */
    @Required(groups = {Default.class, ValidacaoPadrao.class})
    @Column(name = "BAIRRO", length = 30, required = true, label = "Bairro")
    private String bairro;

    /*
     * Complemento
     */
    @Column(name = "COMPLEMENTO", length = 30, label = "Complemento")
    private String complemento;

    /*
     * Cidade do cliente
     */
    @Required(groups = {Default.class, ValidacaoPadrao.class})
    @Column(name = "DS_CIDADE", length = 40, required = true, label = "Cidade")
    private String Cidade;

    /*
     * Estado do cliente
     */
    @Required(groups = {Default.class, ValidacaoPadrao.class})
    @Column(name = "UF", required = true, length = 2, label = "Estado")
    private Estado estado;

    /*
     * Data do cadastro do cliente
	 */
    @Past
    @Required(groups = {Default.class, ValidacaoPadrao.class})
    @Temporal(TemporalType.DATE_TIME)
    @Column(name = "DT_CADASTRO", required = true, label = "Data do cadastro")
    private Date dtCadastro;


    @Length(min = 10, max = 11)
    @Required(groups = {Default.class, ValidacaoPadrao.class})
    @Column(name = "TELEFONE", length = 11, required = true, label = "Telefone")
    private String telefone;

    @Email
    @Required(groups = {Default.class, ValidacaoPadrao.class})
    @Column(name = "EMAIL", required = true, length = 50, label = "Email")
    private String email;

    /*
     * Anexos do cliente: FOTOS, DOCUMENTOS, CONTRATOS, etc
     */
    @Fetch(type = FetchType.LAZY, mode = FetchMode.ONE_TO_MANY, mappedBy = "cliente")
    @Cascade(values = {CascadeType.ALL})
    private List<Anexo> anexos;


    public Cliente() {
    }

    public Long getId() {
        if (id == null) {
            id = new Long(0);
        }
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public TipoLogradouro getTpLogradouro() {
        return tpLogradouro;
    }

    public void setTpLogradouro(TipoLogradouro tpLogradouro) {
        this.tpLogradouro = tpLogradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNrLogradouro() {
        return nrLogradouro;
    }

    public void setNrLogradouro(String nrLogradouro) {
        this.nrLogradouro = nrLogradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public br.com.anteros.vendas.modelo.Estado getEstado() {
        return estado;
    }

    public void setEstado(br.com.anteros.vendas.modelo.Estado est) {
        estado = est;
    }

    public List<Anexo> getAnexos() {
        if (anexos == null) {
            anexos = new ArrayList<Anexo>();
        }
        return anexos;
    }

    public void setAnexos(List<Anexo> anexos) {
        this.anexos = anexos;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        return id != null ? id.equals(cliente.id) : cliente.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return id + " " + razaoSocial;
    }
}
