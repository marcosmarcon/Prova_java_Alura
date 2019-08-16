package br.com.casadocodigo.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Query;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class Relatorio implements Serializable {
		
	private static final long serialVersionUID = 1L;


	@DateTimeFormat
	private Calendar dataGeracao;
	private Object quantidade;
	
	@ElementCollection
	private List<Produto> produto = new ArrayList<>();
	
	public void setDataGeracao(Calendar dataGeracao) {
    	this.dataGeracao = dataGeracao;
    	
	}
	
	public Object getDataGeracao() {
		return dataGeracao;
	}
	

	
	public void setQuantidade(Object quantidade) {
	//	System.out.println(quantidade);
    	this.quantidade = quantidade;		
	}
	
	public Object getQuantidade() {
		return quantidade;
	}



	public List<Produto> getProduto() {
		return produto;
	}
	
	public void setProduto(List<Produto> produto) {
		System.out.println(produto);
		this.produto = produto;
	}
}
