package br.com.vetorsistemas.ronalds_ws.movimento.contaCorrente.mapper;

import br.com.vetorsistemas.ronalds_ws.movimento.contaCorrente.ContaCorrente;
import br.com.vetorsistemas.ronalds_ws.movimento.contaCorrente.dto.ContaCorrenteDto;
import org.springframework.stereotype.Component;

@Component
public class ContaCorrenteMapper {
    public ContaCorrenteDto toDTO(ContaCorrente c) {
        if (c == null) return null;
        return ContaCorrenteDto.builder()
                .id(c.getId())
                .banco(c.getBanco())
                .agencia(c.getAgencia())
                .conta(c.getConta())
                .descricao(c.getDescricao())
                .dataAbertura(c.getDataAbertura())
                .tipoConta(c.getTipoConta())
                .dataCadastro(c.getDataCadastro())
                .dataAlteracao(c.getDataAlteracao())
                .build();
    }

    public ContaCorrente fromDTO(ContaCorrenteDto d) {
        if (d == null) return null;
        return ContaCorrente.builder()
                .id(d.getId())
                .banco(d.getBanco())
                .agencia(d.getAgencia())
                .conta(d.getConta())
                .descricao(d.getDescricao())
                .dataAbertura(d.getDataAbertura())
                .tipoConta(d.getTipoConta())
                .dataCadastro(d.getDataCadastro())
                .dataAlteracao(d.getDataAlteracao())
                .build();
    }

    public void updateEntityFromDTO(ContaCorrenteDto d, ContaCorrente e) {
        if (d == null || e == null) return;
        e.setBanco(d.getBanco());
        e.setAgencia(d.getAgencia());
        e.setConta(d.getConta());
        e.setDescricao(d.getDescricao());
        e.setDataAbertura(d.getDataAbertura());
        e.setTipoConta(d.getTipoConta());
        e.setDataCadastro(d.getDataCadastro());
        e.setDataAlteracao(d.getDataAlteracao());
    }
}

