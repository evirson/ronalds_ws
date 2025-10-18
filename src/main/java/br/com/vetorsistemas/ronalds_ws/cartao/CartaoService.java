package br.com.vetorsistemas.ronalds_ws.cartao;

import br.com.vetorsistemas.ronalds_ws.cartao.Dto.CartaoDto;
import br.com.vetorsistemas.ronalds_ws.cartao.mapper.CartaoMapper;
import br.com.vetorsistemas.ronalds_ws.shared.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartaoService {

    @Autowired
    CartaoRepository repository;

    @Autowired
    CartaoMapper mapper;

    public void create(CartaoDto dto){
        Optional<Cartao> existente = repository.findByDescricao(dto.getDescricao());
        if (existente.isPresent()){
            throw new AppException(HttpStatus.CONFLICT, "Cartão já cadastrado");
        }

        Cartao entidade = mapper.toEntity(dto);
        if (entidade == null) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Dados do cartão inválidos");
        }

        entidade.setDataCadastro(LocalDateTime.now());
        entidade.setDataAlteracao(LocalDateTime.now());

        repository.save(entidade);
    }

    public void delete(Integer id){
        Cartao existente = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));
        repository.delete(existente);
    }

    public CartaoDto getById(Integer id) {
        Cartao entity = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));
        return mapper.toDto(entity);
    }

    public CartaoDto update(Integer id, CartaoDto dto) {
        Cartao entity = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));

        if (dto.getDescricao() != null) {
            Optional<Cartao> outroComDescricao = repository.findByDescricao(dto.getDescricao());
            if (outroComDescricao.isPresent() && !outroComDescricao.get().getId().equals(id)) {
                throw new AppException(HttpStatus.CONFLICT, "Descrição já utilizada por outro cartão");
            }
            entity.setDescricao(dto.getDescricao());
        }

        if (dto.getTaxaAdministrativa() != null) {
            entity.setTaxaAdministrativa(dto.getTaxaAdministrativa());
        }
        if (dto.getDiaFechamento() != null) {
            entity.setDiaFechamento(dto.getDiaFechamento());
        }
        if (dto.getDiaCredito() != null) {
            entity.setDiaCredito(dto.getDiaCredito());
        }

        entity.setDataAlteracao(LocalDateTime.now());

        return mapper.toDto(repository.save(entity));
    }

    public List<CartaoDto> findAll(String descricao) {
        List<Cartao> lista;
        if (descricao != null && !descricao.isBlank()) {
            lista = repository.findByDescricaoContainingIgnoreCaseOrderByDescricaoAsc(descricao);
        } else {
            lista = repository.findAll(org.springframework.data.domain.Sort.by("descricao").ascending());
        }
        return lista.stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
