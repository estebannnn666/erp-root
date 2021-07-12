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
							<td align="center" valign="botton" style="text-transform: uppercase;"><pre>REPORTE DE VENTAS</pre></td>
						</tr>						
					</table>        
				  </td>
				</tr>
				<tr><td style="height:0.5cm;"></td></tr>
				<tr>
				  <td>
					<table>
						<col style="width:8cm; "/>
						<col style="width:2cm; "/>
						<col style="width:3cm; "/>
						<col style="width:2cm; "/>
						<col style="width:3cm; "/>
						<tr>
							<td><b style="font-size:10px;">Las fechas seleccionadas para el reporte son: </b></td>
							<td><b style="font-size:10px;">Desde:</b></td>
							<td align="left" valign="botton"><b style="font-size:10px;"><xsl:value-of select="facturas/fechaInicio"/></b></td>
							<td><b style="font-size:10px;">Hasta:</b></td>
							<td align="left" valign="botton"><b style="font-size:10px;"><xsl:value-of select="facturas/fechaFin"/></b></td>
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
										<col style="width:1.0cm;"/>
										<col style="width:4.6cm;"/>
										<col style="width:3.0cm;"/>
										<col style="width:1.5cm;"/>
										<col style="width:1.0cm; "/>
										<col style="width:1.0cm; "/>
										<col style="width:1.2cm; "/>
										<col style="width:1.2cm; "/>
										<col style="width:1.5cm; "/>										
										<col style="width:1.5cm; "/>
										<col style="width:1.0cm; "/>
										<col style="width:1.5cm; "/>
										<tr>
											<td class="borde" align="center">
												<table border="1">
													<tr>
														<td align="center" valign="botton" style="width:1.0cm;">  
															<h4>Nro.</h4>
														</td>
														<td align="center" valign="botton" style="width:4.6cm;">  
															<h4>Nombre vendedor</h4>
														</td>
														<td align="center" valign="botton" style="width:3.0cm;">  
															<h4>Nombre art&#xed;culo</h4>
														</td>
														<td align="center" valign="botton" style="width:1.5cm;">  
															<h4>Uni. Man.</h4>
														</td>
														<td align="center" valign="botton" style="width:1.0cm;">  
															<h4>P. May</h4>
														</td>
														<td align="center" valign="botton" style="width:1.0cm;">  
															<h4>P. Min</h4>
														</td>
														<td align="center" valign="botton" style="width:1.2cm;">  
															<h4>%C.Min</h4>
														</td>
														<td align="center" valign="botton" style="width:1.2cm;">  
															<h4>%C.May</h4>
														</td>
														<td align="center" valign="botton" style="width:1.5cm;">  
															<h4>Tipo Com.</h4>
														</td>
														<td align="center" valign="botton" style="width:1.5cm;">  
															<h4>Cantidad</h4>
														</td>
														<td align="center" valign="botton" style="width:1.0cm;">  
															<h4>Valor</h4>
														</td>
														<td align="center" valign="botton" style="width:1.5cm;">  
															<h4>Comisi&#xf3;n</h4>
														</td>
													</tr>
													<xsl:for-each select="facturas/listaFacturas/factura">
														<tr>
															<td align="center" style="width:1.0cm;">
																<xsl:value-of select="numeroFila"/>
															</td>
															<td align="left" style="padding-left: 3px; width:4.6cm;">
																<b style="font-size:7px;"><xsl:value-of select="nombreVendodor"/></b>
															</td>
															<td align="left" style="padding-left: 3px; width:3.0cm;">
																<b style="font-size:7px;"><xsl:value-of select="nombreArticulo"/></b>
															</td>
															<td align="left" style="padding-left: 3px; width:1.5cm;">
																<b style="font-size:7px;"><xsl:value-of select="unidadManejo"/></b>
															</td>											
															<td align="center" style="padding-right: 3px; width:1.0cm;">
																<b style="font-size:7px;"><xsl:value-of select="precioMayorista"/></b>
															</td>
															<td align="right" style="padding-right: 3px; width:1.0cm;">
																<b style="font-size:7px;"><xsl:value-of select="precioMinorista"/></b>
															</td>
															<td align="right" style="padding-right: 3px; width:1.2cm;">
																<b style="font-size:7px;"><xsl:value-of select="porcentajeComision"/></b>
															</td>
															<td align="right" style="padding-right: 3px; width:1.2cm;">
																<b style="font-size:7px;"><xsl:value-of select="porcentajeComisionMayor"/></b>
															</td>
															<td align="center" style="padding-right: 3px; width:1.5cm;">
																<b style="font-size:7px;"><xsl:value-of select="tipoCliente"/></b>
															</td>
															<td align="left" style="padding-left: 3px; width:1.5cm;">
																<b style="font-size:7px;"><xsl:value-of select="cantidadVendida"/></b>
															</td>
															<td align="right" style="padding-right: 3px; width:1.0cm;">
																<b style="font-size:7px;"><xsl:value-of select="valorVendido"/></b>
															</td>
															<td align="right" style="padding-right: 3px; width:1.5cm;">
																<b style="font-size:7px;"><xsl:value-of select="comision"/></b>
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
														<td align="right" style="width:1.0cm;">
														</td>
														<td align="right" style="width:5.0cm;">
														</td>
														<td align="right" style="width:3.0cm;">
														</td>
														<td align="center" valign="botton" style="width:7cm;">  
															<h4>TOTAL</h4>
														</td>
														<td align="right" valign="botton" style=" padding-right: 3px; width:1.5cm;" border="1">  
															<h4><xsl:value-of select="facturas/totalVenta"/></h4>
														</td>
														<td align="right" valign="botton" style=" padding-right: 3px; width:1.0cm;" border="1">  
															<h4><xsl:value-of select="facturas/totalVendido"/></h4>
														</td>
														<td align="right" valign="botton" style=" padding-right: 3px; width:1.5cm;" border="1">  
															<h4><xsl:value-of select="facturas/totalComision"/></h4>
														</td>
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
			</table>			
		</html>
	</xsl:template>
</xsl:stylesheet>