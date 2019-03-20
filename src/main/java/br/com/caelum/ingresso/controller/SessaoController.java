package br.com.caelum.ingresso.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Carrinho;
import br.com.caelum.ingresso.model.ImagemCapa;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;
import br.com.caelum.ingresso.model.form.SessaoForm;
import br.com.caelum.ingresso.rest.OmdbClient;

/**
 * Created by nando on 03/03/17.
 */
@Controller
public class SessaoController {

	@Autowired
	private FilmeDao filmeDao;
	
	@Autowired
	private SalaDao salaDao;
	
	@Autowired
	private SessaoDao sessaoDao;
	
	@Autowired
	private OmdbClient client;
	
	private Carrinho carrinho;

    @GetMapping("admin/sessao")
    public ModelAndView form(Integer salaId, SessaoForm form){
    	
    	form.setSalaId(salaId);
    	
        ModelAndView mv = new ModelAndView("sessao/sessao");
    
    	mv.addObject("filmes", filmeDao.findAll());
    	
    	mv.addObject("form", form);
    	
    	mv.addObject("sala", salaDao.findOne(salaId));
    	
        return mv;
    }

    @GetMapping("sessao/{id}/lugares")
    public ModelAndView lugaresNaSessao(@PathVariable Integer id){
    	ModelAndView mv = new ModelAndView("sessao/lugares");
    	
    	Sessao sessao = sessaoDao.findOne(id);
    	Optional<ImagemCapa> imagemCapa = client.request(sessao.getFilme(), ImagemCapa.class);

        mv.addObject("sessao", sessao);
        mv.addObject("carrinho", carrinho);
        mv.addObject("imagemCapa", imagemCapa.orElse(new ImagemCapa()));
        mv.addObject("tiposDeIngressos", TipoDeIngresso.values());
        
        return mv;
    }
    
    @PostMapping(value = "/admin/sessao")
    @Transactional
    public ModelAndView salva(@Valid SessaoForm form, BindingResult result) {
    	if(result.hasErrors()) return form(form.getSalaId(), form);
    	Sessao sessao = form.toSessao(salaDao, filmeDao);
    	
    	sessaoDao.save(sessao);
    	
    	return new ModelAndView("redirect:/admin/sala/" + form.getSalaId() + "/sessoes");
    }
    
}
