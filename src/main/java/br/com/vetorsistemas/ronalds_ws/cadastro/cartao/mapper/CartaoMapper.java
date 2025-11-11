package br.com.vetorsistemas.ronalds_ws.cadastro.cartao.mapper;

import br.com.vetorsistemas.ronalds_ws.cadastro.cartao.Cartao;
import br.com.vetorsistemas.ronalds_ws.cadastro.cartao.Dto.CartaoDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CartaoMapper {

    public Cartao toEntity(CartaoDto dto){

        if (dto == null){
            return null;
        }

        Cartao ent = new Cartao();
        // id é gerado pelo banco; ignoramos dto.id na criação
        ent.setDescricao(dto.getDescricao());
        ent.setDiaCredito(dto.getDiaCredito());
        ent.setDiaFechamento(dto.getDiaFechamento());
        ent.setTaxaAdministrativa(dto.getTaxaAdministrativa());
        ent.setDataCadastro(LocalDateTime.now());
        ent.setDataAlteracao(LocalDateTime.now());

        return ent;

    }

    public CartaoDto toDto(Cartao ent){
        if (ent == null){
            return null;
        }

        CartaoDto dto = new CartaoDto();
        dto.setId(ent.getId());
        dto.setDescricao(ent.getDescricao());
        dto.setDiaCredito(ent.getDiaCredito());
        dto.setDiaFechamento(ent.getDiaFechamento());
        dto.setTaxaAdministrativa(ent.getTaxaAdministrativa());

        return dto;

    }
}
