package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import repositories.TasaRepository;
import domain.Tasa;

@Component
@Transactional
public class StringToTasaConverter implements Converter<String, Tasa>{

	@Autowired
	public TasaRepository tasaRepository;
	
	@Override
	public Tasa convert(String text) {
		Tasa result;
		int id;
		try {
			if(StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = tasaRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		
		return result;
	}
	
}
