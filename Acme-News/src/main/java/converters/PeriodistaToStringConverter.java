package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Periodista;


@Component
@Transactional
public class PeriodistaToStringConverter implements Converter<Periodista, String> {

	@Override
	public String convert(Periodista periodista) {
		String result;
		if(periodista == null)
			result = null;
		else
			result = String.valueOf(periodista.getId());
		return result;
	}

}
