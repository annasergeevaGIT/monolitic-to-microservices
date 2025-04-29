package com.mono_to_micro.app.job;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> { //extends CrudRepository, JPA has more functionality. Long - datatype of PK

}
