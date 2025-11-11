package br.com.vetorsistemas.ronalds_ws.cadastro.funcionario.mapper;

import br.com.vetorsistemas.ronalds_ws.cadastro.funcionario.Funcionario;
import br.com.vetorsistemas.ronalds_ws.cadastro.funcionario.dto.FuncionarioDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FuncionarioMapper {

    public Funcionario toEntity(FuncionarioDto dto) {
        if (dto == null) return null;
        Funcionario f = new Funcionario();
        f.setNome(dto.getNome());
        f.setCpf(dto.getCpf());
        f.setCargo(dto.getCargo());
        f.setDataAdmissao(dto.getDataAdmissao());
        f.setDataNascimento(dto.getDataNascimento());
        f.setDataDemissao(dto.getDataDemissao());
        f.setComissaoPecas(dto.getComissaoPecas());
        f.setComissaoMaoObra(dto.getComissaoMaoObra());
        f.setDocumento(dto.getDocumento());
        f.setEndereco(dto.getEndereco());
        f.setBairro(dto.getBairro());
        f.setCep(dto.getCep());
        f.setCidade(dto.getCidade());
        f.setEstado(dto.getEstado());
        f.setFone(dto.getFone());
        f.setCelular(dto.getCelular());
        f.setDataCadastro(LocalDateTime.now());
        f.setDataAlteracao(LocalDateTime.now());
        return f;
    }

    public FuncionarioDto toDto(Funcionario f) {
        if (f == null) return null;
        return FuncionarioDto.builder()
                .id(f.getId())
                .nome(f.getNome())
                .cpf(f.getCpf())
                .cargo(f.getCargo())
                .dataAdmissao(f.getDataAdmissao())
                .dataNascimento(f.getDataNascimento())
                .dataDemissao(f.getDataDemissao())
                .dataCadastro(f.getDataCadastro())
                .dataAlteracao(f.getDataAlteracao())
                .comissaoPecas(f.getComissaoPecas())
                .comissaoMaoObra(f.getComissaoMaoObra())
                .documento(f.getDocumento())
                .endereco(f.getEndereco())
                .bairro(f.getBairro())
                .cep(f.getCep())
                .cidade(f.getCidade())
                .estado(f.getEstado())
                .fone(f.getFone())
                .celular(f.getCelular())
                .build();
    }
}

