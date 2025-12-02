package br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.ordemServico;

import br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.itemOrdemServico.ItemOrdemServico;
import br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.itemOrdemServico.ItemOrdemServicoRepository;
import br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.itemOrdemServico.dto.ItemOrdemServicoCreateUpdateDTO;
import br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.itemOrdemServico.mapper.ItemOrdemServicoMapper;
import br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.ordemServico.dto.OrdemServicoCreateUpdateDTO;
import br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.ordemServico.dto.OrdemServicoDTO;
import br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.ordemServico.mapper.OrdemServicoMapper;
import br.com.vetorsistemas.ronalds_ws.shared.AppException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdemServicoService {

    private final OrdemServicoRepository repository;
    private final OrdemServicoMapper mapper;
    private final EntityManager entityManager;
    private final ItemOrdemServicoRepository itemOrdemServicoRepository;
    private final ItemOrdemServicoMapper itemMapper;

    public OrdemServicoService(OrdemServicoRepository repository,
                               OrdemServicoMapper mapper,
                               EntityManager entityManager,
                               ItemOrdemServicoRepository itemOrdemServicoRepository,
                               ItemOrdemServicoMapper itemMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.entityManager = entityManager;
        this.itemOrdemServicoRepository = itemOrdemServicoRepository;
        this.itemMapper = itemMapper;
    }

    @Transactional(readOnly = true)
    public Page<OrdemServicoDTO> search(Integer codigoOS,
                                         String placa,
                                         LocalDate dataInicial,
                                         LocalDate dataFinal,
                                         Integer codigoFuncionario,
                                         Integer codigoCliente,
                                         String numeroNotaFiscalServico,
                                         String numeroNotaFiscalProduto,
                                         String faturamento,
                                         Integer pagina,
                                         Integer tamanhoPagina) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrdemServico> query = cb.createQuery(OrdemServico.class);
        Root<OrdemServico> root = query.from(OrdemServico.class);

        // Join fetch para carregar as entidades relacionadas
        root.fetch("funcionario", JoinType.LEFT);
        root.fetch("cliente", JoinType.LEFT);
        root.fetch("veiculo", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();

        // Filtro por código da OS
        if (codigoOS != null) {
            predicates.add(cb.equal(root.get("id"), codigoOS));
        }

        // Filtro por placa do veículo
        if (placa != null && !placa.trim().isEmpty()) {
            Join<Object, Object> veiculoJoin = (Join<Object, Object>) root.fetch("veiculo", JoinType.LEFT);
            predicates.add(cb.equal(cb.upper(veiculoJoin.get("placa")), placa.trim().toUpperCase() ));
        }

        // Filtro por período de data de emissão
        if (dataInicial != null) {
            LocalDateTime dataInicialDateTime = dataInicial.atStartOfDay();
            predicates.add(cb.greaterThanOrEqualTo(root.get("dataAbertura"), dataInicialDateTime));
        }

        if (dataFinal != null) {
            LocalDateTime dataFinalDateTime = dataFinal.atTime(LocalTime.MAX);
            predicates.add(cb.lessThanOrEqualTo(root.get("dataAbertura"), dataFinalDateTime));
        }

        // Filtro por funcionário
        if (codigoFuncionario != null) {
            predicates.add(cb.equal(root.get("codigoFuncionario"), codigoFuncionario));
        }

        // Filtro por cliente
        if (codigoCliente != null) {
            predicates.add(cb.equal(root.get("codigoCliente"), codigoCliente));
        }

        // Filtro por nota fiscal de serviço
        if (numeroNotaFiscalServico != null && !numeroNotaFiscalServico.trim().isEmpty()) {
            predicates.add(cb.like(cb.upper(root.get("numeroNotaFiscalServico")),
                "%" + numeroNotaFiscalServico.trim().toUpperCase() + "%"));
        }

        // Filtro por nota fiscal de produto
        if (numeroNotaFiscalProduto != null && !numeroNotaFiscalProduto.trim().isEmpty()) {
            predicates.add(cb.like(cb.upper(root.get("numeroNotaFiscalProduto")),
                "%" + numeroNotaFiscalProduto.trim().toUpperCase() + "%"));
        }

        if (faturamento == null){ faturamento = "N"; }

        if (faturamento.equals("S") || faturamento.equals("N")) {
            predicates.add(cb.equal(root.get("faturamento"), faturamento));

        }

        query.where(predicates.toArray(new Predicate[0]));
        query.orderBy(cb.desc(root.get("dataAbertura")));

        // Paginação
        if (pagina == null || pagina < 0) pagina = 0;
        if (tamanhoPagina == null || tamanhoPagina <= 0) tamanhoPagina = 10;

        // Query para contar total de registros
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<OrdemServico> countRoot = countQuery.from(OrdemServico.class);

        // Recriar os predicates para a query de contagem (sem joins)
        List<Predicate> countPredicates = new ArrayList<>();
        if (codigoOS != null) {
            countPredicates.add(cb.equal(countRoot.get("id"), codigoOS));
        }
        if (placa != null && !placa.trim().isEmpty()) {
            Join<Object, Object> veiculoJoin = countRoot.join("veiculo", JoinType.LEFT);
            countPredicates.add(cb.equal(cb.upper(veiculoJoin.get("placa")), placa.trim().toUpperCase()));
        }
        if (dataInicial != null) {
            LocalDateTime dataInicialDateTime = dataInicial.atStartOfDay();
            countPredicates.add(cb.greaterThanOrEqualTo(countRoot.get("dataAbertura"), dataInicialDateTime));
        }
        if (dataFinal != null) {
            LocalDateTime dataFinalDateTime = dataFinal.atTime(LocalTime.MAX);
            countPredicates.add(cb.lessThanOrEqualTo(countRoot.get("dataAbertura"), dataFinalDateTime));
        }
        if (codigoFuncionario != null) {
            countPredicates.add(cb.equal(countRoot.get("codigoFuncionario"), codigoFuncionario));
        }
        if (codigoCliente != null) {
            countPredicates.add(cb.equal(countRoot.get("codigoCliente"), codigoCliente));
        }
        if (numeroNotaFiscalServico != null && !numeroNotaFiscalServico.trim().isEmpty()) {
            countPredicates.add(cb.like(cb.upper(countRoot.get("numeroNotaFiscalServico")),
                "%" + numeroNotaFiscalServico.trim().toUpperCase() + "%"));
        }
        if (numeroNotaFiscalProduto != null && !numeroNotaFiscalProduto.trim().isEmpty()) {
            countPredicates.add(cb.like(cb.upper(countRoot.get("numeroNotaFiscalProduto")),
                "%" + numeroNotaFiscalProduto.trim().toUpperCase() + "%"));
        }

        countQuery.select(cb.count(countRoot));
        countQuery.where(countPredicates.toArray(new Predicate[0]));

        Long total = entityManager.createQuery(countQuery).getSingleResult();

        // Buscar resultados paginados
        List<OrdemServico> resultList = entityManager.createQuery(query)
                .setFirstResult(pagina * tamanhoPagina)
                .setMaxResults(tamanhoPagina)
                .getResultList();

        List<OrdemServicoDTO> dtos = resultList.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());

        Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina);
        return new PageImpl<>(dtos, pageRequest, total);
    }

    @Transactional(readOnly = true)
    public OrdemServicoDTO findById(Integer id) {
        // Usar EntityGraph para fazer o fetch dos relacionamentos
        OrdemServico entity = entityManager.createQuery(
                "SELECT os FROM OrdemServico os " +
                "LEFT JOIN FETCH os.funcionario " +
                "LEFT JOIN FETCH os.cliente " +
                "LEFT JOIN FETCH os.veiculo " +
                "WHERE os.id = :id", OrdemServico.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst()
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Ordem de Serviço não encontrada"));

        return mapper.toDTO(entity);
    }

    @Transactional
    public OrdemServicoDTO create(OrdemServicoCreateUpdateDTO dto) {
        validarOrdemServico(dto);
        OrdemServico entity = mapper.fromCreateUpdateDTO(dto);
        OrdemServico saved = repository.save(entity);

        // Processar itens se existirem
        processarItens(saved.getId(), dto.getItens());

        return findById(saved.getId());
    }

    @Transactional
    public OrdemServicoDTO update(Integer id, OrdemServicoCreateUpdateDTO dto) {
        validarOrdemServico(dto);

        OrdemServico entity = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Ordem de Serviço não encontrada"));

        mapper.updateEntityFromDTO(dto, entity);
        repository.save(entity);

        // Processar itens se existirem
        processarItens(id, dto.getItens());

        return findById(id);
    }

    /**
     * Processa os itens da ordem de serviço.
     * - Item com id = null: inserção
     * - Item com id preenchido: alteração
     */
    private void processarItens(Integer codigoOrdemServico, List<ItemOrdemServicoCreateUpdateDTO> itens) {
        if (itens == null || itens.isEmpty()) {
            return;
        }

        for (ItemOrdemServicoCreateUpdateDTO itemDTO : itens) {
            // Define o código da ordem de serviço no item
            itemDTO.setCodigoOrdemServico(codigoOrdemServico);

            if (itemDTO.getId() == null) {
                // Inserção: criar novo item
                ItemOrdemServico novoItem = itemMapper.fromCreateUpdateDTO(itemDTO);
                itemOrdemServicoRepository.save(novoItem);
            } else {
                // Alteração: buscar item existente e atualizar
                ItemOrdemServico itemExistente = itemOrdemServicoRepository.findById(itemDTO.getId())
                        .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND,
                                "Item da Ordem de Serviço não encontrado: " + itemDTO.getId()));

                itemMapper.updateEntityFromDTO(itemDTO, itemExistente);
                itemOrdemServicoRepository.save(itemExistente);
            }
        }
    }

    @Transactional
    public void delete(Integer id) {
        OrdemServico ordemServico = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Ordem de Serviço não encontrada"));

        // Verificar se a ordem de serviço está faturada
        if ("S".equalsIgnoreCase(ordemServico.getFaturamento())) {
            throw new AppException(HttpStatus.BAD_REQUEST,
                    "Não é possível excluir uma Ordem de Serviço já faturada");
        }

        // Deletar todos os itens da ordem de serviço (cascade manual)
        itemOrdemServicoRepository.deleteByCodigoOrdemServico(id);

        // Deletar a ordem de serviço
        repository.deleteById(id);
    }

    private void validarOrdemServico(OrdemServicoCreateUpdateDTO dto) {
        if (dto.getTipoServico() == null || dto.getTipoServico().isEmpty()) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Tipo de serviço é obrigatório");
        }

        if (dto.getCodigoFuncionario() == null) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Funcionário é obrigatório");
        }

        if (dto.getCodigoCliente() == null) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Cliente é obrigatório");
        }

        if (dto.getCodigoVeiculo() == null) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Veículo é obrigatório");
        }

        // Definir valores padrão se não informados
        if (dto.getDataAbertura() == null) {
            dto.setDataAbertura(LocalDateTime.now());
        }

        if (dto.getHoraAbertura() == null) {
            dto.setHoraAbertura(LocalDateTime.now());
        }

        if (dto.getCodigoCondicaoPagamento() == null) {
            dto.setCodigoCondicaoPagamento(0);
        }

        if (dto.getServicoLiberado() == null) {
            dto.setServicoLiberado("N");
        }

        if (dto.getFaturamento() == null) {
            dto.setFaturamento("N");
        }

        // Inicializar valores numéricos com 0 se null
        if (dto.getTotalBruto() == null) dto.setTotalBruto(0.0);
        if (dto.getValorDesconto() == null) dto.setValorDesconto(0.0);
        if (dto.getTotalLiquido() == null) dto.setTotalLiquido(0.0);
        if (dto.getProdutoBruto() == null) dto.setProdutoBruto(0.0);
        if (dto.getProdutoDesconto() == null) dto.setProdutoDesconto(0.0);
        if (dto.getProdutoLiquido() == null) dto.setProdutoLiquido(0.0);
        if (dto.getServicoBruto() == null) dto.setServicoBruto(0.0);
        if (dto.getServicoDesconto() == null) dto.setServicoDesconto(0.0);
        if (dto.getServicoLiquido() == null) dto.setServicoLiquido(0.0);
        if (dto.getLubrificanteBruto() == null) dto.setLubrificanteBruto(0.0);
        if (dto.getLubrificanteDesconto() == null) dto.setLubrificanteDesconto(0.0);
        if (dto.getLubrificanteLiquido() == null) dto.setLubrificanteLiquido(0.0);
        if (dto.getPecaParalelaBruta() == null) dto.setPecaParalelaBruta(0.0);
        if (dto.getPecaParalelaDesconto() == null) dto.setPecaParalelaDesconto(0.0);
        if (dto.getPecaParalelaLiquida() == null) dto.setPecaParalelaLiquida(0.0);
        if (dto.getTotalDeslocamento() == null) dto.setTotalDeslocamento(0.0);
    }
}
