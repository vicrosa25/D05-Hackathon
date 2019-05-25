package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import domain.Banner;
import repositories.BannerRepository;


@Component
@Transactional
public class StringToBannerConverter implements Converter<String, Banner> {

		@Autowired
		BannerRepository bannerRepository;

		@Override
		public Banner convert(String text) {
			Banner result;
			int id;
			
			try{
				if(StringUtils.isEmpty(text))
					result = null;
				else {
					id = Integer.valueOf(text);
					result = this.bannerRepository.findOne(id);
				}
			}catch(Throwable oops){
				throw new IllegalArgumentException(oops);
			}
			return result;
		}
}
