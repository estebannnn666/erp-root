<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:a="http://www.w3.org/1999/xhtml" exclude-result-prefixes="a">
	<xsl:output method="html"/>
	<xsl:decimal-format name="numerico" NaN="-" decimal-separator="," grouping-separator="."/>
	<xsl:decimal-format name="dolar" NaN="-" decimal-separator="." grouping-separator=","/>
	<xsl:template match="/">
		<html>
		    <table style="font-family: sans-serif">			
				<col style="width:22.0cm;"/> 
				<tr>
				  <td>
					<table>
						<col style="width:22cm; "/>
						<tr>
							<td align="center" valign="botton" style="font-size: 18px; background:#003a6c; color: #FFFFFF; text-transform: uppercase;">NOTA DE VENTA</td>
						</tr>						
					</table>        
				  </td>
				</tr>	
				<tr>
				  <td style="border-bottom: 5px solid #003a6c;">
					<table>
						<col style="width:10.0cm;"/>
						<col style="width:12.0cm;"/>
						<tr>
							<td style="width:10.0cm;" rowspan="7">								
								<img height="105" width="250">
									<xsl:attribute name="src">
										<xsl:value-of select="factura/imagen"/>
									</xsl:attribute>
								</img>
							</td>
							<td>
							</td>
						</tr>
						<tr style="height: 10px">
							<td align="left" style="font-size:10px; height: 10px">BENAVIDES VALENZUELA EDUARDO HOMERO</td>
						</tr>
						<tr style="height: 10px">
							<td align="left" style="font-size:10px; height: 10px">RUC: 1001594132001</td>
						</tr>
						<tr style="height: 10px">
							<td align="left" style="font-size:10px; height: 10px">Direcci&#xf3;n: Barrio El Olivo Alto calle principal</td>
						</tr>
						<tr style="height: 10px">
							<td align="left" style="font-size:10px; height: 10px">Telfs: 062602346 Cel: 0987033352 Barrio El Olivo Alto calle principal</td>
						</tr>	
						<tr style="height: 10px">
							<td align="left" style="font-size:10px; height: 10px">IBARRA - ECUADOR</td>
						</tr>
						<tr style="height: 10px">
							<td align="left" style="font-size:10px; height: 10px"></td>
						</tr>						
					</table>        
				  </td>
				</tr>									
				<tr><td style="height:0.2cm;"></td></tr>
				<col style="width:22.0cm;"/> 				
				<tr>
					<td class="borde">
						<table>
							<col style="width:3.0cm;"/>
						    <col style="width:8.0cm;"/>
						    <col style="width:1.0cm;"/>
						    <col style="width:2.0cm;"/>
						    <col style="width:8.0cm;"/>					
							<tr style="height: 10px">
								<td align="left" style="font-size: 10px;">NRO........................:</td>
								<td align="left" style="font-size: 10px;"><xsl:value-of select="factura/numeroFactura"/></td>
								<td align="left"></td>
								<td align="left" style="font-size: 10px;">FECHA........:</td>
								<td align="left" style="font-size: 10px;"><xsl:value-of select="factura/fechaVenta"/></td>
							</tr>
							<tr style="height: 10px">
								<td align="left" style="font-size: 10px;">RUC/DOCUMENTO:</td>
								<td align="left" style="font-size: 10px;"><xsl:value-of select="factura/documentoCliente"/></td>
								<td align="left"></td>
								<td align="left" style="font-size: 10px;">TEL&#xc9;FONO.:</td>
								<td align="left" style="font-size: 10px;"><xsl:value-of select="factura/telefono"/></td>
							</tr>
							<tr>
								<td align="left" style="font-size: 10px;">CLIENTE.................:</td>
								<td align="left" style="font-size: 10px;"><xsl:value-of select="factura/nombreCliente"/></td>
								<td align="left"></td>
								<td align="left" style="font-size: 10px;">DIRECCI&#xd3;N:</td>
								<td align="left" style="font-size: 10px;"><xsl:value-of select="factura/direccion"/></td>								
							</tr>							
						</table>
					</td>
				</tr>				
				<tr>
					<td style="height:0.2cm;"></td>
				</tr>													
				<col style="width:22.0cm;"/> 				
				<tr>
					<td>
						<table>
							<col style="width:22.0cm;"/>
							<tr>
								<td align="center" valign="botton">
									<table>
										<col style="width:3.0cm;"/>
										<col style="width:4.0cm;"/>
										<col style="width:8.0cm;"/>
										<col style="width:3.0cm;"/>
										<col style="width:4.0cm;"/>
										<tr>
											<td class="borde" align="center">
												<table>
													<tr>
														<td align="center" valign="botton" style="border-left: 1px solid; border-top: 1px solid; border-bottom: 1px solid; font-size: 10px; font-weight: bold; width:3.0cm; padding-top:4px;">Cantidad.</td>
														<td align="center" valign="botton" style="border-top: 1px solid; border-bottom: 1px solid; font-size: 10px; font-weight: bold; width:4.0cm; padding-top:4px;">Unidad de medida</td>
														<td align="center" valign="botton" style="border-top: 1px solid; border-bottom: 1px solid; font-size: 10px; font-weight: bold; width:8.0cm; padding-top:4px;">Descripci&#xf3;n</td>
														<td align="center" valign="botton" style="border-top: 1px solid; border-bottom: 1px solid; font-size: 10px; font-weight: bold;width:3.0cm; padding-top:4px;">Precio Unitario</td>
														<td align="center" valign="botton" style="border-right: 1px solid; border-top: 1px solid; border-bottom: 1px solid; font-size: 10px; font-weight: bold; width:4.0cm; padding-top:4px;">Precio Total</td>
													</tr>
													<xsl:for-each select="factura/detallesFactura/detalle">
														<tr>
															<td align="left" style="border-left: 1px solid; font-size: 10px; width:3.0cm; height:0.4cm; padding-left:4px; padding-top:4px;"><xsl:value-of select="cantidad"/></td>
															<td align="left" style="border-left: 1px solid; font-size: 10px; width:4.0cm; height:0.4cm; padding-left:4px; padding-top:4px;"><xsl:value-of select="unidadManejo"/></td>
															<td align="left" style="border-left: 1px solid; font-size: 10px; width:8.0cm; height:0.4cm; padding-left:4px; padding-top:4px;"><xsl:value-of select="descripcion"/></td>
															<td align="right" style="border-left: 1px solid; font-size: 10px; width:3.0cm; height:0.4cm; padding-right: 3px; padding-top:4px;"><xsl:value-of select="valorUnitario"/></td>
															<td align="right" style="border-left: 1px solid; border-right: 1px solid; font-size: 10px; width:4.0cm; height:0.4cm; padding-right: 3px; padding-top:4px;"><xsl:value-of select="subTotal"/></td>								
														</tr>
													</xsl:for-each>
													<tr>
														<td colspan="2" style="border-left: 1px solid; border-top: 1px solid; font-size: 10px; width:7.0cm;"></td>
														<td style="border-top: 1px solid; font-size: 10px; width:8.0cm;"></td>
														<td align="right" style="border-left: 1px solid; border-top: 1px solid; font-size: 10px; width:3.0cm; height:0.4cm; padding-right:4px; padding-top:4px; font-weight: bold;">SUBTOTAL:</td>															
														<td align="right" style="border-top: 1px solid; border-left: 1px solid; border-right: 1px solid; font-size: 10px; width:4.0cm; height:0.4cm; padding-right: 3px; padding-top:4px;"><xsl:value-of select="factura/subtotal"/></td>								
													</tr>
													<tr>
														<td colspan="2" style="border-left: 1px solid; font-size: 10px; width:7.0cm;"></td>
														<td style="font-size: 10px; width:8.0cm;"></td>
														<td align="right" style="border-left: 1px solid; font-size: 10px; width:3.0cm; height:0.4cm; padding-right:4px; padding-top:4px; font-weight: bold;">DESCUENTO:</td>															
														<td align="right" style="border-left: 1px solid; border-right: 1px solid; font-size: 10px; width:4.0cm; height:0.4cm; padding-right: 3px; padding-top:4px;"><xsl:value-of select="factura/descuento"/></td>								
													</tr>
													<tr>
														<td colspan="2" style="border-left: 1px solid; font-size: 10px; width:7.0cm;"></td>
														<td style="font-size: 10px; width:8.0cm;"></td>
														<td align="right" style="border-left: 1px solid; font-size: 10px; width:3.0cm; height:0.4cm; padding-right:4px; padding-top:4px; font-weight: bold;">TARIFA 0%:</td>															
														<td align="right" style="border-left: 1px solid; border-right: 1px solid; font-size: 10px; width:4.0cm; height:0.4cm; padding-right: 3px; padding-top:4px;"><xsl:value-of select="factura/tarifaCero"/></td>								
													</tr>
													<tr>
														<td colspan="2" align="center" style="border-left: 1px solid; font-size: 10px; width:7.0cm;">________________________________</td>
														<td align="center" style="font-size: 10px; width:8.0cm;">________________________________</td>
														<td align="right" style="border-left: 1px solid; font-size: 10px; width:3.0cm; height:0.4cm; padding-right:4px; padding-top:4px; font-weight: bold;">TARIFA 12%:</td>															
														<td align="right" style="border-left: 1px solid; border-right: 1px solid; font-size: 10px; width:4.0cm; height:0.4cm; padding-right: 3px; padding-top:4px;"><xsl:value-of select="factura/tarifaDoce"/></td>								
													</tr>
													<tr>
														<td colspan="2" align="center" style="border-left: 1px solid; font-size: 10px; width:7.0cm;">FIRMA AUTORIZADA</td>
														<td align="center" style="font-size: 10px; width:8.0cm;">RECIBI CONFORME</td>
														<td align="right" style="border-left: 1px solid; font-size: 10px; width:3.0cm; height:0.4cm; padding-right:4px; padding-top:4px; font-weight: bold;">IVA   %:</td>															
														<td align="right" style="border-left: 1px solid; border-right: 1px solid; font-size: 10px; width:4.0cm; height:0.4cm; padding-right: 3px; padding-top:4px;"><xsl:value-of select="factura/iva"/></td>								
													</tr>
													<tr>
														<td colspan="2" style="border-left: 1px solid; border-bottom: 1px solid; font-size: 10px; width:7.0cm;"></td>
														<td style="border-bottom: 1px solid; font-size: 10px; width:8.0cm;"></td>
														<td align="right" style="border-left: 1px solid; border-bottom: 1px solid; font-size: 10px; width:3.0cm; height:0.4cm; padding-right:4px; padding-top:4px; font-weight: bold;">TOTAL:</td>															
														<td align="right" style="border-left: 1px solid; border-right: 1px solid; border-bottom: 1px solid; font-size: 10px; width:4.0cm; height:0.4cm; padding-right: 3px; padding-top:4px;"><xsl:value-of select="factura/total"/></td>								
													</tr>
												</table>
											</td>
										</tr>											
									</table>
								</td>
							</tr>
							<col style="width:22.0cm;"/> 
							<tr>
								<td>
									<table>
										<col style="width:22.0cm;"/>
										<tr>
											<td style="font-size: 10px; width:22.0cm;" align="left">
												Fecha de vencimiento:_____________________________________________________________________________________________________
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<col style="width:22.0cm;"/>
							<tr>
								<td>	
									<table>
										<col style="width:14.0cm;"/>
										<col style="width:1.0cm;"/>
										<col style="width:7.0cm;"/>
										<tr>
											<td style="font-size: 10px; width:14.0cm;">
												Fecha:___________________________________________________________________________
												<br/>
												<br/>
												A:________dias vista se servira Ud. pagar por esta unica LETRA DE CAMBIO a la orden de_______.
												<br/>
												<br/>
												La cantidad de ____________________________________________________________________
												<br/>
												<br/>
												con el interes del________% anual desde su vencimiento.
												<br/>
												<br/>
												Sin protesto. Eximase de presentacion y pago asi como avisos por falta de estos hechos.
											</td>
											<td style="width:1.0cm;"></td>
											<td style="font-size: 10px; width:7.0cm; text-align: justify">
												Aceptada, Valor recibido.  El pago  no podra hacerce por partes ni aun por mis herederos. Me  sujeto a los  jueces de  esta ciudad  y al juicio  ejecutivo o verbal sumario  a  eleccion del demandante.
											</td>
										</tr>	
										<tr>
											<td style="height:1.5cm;"></td>
										</tr>
										<col style="width:14.0cm;"/>
										<col style="width:1.0cm;"/>
										<col style="width:7.0cm;"/>
										<tr>
											<td align="center" style="font-size: 10px; width:14.0cm;">
												____________________________
											</td>
											<td style="width:1.0cm;"></td>
											<td align="center" style="font-size: 10px; width:7.0cm;">																
												____________________________
											</td>
										</tr>
										<col style="width:14.0cm;"/>
										<col style="width:1.0cm;"/>
										<col style="width:7.0cm;"/>
										<tr>
											<td align="center" style="font-size: 10px; width:14.0cm;">
												Atentamente
											</td>
											<td style="width:1.0cm;"></td>
											<td align="center" style="font-size: 10px; width:7.0cm;">																
												Deudor.
											</td>
										</tr>						
									</table>
								</td>
							</tr>
							<tr>
								<td style="height:0.5cm; border-bottom: 1px solid"></td>
							</tr>
						</table>
					</td>
				</tr>								
			</table>			
		</html>
	</xsl:template>
</xsl:stylesheet>