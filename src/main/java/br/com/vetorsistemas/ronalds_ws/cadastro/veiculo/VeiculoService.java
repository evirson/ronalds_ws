package br.com.vetorsistemas.ronalds_ws.cadastro.veiculo;

import br.com.vetorsistemas.ronalds_ws.shared.AppException;
import br.com.vetorsistemas.ronalds_ws.cadastro.veiculo.dto.VeiculoDto;
import br.com.vetorsistemas.ronalds_ws.cadastro.veiculo.mapper.VeiculoMapper;
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
public class VeiculoService {

    private final VeiculoRepository repository;
    private final VeiculoMapper mapper;

    public VeiculoService(VeiculoRepository repository, VeiculoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Page<VeiculoDto> search(String placa,
            String tipo,
            String modelo,
            Integer pagina,
            Integer tamanhoPagina) {

        Specification<Veiculo> specs = (root, query, cb) -> cb.conjunction();

        if (placa != null && !placa.isBlank()) {
            String p = placa.trim().toUpperCase();
            specs = specs.and((root, q, cb2) -> cb2.equal(cb2.upper(root.get("placa")), p));
        }
        if (tipo != null && !tipo.isBlank()) {
            String m = "%" + tipo.toUpperCase() + "%";
            specs = specs.and((root, q, cb2) -> cb2.like(cb2.upper(root.get("tipoCarro")), m));
        }
        if (modelo != null && !modelo.isBlank()) {
            String md = "%" + modelo.toUpperCase() + "%";
            specs = specs.and((root, q, cb2) -> cb2.like(cb2.upper(root.get("modelo")), md));
        }

        Sort sort = Sort.by("placa").ascending();
        int pageIndex = (pagina == null || pagina < 0) ? 0 : pagina;
        int pageSize = (tamanhoPagina == null || tamanhoPagina <= 0) ? 20 : tamanhoPagina;
        Pageable pageRequest = PageRequest.of(pageIndex, pageSize, sort);
        return repository.findAll(specs, pageRequest).map(mapper::toDto);
    }

    public VeiculoDto findById(Integer id) {
        Veiculo v = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Veiculo nao encontrado"));
        return mapper.toDto(v);
    }

    @Transactional
    public VeiculoDto create(VeiculoDto dto) {
        Veiculo v = mapper.toEntity(dto);
        LocalDateTime agora = LocalDateTime.now();
        if (v.getDataCadastro() == null) {
            v.setDataCadastro(agora);
        }
        if (v.getDataAtualizacao() == null) {
            v.setDataAtualizacao(agora);
        }
        return mapper.toDto(repository.save(v));
    }

    @Transactional
    public VeiculoDto update(Integer id, VeiculoDto dto) {
        Veiculo v = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Veiculo nao encontrado"));
        mapper.updateEntityFromDTO(dto, v);

        v.setDataAtualizacao(LocalDateTime.now());

        return mapper.toDto(repository.save(v));
    }

    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND, "Veiculo nao encontrado");
        }
        repository.deleteById(id);
    }
}
