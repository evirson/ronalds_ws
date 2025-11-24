package br.com.vetorsistemas.ronalds_ws.cadastro.classificacaoFiscal;

import br.com.vetorsistemas.ronalds_ws.cadastro.classe.Classe;
import br.com.vetorsistemas.ronalds_ws.cadastro.classe.ClasseRepository;
import br.com.vetorsistemas.ronalds_ws.cadastro.classe.dto.ClasseDto;
import br.com.vetorsistemas.ronalds_ws.cadastro.classe.mapper.ClasseMapper;
import br.com.vetorsistemas.ronalds_ws.cadastro.classificacaoFiscal.dto.ClassificacaoFiscalDto;
import br.com.vetorsistemas.ronalds_ws.cadastro.classificacaoFiscal.mapper.ClassificacaoFiscalMapper;
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
public class ClassificacaoFiscalService {

    private final ClassificacaoFiscalRepository repository;
    private final ClassificacaoFiscalMapper mapper;

    public ClassificacaoFiscalService(ClassificacaoFiscalRepository repository, ClassificacaoFiscalMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Page<ClassificacaoFiscalDto> search(String codigoNcm,
                                               String descricaoNcm,
                                               Integer pagina,
                                               Integer tamanhoPagina) {

        Specification<ClassificacaoFiscal> specs = (root, query, cb) -> cb.conjunction();

        if (codigoNcm != null) {
            specs = specs.and((root, query, cb) ->
                    cb.equal(root.get("codigoNcm"), codigoNcm.toUpperCase()));

        }

        if (descricaoNcm != null) {
            specs = specs.and((root, query, cb) ->
                    cb.like(root.get("descricaoNcm"), "%" + descricaoNcm.toUpperCase() + "%"));

        }

        Sort sort = Sort.by("descricaoNcm").ascending();

        Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina, sort);

        Page<ClassificacaoFiscal> pageResult = repository.findAll(specs, pageRequest);

        Page<ClassificacaoFiscalDto> resultado = pageResult.map(mapper::toDTO);

        return resultado;

    }



    @Transactional
    public ClassificacaoFiscalDto create(ClassificacaoFiscalDto dto) {

        ClassificacaoFiscal entity = mapper.toEntity(dto);

        entity.setId(null);

        return mapper.toDTO(repository.save(entity));
    }

    @Transactional
    public ClassificacaoFiscalDto update(Integer id, ClassificacaoFiscalDto dto) {
        ClassificacaoFiscal entity = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Classificação não encontrada"));

        /*atualiza apenas os campos necessários*/
        entity.setCodigoNcm(dto.getCodigoNcm());
        entity.setDescricaoNcm(dto.getDescricaoNcm());
        entity.setAliquotaIbpt(dto.getAliquotaIbpt());
        entity.setCodigoCest(dto.getCodigoCest());

        return mapper.toDTO(repository.save(entity));
    }

    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND, "Classe não encontrada");
        }
        repository.deleteById(id);
    }

}
