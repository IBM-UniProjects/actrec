package com.ibm.actrec;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface LocationRepository extends JpaRepository<Location, Long>  {
    Collection<Location> findByPersonIdOrderByLastLocationTime(Long id);
}
