package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.Tasa;

@Component
@Transactional
public class TasaToStringConverter implements Converter<Tasa, String> {
	
	@Override
	public String convert(Tasa tasa) {
		String result;
		if(tasa == null)
			result = null;
		else{
			result = String.valueOf(tasa.getId());
		}
		return result;
	}

}
