package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.ComentarioRepository;
import domain.Comentario;


@Component
@Transactional
public class StringToComentarioConverter implements Converter<String, Comentario> {

		@Autowired
		ComentarioRepository comentarioRepository;

		@Override
		public Comentario convert(String text) {
			Comentario result;
			int id;
			
			try{
				if(StringUtils.isEmpty(text))
					result = null;
				else {
					id = Integer.valueOf(text);
					result = this.comentarioRepository.findOne(id);
				}
			}catch(Throwable oops){
				throw new IllegalArgumentException(oops);
			}
			return result;
		}
}
