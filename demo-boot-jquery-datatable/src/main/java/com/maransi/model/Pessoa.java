package com.maransi.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maransi.util.CpfCnpj;
import com.maransi.util.UF;


@Entity
@Table( indexes = { @Index( name="idxPessoa_CPF", columnList="cpf") } )
@NamedQueries({ @NamedQuery(name="pessoa.cpf", query="Select p from Pessoa p Where p.cpf = :cpf" ),
				@NamedQuery(name="pessoa.nome", query="Select p from Pessoa p Where p.nome like :nome")
			  })
public class Pessoa {

	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY)
	private Long sequencial;
	
	@Column(nullable=false)
	@NotNull(message="CPF não pode ser vazio")
	@CpfCnpj
	private String cpf;
	
	@Column( length=100, nullable=false)
	@NotNull(message="Nome não pode ser vazio")
	@Size(min=10, max=100, message="Nome deve conter tamanho minimo de 10 dígitos")
	private String nome;
	
	@Column(name="datnasc", columnDefinition="DATE", nullable=false)
//	@NotNull(message="Data Inválida")
	@DateTimeFormat( iso = ISO.DATE)
//	@Past(message="Data inválida")
	private LocalDate datNasc;
	
	@Column(length=150, nullable=false)
	@NotNull(message="Endereço não pode ser vazio")
	@Size(min=10, max=150, message="Nome deve conter tamanho minimo de 10 dígitos")
	private String endereco;
	
	@Column(length=100, nullable=false)
	@NotNull(message="Cidade não pode ser vazio")
	@Size(min=10, max=100, message="Cidade deve conter tamanho minimo de 10 dígitos")
	private String cidade;
	
	@Column(nullable=false, length=2, name="estado")
	@NotNull(message="Estado não pode ser vazio")
	@Enumerated(EnumType.STRING)	// Informa ao bd que o conteúdo deverá ser salvo como STRING
	private UF estado;
	
	@Column(length=10, nullable=false)
	@NotNull(message="CEP não pode ser vazio")
	private String cep;
	
	@Column(name = "foneres", length=30)
	private String foneRes;
	
	@Positive( message="Salário inválido")
	@NumberFormat(style=Style.CURRENCY, pattern = "###,###,##0.00" )
	@Digits( integer = 9, fraction = 2)
	private BigDecimal salario;

	public Pessoa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pessoa( @JsonProperty("cpf") String cpf, @JsonProperty("nome") String nome, 
			@JsonProperty("datNasc") LocalDate datNasc, @JsonProperty("endereco") String endereco, 
			@JsonProperty("cidade") String cidade, @JsonProperty("cep") String cep,
			@JsonProperty("foneRes") String foneRes, @JsonProperty("salario") BigDecimal salario, 
			@JsonProperty("estado") UF estado) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.datNasc = datNasc;
		this.endereco = endereco;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.foneRes = foneRes;
		this.salario = salario;
	}

	public Long getSequencial() {
		return sequencial;
	}

	public void setSequencial(Long sequencial) {
		this.sequencial = sequencial;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDatNasc() {
		return datNasc;
	}

	public void setDatNasc(LocalDate datNasc) {
		this.datNasc = datNasc;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public UF getEstado() {
		return estado;
	}

	public void setEstado(UF estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getFoneRes() {
		return foneRes;
	}

	public void setFoneRes(String foneRes) {
		this.foneRes = foneRes;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((foneRes == null) ? 0 : foneRes.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sequencial == null) ? 0 : sequencial.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (foneRes == null) {
			if (other.foneRes != null)
				return false;
		} else if (!foneRes.equals(other.foneRes))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sequencial == null) {
			if (other.sequencial != null)
				return false;
		} else if (!sequencial.equals(other.sequencial))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\n Pessoa [sequencial=" + sequencial + ", cpf=" + cpf + ", nome=" + nome + ", endereco=" + endereco
				+ ", cidade=" + cidade + ", estado=" + estado + ", cep=" + cep + ", foneRes=" + foneRes + "]";
	}
	
	
	
}
