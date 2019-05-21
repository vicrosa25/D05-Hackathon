package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import domain.Periodista;
import repositories.PeriodistaRepository;


@Component
@Transactional
public class StringToPeriodistaConverter implements Converter<String, Periodista> {

		@Autowired
		PeriodistaRepository periodistaRepository;

		@Override
		public Periodista convert(String text) {
			Periodista result;
			int id;
			
			try{
				if(StringUtils.isEmpty(text))
					result = null;
				else {
					id = Integer.valueOf(text);
					result = this.periodistaRepository.findOne(id);
				}
			}catch(Throwable oops){
				throw new IllegalArgumentException(oops);
			}
			return result;
		}
}