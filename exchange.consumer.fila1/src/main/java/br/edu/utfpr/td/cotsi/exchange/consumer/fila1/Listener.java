package br.edu.utfpr.td.cotsi.exchange.consumer.fila1;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Listener {
	
	private final ObjectMapper objectMapper = new ObjectMapper();

	@RabbitListener(queues = "policia.federal")
	public void listen(String json) {
        try {
            Transacao transacao = objectMapper.readValue(json, Transacao.class);
            processarTransacao(transacao, json);
        } catch (Exception e) {
            System.err.println("Erro ao processar JSON: " + e.getMessage());
        }
    }
	
	public void processarTransacao(Transacao transacao, String json) {
        try {
            Thread.sleep(1000);
            System.out.println("Transação suspeita recebida pela policia federal: " + transacao);

        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}

