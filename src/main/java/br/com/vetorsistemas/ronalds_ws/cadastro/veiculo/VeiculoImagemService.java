package br.com.vetorsistemas.ronalds_ws.cadastro.veiculo;

import br.com.vetorsistemas.ronalds_ws.cadastro.veiculo.dto.VeiculoImagemDto;
import br.com.vetorsistemas.ronalds_ws.cadastro.veiculo.mapper.VeiculoImagemMapper;
import br.com.vetorsistemas.ronalds_ws.shared.AppException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service responsável pela lógica de negócio de imagens de veículos
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VeiculoImagemService {

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB
    private static final int MAX_IMAGES_PER_REQUEST = 10;
    private static final List<String> ALLOWED_CONTENT_TYPES = List.of(
            "image/jpeg",
            "image/jpg",
            "image/png",
            "image/gif",
            "image/webp",
            "image/bmp"
    );

    private final VeiculoImagemRepository imagemRepository;
    private final VeiculoRepository veiculoRepository;
    private final VeiculoImagemMapper mapper;

    @Transactional
    public List<VeiculoImagemDto> salvarImagens(Integer codVei, List<MultipartFile> imagens) {
        log.info("Iniciando upload de {} imagens para o veículo {}",
                 imagens != null ? imagens.size() : 0, codVei);

        // Validar se o veículo existe
        if (!veiculoRepository.existsById(codVei)) {
            log.warn("Tentativa de upload de imagens para veículo inexistente: {}", codVei);
            throw new AppException(HttpStatus.NOT_FOUND, "Veiculo nao encontrado com ID: " + codVei);
        }

        if (imagens == null || imagens.isEmpty()) {
            log.warn("Lista de imagens vazia para o veículo {}", codVei);
            throw new AppException(HttpStatus.BAD_REQUEST, "Nenhuma imagem foi enviada");
        }

        if (imagens.size() > MAX_IMAGES_PER_REQUEST) {
            throw new AppException(HttpStatus.BAD_REQUEST,
                    "Numero maximo de imagens por requisicao excedido. Maximo: " + MAX_IMAGES_PER_REQUEST);
        }

        List<VeiculoImagem> imagensSalvas = new ArrayList<>();

        for (MultipartFile arquivo : imagens) {
            validarArquivo(arquivo);

            try {
                VeiculoImagem imagem = VeiculoImagem.builder()
                        .codVei(codVei)
                        .documento(arquivo.getBytes())
                        .nomeArq(arquivo.getOriginalFilename())
                        .contentType(arquivo.getContentType())
                        .tamanho(arquivo.getSize())
                        .build();

                VeiculoImagem imagemSalva = imagemRepository.save(imagem);
                imagensSalvas.add(imagemSalva);

                log.info("Imagem salva com sucesso: ID={}, Nome={}, Tamanho={} bytes",
                         imagemSalva.getId(), imagemSalva.getNomeArq(), imagemSalva.getTamanho());
            } catch (IOException e) {
                log.error("Erro ao processar arquivo: {}", arquivo.getOriginalFilename(), e);
                throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Erro ao processar arquivo: " + arquivo.getOriginalFilename());
            }
        }

        log.info("Upload concluído: {} imagens salvas para o veículo {}", imagensSalvas.size(), codVei);

        return imagensSalvas.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<VeiculoImagemDto> listarImagensPorVeiculo(Integer codVei) {
        log.debug("Listando imagens do veículo {}", codVei);

        if (!veiculoRepository.existsById(codVei)) {
            throw new AppException(HttpStatus.NOT_FOUND, "Veiculo nao encontrado com ID: " + codVei);
        }

        List<VeiculoImagem> imagens = imagemRepository.findByCodVei(codVei);
        log.debug("Encontradas {} imagens para o veículo {}", imagens.size(), codVei);

        return imagens.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public VeiculoImagem buscarImagemPorId(Integer id) {
        log.debug("Buscando imagem com ID {}", id);
        return imagemRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Imagem não encontrada com ID: {}", id);
                    return new AppException(HttpStatus.NOT_FOUND, "Imagem nao encontrada com ID: " + id);
                });
    }

    @Transactional
    public void excluirImagem(Integer id) {
        log.info("Excluindo imagem com ID {}", id);

        VeiculoImagem imagem = buscarImagemPorId(id);
        imagemRepository.deleteById(id);

        log.info("Imagem excluída com sucesso: ID={}, Nome={}", id, imagem.getNomeArq());
    }

    @Transactional
    public void excluirTodasImagensDoVeiculo(Integer codVei) {
        log.info("Excluindo todas as imagens do veículo {}", codVei);

        long count = imagemRepository.deleteByCodVei(codVei);

        log.info("{} imagens excluídas do veículo {}", count, codVei);
    }

    private void validarArquivo(MultipartFile arquivo) {
        if (arquivo == null || arquivo.isEmpty()) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Arquivo vazio");
        }

        String nomeArquivo = arquivo.getOriginalFilename();
        if (nomeArquivo == null || nomeArquivo.isBlank()) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Nome do arquivo invalido");
        }

        if (arquivo.getSize() > MAX_FILE_SIZE) {
            throw new AppException(HttpStatus.BAD_REQUEST,
                    String.format("Arquivo muito grande (%s). Tamanho maximo: 5MB", nomeArquivo));
        }

        String contentType = arquivo.getContentType();
        if (contentType == null || !ALLOWED_CONTENT_TYPES.contains(contentType.toLowerCase())) {
            throw new AppException(HttpStatus.BAD_REQUEST,
                    String.format("Tipo de arquivo nao permitido: %s. Apenas imagens sao aceitas (JPEG, PNG, GIF, WEBP, BMP)",
                                  contentType != null ? contentType : "desconhecido"));
        }

        log.debug("Arquivo validado com sucesso: {}, Tipo: {}, Tamanho: {} bytes",
                  nomeArquivo, contentType, arquivo.getSize());
    }
}
