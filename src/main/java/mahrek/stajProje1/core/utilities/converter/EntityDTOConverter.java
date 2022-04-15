package mahrek.stajProje1.core.utilities.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NameTokenizers;

public class EntityDTOConverter<F, T> {

    private ModelMapper modelMapper = new ModelMapper();
    private Class<T> toClass;

    public EntityDTOConverter(Class<T> toClass) {
        this.toClass = toClass;
    }

    public T convert(F from) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        T to = this.modelMapper.map(from, this.toClass);
        return to;
    }
}
