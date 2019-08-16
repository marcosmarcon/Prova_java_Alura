package br.com.casadocodigo.loja.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.Relatorio;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.validation.ProdutoValidation;

@Controller
@RequestMapping("/relatorio-produtos")
public class RelatorioProdutosController {
	
	@Autowired
	private ProdutoDAO dao;
	
	@RequestMapping(method=RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Relatorio detalheJson(@RequestParam(value="data", required=false) String dataLancamento){

		
		/*
		 * funciona enviando uma data direto 
		 * http://localhost:8080/casadocodigo/relatorio-produtos?data=2017-03-21
		 * 
		 * ou
		 * 
		 * sem enviar data nenhuma
		 * http://localhost:8080/casadocodigo/relatorio-produtos
		 * */

            Relatorio relatorio = new Relatorio() ;
            
            Date dateHj = Calendar.getInstance().getTime();
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(dateHj);
            relatorio.setDataGeracao(cal2);
            
            if(dataLancamento != null) {
            	
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                
				try {
					date = format.parse(dataLancamento);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                
                relatorio.setQuantidade(dao.findQuant(cal)); 
                relatorio.setProduto(dao.findLista(cal));
                return relatorio;
                
            }else {
            	
            	relatorio.setQuantidade(dao.findQuantSemData()); 
            	relatorio.setProduto(dao.listar());
            	return relatorio;
            }
            	

    }
	
	
}
