package br.com.vetorsistemas.ronalds_ws.cadastro.funcionario;

import br.com.vetorsistemas.ronalds_ws.cadastro.funcionario.dto.FuncionarioDto;
import br.com.vetorsistemas.ronalds_ws.cadastro.funcionario.mapper.FuncionarioMapper;
import br.com.vetorsistemas.ronalds_ws.shared.AppException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repository;
    private final FuncionarioMapper mapper;

    public FuncionarioService(FuncionarioRepository repository, FuncionarioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Page<FuncionarioDto> list(String nome, Integer pagina, Integer tamanhoPagina) {
        Pageable pageable = PageRequest.of(pagina == null ? 0 : pagina, tamanhoPagina == null ? 10 : tamanhoPagina);
        Page<Funcionario> page;
        if (nome != null && !nome.isBlank()) {
            page = repository.findByNomeContainingIgnoreCaseOrderByNomeAsc(nome, pageable);
        } else {
            page = repository.findAll(pageable);
        }
        return page.map(mapper::toDto);
    }

    public FuncionarioDto findById(Integer id) {
        Funcionario f = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Funcionário não encontrado"));
        return mapper.toDto(f);
    }

    public FuncionarioDto create(FuncionarioDto dto) {
        repository.findByCpf(dto.getCpf()).ifPresent(existing -> {
            throw new AppException(HttpStatus.CONFLICT, "CPF já cadastrado");
        });
        Funcionario ent = mapper.toEntity(dto);
        ent.setDataCadastro(LocalDateTime.now());
        ent.setDataAlteracao(LocalDateTime.now());
        Funcionario saved = repository.save(ent);
        return mapper.toDto(saved);
    }

    public FuncionarioDto update(Integer id, FuncionarioDto dto) {
        Funcionario ent = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Funcionário não encontrado"));

        if (dto.getNome() != null) ent.setNome(dto.getNome());
        if (dto.getCpf() != null && !Objects.equals(dto.getCpf(), ent.getCpf())) {
            repository.findByCpf(dto.getCpf()).ifPresent(other -> {
                if (!Objects.equals(other.getId(), id))
                    throw new AppException(HttpStatus.CONFLICT, "CPF já cadastrado");
            });
            ent.setCpf(dto.getCpf());
        }
        if (dto.getCargo() != null) ent.setCargo(dto.getCargo());
        if (dto.getDataAdmissao() != null) ent.setDataAdmissao(dto.getDataAdmissao());
        if (dto.getDataDemissao() != null) ent.setDataDemissao(dto.getDataDemissao());
        if (dto.getDataNascimento() != null) ent.setDataNascimento(dto.getDataNascimento());
        if (dto.getComissaoPecas() != null) ent.setComissaoPecas(dto.getComissaoPecas());
        if (dto.getComissaoMaoObra() != null) ent.setComissaoMaoObra(dto.getComissaoMaoObra());
        if (dto.getDocumento() != null) ent.setDocumento(dto.getDocumento());
        if (dto.getEndereco() != null) ent.setEndereco(dto.getEndereco());
        if (dto.getBairro() != null) ent.setBairro(dto.getBairro());
        if (dto.getCep() != null) ent.setCep(dto.getCep());
        if (dto.getCidade() != null) ent.setCidade(dto.getCidade());
        if (dto.getEstado() != null) ent.setEstado(dto.getEstado());
        if (dto.getFone() != null) ent.setFone(dto.getFone());
        if (dto.getCelular() != null) ent.setCelular(dto.getCelular());

        ent.setDataAlteracao(LocalDateTime.now());
        return mapper.toDto(repository.save(ent));
    }

    public void delete(Integer id) {
        Funcionario ent = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Funcionário não encontrado"));
        repository.delete(ent);
    }
}

