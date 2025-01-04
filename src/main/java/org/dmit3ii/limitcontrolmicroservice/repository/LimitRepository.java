package org.dmit3ii.limitcontrolmicroservice.repository;

import org.dmit3ii.limitcontrolmicroservice.model.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Integer> {
}
