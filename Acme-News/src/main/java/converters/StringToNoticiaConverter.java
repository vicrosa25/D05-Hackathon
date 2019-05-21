package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.NoticiaRepository;
import domain.Noticia;


@Component
@Transactional
public class StringToNoticiaConverter implements Converter<String, Noticia> {

		@Autowired
		NoticiaRepository noticiaRepository;

		@Override
		public Noticia convert(String text) {
			Noticia result;
			int id;
			
			try{
				if(StringUtils.isEmpty(text))
					result = null;
				else {
					id = Integer.valueOf(text);
					result = this.noticiaRepository.findOne(id);
				}
			}catch(Throwable oops){
				throw new IllegalArgumentException(oops);
			}
			return result;
		}
}
