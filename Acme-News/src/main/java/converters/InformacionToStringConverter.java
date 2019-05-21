package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Informacion;


@Component
@Transactional
public class InformacionToStringConverter implements Converter<Informacion, String> {

	@Override
	public String convert(Informacion informacion) {
		String result;
		if(informacion == null)
			result = null;
		else
			result = String.valueOf(informacion.getId());
		return result;
	}

}
