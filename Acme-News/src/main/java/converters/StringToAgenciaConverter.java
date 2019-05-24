package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import domain.Agencia;
import repositories.AgenciaRepository;


@Component
@Transactional
public class StringToAgenciaConverter implements Converter<String, Agencia> {

		@Autowired
		AgenciaRepository agenciaRepository;

		@Override
		public Agencia convert(String text) {
			Agencia result;
			int id;
			
			try{
				if(StringUtils.isEmpty(text))
					result = null;
				else {
					id = Integer.valueOf(text);
					result = this.agenciaRepository.findOne(id);
				}
			}catch(Throwable oops){
				throw new IllegalArgumentException(oops);
			}
			return result;
		}
}
