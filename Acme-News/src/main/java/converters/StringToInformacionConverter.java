package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.InformacionRepository;
import domain.Informacion;


@Component
@Transactional
public class StringToInformacionConverter implements Converter<String, Informacion> {

		@Autowired
		InformacionRepository informacionRepository;

		@Override
		public Informacion convert(String text) {
			Informacion result;
			int id;
			
			try{
				if(StringUtils.isEmpty(text))
					result = null;
				else {
					id = Integer.valueOf(text);
					result = this.informacionRepository.findOne(id);
				}
			}catch(Throwable oops){
				throw new IllegalArgumentException(oops);
			}
			return result;
		}
}
