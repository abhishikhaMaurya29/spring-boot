package com.abhishikha.repository;

import com.abhishikha.NameOnly;
import com.abhishikha.dto.EmployeeIdAndName;
import com.abhishikha.model.Employee;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Lock(LockModeType.READ)
    List<Employee> findByLastName(String lastName);

    @Modifying
    @Transactional(timeout = 10)
    @Query("delete from Employee e where e.isActiveEmployee = false")
    void deleteInactiveEmployees();

    Collection<NameOnly> findByRole(String role);
    List<EmployeeIdAndName> findByFirstName(String firstName);

    <T> List<T> findByLastName(String lastName, Class<T> type);
}