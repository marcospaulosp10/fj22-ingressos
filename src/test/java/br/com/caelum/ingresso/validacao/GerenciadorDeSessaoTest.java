package br.com.caelum.ingresso.validacao;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.validacao.GerenciadorDeSessao;

public class GerenciadorDeSessaoTest {

	private Filme filmeCom2Horas;
	private Sala sala3D;
	private Sessao sessaoDasDez;
	
	@Before
	public void preparaSessoes() {
		this.filmeCom2Horas = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal(20.32));
		this.sala3D = new Sala("Sala 3D", new BigDecimal(20.32));
		this.sessaoDasDez = new Sessao(LocalTime.parse("10:00:00"), filmeCom2Horas, sala3D);
	}

	@Test
	public void naoDevePermitirNovaSessaoNoMesmoHorarioDaSessaoExistente() {
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		Assert.assertFalse(gerenciador.cabe(sessaoDasDez));
	}
	
	@Test
	public void naoDevePermitirNovaSessaoComecandoNoMesmoHorarioETerminandoAntesDaSessaoExistente() {
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		
		Filme filmeCom1Hora = new Filme("The Matrix", Duration.ofMinutes(60), "SCI-FI", new BigDecimal(20.32));
		Sessao nova = new Sessao(LocalTime.parse("10:00:00"), filmeCom1Hora, sala3D);
		
		Assert.assertFalse(gerenciador.cabe(nova));
	}
	
	@Test
	public void naoDevePermitirNovaSessaoComecandoNoMesmoHorarioETerminandoDepoisDaSessaoExistente() {
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		
		Filme filmeCom3Horas = new Filme("The Matrix", Duration.ofMinutes(180), "SCI-FI", new BigDecimal(20.32));
		Sessao nova = new Sessao(LocalTime.parse("10:00:00"), filmeCom3Horas, sala3D);
		
		Assert.assertFalse(gerenciador.cabe(nova));
	}
	
	@Test
	public void naoDevePermitirNovaSessaoComecandoNoHorarioDeTerminoDaSessaoExistente() {
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		
		Filme filmeCom3Horas = new Filme("The Matrix", Duration.ofMinutes(180), "SCI-FI", new BigDecimal(20.32));
		Sessao nova = new Sessao(LocalTime.parse("12:00:00"), filmeCom3Horas, sala3D);
		
		Assert.assertFalse(gerenciador.cabe(nova));
	}
	
	@Test
	public void naoDevePermitirNovaSessaoComecandoNoMeioDaSessaoExistenteETerminandoNoHorarioDeTerminoDela() {
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		
		Filme filmeCom1Hora = new Filme("The Matrix", Duration.ofMinutes(60), "SCI-FI", new BigDecimal(20.32));
		Sessao nova = new Sessao(LocalTime.parse("11:00:00"), filmeCom1Hora, sala3D);
		
		Assert.assertFalse(gerenciador.cabe(nova));
	}
	
	@Test
	public void naoDevePermitirNovaSessaoComecandoNoMeioDaSessaoExistenteETerminandoAposHorarioDeTerminoDela() {
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		
		Filme filmeCom3Horas = new Filme("The Matrix", Duration.ofMinutes(180), "SCI-FI", new BigDecimal(20.32));
		Sessao nova = new Sessao(LocalTime.parse("11:00:00"), filmeCom3Horas, sala3D);
		
		Assert.assertFalse(gerenciador.cabe(nova));
	}
	
	@Test
	public void naoDevePermitirNovaSessaoComecandoAntesDaSessaoExistenteETerminandoNoInicioDela() {
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		
		Filme filmeCom3Horas = new Filme("The Matrix", Duration.ofMinutes(180), "SCI-FI", new BigDecimal(20.32));
		Sessao nova = new Sessao(LocalTime.parse("07:00:00"), filmeCom3Horas, sala3D);
		
		Assert.assertFalse(gerenciador.cabe(nova));
	}
	
	@Test
	public void naoDevePermitirNovaSessaoComecandoAntesDaSessaoExistenteETerminandoNoMeioDela() {
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		
		Filme filmeCom3Horas = new Filme("The Matrix", Duration.ofMinutes(180), "SCI-FI", new BigDecimal(20.32));
		Sessao nova = new Sessao(LocalTime.parse("08:00:00"), filmeCom3Horas, sala3D);
		
		Assert.assertFalse(gerenciador.cabe(nova));
	}
	
	@Test
	public void naoDevePermitirNovaSessaoComecandoAntesDaSessaoExistenteETerminandoNoTerminoDela() {
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		
		Filme filmeCom3Horas = new Filme("The Matrix", Duration.ofMinutes(180), "SCI-FI", new BigDecimal(20.32));
		Sessao nova = new Sessao(LocalTime.parse("09:00:00"), filmeCom3Horas, sala3D);
		
		Assert.assertFalse(gerenciador.cabe(nova));
	}
	
	@Test
	public void devePermitirNovaSessaoComecandoETerminandoAntesDaSessaoExistente() {
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		
		Filme filmeCom3Horas = new Filme("The Matrix", Duration.ofMinutes(180), "SCI-FI", new BigDecimal(20.32));
		Sessao nova = new Sessao(LocalTime.parse("05:00:00"), filmeCom3Horas, sala3D);
		
		Assert.assertTrue(gerenciador.cabe(nova));
	}
	
	@Test
	public void devePermitirNovaSessaoComecandoETerminandoDepoisDaSessaoExistente() {
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		
		Filme filmeCom3Horas = new Filme("The Matrix", Duration.ofMinutes(180), "SCI-FI", new BigDecimal(20.32));
		Sessao nova = new Sessao(LocalTime.parse("13:00:00"), filmeCom3Horas, sala3D);
		
		Assert.assertTrue(gerenciador.cabe(nova));
	}
	
	@Test
	public void devePermitirNovaSessaoComecandoETerminandoEntreSessoesExistentes() {
		Sessao sessaoDasDezoito = new Sessao(LocalTime.parse("18:00:00"), filmeCom2Horas, sala3D);
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez, sessaoDasDezoito);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		
		Filme filmeCom3Horas = new Filme("The Matrix", Duration.ofMinutes(180), "SCI-FI", new BigDecimal(20.32));
		Sessao nova = new Sessao(LocalTime.parse("13:00:00"), filmeCom3Horas, sala3D);
		
		Assert.assertTrue(gerenciador.cabe(nova));
	}
	
}
