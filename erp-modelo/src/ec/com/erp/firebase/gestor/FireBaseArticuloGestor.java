package ec.com.erp.firebase.gestor;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

import org.apache.commons.collections.CollectionUtils;

import ec.com.erp.articulo.gestor.IArticuloGestor;
import ec.com.erp.cliente.common.constantes.ERPConstantes;
import ec.com.erp.cliente.common.exception.ERPException;
import ec.com.erp.cliente.mdl.dto.ArticuloDTO;
import ec.com.erp.cliente.mdl.dto.ArticuloImpuestoDTO;
import ec.com.erp.cliente.mdl.dto.ArticuloUnidadManejoDTO;
import ec.com.erp.cliente.mdl.dto.InventarioDTO;
import ec.com.erp.firebase.commons.provider.CommonProvider;
import ec.com.erp.firebase.commons.provider.ItemProvider;
import ec.com.erp.firebase.model.DataItem;
import ec.com.erp.firebase.model.DriveUnit;
import ec.com.erp.firebase.model.Item;
import ec.com.erp.firebase.model.Sequence;
import ec.com.erp.firebase.model.Taxe;
import ec.com.erp.inventario.gestor.IInventarioGestor;

public class FireBaseArticuloGestor implements IFireBaseArticuloGestor {

	private IArticuloGestor articuloGestor;
	private IInventarioGestor inventarioGestor;
	
	public IArticuloGestor getArticuloGestor() {
		return articuloGestor;
	}

	public void setArticuloGestor(IArticuloGestor articuloGestor) {
		this.articuloGestor = articuloGestor;
	}
	
	public IInventarioGestor getInventarioGestor() {
		return inventarioGestor;
	}

	public void setInventarioGestor(IInventarioGestor inventarioGestor) {
		this.inventarioGestor = inventarioGestor;
	}

