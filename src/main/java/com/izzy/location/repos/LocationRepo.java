package com.izzy.location.repos;

import com.izzy.location.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepo extends JpaRepository<Location, Long> {

    @Query(value = "SELECT type, COUNT(type) FROM location GROUP BY type", nativeQuery = true)
    public List<Object[]> findTypeAndTypeCount();

}
