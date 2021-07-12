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
							<td align="right" valign="botton"><b style="font-size:12px;"><xsl:value-of select="reporte/fechaReporte"/></b></td>
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
							<td align="center" valign="botton" style="text-transform: uppercase;"><pre>REPORTE DE CAJA</pre></td>
						</tr>						
					</table>        
				  </td>
				</tr>
				<tr>
				  <td>
					<table>
						<col style="width:1cm; "/>
						<col style="width:18cm; "/>
						<col style="width:1cm; "/>
						<tr>
							<td style="width:1.0cm;"></td>
							<td style="width:18.0cm;">
								<table>
									<col style="width:2cm; "/>
									<col style="width:2cm; "/>
									<col style="width:10cm; "/>
									<col style="width:2cm; "/>
									<col style="width:2cm; "/>
									<tr>
										<td style="width:2.0cm; font-weight: bold;">Total ingresos:</td>
										<td align="right"  style="padding-right: 3px; width:2.0cm; color: #2F96B4"><xsl:value-of select="reporte/tatalIngresos"/></td>
										<td style="width:10.0cm;"></td>
										<td style="width:2.0cm; font-weight: bold;">Fecha inicio:</td>
										<td align="right" style="width:2.0cm;"><xsl:value-of select="reporte/fechaInicio"/></td>
									</tr>
									<tr>
										<td style="width:2.0cm; font-weight: bold;">Total gastos:</td>
										<td align="right"  style="padding-right: 3px; width:2.0cm; color: #E74C3C"><xsl:value-of select="reporte/totalGastos"/></td>
										<td style="width:10.0cm;"></td>
										<td style="width:2.0cm; font-weight: bold;">Fecha fin:</td>
										<td align="right" style="width:2.0cm;"><xsl:value-of select="reporte/fechaFin"/></td>
									</tr>
									<tr>
										<td style="width:2.0cm; font-weight: bold;">Ganancias:</td>
										<td align="right"  style="padding-right: 3px; width:2.0cm; color: #4CAF50"><xsl:value-of select="reporte/ganancias"/></td>
										<td style="width:10.0cm;"></td>
										<td style="width:2.0cm;"></td>
										<td style="width:2.0cm;"></td>
									</tr>									
								</table> 
							</td>
							<td style="width:1.0cm;"></td>
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
							<col style="width:1.0cm; "/>
							<tr>
								<td style="width:1.0cm;"></td>
								<td align="center" valign="botton" style="width:18.0cm;">
									<table>
										<col style="width:2.0cm;"/>
										<col style="width:3.0cm;"/>
										<col style="width:3.0cm;"/>
										<col style="width:7.0cm;"/>
										<col style="width:3.0cm; "/>
										<tr style="background-color: #2F96B4">
											<td colspan="5" align="left" style="padding-left: 5px; border: solid 1px;"> 
												<pre>Ingresos</pre>
											</td>														
										</tr>
										<tr>
											<td class="borde" align="center">
												<table border="1">													
													<tr>
														<td align="center" valign="botton" style="width:2.0cm;">  
															<h3>Nro.</h3>
														</td>
														<td align="center" valign="botton" style="width:3.0cm;">  
															<h3>Transaccion</h3>
														</td>
														<td align="center" valign="botton" style="width:3.0cm;">  
															<h3>Fecha transaccion</h3>
														</td>
														<td align="center" valign="botton" style="width:7.0cm;">  
															<h3>Concepto</h3>
														</td>	
														<td align="center" valign="botton" style="width:3.0cm;">  
															<h3>Valor transaccion</h3>
														</td>													
													</tr>
													<xsl:for-each select="reporte/listaIngresos/ingreso">
														<tr>
															<td align="center" style="width:2.0cm;">
																<xsl:value-of select="numero"/>
															</td>
															<td align="left" style="padding-left: 3px; width:3.0cm;">
																<xsl:value-of select="numeroTransaccion"/>
															</td>
															<td align="left" style="padding-left: 3px; width:3.0cm;">
																<xsl:value-of select="fechaTransaccion"/>
															</td>
															<td align="left" style="padding-left: 3px; width:7.0cm;">
																<xsl:value-of select="concepto"/>
															</td>	
															<td align="right" style="padding-right: 3px; width:3.0cm;">
																<xsl:value-of select="valorTransaccion"/>
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
														<td align="right" style="width:2.0cm;">
														</td>
														<td align="right" style="width:3.0cm;">
														</td>
														<td align="right" style="width:3.0cm;">
														</td>
														<td align="right" valign="botton" style="padding-right: 3px; width:7.0cm;"> 
															<h3>Total ingresos:</h3>														
														</td>													
														<td align="right" valign="botton" style=" padding-right: 3px; width:3.0cm;" border="1">  
															<h3><xsl:value-of select="reporte/tatalIngresos"/></h3>
														</td>														
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
								<td style="width:1.0cm;"></td>
							</tr>
							<tr>
								<td style="width:1.0cm;"></td>
								<td style="height:0.5cm;"></td>
								<td style="width:1.0cm;"></td>
							</tr>
							<tr>
								<td style="width:1.0cm;"></td>
								<td align="center" valign="botton" style="width:18.0cm;">
									<table>
										<col style="width:2.0cm;"/>
										<col style="width:3.0cm;"/>
										<col style="width:3.0cm;"/>
										<col style="width:7.0cm;"/>
										<col style="width:3.0cm; "/>
										<tr style="background-color: #E74C3C">
											<td colspan="5" align="left" style="padding-left: 5px; border: solid 1px;"> 
												<pre>Gastos</pre>
											</td>														
										</tr>
										<tr>
											<td class="borde" align="center">
												<table border="1">													
													<tr>
														<td align="center" valign="botton" style="width:2.0cm;">  
															<h3>Nro.</h3>
														</td>
														<td align="center" valign="botton" style="width:3.0cm;">  
															<h3>Transaccion</h3>
														</td>
														<td align="center" valign="botton" style="width:3.0cm;">  
															<h3>Fecha transaccion</h3>
														</td>
														<td align="center" valign="botton" style="width:7.0cm;">  
															<h3>Concepto</h3>
														</td>	
														<td align="center" valign="botton" style="width:3.0cm;">  
															<h3>Valor transaccion</h3>
														</td>													
													</tr>
													<xsl:for-each select="reporte/listaGastos/gasto">
														<tr>
															<td align="center" style="width:2.0cm;">
																<xsl:value-of select="numero"/>
															</td>
															<td align="left" style="padding-left: 3px; width:3.0cm;">
																<xsl:value-of select="numeroTransaccion"/>
															</td>
															<td align="left" style="padding-left: 3px; width:3.0cm;">
																<xsl:value-of select="fechaTransaccion"/>
															</td>
															<td align="left" style="padding-left: 3px; width:7.0cm;">
																<xsl:value-of select="concepto"/>
															</td>	
															<td align="right" style="padding-right: 3px; width:3.0cm;">
																<xsl:value-of select="valorTransaccion"/>
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
														<td align="right" style="width:2.0cm;">
														</td>
														<td align="right" style="width:3.0cm;">
														</td>
														<td align="right" style="width:3.0cm;">
														</td>
														<td align="right" valign="botton" style="padding-right: 3px; width:7.0cm;"> 
															<h3>Total gastos:</h3>														
														</td>													
														<td align="right" valign="botton" style=" padding-right: 3px; width:3.0cm;" border="1">  
															<h3><xsl:value-of select="reporte/totalGastos"/></h3>
														</td>														
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
								<td style="width:1.0cm;"></td>
							</tr>
						</table>						
				    </td>
				</tr>							
			</table>			
		</html>
	</xsl:template>
</xsl:stylesheet>