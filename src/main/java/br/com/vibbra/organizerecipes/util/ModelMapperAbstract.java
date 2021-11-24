package br.com.vibbra.organizerecipes.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ModelMapperAbstract<E, D> {

    @Autowired
    private ModelMapper modelMapper;

    public E convertToEntity(D dto) {
        if (dto == null)
            return null;

        return modelMapper.map(dto, (Class<E>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public List<E> convertToEntity(List<D> dtos) {
        if (dtos == null)
            return Collections.emptyList();

        return dtos.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    public D convertToDto(E entity) {
        if (entity == null)
            return null;

        return modelMapper.map(entity, (Class<D>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1]);
    }

    public List<D> convertToDto(List<E> entitys) {
        if (entitys == null)
            return Collections.emptyList();

        return entitys.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
