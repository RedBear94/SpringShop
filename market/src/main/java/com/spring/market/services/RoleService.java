package com.spring.market.services;

import com.spring.market.entities.Role;
import com.spring.market.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findRoleByName("ROLE_USER");
    }
}