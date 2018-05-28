<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:a="http://www.w3.org/1999/xhtml" exclude-result-prefixes="a">
	<xsl:output method="html"/>
	<xsl:param name="pCuentaCorriente"/>
	<xsl:decimal-format name="numerico" NaN="-" decimal-separator="," grouping-separator="."/>
	<xsl:decimal-format name="dolar" NaN="-" decimal-separator="." grouping-separator=","/>
	<xsl:template match="/">
		<html>
		    <table>				
				<tr><td style="height:1.5cm;"></td></tr>
				<col style="width:20.0cm;"/> 
				<tr>
				  <td>
					<table>
						<col style="width:20cm; "/>
						<tr>
							<td align="center" valign="botton" style="text-transform: uppercase;"><pre>AUTORIZACI&#xf3;N DE DESCUENTO</pre></td>
						</tr>						
					</table>        
				  </td>
				</tr>
				<tr><td style="height:1.0cm;"></td></tr>
				<col style="width:18.0cm;"/> 
				<tr>
				  <td>
					<table>
						<col style="width:2.5cm; "/>
						<col style="width:14.0cm; "/>
						<col style="width:1.5cm; "/>
						<tr>
							<td align="left" valign="botton"></td>
							<td style="text-align: justify;" valign="botton"><b style="font-size:12px;">Yo,</b> <b style="font-weight:bold; font-size:12px; text-transform: uppercase;"><xsl:value-of select="compras/nombreEmpleado"/></b> <b style="font-size:12px;">con C.I. #</b> <b style="font-weight:bold; font-size:12px;"><xsl:value-of select="compras/cedulaEmpleado"/></b> <b style="font-size:12px;">colaborador de la Corporaci&#xf3;n Favorita con c&#xf3;digo #</b> <b style="font-weight:bold; font-size:12px;"><xsl:value-of select="compras/codigoEmpleado"/></b> <b style="font-size:12px;">expreso mi deseo de comprar acciones de la empresa.</b></td>
							<td align="left" valign="botton"></td>
						</tr>
					</table>        
				  </td>
				</tr>
				<tr><td style="height:0.5cm;"></td></tr>
				<col style="width:18.0cm;"/> 
				<tr>
				  <td>
					<table>
						<col style="width:2.5cm; "/>
						<col style="width:14.0cm; "/>
						<col style="width:1.5cm; "/>
						<tr>
							<td align="left" valign="botton"></td>
							<td align="left" valign="botton"><b style="font-size:12px;">El valor a adquirir es de</b> <b style="font-weight:bold; font-size:12px;">$ <xsl:value-of select="format-number(compras/totalCompra,'###.##0,00','numerico')"/> d&#xf3;lares </b></td>
							<td align="left" valign="botton"></td>
						</tr>
					</table>  					
				  </td>
				</tr>
				<tr><td style="height:0.5cm;"></td></tr>
				<col style="width:18.0cm;"/> 
				<tr>
				  <td>
					<table>
						<col style="width:2.5cm; "/>
						<col style="width:14.0cm; "/>
						<col style="width:1.5cm; "/>
						<tr>
							<td align="left" valign="botton"></td>
							<td style="text-align: justify;" valign="botton"><b style="font-size:12px;">Autorizo a Corporaci&#xf3;n Favorita para comprar acciones a mi nombre y que dicho valor sea descontado de mis utilidades a recibir en abril.</b></td>
							<td align="left" valign="botton"></td>
						</tr>
					</table> 
				  </td>
				</tr>
				<tr><td style="height:0.5cm;"></td></tr>
				<col style="width:18.0cm;"/> 
				<tr>
				  <td>
					<table>
						<col style="width:2.5cm; "/>
						<col style="width:15.0cm; "/>
						<col style="width:1.5cm; "/>
						<tr>
							<td align="left" valign="botton"></td>
							<td align="center" valign="botton" style="font-weight:bold;"><b style="font-size:12px;">Fecha: Quito, <xsl:value-of select="compras/fechaAutorizacion"/></b></td>
							<td align="left" valign="botton"></td>
						</tr>
					</table>        
				  </td>
				</tr>
				<tr><td style="height:2.0cm;"></td></tr>					
				<tr>
					<td>
						<table>
							<col style="width:20cm; "/>
							<tr>
								<td align="center" valign="botton"><pre>DETALLE DE COMPRA</pre></td>
							</tr>
						</table>        
					</td>
				</tr>	
				<tr><td style="height:1.0cm;"></td></tr>
				<col style="width:18.0cm;"/> 
				<tr>
				  <td>
					<table>
						<col style="width:2.0cm; "/>
						<col style="width:4.0cm; "/>
						<col style="width:5.0cm; "/>
						<col style="width:3.5cm; "/>
						<col style="width:4.0cm; "/>
						<col style="width:1.5cm; "/>
						<tr>
							<td align="left" valign="botton"></td>
							<td align="left" valign="botton" style="font-weight:bold;"><b style="font-size:11px;">C&#xf3;digo Local:</b></td>
							<td align="left" valign="botton"><b style="font-size:11px;"><xsl:value-of select="compras/codigoLocal"/></b></td>
							<td align="left" valign="botton" style="font-weight:bold;"><b style="font-size:11px;">Lugar de Trabajo:</b></td>
							<td align="left" valign="botton"><b style="font-size:11px;"><xsl:value-of select="compras/lugarTrabajo"/></b></td>
							<td align="left" valign="botton"></td>
						</tr>
						<tr>
							<td align="left" valign="botton"></td>
							<td align="left" valign="botton" style="font-weight:bold;"><b style="font-size:11px;">C&#xf3;digo Colaborador:</b></td>
							<td align="left" valign="botton"><b style="font-size:11px;"><xsl:value-of select="compras/codigoEmpleado"/></b></td>
							<td align="left" valign="botton" style="font-weight:bold;"><b style="font-size:11px;">C.I.#:</b></td>
							<td align="left" valign="botton"><b style="font-size:11px;"><xsl:value-of select="compras/cedulaEmpleado"/></b></td>
							<td align="left" valign="botton"></td>
						</tr>						
					</table> 
				  </td>
				</tr>
				<tr>
					<td>
						<table>
							<col style="width:2.0cm; "/>
							<col style="width:4.0cm; "/>
							<col style="width:9.0cm; "/>
							<col style="width:1.5cm; "/>
							<tr>
								<td align="left" valign="botton"></td>
								<td align="left" valign="botton" style="font-weight:bold;"><b style="font-size:11px;">Nombre Colaborador:</b></td>
								<td align="left" valign="botton" style="text-transform: uppercase;"><b style="font-size:11px;"><xsl:value-of select="compras/nombreEmpleado"/></b></td>							
								<td align="left" valign="botton"></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr><td style="height:0.5cm;"></td></tr>				
				<tr>
					<td>
						<table>
							<col style="width:2.0cm;"/>
							<col style="width:18.0cm;"/>
							<col style="width:1.0cm;"/>
							<tr>
								<td align="left" valign="botton"></td>
								<td align="center" valign="botton">
									<table>
										<col style="width:8.0cm;"/>
										<col style="width:4.0cm; "/>
										<col style="width:3.0cm; "/>
										<col style="width:2.0cm; "/>
										<tr>
											<td class="borde" align="center">
												<table border="1">
													<tr>
														<td align="center" valign="botton" style="width:8.0cm;">  
															<h3>Nombres</h3>
														</td>
														<td align="center" valign="botton" style="width:4.0cm;">  
															<h3>C&#xe9;dula</h3>
														</td>
														<td align="center" valign="botton" style="width:3.0cm;">  
															<h3>Parentesco</h3>
														</td>
														<td align="right" valign="botton" style="width:2.0cm;">  
															<h3>Monto</h3>
														</td>
													</tr>
													<xsl:for-each select="compras/detalleCompra/detalle">
														<tr>
															<td align="left" style="width:8.0cm; text-transform: uppercase;">
																<xsl:value-of select="nombreBeneficiario"/>
															</td>
															<td align="left" style="width:4.0cm;">
																<xsl:value-of select="numeroDocumento"/>
															</td>
															<td align="left" style="width:3.0cm;">
																<xsl:value-of select="parentesco"/>
															</td>											
															<td align="right" style="width:2.0cm;">
																<xsl:value-of select="format-number(monto,'###.##0,00','numerico')"/>
															</td>
														</tr>
													</xsl:for-each>
												</table>
											</td>
										 </tr>
										 <tr>
											<td class="borde" align="center">
												<table border="1">
													<tr>									
														<td align="center" valign="botton" style="width:8.0cm;">  
															
														</td>
														<td align="center" valign="botton" style="width:4.0cm;">  
														
														</td>
														<td align="center" valign="botton" style="width:3.0cm;" border="1">  
															<h3>TOTAL</h3>
														</td>
														<td align="right" valign="botton" style="width:2.0cm;">  
															<h3><xsl:value-of select="format-number(compras/totalCompra,'###.##0,00','numerico')"/></h3>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
								<td align="left" valign="botton"></td>
							</tr>
						</table>						
				    </td>
				</tr>
				<tr><td style="height:3.0cm;"></td></tr>	
				<col style="width:18.0cm;"/>                  
				<tr>
				  <td>
					<table>	
						<col style="width:2.5cm; "/>
						<col style="width:14.0cm; "/>
						<col style="width:1.5cm; "/>
						<tr>
							<td align="left" valign="botton"></td>
							<td align="center" valign="botton">_____________________________________</td>
							<td align="left" valign="botton"></td>
						</tr>
						<tr>
							<td align="left" valign="botton"></td>
							<td align="center" valign="botton"><h3>FIRMA DEL COLABORADOR</h3></td>
							<td align="left" valign="botton"></td>
						</tr>
						<tr>
							<td align="left" valign="botton"></td>
							<td align="center" valign="botton" style="text-transform: uppercase;"><h3><xsl:value-of select="compras/repreLegal"/></h3></td>
							<td align="left" valign="botton"></td>
						</tr>
					</table>        
				  </td>
				</tr>				
			</table>			
		</html>
	</xsl:template>
</xsl:stylesheet>