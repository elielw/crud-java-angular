package com.totvs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.totvs.model.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

	public List<Telefone> findAllByTelefone(String telefone);

}
