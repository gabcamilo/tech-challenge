package br.com.gabrielacamilo.techchallenge.utils;

import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.Optional;

public final class GenericMapper {
    // This should be the only use of ModelMapper dependency on this application
    private static final ModelMapper modelMapper = new ModelMapper();

    public static <T, G> G map(T a, Class<G> bClass) {
        return modelMapper.map(a, bClass);
    }

    public static <T, G> Optional<G> map(Optional<T> a, Class<G> bClass) {
        return a.map(optional -> modelMapper.map(optional, bClass));
    }

    public static <T, G> List<G> map(List<T> a, Class<G> bClass) {
        return a.stream()
                .map(
                        item -> modelMapper.map(item, bClass)
                ).toList();
    }
}
