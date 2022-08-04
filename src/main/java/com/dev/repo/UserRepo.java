package com.dev.repo;

import com.dev.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Student,Long> {
}
