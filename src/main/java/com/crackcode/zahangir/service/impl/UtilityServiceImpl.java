package com.crackcode.zahangir.service.impl;

import com.crackcode.zahangir.entity.Role;
import com.crackcode.zahangir.repository.RoleRepository;
import com.crackcode.zahangir.service.UtilityService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtilityServiceImpl implements UtilityService {
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    public UtilityServiceImpl(ModelMapper modelMapper, RoleRepository roleRepository) {
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source.stream().map(element -> modelMapper.map(element, targetClass)).collect(Collectors.toList());
    }

    @Override
    public <S, T> T map(S s, Class<T> targetClass) {
        return modelMapper.map(s, targetClass);
    }

    @Override
    public <S, T> void updateMap(S s, T t) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(s, t);
    }

    @Override
    public Role getRole(String roleName) {
        Role role = roleRepository.findByName(roleName);
        if(role == null){
            role = new Role();
            role.setName(roleName);
            role = roleRepository.save(role);
        }
        return role;
    }
}
