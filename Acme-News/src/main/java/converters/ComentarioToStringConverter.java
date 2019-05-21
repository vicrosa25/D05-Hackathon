package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Comentario;


@Component
@Transactional
public class ComentarioToStringConverter implements Converter<Comentario, String> {

	@Override
	public String convert(Comentario comentario) {
		String result;
		if(comentario == null)
			result = null;
		else
			result = String.valueOf(comentario.getId());
		return result;
	}

}
