package de.irahi.welp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import de.irahi.welp.entity.Bread;

public interface BreadRepository extends JpaRepository<Bread, Long> {

}
