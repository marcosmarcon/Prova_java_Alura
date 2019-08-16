package br.com.casadocodigo.loja.controllers;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.models.Pedidos;

@RequestMapping("/pedidos")
@Controller
public class PedidosServicoController {

    @Autowired
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    public Callable<ModelAndView> listar(RedirectAttributes model) {
        return () -> {
            try {
                String url ="https://book-payment.herokuapp.com/orders";

                Pedidos[] response = restTemplate.getForObject(url
                    ,Pedidos[].class);

                System.out.println(response.toString());

                ModelAndView modelAndView = new ModelAndView("pedidos");

                modelAndView.addObject("response", response);
                return modelAndView;
            } catch (HttpClientErrorException e) {
                e.printStackTrace();
                return new ModelAndView("redirect:/pedidos");
            }
        };
    }
}