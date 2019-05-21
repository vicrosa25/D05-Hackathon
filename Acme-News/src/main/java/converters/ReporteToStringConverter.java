package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Reporte;


@Component
@Transactional
public class ReporteToStringConverter implements Converter<Reporte, String> {

	@Override
	public String convert(Reporte reporte) {
		String result;
		if(reporte == null)
			result = null;
		else
			result = String.valueOf(reporte.getId());
		return result;
	}

}
