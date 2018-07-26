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
				<tr>
				  <td>
					<table>
						<col style="width:19cm; "/>
						<col style="width:1cm; "/>
						<tr>
							<td align="right" valign="botton"><b style="font-size:12px;"><xsl:value-of select="facturas/fechaReporte"/></b></td>
							<td></td>
						</tr>						
					</table>        
				  </td>
				</tr>
				<tr><td style="height:0.5cm;"></td></tr>
				<tr>
				  <td>
					<table>
						<col style="width:20cm; "/>
						<tr>
							<td align="center" valign="botton" style="text-transform: uppercase;"><pre>LISTA DE FACTURAS</pre></td>
						</tr>						
					</table>        
				  </td>
				</tr>						
				<tr><td style="height:0.5cm;"></td></tr>
				<col style="width:20.0cm;"/> 				
				<tr>
					<td>
						<table>
							<col style="width:1.0cm;"/>
							<col style="width:18.0cm;"/>
							<col style="width:1.0cm;"/>
							<tr>
								<td align="left" valign="botton"></td>
								<td align="center" valign="botton">
									<table>
										<col style="width:1.0cm;"/>
										<col style="width:2.0cm;"/>
										<col style="width:2.0cm;"/>
										<col style="width:6.0cm;"/>
										<col style="width:2.0cm; "/>
										<col style="width:2.0cm; "/>
										<col style="width:3.0cm; "/>
										<tr>
											<td class="borde" align="center">
												<table border="1">
													<tr>
														<td align="center" valign="botton" style="width:1.0cm;">  
															<h3>Nro.</h3>
														</td>
														<td align="center" valign="botton" style="width:2.0cm;">  
															<h3>Nro factura</h3>
														</td>
														<td align="center" valign="botton" style="width:2.0cm;">  
															<h3>Doc. cliente</h3>
														</td>
														<td align="center" valign="botton" style="width:6.0cm;">  
															<h3>Raz&#xf3;n social</h3>
														</td>
														<td align="center" valign="botton" style="width:2.0cm;">  
															<h3>Fecha</h3>
														</td>
														<td align="center" valign="botton" style="width:2.0cm;">  
															<h3>Valor</h3>
														</td>
														<td align="center" valign="botton" style="width:3.0cm;">  
															<h3>Cobrada/Pagada</h3>
														</td>
													</tr>
													<xsl:for-each select="facturas/listaFacturas/factura">
														<tr>
															<td align="center" style="width:1.0cm;">
																<xsl:value-of select="numeroFila"/>
															</td>
															<td align="left" style="padding-left: 3px; width:2.0cm;">
																<xsl:value-of select="numeroDocumento"/>
															</td>
															<td align="left" style="padding-left: 3px; width:2.0cm;">
																<xsl:value-of select="documentoCliente"/>
															</td>
															<td align="left" style="padding-left: 3px; width:6.0cm;">
																<xsl:value-of select="nombreClienteProveedor"/>
															</td>											
															<td align="center" style="width:2.0cm;">
																<xsl:value-of select="fechaEmision"/>
															</td>
															<td align="right" style="padding-right: 3px; width:2.0cm;">
																<xsl:value-of select="valorTotal"/>
															</td>
															<td align="center" style="width:3.0cm;">
																<xsl:value-of select="estado"/>
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
														<td align="right" style="width:2.0cm;">
														</td>
														<td align="right" style="width:2.0cm;">
														</td>
														<td align="center" valign="botton" style="width:6.0cm;">  															
														</td>
														<td align="center" valign="botton" style="width:2.0cm;">  
															<h3>TOTAL</h3>
														</td>
														<td align="right" valign="botton" style=" padding-right: 3px; width:2.0cm;" border="1">  
															<h3><xsl:value-of select="facturas/totalPago"/></h3>
														</td>
														<td align="right" valign="botton" style="width:3.0cm;">  															
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
			</table>			
		</html>
	</xsl:template>
</xsl:stylesheet>