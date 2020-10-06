<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:a="http://www.w3.org/1999/xhtml" exclude-result-prefixes="a">
	<xsl:output method="html"/>
	<xsl:decimal-format name="numerico" NaN="-" decimal-separator="," grouping-separator="."/>
	<xsl:decimal-format name="dolar" NaN="-" decimal-separator="." grouping-separator=","/>
	<xsl:template match="/">
		<html>
		    <table style="font-family; monospace">	
				<col style="width:20.0cm;"/> 				
				<tr><td style="height:0.5cm;"></td></tr>
				<tr>
				  <td>
					<table>
						<col style="width:20cm; "/>
						<tr>
							<td align="center" valign="botton" style="text-transform: uppercase;"><pre style="font-size: 14px !important;">GUIA DE DESPACHO</pre></td>
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
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">NRO GUIA................:</pre></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;"><xsl:value-of select="guiaDespacho/numeroGuiaDespacho"/></pre></td>
								<td align="left"></td>
								<td align="left"></td>
								<td align="left"></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">DESPACHADOR:</pre></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;"><xsl:value-of select="guiaDespacho/despachador"/></pre></td>
							</tr>
							<tr>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">FECHA DESPACHO:</pre></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;"><xsl:value-of select="guiaDespacho/fechaDespacho"/></pre></td>
								<td align="left"></td>
								<td align="left"></td>
								<td align="left"></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">PLACA................:</pre></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;"><xsl:value-of select="guiaDespacho/placaVehiculo"/></pre></td>
							</tr>
							<tr>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">TRANSPORTISTA...:</pre></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;"><xsl:value-of select="guiaDespacho/transportista"/></pre></td>
								<td align="left"></td>
								<td align="left"></td>
								<td align="left"></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">MARCA...............:</pre></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;"><xsl:value-of select="guiaDespacho/marcaVehiculo"/></pre></td>
							</tr>
							<tr>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">CHOFER 1...............:</pre></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;"><xsl:value-of select="guiaDespacho/choferPrincipal"/></pre></td>
								<td align="left"></td>
								<td align="left"></td>
								<td align="left"></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">CHOFER 2..........:</pre></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;"><xsl:value-of select="guiaDespacho/choferSecundario"/></pre></td>
						   </tr>
						</table>
					</td>
				</tr>
				<xsl:if test="count(//listaDestinos) > 0">
					<tr>
						<td style="height:0.5cm;"></td>
					</tr>
					<col style="width:20.0cm;"/>
					<tr>
						<td align="left"><pre style="font-size: 12px !important; padding-left:4px !important;">LISTA DE DESTINOS:</pre></td>
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
															<td align="center" valign="botton" style="width:2.0cm; height:0.4cm; padding-top:4px; background-color: #CCE4FB;"><pre style="font-size: 12px !important;">Nro. Destino</pre></td>
															<td align="center" valign="botton" style="width:7.0cm; height:0.4cm; padding-top:4px; background-color: #CCE4FB;"><pre style="font-size: 12px !important;">Descripci&#xf3;n producto</pre></td>
															<td align="center" valign="botton" style="width:3.0cm; height:0.4cm; padding-top:4px; background-color: #CCE4FB;"><pre style="font-size: 12px !important;">Cantidad</pre></td>
															<td align="center" valign="botton" style="width:8.0cm; height:0.4cm; padding-top:4px; background-color: #CCE4FB;"><pre style="font-size: 12px !important;">Observaci&#xf3;n</pre></td>
														</tr>
														<xsl:for-each select="guiaDespacho/listaDestinos/destino">
															<tr>
																<td align="left" style="width:2.0cm; height:0.4cm; padding-left:4px;"><xsl:value-of select="nroDestino"/></td>
																<td align="left" style="width:7.0cm; height:0.4cm; padding-left:4px;"><xsl:value-of select="descripcionProducto"/></td>
																<td align="right" style="padding-right: 3px; width:3.0cm;"><xsl:value-of select="cantidadPedida"/></td>
																<td align="left" style="width:8.0cm; height:0.4cm; padding-left:4px;"><xsl:value-of select="observacion"/></td>								
															</tr>
														</xsl:for-each>							
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
				<xsl:if test="count(//listaExtras) > 0">
					<tr>
						<td style="height:0.5cm;"></td>
					</tr>
					<col style="width:20.0cm;"/>
					<tr>
						<td align="left"><pre style="font-size: 12px !important; padding-left:4px !important;">ARTICULOS EXTRAS:</pre></td>
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
											<col style="width:7.0cm;"/>
											<col style="width:3.0cm;"/>
											<col style="width:8.0cm;"/>
											<tr>
												<td class="borde" align="center">
													<table border="1">
														<tr>
															<td align="center" valign="botton" style="width:2.0cm; height:0.4cm; padding-top:4px; background-color: #CCE4FB;"><pre style="font-size: 12px !important;">Nro. Destino</pre></td>
															<td align="center" valign="botton" style="width:7.0cm; height:0.4cm; padding-top:4px; background-color: #CCE4FB;"><pre style="font-size: 12px !important;">Descripci&#xf3;n producto</pre></td>
															<td align="center" valign="botton" style="width:3.0cm; height:0.4cm; padding-top:4px; background-color: #CCE4FB;"><pre style="font-size: 12px !important;">Cantidad</pre></td>
															<td align="center" valign="botton" style="width:8.0cm; height:0.4cm; padding-top:4px; background-color: #CCE4FB;"><pre style="font-size: 12px !important;">Observaci&#xf3;n</pre></td>
														</tr>
														<xsl:for-each select="guiaDespacho/listaExtras/extra">
															<tr>
																<td align="left" style="width:2.0cm; height:0.4cm; padding-left:4px;"><xsl:value-of select="nroExtra"/></td>
																<td align="left" style="width:7.0cm; height:0.4cm; padding-left:4px;"><xsl:value-of select="descripcionProducto"/></td>
																<td align="right" style="padding-right: 3px; width:3.0cm;"><xsl:value-of select="cantidad"/></td>
																<td align="left" style="width:8.0cm; height:0.4cm; padding-left:4px;"><xsl:value-of select="observacionExtra"/></td>								
															</tr>
														</xsl:for-each>							
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
					<td>
						<table>
							<col style="width:1.0cm;"/>
							<col style="width:5.0cm;"/>
							<col style="width:1.5cm;"/>
							<col style="width:5.0cm;"/>
							<col style="width:1.5cm;"/>
							<col style="width:5.0cm;"/>
							<col style="width:1.0cm;"/>
							<tr>
								<td align="center" valign="botton" style="width:1.0cm;"></td>
								<td align="center" valign="botton" style="width:5.0cm;"><pre style="font-size: 12px !important; font-weight: normal !important;">-------------------------------------------------------</pre></td>
								<td align="center" valign="botton" style="width:1.5cm;"></td>
								<td align="center" valign="botton" style="width:5.0cm;"><pre style="font-size: 12px !important; font-weight: normal !important;">-------------------------------------------------------</pre></td>
								<td align="center" valign="botton" style="width:1.5cm;"></td>
								<td align="center" valign="botton" style="width:5.0cm;"><pre style="font-size: 12px !important; font-weight: normal !important;">-------------------------------------------------------</pre></td>
								<td align="center" valign="botton" style="width:1.0cm;"></td>
							</tr>
							<tr>
								<td align="center" valign="botton" style="width:1.0cm;"></td>
								<td align="center" valign="botton" style="width:5.0cm;"><pre style="font-size: 12px !important; font-weight: normal !important;">FIRMA DESPACHADOR</pre></td>
								<td align="center" valign="botton" style="width:1.5cm;"></td>
								<td align="center" valign="botton" style="width:5.0cm;"><pre style="font-size: 12px !important; font-weight: normal !important;">FIRMA CHOFER 1</pre></td>
								<td align="center" valign="botton" style="width:1.5cm;"></td>
								<td align="center" valign="botton" style="width:5.0cm;"><pre style="font-size: 12px !important; font-weight: normal !important;">FIRMA CHOFER 2</pre></td>
								<td align="center" valign="botton" style="width:1.0cm;"></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td style="height:0.5cm;"></td>
				</tr>
				<col style="width:20.0cm;"/>
				<tr>
					<td>
						<pre style="font-size: 12px !important; font-weight: normal !important;">
							=========================================================================================================================
						</pre>
					</td>
				</tr>
				<col style="width:20.0cm;"/>
				<tr>
					<td>
						<table>
							<col style="width:20.0cm;"/>						
							<tr>
								<td align="center">
									<pre style="font-size: 12px !important; font-weight: normal !important;">
										OBSERVACIONES SALIDA:________________________________________________________________________________________________________
									</pre>
								</td>								
							</tr>
							<tr>
								<td align="center">
									<pre style="font-size: 12px !important; font-weight: normal !important;">
										_______________________________________________________________________________________________________________________________
									</pre>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<col style="width:20.0cm;"/>
				<tr>
					<td>
						<pre style="font-size: 12px !important; font-weight: normal !important;">
							=========================================================================================================================
						</pre>
					</td>
				</tr>
				<col style="width:20.0cm;"/>
				<tr>
					<td>
						<table>
							<col style="width:20.0cm;"/>						
							<tr>
								<td align="center">
									<pre style="font-size: 12px !important; font-weight: normal !important;">
										OBSERVACIONES LLEGADA:______________________________________________________________________________________________________
									</pre>
								</td>								
							</tr>
							<tr>
								<td align="center">
									<pre style="font-size: 12px !important; font-weight: normal !important;">
										_______________________________________________________________________________________________________________________________
									</pre>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<col style="width:20.0cm;"/>
				<tr>
					<td>
						<pre style="font-size: 12px !important; font-weight: normal !important;">
							=========================================================================================================================
						</pre>
					</td>
				</tr>
				<col style="width:20.0cm;"/>
				<tr>
					<td align="left"><pre style="font-size: 12px !important; padding-left:4px !important;">PRODUCTOS REGRESARON:</pre></td>
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
										<col style="width:1.0cm;"/>
										<col style="width:8.0cm;"/>
										<col style="width:3.0cm;"/>
										<col style="width:8.0cm;"/>
										<tr>
											<td class="borde" align="center">
												<table border="1">
													<tr>
														<td align="center" valign="botton" style="width:1.0cm; height:0.4cm; padding-top:4px; background-color: #CCE4FB;"><pre style="font-size: 12px !important;">NRO.</pre></td>
														<td align="center" valign="botton" style="width:8.0cm; height:0.4cm; padding-top:4px; background-color: #CCE4FB;"><pre style="font-size: 12px !important;">NOMBRE DEL PRODUCTO</pre></td>
														<td align="center" valign="botton" style="width:3.0cm; height:0.4cm; padding-top:4px; background-color: #CCE4FB;"><pre style="font-size: 12px !important;">CANTIDAD</pre></td>
														<td align="center" valign="botton" style="width:8.0cm; height:0.4cm; padding-top:4px; background-color: #CCE4FB;"><pre style="font-size: 12px !important;">OBSERVACI&#xd3;N</pre></td>
													</tr>
													<tr>
														<td align="center" style="width:1.0cm; height:0.4cm; padding-top:4px;">1</td>
														<td align="left" style="width:8.0cm; height:0.4cm; padding-left:4px;"></td>
														<td align="left" style="width:3.0cm; height:0.4cm; padding-left:4px;"></td>
														<td align="left" style="width:8.0cm; height:0.4cm; padding-left:4px;"></td>								
													</tr>
													<tr>
														<td align="center" style="width:1.0cm; height:0.4cm; padding-top:4px;">2</td>
														<td align="left" style="width:8.0cm; height:0.4cm; padding-left:4px;"></td>
														<td align="left" style="width:3.0cm; height:0.4cm; padding-left:4px;"></td>
														<td align="left" style="width:8.0cm; height:0.4cm; padding-left:4px;"></td>								
													</tr>
													<tr>
														<td align="center" style="width:1.0cm; height:0.4cm; padding-top:4px;">3</td>
														<td align="left" style="width:8.0cm; height:0.4cm; padding-left:4px;"></td>
														<td align="left" style="width:3.0cm; height:0.4cm; padding-left:4px;"></td>
														<td align="left" style="width:8.0cm; height:0.4cm; padding-left:4px;"></td>								
													</tr>
													<tr>
														<td align="center" style="width:1.0cm; height:0.4cm; padding-top:4px;">4</td>
														<td align="left" style="width:8.0cm; height:0.4cm; padding-left:4px;"></td>
														<td align="left" style="width:3.0cm; height:0.4cm; padding-left:4px;"></td>
														<td align="left" style="width:8.0cm; height:0.4cm; padding-left:4px;"></td>								
													</tr>
													<tr>
														<td align="center" style="width:1.0cm; height:0.4cm; padding-top:4px;">5</td>
														<td align="left" style="width:8.0cm; height:0.4cm; padding-left:4px;"></td>
														<td align="left" style="width:3.0cm; height:0.4cm; padding-left:4px;"></td>
														<td align="left" style="width:8.0cm; height:0.4cm; padding-left:4px;"></td>								
													</tr>
													<tr>
														<td align="center" style="width:1.0cm; height:0.4cm; padding-top:4px;">6</td>
														<td align="left" style="width:8.0cm; height:0.4cm; padding-left:4px;"></td>
														<td align="left" style="width:3.0cm; height:0.4cm; padding-left:4px;"></td>
														<td align="left" style="width:8.0cm; height:0.4cm; padding-left:4px;"></td>								
													</tr>
													<tr>
														<td align="center" style="width:1.0cm; height:0.4cm; padding-top:4px;">7</td>
														<td align="left" style="width:8.0cm; height:0.4cm; padding-left:4px;"></td>
														<td align="left" style="width:3.0cm; height:0.4cm; padding-left:4px;"></td>
														<td align="left" style="width:8.0cm; height:0.4cm; padding-left:4px;"></td>								
													</tr>
													<tr>
														<td align="center" style="width:1.0cm; height:0.4cm; padding-top:4px;">8</td>
														<td align="left" style="width:8.0cm; height:0.4cm; padding-left:4px;"></td>
														<td align="left" style="width:3.0cm; height:0.4cm; padding-left:4px;"></td>
														<td align="left" style="width:8.0cm; height:0.4cm; padding-left:4px;"></td>								
													</tr>
													<tr>
														<td align="center" style="width:1.0cm; height:0.4cm; padding-top:4px;">9</td>
														<td align="left" style="width:8.0cm; height:0.4cm; padding-left:4px;"></td>
														<td align="left" style="width:3.0cm; height:0.4cm; padding-left:4px;"></td>
														<td align="left" style="width:8.0cm; height:0.4cm; padding-left:4px;"></td>								
													</tr>
													<tr>
														<td align="center" style="width:1.0cm; height:0.4cm; padding-top:4px;">10</td>
														<td align="left" style="width:8.0cm; height:0.4cm; padding-left:4px;"></td>
														<td align="left" style="width:3.0cm; height:0.4cm; padding-left:4px;"></td>
														<td align="left" style="width:8.0cm; height:0.4cm; padding-left:4px;"></td>								
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
				<col style="width:20.0cm;"/>
				<tr>
					<td>
						<pre style="font-size: 12px !important; font-weight: normal !important;">
							=========================================================================================================================
						</pre>
					</td>
				</tr>
				<col style="width:20.0cm;"/>
				<tr>
					<td>
						<table>
							<col style="width:20.0cm;"/>						
							<tr>
								<td align="center">
									<pre style="font-size: 12px !important; font-weight: normal !important;">
										OBSERVACIONES BODEGA:_______________________________________________________________________________________________________
									</pre>
								</td>								
							</tr>
							<tr>
								<td align="center">
									<pre style="font-size: 12px !important; font-weight: normal !important;">
										_______________________________________________________________________________________________________________________________
									</pre>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<col style="width:20.0cm;"/>
				<tr>
					<td>
						<pre style="font-size: 12px !important; font-weight: normal !important;">
							=========================================================================================================================
						</pre>
					</td>
				</tr>
				<col style="width:20.0cm;"/>
				<tr>
					<td align="center"><pre style="font-size: 12px !important; padding-left:4px !important;">* * * ORIGINAL * * * </pre></td>
				</tr>
			</table>			
		</html>
	</xsl:template>
</xsl:stylesheet>