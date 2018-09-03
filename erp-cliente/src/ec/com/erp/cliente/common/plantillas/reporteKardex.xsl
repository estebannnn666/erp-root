<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:a="http://www.w3.org/1999/xhtml" exclude-result-prefixes="a">
	<xsl:output method="html"/>
	<xsl:param name="pCuentaCorriente"/>
	<xsl:decimal-format name="numerico" NaN="-" decimal-separator="," grouping-separator="."/>
	<xsl:decimal-format name="dolar" NaN="-" decimal-separator="." grouping-separator=","/>
	<xsl:template match="/">
		<html>
		    <table>				
				<tr><td style="height:0.5cm;"></td></tr>
				<col style="width:20.0cm;"/> 				
				<tr><td style="height:0.5cm;"></td></tr>
				<tr>
				  <td>
					<table>
						<col style="width:20cm; "/>
						<tr>
							<td align="center" valign="botton" style="text-transform: uppercase;"><pre>KARDEX</pre></td>
						</tr>						
					</table>        
				  </td>
				</tr>
				<tr><td style="height:0.5cm;"></td></tr>
				<col style="width:20.0cm;"/>
				<tr>
					<td>
						<table>
							<col style="width:20.0cm;"/>
							<tr>
								<td align="center" valign="botton">
									<table>
										<col style="width:3.5cm;"/>
										<col style="width:4.5cm;"/>
										<col style="width:4.0cm;"/>
										<col style="width:3.5cm;"/>
										<col style="width:4.5cm;"/>
										<tr>
											<td align="left" valign="botton" style="width:3.5cm;"><pre style="margin-bottom: -0.5em !important; font-size: 12px !important;">C&#xF3;digo de barras:</pre></td>
											<td align="left" valign="botton" style="width:4.5cm;"><pre style="margin-bottom: -0.5em !important; font-size: 12px !important; font-weight: normal !important;"><xsl:value-of select="kardex/codigoBarras"/></pre></td>
											<td align="left" valign="botton" style="width:4.0cm;"></td>
											<td align="left" valign="botton" style="width:3.5cm;"><pre style="margin-bottom: -0.5em !important; font-size: 12px !important;">Nombre del art&#xed;culo:</pre></td>
											<td align="left" valign="botton" style="width:4.5cm;"><pre style="margin-bottom: -0.5em !important; font-size: 12px !important; font-weight: normal !important;"><xsl:value-of select="kardex/nombreArticulo"/></pre></td>									
										</tr>
										<tr>
											<td align="left" valign="botton" style="width:3.5cm;"><pre style="margin-bottom: -0.5em !important; font-size: 12px !important;">Peso del art&#xed;culo:</pre></td>
											<td align="left" valign="botton" style="width:4.5cm;"><pre style="margin-bottom: -0.5em !important; font-size: !important 12px !important; font-weight: normal !important;"><xsl:value-of select="kardex/pesoArticulo"/></pre></td>
											<td align="left" valign="botton" style="width:4.0cm;"></td>
											<td align="left" valign="botton" style="width:3.5cm;"><pre style="margin-bottom: -0.5em !important; font-size: 12px !important;">Precio del art&#xed;culo:</pre></td>
											<td align="left" valign="botton" style="width:4.5cm;"><pre style="margin-bottom: -0.5em !important; font-size: 12px !important; font-weight: normal !important;"><xsl:value-of select="kardex/precioArticulo"/></pre></td>									
										</tr>
										<tr>
											<td align="left" valign="botton" style="width:3.5cm;"><pre style="margin-bottom: -0.5em !important; font-size: 12px !important;">Fecha inicio:</pre></td>
											<td align="left" valign="botton" style="width:4.5cm;"><pre style="margin-bottom: -0.5em !important; font-size: 12px !important; font-weight: normal !important;"><xsl:value-of select="kardex/fechaInicio"/></pre></td>
											<td align="left" valign="botton" style="width:4.0cm;"></td>
											<td align="left" valign="botton" style="width:3.5cm;"><pre style="margin-bottom: -0.5em !important; font-size: 12px !important;">Fecha fin:</pre></td>
											<td align="left" valign="botton" style="width:4.5cm;"><pre style="margin-bottom: -0.5em !important; font-size: 12px !important; font-weight: normal !important;"><xsl:value-of select="kardex/fechaFin"/></pre></td>									
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>				
				<tr><td style="height:0.5cm;"></td></tr>
				<col style="width:20.0cm;"/> 
				<tr>
					<td>
						<table>
							<col style="width:20.0cm;"/>
							<tr>
								<td align="center" valign="botton">
									<table>
										<col style="width:6.5cm;"/>
										<col style="width:4.5cm;"/>
										<col style="width:4.5cm;"/>
										<col style="width:4.5cm;"/>
										<tr>
											<td class="borde" align="center">
												<table border="1">
													<tr>
														<td style="width:6.5cm;"></td>
														<td align="center" style="width:4.5cm;"><pre style="font-size: 12px !important;">Entradas</pre></td>
														<td align="center" style="width:4.5cm;"><pre style="font-size: 12px !important;">Salidas</pre></td>
														<td align="center" style="width:4.5cm;"><pre style="font-size: 12px !important;">Existencias</pre></td>
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
						<table>
							<col style="width:20.0cm;"/>
							<tr>
								<td align="center" valign="botton">
									<table>
										<col style="width:2.0cm;"/>
										<col style="width:4.5cm;"/>
										<col style="width:1.5cm;"/>
										<col style="width:1.5cm;"/>
										<col style="width:1.5cm;"/>
										<col style="width:1.5cm;"/>
										<col style="width:1.5cm;"/>
										<col style="width:1.5cm;"/>
										<col style="width:1.5cm;"/>
										<col style="width:1.5cm;"/>
										<col style="width:1.5cm;"/>
										<tr>
											<td class="borde" align="center">
												<table border="1">
													<tr>
														<td align="center" valign="botton" style="width:2.0cm;">  
															<h3>Fecha</h3>
														</td>
														<td align="center" valign="botton" style="width:4.5cm;">  
															<h3>Detalle</h3>
														</td>
														<td align="center" valign="botton" style="width:1.5cm;">  
															<h3>Cant.</h3>
														</td>
														<td align="center" valign="botton" style="width:1.5cm;">  
															<h3>V. Unidad</h3>
														</td>
														<td align="center" valign="botton" style="width:1.5cm;">  
															<h3>V. Total</h3>
														</td>
														<td align="center" valign="botton" style="width:1.5cm;">  
															<h3>Cant.</h3>
														</td>
														<td align="center" valign="botton" style="width:1.5cm;">  
															<h3>V. Unidad</h3>
														</td>
														<td align="center" valign="botton" style="width:1.5cm;">  
															<h3>V. Total</h3>
														</td>
														<td align="center" valign="botton" style="width:1.5cm;">  
															<h3>Cant</h3>
														</td>
														<td align="center" valign="botton" style="width:1.5cm;">  
															<h3>V. Unidad</h3>
														</td>
														<td align="center" valign="botton" style="width:1.5cm;">  
															<h3>V. Total</h3>
														</td>
													</tr>
													<xsl:for-each select="kardex/listaMovimientos/movimiento">
														<tr>
															<td align="center" style="width:2.0cm;">
																<xsl:value-of select="fechaMovimiento"/>
															</td>
															<td align="left" style="padding-left: 3px; width:4.5cm;">
																<xsl:value-of select="detalleMovimiento"/>
															</td>
															<td align="right" style="padding-right: 3px; width:1.5cm;">
																<xsl:value-of select="cantidadEntrada"/>
															</td>
															<td align="right" style="padding-right: 3px; width:1.5cm;">
																<xsl:value-of select="valorUnidadEntrada"/>
															</td>
															<td align="right" style="padding-right: 3px; width:1.5cm;">
																<xsl:value-of select="valorTotalEntrada"/>
															</td>
															<td align="right" style="padding-right: 3px; width:1.5cm;">
																<xsl:value-of select="cantidadSalida"/>
															</td>
															<td align="right" style="padding-right: 3px; width:1.5cm;">
																<xsl:value-of select="valorUnidadSalida"/>
															</td>
															<td align="right" style="padding-right: 3px; width:1.5cm;">
																<xsl:value-of select="valorTotalSalida"/>
															</td>
															<td align="right" style="padding-right: 3px; width:1.5cm;">
																<xsl:value-of select="cantidadExistencia"/>
															</td>
															<td align="right" style="padding-right: 3px; width:1.5cm;">
																<xsl:value-of select="valorUnidadExistencia"/>
															</td>
															<td align="right" style="padding-right: 3px; width:1.5cm;">
																<xsl:value-of select="valorTotalExistencia"/>
															</td>
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
			</table>			
		</html>
	</xsl:template>
</xsl:stylesheet>