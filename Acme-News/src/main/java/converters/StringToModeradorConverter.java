package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import domain.Moderador;
import repositories.ModeradorRepository;

@Component
@Transactional
public class StringToModeradorConverter implements Converter<String, Moderador> {

	@Autowired
	ModeradorRepository moderadorRepository;

	@Override
	public Moderador convert(String text) {
		Moderador result;
		int id;
		
		try{
			if(StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.moderadorRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
