package br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.itemOrdemServico;

import br.com.vetorsistemas.ronalds_ws.cadastro.produto.Produto;
import br.com.vetorsistemas.ronalds_ws.cadastro.produto.ProdutoRepository;
import br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.itemOrdemServico.dto.ItemOrdemServicoCreateUpdateDTO;
import br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.itemOrdemServico.dto.ItemOrdemServicoDTO;
import br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.itemOrdemServico.mapper.ItemOrdemServicoMapper;
import br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.ordemServico.OrdemServico;
import br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.ordemServico.OrdemServicoRepository;
import br.com.vetorsistemas.ronalds_ws.shared.AppException;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemOrdemServicoService {

    private final ItemOrdemServicoRepository repository;
    private final ItemOrdemServicoMapper mapper;
    private final OrdemServicoRepository ordemServicoRepository;
    private final ProdutoRepository produtoRepository;
    private final EntityManager entityManager;

    public ItemOrdemServicoService(ItemOrdemServicoRepository repository,
                                    ItemOrdemServicoMapper mapper,
                                    OrdemServicoRepository ordemServicoRepository,
                                    ProdutoRepository produtoRepository,
                                    EntityManager entityManager) {
        this.repository = repository;
        this.mapper = mapper;
        this.ordemServicoRepository = ordemServicoRepository;
        this.produtoRepository = produtoRepository;
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public Page<ItemOrdemServicoDTO> search(Integer codigoOrdemServico,
                                             Integer codigoProduto,
                                             String tipoProduto,
                                             Integer pagina,
                                             Integer tamanhoPagina) {

        Specification<ItemOrdemServico> specs = (root, query, cb) -> cb.conjunction();

        if (codigoOrdemServico != null) {
            specs = specs.and((root, q, cb) -> cb.equal(root.get("codigoOrdemServico"), codigoOrdemServico));
        }

        if (codigoProduto != null) {
            specs = specs.and((root, q, cb) -> cb.equal(root.get("codigoProduto"), codigoProduto));
        }

        if (tipoProduto != null && !tipoProduto.trim().isEmpty()) {
            specs = specs.and((root, q, cb) -> cb.equal(root.get("tipoProduto"), tipoProduto.trim()));
        }

        if (pagina == null || pagina < 0) pagina = 0;
        if (tamanhoPagina == null || tamanhoPagina <= 0) tamanhoPagina = 10;

        Sort sort = Sort.by("id").ascending();
        Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina, sort);

        Page<ItemOrdemServico> page = repository.findAll(specs, pageRequest);
        return page.map(mapper::toDTO);
    }

    @Transactional(readOnly = true)
    public List<ItemOrdemServicoDTO> findByOrdemServico(Integer codigoOrdemServico) {
        List<ItemOrdemServico> items = entityManager.createQuery(
                "SELECT i FROM ItemOrdemServico i " +
                "LEFT JOIN FETCH i.produto " +
                "WHERE i.codigoOrdemServico = :codigoOrdemServico " +
                "ORDER BY i.id", ItemOrdemServico.class)
                .setParameter("codigoOrdemServico", codigoOrdemServico)
                .getResultList();

        return items.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ItemOrdemServicoDTO findById(Integer id) {
        ItemOrdemServico entity = entityManager.createQuery(
                "SELECT i FROM ItemOrdemServico i " +
                "LEFT JOIN FETCH i.produto " +
                "WHERE i.id = :id", ItemOrdemServico.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst()
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Item da Ordem de Serviço não encontrado"));

        return mapper.toDTO(entity);
    }

    @Transactional
    public ItemOrdemServicoDTO create(ItemOrdemServicoCreateUpdateDTO dto) {
        validarItem(dto);

        // Se o tipo de produto for 2 (Serviço) ou 4 (Geometria), a descrição é alterável
        // Caso contrário, busca a descrição do cadastro de produtos
        if (dto.getTipoProduto() != null &&
            !dto.getTipoProduto().equals("2") &&
            !dto.getTipoProduto().equals("4")) {

            Produto produto = produtoRepository.findById(dto.getCodigoProduto())
                    .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

            dto.setDescricaoProduto(produto.getDescricao());
        }

        // Calcular valores
        calcularValores(dto);

        ItemOrdemServico entity = mapper.fromCreateUpdateDTO(dto);
        ItemOrdemServico saved = repository.save(entity);
        return findById(saved.getId());
    }

    @Transactional
    public ItemOrdemServicoDTO update(Integer id, ItemOrdemServicoCreateUpdateDTO dto) {
        validarItem(dto);

        ItemOrdemServico entity = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Item da Ordem de Serviço não encontrado"));

        // Se o tipo de produto for 2 (Serviço) ou 4 (Geometria), a descrição é alterável
        // Caso contrário, busca a descrição do cadastro de produtos
        if (dto.getTipoProduto() != null &&
            !dto.getTipoProduto().equals("2") &&
            !dto.getTipoProduto().equals("4")) {

            Produto produto = produtoRepository.findById(dto.getCodigoProduto())
                    .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

            dto.setDescricaoProduto(produto.getDescricao());
        }

        // Calcular valores
        calcularValores(dto);

        mapper.updateEntityFromDTO(dto, entity);
        repository.save(entity);

        return findById(id);
    }

    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND, "Item da Ordem de Serviço não encontrado");
        }
        repository.deleteById(id);
    }

    private void validarItem(ItemOrdemServicoCreateUpdateDTO dto) {
        if (dto.getCodigoOrdemServico() == null) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Código da Ordem de Serviço é obrigatório");
        }

        // Verificar se a ordem de serviço existe
        OrdemServico ordemServico = ordemServicoRepository.findById(dto.getCodigoOrdemServico())
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Ordem de Serviço não encontrada"));

        // Verificar se a ordem de serviço está faturada
        if ("S".equalsIgnoreCase(ordemServico.getFaturamento())) {
            throw new AppException(HttpStatus.BAD_REQUEST,
                "Não é possível adicionar/alterar itens em uma Ordem de Serviço já faturada");
        }

        if (dto.getCodigoProduto() == null) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Código do Produto é obrigatório");
        }

        if (dto.getTipoProduto() == null || dto.getTipoProduto().trim().isEmpty()) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Tipo de Produto é obrigatório");
        }

        if (dto.getDescricaoProduto() == null || dto.getDescricaoProduto().trim().isEmpty()) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Descrição do Produto é obrigatória");
        }

        // Definir valores padrão
        if (dto.getQuantidade() == null) dto.setQuantidade(0.0);
        if (dto.getPrecoUnitario() == null) dto.setPrecoUnitario(0.0);
        if (dto.getPercentualDesconto() == null) dto.setPercentualDesconto(0.0);
        if (dto.getCodigoCfo() == null) dto.setCodigoCfo(0);
    }

    private void calcularValores(ItemOrdemServicoCreateUpdateDTO dto) {
        // Valor bruto = quantidade * preço unitário
        Double valorBruto = dto.getQuantidade() * dto.getPrecoUnitario();
        dto.setValorBruto(valorBruto);

        // Valor desconto = valor bruto * (percentual desconto / 100)
        Double valorDesconto = valorBruto * (dto.getPercentualDesconto() / 100);
        dto.setValorDesconto(valorDesconto);

        // Valor líquido = valor bruto - valor desconto
        Double valorLiquido = valorBruto - valorDesconto;
        dto.setValorLiquido(valorLiquido);
    }
}
