package br.com.vetorsistemas.ronalds_ws.cadastro.municipio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer>, JpaSpecificationExecutor<Municipio> {
}
