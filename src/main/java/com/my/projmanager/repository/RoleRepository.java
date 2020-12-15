package com.my.projmanager.repository;

import com.my.projmanager.model.impl.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
