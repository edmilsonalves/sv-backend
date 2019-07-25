package com.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.api.dto.ImagemDTO;
import com.api.dto.OptionDTO;
import com.api.dto.ProdutoDTO;
import com.api.dto.TipoAtributoDTO;
import com.api.dto.VariacaoDTO;
import com.api.entity.Atributo;
import com.api.entity.Categoria;
import com.api.entity.Estoque;
import com.api.entity.Imagem;
import com.api.entity.Produto;
import com.api.entity.TipoAtributo;
import com.api.entity.UnidadeMedida;
import com.api.entity.Variacao;
import com.api.entity.VariacaoItem;
import com.api.enums.ErrorCode;
import com.api.enums.StatusProdutoEnum;
import com.api.enums.TipoProdutoEnum;
import com.api.exception.BusinessException;
import com.api.repository.CategoriaRepository;
import com.api.repository.EstoqueRepository;
import com.api.repository.ImagemRepository;
import com.api.repository.ProdutoRepository;
import com.api.repository.TipoAtributoRepository;
import com.api.repository.UnidadeMedidaRepository;
import com.api.repository.VariacaoItemRepository;
import com.api.repository.VariacaoRepository;
import com.api.util.SUtils;

@Service
@EnableTransactionManagement
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private TipoAtributoRepository tipoAtributoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private UnidadeMedidaRepository unidadeMedidaRepository;

	@Autowired
	private EstoqueRepository estoqueRepository;

	@Autowired
	private VariacaoRepository variacaoRepository;

	@Autowired
	private VariacaoItemRepository variacaoItemRepository;

	@Autowired
	private ImagemRepository imagemRepository;

	@Transactional(rollbackFor = { Exception.class })
	public ProdutoDTO insert(ProdutoDTO produtoDTO) throws BusinessException {
		Produto produto = new Produto();

		if (!SUtils.isNullOrEmpty(produtoDTO.getCodigo())) {
			Produto produtoPK = this.produtoRepository.findByCodigo(produtoDTO.getCodigo());

			if (!SUtils.isNull(produtoPK) && !produtoPK.getCodigo().equals(produtoDTO.getCodigo())) {
				throw new BusinessException("Esse Código busca já está sendo utilizado em outro produto!");
			}
		}

		BeanUtils.copyProperties(produtoDTO, produto);
		produto.setStatus(StatusProdutoEnum.findById(produtoDTO.getStatus()));
		produto.setTipo(TipoProdutoEnum.findById(produtoDTO.getTipo()));

		Estoque estoque = new Estoque();
		// estoque.setId(produtoDTO.getIdEstoque());
		// estoque.setAlerta(produtoDTO.getEstoqueAlerta());
		// estoque.setAtual(produtoDTO.getEstoqueAtual());
		// estoque = this.estoqueRepository.save(estoque);
		// produto.setIdEstoque(estoque.getId());
		//
		// produto = this.produtoRepository.save(produto);
		// produto.setEstoque(estoque);

		return convertToDto(this.produtoRepository.save(produto));
	}

	@Transactional(rollbackFor = { Exception.class })
	public ProdutoDTO update(ProdutoDTO produtoDTO) throws BusinessException {

		Produto produto = this.produtoRepository.findById(produtoDTO.getId()).orElse(new Produto());

		if (!SUtils.isNullOrEmpty(produtoDTO.getCodigo())) {
			Produto produtoPK = this.produtoRepository.findByCodigo(produtoDTO.getCodigo());

			if (!SUtils.isNull(produtoPK) && !produtoPK.getCodigo().equals(produtoDTO.getCodigo())) {
				throw new BusinessException(ErrorCode.SIS_6);
			}
		}

		BeanUtils.copyProperties(produtoDTO, produto, "listCategoria", "listUnidadeMedida", "listImagem",
				"listVariacao", "abaManipulada");

		produto.setStatus(StatusProdutoEnum.findById(produtoDTO.getStatus()));
		produto.setTipo(TipoProdutoEnum.findById(produtoDTO.getTipo()));

		if (produto.getPossuiVariacao()) {
			salvarVariacao(produto.getId(), produtoDTO.getListVariacao());
		}

		// produto.getEstoque().setAlerta(produtoDTO.getEstoqueAlerta());
		// produto.getEstoque().setAtual(produtoDTO.getEstoqueAtual());
		// this.estoqueRepository.save(produto.getEstoque());

		produto = this.produtoRepository.save(produto);

		produtoDTO = findById(produto.getId());

		return produtoDTO;
	}

	private void salvarVariacao(Long idProduto, List<VariacaoDTO> listVariacao) {

		if (listVariacao != null && !listVariacao.isEmpty()) {
			for (VariacaoDTO variacaoDTO : listVariacao) {
				Variacao variacao = new Variacao();

				if (variacaoDTO.getId() != null) {
					this.variacaoItemRepository.deleteByIdVariacao(variacaoDTO.getId());
					this.imagemRepository.deleteByIdVariacao(variacaoDTO.getId());
					variacao = this.variacaoRepository.findById(variacaoDTO.getId()).orElse(null);
				}

				BeanUtils.copyProperties(variacaoDTO, variacao, "listTipoAtributo", "listImagem");

				variacao.setIdProduto(idProduto);
				variacao = this.variacaoRepository.save(variacao);

				if (variacaoDTO.getListTipoAtributo() != null && !variacaoDTO.getListTipoAtributo().isEmpty()) {

					for (TipoAtributoDTO tipoAtributo : variacaoDTO.getListTipoAtributo()) {
						if (tipoAtributo.getIdAtributoSelecionado() != null) {
							VariacaoItem variacaoItem = new VariacaoItem(variacao.getId(), tipoAtributo.getId(),
									tipoAtributo.getIdAtributoSelecionado());
							this.variacaoItemRepository.save(variacaoItem);
						}
					}
				}

				if (variacaoDTO.getListImagem() != null && !variacaoDTO.getListImagem().isEmpty()) {

					for (ImagemDTO imagemDTO : variacaoDTO.getListImagem()) {
						Imagem imagem = new Imagem();
						BeanUtils.copyProperties(imagemDTO, imagem, "file");

						String remove = "data:" + imagem.getType() + ";base64,";

						Base64 codec = new Base64();
						byte[] bytes = codec.decode(imagemDTO.getFile().replace(remove, ""));

						imagem.setId(null);
						imagem.setFile(bytes);
						imagem.setIdVariacao(variacao.getId());

						this.imagemRepository.save(imagem);
					}
				}
			}
		}
	}

	@Transactional(rollbackFor = { Exception.class })
	public List<Produto> findByNomeStartingWith(String nome) {
		List<Produto> produtoList = produtoRepository.findByNomeStartingWith(nome);

		if (CollectionUtils.isEmpty(produtoList)) {
			throw new BusinessException("Nenhuma produto encontrado.");
		}

		return produtoList;
	}

	public void verificaExistencia(Produto produto) throws BusinessException {
		buscar(produto.getId());
	}

	public Produto buscar(Long id) throws BusinessException {
		Produto produto = produtoRepository.findById(id).orElse(null);
		if (ObjectUtils.isEmpty(produto)) {
			throw new BusinessException("Nenhum produto encontrado.");
		}
		return produto;
	}

	public Page<Produto> listar(Pageable pageable, String search) {
		return produtoRepository.findByNomeContainingOrCodigoContaining(pageable, search, search);
	}

	public void excluir(Long id) throws BusinessException {

		// foi necessario buscar o produto novamente para pode recuperar a
		// classe Produto no
		// metodo doBasicDomainAuditoriaDelete da classe AuditoriaAspect
		Produto produto = produtoRepository.findById(id).orElse(null);
		produtoRepository.delete(produto);
	}

	public List<ProdutoDTO> findAll() {
		List<Produto> produtoList = produtoRepository.findAll();

		return convertToDtoList(produtoList);
	}

	public ProdutoDTO findById(Long id) {
		Produto produto = this.produtoRepository.findByIdWithChildren(id);

		if (produto == null) {
			throw new BusinessException(ErrorCode.SIS_2);
		}

		ProdutoDTO produtoDTO = convertToDto(produto);

		return produtoDTO;
	}

	public List<ProdutoDTO> findByNomeContainingOrCodigoContaining(String query) {
		return convertToDtoList(produtoRepository.findByNomeContainingOrCodigoContaining(query, query));
	}

	public List<ProdutoDTO> findByNomeContainingOrCodigoContainingAndAtivo(String query, StatusProdutoEnum status) {
		return convertToDtoList(
				produtoRepository.findByNomeContainingOrCodigoContainingAndStatus(query, query, status));
	}

	public List<ProdutoDTO> listByFilter(String query, String status) {
		return convertToDtoList(produtoRepository.listByFilter(query != null && !query.isEmpty() ? query : null,
				status != null ? StatusProdutoEnum.findByName(status) : StatusProdutoEnum.ATIVO));
	}

	public ProdutoDTO findByCodigo(String id) {
		return convertToDto(produtoRepository.findByCodigo(id));
	}

	public List<ProdutoDTO> findByAtivo(boolean ativo) {
		return convertToDtoList(produtoRepository.findByStatus(StatusProdutoEnum.ATIVO));
	}

	private List<ProdutoDTO> convertToDtoList(List<Produto> entities) {

		if (entities == null) {
			return null;
		}

		List<ProdutoDTO> list = new ArrayList<>(0);

		entities.forEach(produto -> {
			ProdutoDTO dto = convertSemImagemToDto(produto);

			Imagem imagem = null;
			if (dto.getPossuiVariacao()) {
				for (Variacao variacao : produto.getListVariacao()) {
					if (variacao.getOrdem() != null && variacao.getOrdem().equals(1)) {
						imagem = variacao.getListImagem().stream().filter(img -> img.getOrdem().equals(1)).findFirst()
								.orElse(null);
						break;
					}
				}
			} else {
				imagem = produto.getListImagem().stream().filter(img -> img.getOrdem().equals(1)).findFirst()
						.orElse(null);
			}

			if (imagem != null) {
				Base64 codec = new Base64();
				String base64 = codec.encodeAsString(imagem.getFile());
				dto.setImagemDestaque("data:" + imagem.getType() + ";base64," + base64);
			}

			list.add(dto);
		});

		return list;
	}

	private ProdutoDTO convertSemImagemToDto(Produto entity) {
		if (entity == null) {
			return null;
		}

		ProdutoDTO dto = new ProdutoDTO();
		BeanUtils.copyProperties(entity, dto, "listImagem");

		dto.setTipo(entity.getTipo().ordinal());
		dto.setTipoDescricao(entity.getTipo().getDescricao());
		dto.setIdCategoria(entity.getIdCategoria());

		dto.setStatus(entity.getStatus().ordinal());
		dto.setStatusDescricao(entity.getStatus().getDescricao());

		if (entity.getEstoque() != null) {
			dto.setEstoqueAtual(entity.getEstoque().getAtual());
			dto.setEstoqueMinimo(entity.getEstoque().getMinimo());
		}

		return dto;
	}

	private ProdutoDTO convertToDto(Produto entity) {
		if (entity == null) {
			return null;
		}

		ProdutoDTO dto = new ProdutoDTO();
		BeanUtils.copyProperties(entity, dto, "listImagem");

		dto.setTipo(entity.getTipo().ordinal());
		dto.setTipoDescricao(entity.getTipo().getDescricao());
		dto.setIdCategoria(entity.getIdCategoria());

		dto.setStatus(entity.getStatus().ordinal());
		dto.setStatusDescricao(entity.getStatus().getDescricao());

		if (entity.getEstoque() != null) {
			dto.setEstoqueAtual(entity.getEstoque().getAtual());
			dto.setEstoqueMinimo(entity.getEstoque().getMinimo());
		}

		if (entity.getPossuiVariacao()) {
			dto.setListVariacao(montaVariacoesProduto(entity.getListVariacao()));
		} else {
			if (entity.getListImagem() != null && !entity.getListImagem().isEmpty()) {
				entity.getListImagem().forEach(imagem -> {
					ImagemDTO imagemDTO = new ImagemDTO();

					BeanUtils.copyProperties(imagem, imagemDTO, "file");

					Base64 codec = new Base64();
					String base64 = codec.encodeAsString(imagem.getFile());
					imagemDTO.setFile("data:" + imagem.getType() + ";base64," + base64);

					dto.getListImagem().add(imagemDTO);
				});
			}
		}

		return dto;
	}

	private List<VariacaoDTO> montaVariacoesProduto(Set<Variacao> entities) {

		if (entities == null) {
			return null;
		}

		List<VariacaoDTO> list = new ArrayList<>(0);

		List<TipoAtributo> listTipoAtributo = this.tipoAtributoRepository.findByWithChildren();

		for (Variacao variacao : entities) {
			VariacaoDTO variacaoDTO = new VariacaoDTO();
			variacaoDTO.setId(variacao.getId());
			variacaoDTO.setCodigo(variacao.getCodigo());
			variacaoDTO.setIdProduto(variacao.getIdProduto());
			variacaoDTO.setPrecoCusto(variacao.getPrecoCusto());
			variacaoDTO.setPrecoVenda(variacao.getPrecoVenda());
			variacaoDTO.setPrecoPromocional(variacao.getPrecoPromocional());
			variacaoDTO.setOrdem(variacao.getOrdem());
			variacaoDTO.setEstoqueAtual(variacao.getEstoqueAtual());

			if (variacao.getListImagem() != null && !variacao.getListImagem().isEmpty()) {
				for (Imagem imagem : variacao.getListImagem()) {
					ImagemDTO imagemDTO = new ImagemDTO();
					BeanUtils.copyProperties(imagem, imagemDTO, "file");
					Base64 codec = new Base64();
					String base64 = codec.encodeAsString(imagem.getFile());
					imagemDTO.setFile("data:" + imagem.getType() + ";base64," + base64);
					variacaoDTO.getListImagem().add(imagemDTO);
				}

				variacaoDTO.setListImagem(variacaoDTO.getListImagem().stream()
						.sorted((i1, i2) -> i1.getOrdem().compareTo(i2.getOrdem())).collect(Collectors.toList()));
			}

			if (variacao.getListVariacaoItem() != null && !variacao.getListVariacaoItem().isEmpty()) {
				for (VariacaoItem item : variacao.getListVariacaoItem()) {
					TipoAtributoDTO tipoAtributoDTO = new TipoAtributoDTO();
					tipoAtributoDTO.setId(item.getIdTipoAtributo());
					if (item.getTipoAtributo() == null && item.getIdTipoAtributo() != null) {
						item.setTipoAtributo(
								this.tipoAtributoRepository.findById(item.getIdTipoAtributo()).orElse(null));
					}

					tipoAtributoDTO.setDescricao(item.getTipoAtributo().getDescricao());
					tipoAtributoDTO.setIdAtributoSelecionado(item.getIdAtributoSelecionado());

					TipoAtributo tipoAtributo = listTipoAtributo.stream()
							.filter(ta -> ta.getId() == item.getIdTipoAtributo()).findFirst().orElse(null);

					if (tipoAtributo != null && tipoAtributo.getListAtributo() != null
							&& !tipoAtributo.getListAtributo().isEmpty()) {
						for (Atributo atributo : tipoAtributo.getListAtributo()) {
							tipoAtributoDTO.getListAtributo()
									.add(new OptionDTO(atributo.getId(), atributo.getDescricao()));
						}
					}
					variacaoDTO.getListTipoAtributo().add(tipoAtributoDTO);
				}

				if (listTipoAtributo != null && !listTipoAtributo.isEmpty()) {
					for (TipoAtributo tipoAtributo : listTipoAtributo) {
						TipoAtributoDTO tipoAtributoDTO = new TipoAtributoDTO(tipoAtributo.getId());
						if (!variacaoDTO.getListTipoAtributo().contains(tipoAtributoDTO)) {
							tipoAtributoDTO.setDescricao(tipoAtributo.getDescricao());
							for (Atributo atributo : tipoAtributo.getListAtributo()) {
								tipoAtributoDTO.getListAtributo()
										.add(new OptionDTO(atributo.getId(), atributo.getDescricao()));
							}
							variacaoDTO.getListTipoAtributo().add(tipoAtributoDTO);
						}
					}
				}
			}
			list.add(variacaoDTO);
		}

		return list;
	}

	private Produto convertToEntity(ProdutoDTO dto) {
		if (dto == null) {
			return null;
		}

		Produto entity = new Produto();
		BeanUtils.copyProperties(dto, entity, "listImagem");

		entity.setTipo(TipoProdutoEnum.findById(dto.getTipo()));
		entity.setStatus(StatusProdutoEnum.findById(dto.getStatus()));

		return entity;
	}

}
