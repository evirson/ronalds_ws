package br.com.vetorsistemas.ronalds_ws.cadastro.veiculo.mapper;

import br.com.vetorsistemas.ronalds_ws.cadastro.veiculo.Veiculo;
import br.com.vetorsistemas.ronalds_ws.cadastro.veiculo.dto.VeiculoDto;
import org.springframework.stereotype.Component;

@Component
public class VeiculoMapper {

    public Veiculo toEntity(VeiculoDto dto) {
        if (dto == null) return null;
        Veiculo v = new Veiculo();
        v.setId(dto.getId());
        v.setCodigoCliente(dto.getCodigoCliente());
        v.setTipoCarro(dto.getTipoCarro());
        v.setPlaca(dto.getPlaca());
        v.setAno(dto.getAno());
        v.setCor(dto.getCor());
        v.setCombustivel(dto.getCombustivel());
        v.setChassi(dto.getChassi());
        v.setMotor(dto.getMotor());
        v.setModelo(dto.getModelo());
        v.setKm(dto.getKm());
        v.setDataCadastro(dto.getDataCadastro());
        v.setDataAtualizacao(dto.getDataAtualizacao());
        v.setArCondicionado(dto.getArCondicionado());
        v.setDirecao(dto.getDirecao());
        v.setTransmissao(dto.getTransmissao());
        v.setTamanhoAro(dto.getTamanhoAro());
        v.setTemAbs(dto.getTemAbs());
        v.setOleoMotor(dto.getOleoMotor());
        return v;
    }

    public VeiculoDto toDto(Veiculo v) {
        if (v == null) return null;
        VeiculoDto dto = new VeiculoDto();
        dto.setId(v.getId());
        dto.setCodigoCliente(v.getCodigoCliente());
        dto.setTipoCarro(v.getTipoCarro());
        dto.setPlaca(v.getPlaca());
        dto.setAno(v.getAno());
        dto.setCor(v.getCor());
        dto.setCombustivel(v.getCombustivel());
        dto.setChassi(v.getChassi());
        dto.setMotor(v.getMotor());
        dto.setModelo(v.getModelo());
        dto.setKm(v.getKm());
        dto.setDataCadastro(v.getDataCadastro());
        dto.setDataAtualizacao(v.getDataAtualizacao());
        dto.setArCondicionado(v.getArCondicionado());
        dto.setDirecao(v.getDirecao());
        dto.setTransmissao(v.getTransmissao());
        dto.setTamanhoAro(v.getTamanhoAro());
        dto.setTemAbs(v.getTemAbs());
        dto.setOleoMotor(v.getOleoMotor());
        return dto;
    }

    public void updateEntityFromDTO(VeiculoDto dto, Veiculo v) {
        if (dto == null || v == null) return;
        v.setCodigoCliente(dto.getCodigoCliente());
        v.setTipoCarro(dto.getTipoCarro());
        v.setPlaca(dto.getPlaca());
        v.setAno(dto.getAno());
        v.setCor(dto.getCor());
        v.setCombustivel(dto.getCombustivel());
        v.setChassi(dto.getChassi());
        v.setMotor(dto.getMotor());
        v.setModelo(dto.getModelo());
        v.setKm(dto.getKm());
        v.setArCondicionado(dto.getArCondicionado());
        v.setDirecao(dto.getDirecao());
        v.setTransmissao(dto.getTransmissao());
        v.setTamanhoAro(dto.getTamanhoAro());
        v.setTemAbs(dto.getTemAbs());
        v.setOleoMotor(dto.getOleoMotor());
        // datas tratadas no service
    }
}
