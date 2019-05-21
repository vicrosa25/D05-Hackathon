package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.ReporteRepository;
import domain.Reporte;


@Component
@Transactional
public class StringToReporteConverter implements Converter<String, Reporte> {

		@Autowired
		ReporteRepository reporteRepository;

		@Override
		public Reporte convert(String text) {
			Reporte result;
			int id;
			
			try{
				if(StringUtils.isEmpty(text))
					result = null;
				else {
					id = Integer.valueOf(text);
					result = this.reporteRepository.findOne(id);
				}
			}catch(Throwable oops){
				throw new IllegalArgumentException(oops);
			}
			return result;
		}
}
