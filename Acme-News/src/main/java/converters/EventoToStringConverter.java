package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Evento;


@Component
@Transactional
public class EventoToStringConverter implements Converter<Evento, String> {

	@Override
	public String convert(Evento evento) {
		String result;
		if(evento == null)
			result = null;
		else
			result = String.valueOf(evento.getId());
		return result;
	}

}
