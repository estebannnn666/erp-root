<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:a="http://www.w3.org/1999/xhtml" exclude-result-prefixes="a">
	<xsl:output method="html"/>
	<xsl:decimal-format name="numerico" NaN="-" decimal-separator="," grouping-separator="."/>
	<xsl:decimal-format name="dolar" NaN="-" decimal-separator="." grouping-separator=","/>
	<xsl:template match="/">
		<html>
		    <table style="font-family; monospace">			
				<tr>
					<td style="width:20.0cm; height:0.4cm; padding-top:4px; background-color: #CCE4FB;">
					</td>
				</tr>
				<col style="width:20.0cm;"/> 				
				<tr><td style="height:0.5cm;"></td></tr>
				<tr>
				  <td>
					<table>
						<col style="width:20cm; "/>
						<tr>
							<td align="center" valign="botton" style="text-transform: uppercase;"><pre style="font-size: 14px !important;">FACTURA DE VENTA</pre></td>
						</tr>						
					</table>        
				  </td>
				</tr>						
				<tr><td style="height:0.5cm;"></td></tr>
				<col style="width:20.0cm;"/> 				
				<tr>
					<td class="borde">
						<table>
							<col style="width:3.0cm;"/>
						    <col style="width:5.0cm;"/>
						    <col style="width:1.0cm;"/>
						    <col style="width:1.0cm;"/>
						    <col style="width:1.0cm;"/>
						    <col style="width:3.0cm;"/>
						    <col style="width:6.0cm;"/>					
							<tr>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">NRO FACTURA......:</pre></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;"><xsl:value-of select="factura/numeroFactura"/></pre></td>
								<td align="left"></td>
								<td align="left"></td>
								<td align="left"></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">FECHA...............:</pre></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;"><xsl:value-of select="factura/fechaVenta"/></pre></td>
							</tr>
							<tr>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">RUC/DOCUMENTO:</pre></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;"><xsl:value-of select="factura/documentoCliente"/></pre></td>
								<td align="left"></td>
								<td align="left"></td>
								<td align="left"></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">RAZ&#xd3;N SOCIAL:</pre></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;"><xsl:value-of select="factura/nombreCliente"/></pre></td>
							</tr>
							<tr>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">DIRECCI&#xd3;N............:</pre></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;"><xsl:value-of select="factura/direccion"/></pre></td>
								<td align="left"></td>
								<td align="left"></td>
								<td align="left"></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">TEL&#xc9;FONO........:</pre></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;"><xsl:value-of select="factura/telefono"/></pre></td>
							</tr>							
						</table>
					</td>
				</tr>
				<xsl:if test="count(//detallesFactura) > 0">
					<tr>
						<td style="height:0.5cm;"></td>
					</tr>
					<col style="width:20.0cm;"/>
					<tr>
						<td align="left"><pre style="font-size: 12px !important; padding-left:4px !important;">DETALLE DE PRODUCTOS</pre></td>
					</tr>
					<tr>
						<td style="height:0.2cm;"></td>
					</tr>													
					<col style="width:20.0cm;"/> 				
					<tr>
						<td>
							<table>
								<col style="width:20.0cm;"/>
								<tr>
									<td align="center" valign="botton">
										<table>
											<col style="width:2.0cm;"/>
											<col style="width:4.0cm;"/>
											<col style="width:6.0cm;"/>
											<col style="width:3.0cm;"/>
											<col style="width:5.0cm;"/>
											<tr>
												<td class="borde" align="center">
													<table border="1">
														<tr>
															<td align="center" valign="botton" style="width:2.0cm; height:0.4cm; padding-top:4px; background-color: #CCE4FB;"><pre style="font-size: 12px !important;">Nro.</pre></td>
															<td align="center" valign="botton" style="width:4.0cm; height:0.4cm; padding-top:4px; background-color: #CCE4FB;"><pre style="font-size: 12px !important;">Cantidad</pre></td>
															<td align="center" valign="botton" style="width:6.0cm; height:0.4cm; padding-top:4px; background-color: #CCE4FB;"><pre style="font-size: 12px !important;">Descripci&#xf3;n</pre></td>
															<td align="center" valign="botton" style="width:4.0cm; height:0.4cm; padding-top:4px; background-color: #CCE4FB;"><pre style="font-size: 12px !important;">Precio Unitario</pre></td>
															<td align="center" valign="botton" style="width:4.0cm; height:0.4cm; padding-top:4px; background-color: #CCE4FB;"><pre style="font-size: 12px !important;">Precio Total</pre></td>
														</tr>
														<xsl:for-each select="factura/detallesFactura/detalle">
															<tr>
																<td align="left" style="width:2.0cm; height:0.4cm; padding-left:4px; padding-top:4px;"><xsl:value-of select="nroDetalle"/></td>
																<td align="left" style="width:4.0cm; height:0.4cm; padding-left:4px; padding-top:4px;"><xsl:value-of select="cantidad"/></td>
																<td align="left" style="width:6.0cm; height:0.4cm; padding-left:4px; padding-top:4px;"><xsl:value-of select="descripcion"/></td>
																<td align="right" style="width:4.0cm; height:0.4cm; padding-right: 3px; padding-top:4px;"><xsl:value-of select="valorUnitario"/></td>
																<td align="right" style="width:4.0cm; height:0.4cm; padding-right: 3px; padding-top:4px;"><xsl:value-of select="subTotal"/></td>								
															</tr>
														</xsl:for-each>
														<tr>
															<td colspan="4" align="right" style="width:15.0cm; height:0.4cm; padding-right:4px; padding-top:4px;"><pre style="font-size: 12px !important;">SUBTOTAL:</pre></td>															
															<td align="right" style="width:5.0cm; height:0.4cm; padding-right: 3px; padding-top:4px;"><xsl:value-of select="factura/subtotal"/></td>								
														</tr>
														<tr>
															<td colspan="4" align="right" style="width:15.0cm; height:0.4cm; padding-right:4px; padding-top:4px;"><pre style="font-size: 12px !important;">IVA   %:</pre></td>															
															<td align="right" style="width:5.0cm; height:0.4cm; padding-right: 3px; padding-top:4px;"><xsl:value-of select="factura/iva"/></td>								
														</tr>
														<tr>
															<td colspan="4" align="right" style="width:15.0cm; height:0.4cm; padding-right:4px; padding-top:4px;"><pre style="font-size: 12px !important;">TOTAL:</pre></td>															
															<td align="right" style="width:5.0cm; height:0.4cm; padding-right: 3px; padding-top:4px;"><xsl:value-of select="factura/total"/></td>								
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</xsl:if>				
				<tr>
					<td style="height:2.0cm;"></td>
				</tr>				
				<col style="width:20.0cm;"/>
				<tr>
					<td align="center"><pre style="font-size: 12px !important; padding-left:4px !important;">* * * GRACIAS POR SU COMPRA * * * </pre></td>
				</tr>
			</table>			
		</html>
	</xsl:template>
</xsl:stylesheet>