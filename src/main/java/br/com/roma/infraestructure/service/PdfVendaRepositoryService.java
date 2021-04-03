package br.com.roma.infraestructure.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.roma.domain.filter.VendaDiariaFilter;
import br.com.roma.domain.model.dto.VendaDiaria;
import br.com.roma.domain.model.service.VendaReportService;
import br.com.roma.infraestructure.VendaQueryService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class PdfVendaRepositoryService  implements VendaReportService{
	
	@Autowired
	private VendaQueryService vendaQueryService;

	@Override
	public byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
		
		try {
		InputStream inputStream  = this.getClass().getResourceAsStream(
				"/Relatorios/Relatorio_vendas.jasper");
		
		HashMap parametros = new HashMap<String, Object>();
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		
		List<VendaDiaria> vendaDiaria = vendaQueryService.consultarVendasDiarias(filtro);
		
		JRBeanCollectionDataSource dataSource =  
				new JRBeanCollectionDataSource(vendaDiaria);
				
				
				JasperPrint jasperPrint  = JasperFillManager.fillReport(inputStream, parametros, dataSource);
		
		
		
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (JRException e) {
			throw new ReportException("Não foi possivel abrir o ralatorio",e.getCause());
			
		}
	}

}
