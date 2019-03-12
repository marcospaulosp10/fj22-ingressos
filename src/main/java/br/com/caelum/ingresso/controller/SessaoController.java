package br.com.caelum.ingresso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;

/**
 * Created by nando on 03/03/17.
 */
@Controller
public class SessaoController {

	private FilmeDao filmeDao;
	private SalaDao salaDao;

    @GetMapping({"/admin/sessao"})
    public ModelAndView form(Integer salaId){
        ModelAndView mv = new ModelAndView("sessao/sessao");
    
    	mv.addObject("filmes", filmeDao.findAll());
    	
    	mv.addObject("sala", salaDao.findOne(salaId));
    	
        return mv;
    }
    
}
