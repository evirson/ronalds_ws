package br.com.vetorsistemas.ronalds_ws.fornecedor;

import br.com.vetorsistemas.ronalds_ws.shared.AppException;
import br.com.vetorsistemas.ronalds_ws.fornecedor.dto.FornecedorCreateUpdateDTO;
import br.com.vetorsistemas.ronalds_ws.fornecedor.dto.FornecedorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService {

    private final FornecedorRepository repository;

    public FornecedorService(FornecedorRepository repository) {
        this.repository = repository;
    }

    public Page<FornecedorDTO> search(String razaoSocial,
                                      String nomeFantasia,
                                      String cnpj,
                                      String telefone,
                                      Pageable pageable) {
        boolean hasFilters = (razaoSocial != null && !razaoSocial.trim().isEmpty())
                || (nomeFantasia != null && !nomeFantasia.trim().isEmpty())
                || (cnpj != null && !cnpj.trim().isEmpty())
                || (telefone != null && !telefone.trim().isEmpty());

        if (!hasFilters) {
            return repository.findAll(pageable).map(this::toDTO);
        }

        Fornecedor probe = new Fornecedor();
        if (razaoSocial != null && !razaoSocial.trim().isEmpty()) probe.setRazaoSocial(razaoSocial.trim());
        if (nomeFantasia != null && !nomeFantasia.trim().isEmpty()) probe.setNomeFantasia(nomeFantasia.trim());
        if (cnpj != null && !cnpj.trim().isEmpty()) probe.setCnpj(cnpj.trim());
        if (telefone != null && !telefone.trim().isEmpty()) probe.setFone(telefone.trim());

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreNullValues();

        Example<Fornecedor> example = Example.of(probe, matcher);
        return repository.findAll(example, pageable).map(this::toDTO);
    }

    public FornecedorDTO findById(Integer id) {
        Fornecedor entity = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Fornecedor n\u00e3o encontrado"));
        return toDTO(entity);
    }

    public FornecedorDTO create(FornecedorCreateUpdateDTO dto) {
        if (dto.getId() == null) {
            throw new AppException(HttpStatus.BAD_REQUEST, "ID \u00e9 obrigat\u00f3rio");
        }
        if (repository.existsById(dto.getId())) {
            throw new AppException(HttpStatus.CONFLICT, "Fornecedor j\u00e1 existe");
        }
        Fornecedor entity = fromDTO(dto);
        return toDTO(repository.save(entity));
    }

    public FornecedorDTO update(Integer id, FornecedorCreateUpdateDTO dto) {
        Fornecedor entity = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Fornecedor n\u00e3o encontrado"));

        // Atualiza campos
        entity.setRazaoSocial(dto.getRazaoSocial());
        entity.setNomeFantasia(dto.getNomeFantasia());
        entity.setCnpj(dto.getCnpj());
        entity.setInscricaoEstadual(dto.getInscricaoEstadual());
        entity.setEndereco(dto.getEndereco());
        entity.setBairro(dto.getBairro());
        entity.setCidade(dto.getCidade());
        entity.setEstado(dto.getEstado());
        entity.setCep(dto.getCep());
        entity.setFone(dto.getFone());
        entity.setFax(dto.getFax());
        entity.setEmail(dto.getEmail());
        entity.setContato(dto.getContato());
        entity.setRepresentante(dto.getRepresentante());
        entity.setFoneRepresentante(dto.getFoneRepresentante());
        entity.setFaxRepresentante(dto.getFaxRepresentante());
        entity.setCelularRepresentante(dto.getCelularRepresentante());
        entity.setCondicaoPagamento(dto.getCondicaoPagamento());
        entity.setAcrescimo1(dto.getAcrescimo1());
        entity.setAcrescimo2(dto.getAcrescimo2());
        entity.setAcrescimo3(dto.getAcrescimo3());
        entity.setAcrescimo4(dto.getAcrescimo4());
        entity.setAcrescimo5(dto.getAcrescimo5());
        entity.setDesconto1(dto.getDesconto1());
        entity.setDesconto2(dto.getDesconto2());
        entity.setDesconto3(dto.getDesconto3());
        entity.setDesconto4(dto.getDesconto4());
        entity.setDesconto5(dto.getDesconto5());
        entity.setPercentualLucro(dto.getPercentualLucro());
        entity.setTipoFrete(dto.getTipoFrete());
        entity.setTipoFornecedor(dto.getTipoFornecedor());
        entity.setPlanoContas(dto.getPlanoContas());

        return toDTO(repository.save(entity));
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND, "Fornecedor n\u00e3o encontrado");
        }
        repository.deleteById(id);
    }

    private FornecedorDTO toDTO(Fornecedor f) {
        return FornecedorDTO.builder()
                .id(f.getId())
                .razaoSocial(f.getRazaoSocial())
                .nomeFantasia(f.getNomeFantasia())
                .cnpj(f.getCnpj())
                .inscricaoEstadual(f.getInscricaoEstadual())
                .endereco(f.getEndereco())
                .bairro(f.getBairro())
                .cidade(f.getCidade())
                .estado(f.getEstado())
                .cep(f.getCep())
                .fone(f.getFone())
                .fax(f.getFax())
                .email(f.getEmail())
                .contato(f.getContato())
                .representante(f.getRepresentante())
                .foneRepresentante(f.getFoneRepresentante())
                .faxRepresentante(f.getFaxRepresentante())
                .celularRepresentante(f.getCelularRepresentante())
                .condicaoPagamento(f.getCondicaoPagamento())
                .acrescimo1(f.getAcrescimo1())
                .acrescimo2(f.getAcrescimo2())
                .acrescimo3(f.getAcrescimo3())
                .acrescimo4(f.getAcrescimo4())
                .acrescimo5(f.getAcrescimo5())
                .desconto1(f.getDesconto1())
                .desconto2(f.getDesconto2())
                .desconto3(f.getDesconto3())
                .desconto4(f.getDesconto4())
                .desconto5(f.getDesconto5())
                .percentualLucro(f.getPercentualLucro())
                .tipoFrete(f.getTipoFrete())
                .tipoFornecedor(f.getTipoFornecedor())
                .planoContas(f.getPlanoContas())
                .build();
    }

    private Fornecedor fromDTO(FornecedorCreateUpdateDTO d) {
        return Fornecedor.builder()
                .id(d.getId())
                .razaoSocial(d.getRazaoSocial())
                .nomeFantasia(d.getNomeFantasia())
                .cnpj(d.getCnpj())
                .inscricaoEstadual(d.getInscricaoEstadual())
                .endereco(d.getEndereco())
                .bairro(d.getBairro())
                .cidade(d.getCidade())
                .estado(d.getEstado())
                .cep(d.getCep())
                .fone(d.getFone())
                .fax(d.getFax())
                .email(d.getEmail())
                .contato(d.getContato())
                .representante(d.getRepresentante())
                .foneRepresentante(d.getFoneRepresentante())
                .faxRepresentante(d.getFaxRepresentante())
                .celularRepresentante(d.getCelularRepresentante())
                .condicaoPagamento(d.getCondicaoPagamento())
                .acrescimo1(d.getAcrescimo1())
                .acrescimo2(d.getAcrescimo2())
                .acrescimo3(d.getAcrescimo3())
                .acrescimo4(d.getAcrescimo4())
                .acrescimo5(d.getAcrescimo5())
                .desconto1(d.getDesconto1())
                .desconto2(d.getDesconto2())
                .desconto3(d.getDesconto3())
                .desconto4(d.getDesconto4())
                .desconto5(d.getDesconto5())
                .percentualLucro(d.getPercentualLucro())
                .tipoFrete(d.getTipoFrete())
                .tipoFornecedor(d.getTipoFornecedor())
                .planoContas(d.getPlanoContas())
                .build();
    }
}
