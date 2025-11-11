package br.com.vetorsistemas.ronalds_ws.cadastro.classe;

import br.com.vetorsistemas.ronalds_ws.cadastro.classe.dto.ClasseDto;
import br.com.vetorsistemas.ronalds_ws.cadastro.classe.mapper.ClasseMapper;
import br.com.vetorsistemas.ronalds_ws.shared.AppException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ClasseService {

    private final ClasseRepository repository;
    private final ClasseMapper mapper;

    public ClasseService(ClasseRepository repository, ClasseMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Page<ClasseDto> search(String nomeClasse,
                                  Integer pagina,
                                  Integer tamanhoPagina ) {

        Specification<Classe> specs = (root, query, cb) -> cb.conjunction();


        if (nomeClasse != null){
            specs = specs.and((root, query, cb) ->
                    cb.like(root.get("nomeClasse"), "%" + nomeClasse.toUpperCase() +"%"));

        }

        Sort sort = Sort.by( "nomeClasse").ascending();

        Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina, sort);

        Page<Classe> pageResult = repository.findAll(specs, pageRequest);

        Page<ClasseDto> resultado = pageResult.map(mapper::toDTO);

        return resultado;

    }


    public ClasseDto findById(Integer id) {
        Classe entity = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Classe não encontrada"));
        return mapper.toDTO(entity);
    }

    @Transactional
    public ClasseDto create(ClasseDto dto) {

        Classe entity = mapper.toEntity(dto);

        entity.setDataCadastro(LocalDateTime.now());

        entity.setId(null);

        return mapper.toDTO(repository.save(entity));
    }

    public ClasseDto update(Integer id, ClasseDto dto) {
        Classe entity = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Classe não encontrada"));

        /*atualiza apenas os campos necessários*/
        entity.setNomeClasse(dto.getNomeClasse());
        entity.setTipoClasse(dto.getTipoClasse());
        entity.setPercentualDescontoPadrao(dto.getPercentualDescontoPadrao());

        return mapper.toDTO(repository.save(entity));
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND, "Classe não encontrada");
        }
        repository.deleteById(id);
    }


    
}
