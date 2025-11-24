package br.com.vetorsistemas.ronalds_ws.cadastro.veiculo.mapper;

import br.com.vetorsistemas.ronalds_ws.cadastro.veiculo.VeiculoImagem;
import br.com.vetorsistemas.ronalds_ws.cadastro.veiculo.dto.VeiculoImagemDto;
import org.springframework.stereotype.Component;

/**
 * Mapper responsável pela conversão entre entidade VeiculoImagem e DTO
 */
@Component
public class VeiculoImagemMapper {

    private static final String BASE_URL = "/api/veiculos/imagens";

    /**
     * Converte a entidade VeiculoImagem para DTO
     * @param entity Entidade a ser convertida
     * @return DTO com os dados da imagem
     */
    public VeiculoImagemDto toDto(VeiculoImagem entity) {
        if (entity == null) {
            return null;
        }

        return VeiculoImagemDto.builder()
                .id(entity.getId())
                .codVei(entity.getCodVei())
                .nomeArq(entity.getNomeArq())
                .contentType(entity.getContentType())
                .tamanho(entity.getTamanho() != null ? entity.getTamanho() :
                         (entity.getDocumento() != null ? (long) entity.getDocumento().length : 0L))
                .urlPreview(entity.getId() != null ? BASE_URL + "/" + entity.getId() + "/preview" : null)
                .urlDownload(entity.getId() != null ? BASE_URL + "/" + entity.getId() : null)
                .build();
    }
}
