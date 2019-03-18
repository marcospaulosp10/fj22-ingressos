package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;
import junit.framework.Assert;

public class DescontoTest {
	@SuppressWarnings("deprecation")
	@Test
	public void naoDeveConvederDescontoParaIngressoNormal() {
		
		Lugar lugar = new Lugar("A", 1);
		Sala sala = new Sala("Eldorado - IMAX", new BigDecimal("12.50"));
		Filme filme = new Filme("Rogue One", Duration.ofMinutes(120),
								"SCI-FI", new BigDecimal("2.05"));
		Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), filme, sala);
		Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.INTEIRO, lugar);
		BigDecimal precoEsperado = new BigDecimal("14.55");
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}
}
