package br.com.roma.infraestructure.service.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import br.com.roma.domain.model.service.EnvioEmailService;
import br.com.roma.infraestructure.core.email.EmailProperties;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;

@Service
public class SmtpEnvioEmailService implements EnvioEmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private Configuration freemarkerConfig;
	
	@Autowired
	private EmailProperties emailProperties;

	@Override
	public void enviar(Mensagem mensagem) {
		try {
			String corpo = processarTemplate(mensagem);
			
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
					
					helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
					helper.setFrom(emailProperties.getRemetente());
					helper.setSubject(mensagem.getAssunto());
					helper.setText(corpo,true);
					
					mailSender.send(mimeMessage);
		
		} catch (MessagingException e) {
					throw new EmailException("Email Não Enviado !!", e);
			}
		
	}
	
	private String processarTemplate(Mensagem mensagem) {
	try {
		Template template = freemarkerConfig.getTemplate(mensagem.getCorpo());
			
		return FreeMarkerTemplateUtils.processTemplateIntoString(
					template, mensagem.getVariaveis());	
		
	} catch (Exception e) { 
		throw new EmailException("Não foi possivel montar o template do Email", e);
	} 

	}
	
	


}