	/**
	 * M\u00e9todo para descargar los ARTICULOS de fire base
	 * @return 
	 * @throws ERPException
	 */
	public void descargarArticulosFireBase() throws ERPException{
		try {
			Collection<Item> articulosFireBase = ItemProvider.obtainItemFirebase();
			Collection<ArticuloDTO> articuloDTOCols = this.articuloGestor.obtenerListaArticulos(ERPConstantes.CODIGO_COMPANIA, null, null);
			if(CollectionUtils.isNotEmpty(articulosFireBase)) {
				articulosFireBase.stream().forEach(articuloFireBase ->{
					ArticuloDTO articuloDTOLocal = null;
					if(CollectionUtils.isNotEmpty(articuloDTOCols)) {
						articuloDTOLocal = articuloDTOCols.stream()
								.filter(articuloDTO -> articuloDTO.getCodigoBarras().equals(articuloFireBase.getDataItem().getBarCode()))
								.findFirst().orElse(null);
					}
					
					if(articuloDTOLocal == null) {
						ArticuloDTO articuloDTO = new ArticuloDTO();
						articuloDTO.getId().setCodigoCompania(ERPConstantes.CODIGO_COMPANIA);
						articuloDTO.setUsuarioRegistro(ERPConstantes.USUARIO_GENERICO);
						articuloDTO.setCantidadStock(Integer.parseInt(articuloFireBase.getDataItem().getStock()));
						articuloDTO.setCodigoBarras(articuloFireBase.getDataItem().getBarCode());
						articuloDTO.setNombreArticulo(articuloFireBase.getDataItem().getNameItem());
						articuloDTO.setCosto(BigDecimal.valueOf(Double.parseDouble(articuloFireBase.getDataItem().getCost())));
						articuloDTO.setPorcentajeComision(BigDecimal.valueOf(Double.parseDouble(articuloFireBase.getDataItem().getCommissionPercentage())));
						articuloDTO.setPorcentajeComisionMayor(BigDecimal.valueOf(Double.parseDouble(articuloFireBase.getDataItem().getWholesaleCommissionPercentage())));
						articuloDTO.setPrecio(BigDecimal.valueOf(Double.parseDouble(articuloFireBase.getDataItem().getPriceWholesaler())));
						articuloDTO.setPrecioMinorista(BigDecimal.valueOf(Double.parseDouble(articuloFireBase.getDataItem().getPriceRetail())));
						articuloDTO.setPeso(BigDecimal.ZERO);
						Collection<ArticuloImpuestoDTO> articuloImpuestoDTOCols = new ArrayList<>();
						Collection<ArticuloUnidadManejoDTO> articuloUnidadManejoDTOCols = new ArrayList<>();
						// Agregar unidades de manejo de articulo
						if(CollectionUtils.isNotEmpty(articuloFireBase.getDriveUnit())) {
							for(DriveUnit driveUnit: articuloFireBase.getDriveUnit()) {
								ArticuloUnidadManejoDTO articuloUnidadManejoDTO = new ArticuloUnidadManejoDTO();
								articuloUnidadManejoDTO.getId().setCodigoCompania(ERPConstantes.CODIGO_COMPANIA);
								articuloUnidadManejoDTO.setUsuarioRegistro(ERPConstantes.USUARIO_GENERICO);
								articuloUnidadManejoDTO.setCodigoTipoUnidadManejo(Integer.parseInt(driveUnit.getUnitDriveTypeCode()));
								articuloUnidadManejoDTO.setCodigoValorUnidadManejo(driveUnit.getUnitDriveValueCode());
								if(driveUnit.getIsDefault().equals("true")) {
									articuloUnidadManejoDTO.setEsPorDefecto(Boolean.TRUE);
								}else {
									articuloUnidadManejoDTO.setEsPorDefecto(Boolean.FALSE);
								}
								articuloUnidadManejoDTO.setValorUnidadManejo(Integer.parseInt(driveUnit.getUnitDriveValue()));
								articuloUnidadManejoDTOCols.add(articuloUnidadManejoDTO);
							}
						}	
						// Agregar impuestos del articulo
						if(CollectionUtils.isNotEmpty(articuloFireBase.getTaxes())) {
							ArticuloImpuestoDTO articuloImpuestoDTO = new ArticuloImpuestoDTO();
							articuloImpuestoDTO.getId().setCodigoCompania(ERPConstantes.CODIGO_COMPANIA);
							articuloImpuestoDTO.getId().setCodigoImpuesto(ERPConstantes.CODIGO_IMPUESTO_IVA);
							articuloImpuestoDTO.setUsuarioRegistro(ERPConstantes.USUARIO_GENERICO);
							articuloImpuestoDTOCols.add(articuloImpuestoDTO);
						}
						this.articuloGestor.guardarActualizarArticulo(articuloDTO, articuloImpuestoDTOCols, articuloUnidadManejoDTOCols);
					}
				});
			}
		} catch (InterruptedException e) {
			throw new ERPException("Error, {0}",e.getMessage()) ;
		} catch (ExecutionException e) {
			throw new ERPException("Error, {0}",e.getMessage()) ;
		}
	}	
	
	
	/**
	 * M\u00e9todo para guardar los articulos locales a fire base
	 * @return 
	 * @throws ERPException
	 */
	public void guardarArticulosFireBase() throws ERPException{
		try {
			Sequence secuencial = CommonProvider.obtenerSecuencial();
			Integer[] secuencialItem = new Integer[]{Integer.parseInt(secuencial.getItem())};
			Collection<Item> itemsFireBase = ItemProvider.obtainItemFirebase();
			Collection<ArticuloDTO> articuloDTOCols = this.articuloGestor.obtenerListaArticulos(ERPConstantes.CODIGO_COMPANIA, null, null);
			if(CollectionUtils.isNotEmpty(articuloDTOCols)) {
				Collection<Item> itemsUpload = new ArrayList<>();
				articuloDTOCols.stream().forEach(articuloLocal ->{
					Item item = null;
					if(CollectionUtils.isNotEmpty(itemsFireBase)) {
					    item = itemsFireBase.stream().filter(itemFireBase -> articuloLocal.getCodigoBarras().equals(itemFireBase.getDataItem().getBarCode()))
							.findFirst().orElse(null);
					}
					
					Item itemSave = new Item();
					itemSave.setDataItem(new DataItem());
					itemSave.getDataItem().setBarCode(articuloLocal.getCodigoBarras());
					itemSave.getDataItem().setCommissionPercentage(articuloLocal.getPorcentajeComision().toString());
					itemSave.getDataItem().setWholesaleCommissionPercentage(articuloLocal.getPorcentajeComisionMayor().toString());
					itemSave.getDataItem().setCost(articuloLocal.getCosto().toString());
					itemSave.getDataItem().setNameItem(articuloLocal.getNombreArticulo());
					itemSave.getDataItem().setPriceRetail(articuloLocal.getPrecioMinorista().toString());
					itemSave.getDataItem().setPriceWholesaler(articuloLocal.getPrecio().toString());
					Integer stockItem = 0;
					if(CollectionUtils.isNotEmpty(articuloLocal.getArticuloUnidadManejoDTOCols())) {
						itemSave.setDriveUnit(new ArrayList<>());
						int cont = 0;
						for(ArticuloUnidadManejoDTO articuloUnidadManejoDTO : articuloLocal.getArticuloUnidadManejoDTOCols()) {
							InventarioDTO invetario = this.inventarioGestor.obtenerUltimoInventarioByArticulo(ERPConstantes.CODIGO_COMPANIA, articuloLocal.getCodigoBarras(), articuloUnidadManejoDTO.getId().getCodigoArticuloUnidadManejo());
							if(invetario != null) {
								stockItem = stockItem + (invetario.getCantidadExistencia() * articuloUnidadManejoDTO.getValorUnidadManejo());
							}
							// Agregar unidades de manejo
							DriveUnit driveUnit = new DriveUnit();
							driveUnit.setId(""+cont);
							if(articuloUnidadManejoDTO.getEsPorDefecto()) {
								driveUnit.setIsDefault("true");
							}else {
								driveUnit.setIsDefault("false");
							}
							driveUnit.setUnitDriveName(articuloUnidadManejoDTO.getTipoUnidadManejoCatalogoValorDTO().getNombreCatalogoValor());
							driveUnit.setUnitDriveTypeCode(articuloUnidadManejoDTO.getCodigoTipoUnidadManejo().toString());
							driveUnit.setUnitDriveValueCode(articuloUnidadManejoDTO.getCodigoValorUnidadManejo());
							driveUnit.setUnitDriveValue(articuloUnidadManejoDTO.getValorUnidadManejo().toString());
							itemSave.getDriveUnit().add(driveUnit);
							cont++;
						}
					}
					itemSave.getDataItem().setStock(stockItem.toString());
					
					if(CollectionUtils.isNotEmpty(articuloLocal.getArticuloImpuestoDTOCols())) {
						itemSave.setTaxes(new ArrayList<>());
						int cont = 0;
						for(ArticuloImpuestoDTO articuloImpuestoDTO : articuloLocal.getArticuloImpuestoDTOCols()) {
							Taxe taxe = new Taxe();
							taxe.setId(""+cont);
							taxe.setDescriptionTax(articuloImpuestoDTO.getImpuestoDTO().getDescripcion());
							taxe.setNameTax(articuloImpuestoDTO.getImpuestoDTO().getNombreImpuesto());
							taxe.setValueTax(articuloImpuestoDTO.getImpuestoDTO().getValorImpuesto().toString());
							itemSave.getTaxes().add(taxe);
							cont++;
						}
					}
					if(item == null) {
						itemSave.getDataItem().setId(secuencialItem[0]);
						secuencialItem[0]++;
					}else {
						itemSave.getDataItem().setId(item.getDataItem().getId());
					}
					itemsUpload.add(itemSave);
				});
				// Save client in fire base
				ItemProvider.createUpdateItem(itemsUpload);
				// Update sequense
				CommonProvider.updateSequence("item", String.valueOf(secuencialItem[0]));
			}
		} catch (IOException | InterruptedException | ExecutionException e1) {
			throw new ERPException("Error, {0}",e1.getMessage()) ;
		}
	}	
}
