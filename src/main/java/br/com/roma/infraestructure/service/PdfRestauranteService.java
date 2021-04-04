package br.com.roma.infraestructure.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.roma.domain.model.dto.RestauranteRelatorio;
import br.com.roma.domain.model.service.RestauranteReportService;
import br.com.roma.domain.model.service.infraestructure.RestauranteReportServicePDF;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class PdfRestauranteService implements RestauranteReportServicePDF{
	
	@Autowired
	RestauranteReportService restuaranteReportService;

	@Override
	public byte[] emitirReportRestaurantePDF()  {
		
		
		try {
		
		InputStream inputStream = this.getClass().
				getResourceAsStream("/Relatorios/Restaurante2.jasper");
		
		HashMap parametros = new HashMap<String, Object>();
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		
		List<RestauranteRelatorio> restauranteRelatorio = 
				restuaranteReportService.emitirReportRestaurante();
		
		JRBeanCollectionDataSource dataSource =
					new  JRBeanCollectionDataSource(restauranteRelatorio);
	
			JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,dataSource);
		

		return	JasperExportManager.exportReportToPdf(jasperPrint);
			
		} catch (JRException e) {	
			throw new ReportException("RELATORIO NAO ENCONTRADO");
		}

	}
	
	

}
