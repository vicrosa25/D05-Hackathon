package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.Premio;

@Component
@Transactional
public class PremioToStringConverter implements Converter<Premio, String> {
	
	@Override
	public String convert(Premio premio) {
		String result;
		if(premio == null)
			result = null;
		else{
			result = String.valueOf(premio.getId());
		}
		return result;
	}

}
