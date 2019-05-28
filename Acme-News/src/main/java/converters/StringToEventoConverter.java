package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import domain.Evento;
import repositories.EventoRepository;


@Component
@Transactional
public class StringToEventoConverter implements Converter<String, Evento> {

		@Autowired
		EventoRepository eventoRepository;

		@Override
		public Evento convert(String text) {
			Evento result;
			int id;
			
			try{
				if(StringUtils.isEmpty(text))
					result = null;
				else {
					id = Integer.valueOf(text);
					result = this.eventoRepository.findOne(id);
				}
			}catch(Throwable oops){
				throw new IllegalArgumentException(oops);
			}
			return result;
		}
}
