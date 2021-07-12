<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:a="http://www.w3.org/1999/xhtml" exclude-result-prefixes="a">
	<xsl:output method="html"/>
	<xsl:decimal-format name="numerico" NaN="-" decimal-separator="," grouping-separator="."/>
	<xsl:decimal-format name="dolar" NaN="-" decimal-separator="." grouping-separator=","/>
	<xsl:template match="/">
		<html>
		    <table style="font-family: sans-serif">			
				<col style="width:15.0cm;"/> 
				<tr>
				  <td>
					<table>
						<col style="width:15.0cm; "/>
						<tr>
							<td align="center" valign="botton" style="font-size: 14px; background:#003a6c; color: #FFFFFF; text-transform: uppercase;">NOTA DE VENTA</td>
						</tr>						
					</table>        
				  </td>
				</tr>	
				<col style="width:15.0cm;"/> 
				<tr>
				  <td>
					<table>
						<col style="width:6.0cm;"/>
						<col style="width:8.0cm;"/>
						<tr>
							<td style="width:6.0cm;" rowspan="7">								
								<img height="75" width="200">
									<xsl:attribute name="src">
										<xsl:value-of select="factura/imagen"/>
									</xsl:attribute>
								</img>
							</td>
						</tr>
						<tr style="height: 10px">
							<td align="left" style="font-size:8px; height: 10px">BENAVIDES VALENZUELA EDUARDO HOMERO</td>
						</tr>
						<tr style="height: 10px">
							<td align="left" style="font-size:8px; height: 10px">RUC: 1001594132001</td>
						</tr>
						<tr style="height: 10px">
							<td align="left" style="font-size:8px; height: 10px">Direcci&#xf3;n: Barrio El Olivo Alto calle principal</td>
						</tr>
						<tr style="height: 10px">
							<td align="left" style="font-size:8px; height: 10px">Telfs: 062602346 Cel: 0987033352 Barrio El Olivo Alto calle principal</td>
						</tr>	
						<tr style="height: 10px">
							<td align="left" style="font-size:8px; height: 10px">IBARRA - ECUADOR</td>
						</tr>
						<tr style="height: 10px">
							<td align="left" style="font-size:8px; height: 10px"></td>
						</tr>						
					</table>        
				  </td>
				</tr>	
				<tr>
				  <td>
					<table>
						<col style="width:15.0cm; "/>
						<tr>
							<td align="center" valign="botton" style="height: 5px; background:#003a6c; color: #FFFFFF; text-transform: uppercase;"></td>
						</tr>						
					</table>        
				  </td>
				</tr>					
				<tr><td style="height:0.2cm;"></td></tr>
				<col style="width:15.0cm;"/> 				
				<tr>
					<td class="borde">
						<table>
							<col style="width:2.2cm;"/>
						    <col style="width:5.7cm;"/>
						    <col style="width:0.1cm;"/>
						    <col style="width:1.5cm;"/>
						    <col style="width:5.5cm;"/>					
							<tr style="height: 10px">
								<td align="left" style="font-size: 8px;">NRO........................:</td>
								<td align="left" style="font-size: 8px;"><xsl:value-of select="factura/numeroFactura"/></td>
								<td align="left"></td>
								<td align="left" style="font-size: 8px;">FECHA........:</td>
								<td align="left" style="font-size: 8px;"><xsl:value-of select="factura/fechaVenta"/></td>
							</tr>
							<tr style="height: 10px">
								<td align="left" style="font-size: 8px;">RUC/DOCUMENTO:</td>
								<td align="left" style="font-size: 8px;"><xsl:value-of select="factura/documentoCliente"/></td>
								<td align="left"></td>
								<td align="left" style="font-size: 8px;">TEL&#xc9;FONO.:</td>
								<td align="left" style="font-size: 8px;"><xsl:value-of select="factura/telefono"/></td>
							</tr>
							<tr style="height: 10px">
								<td align="left" style="font-size: 8px;">CLIENTE.................:</td>
								<td align="left" style="font-size: 8px;"><xsl:value-of select="factura/nombreCliente"/></td>
								<td align="left"></td>
								<td align="left" style="font-size: 8px;">DIRECCI&#xd3;N:</td>
								<td align="left" style="font-size: 8px;"><xsl:value-of select="factura/direccion"/></td>								
							</tr>							
						</table>
					</td>
				</tr>				
				<tr>
					<td style="height:0.2cm;"></td>
				</tr>													
				<col style="width:15.0cm;"/> 				
				<tr>					
					<td>
						<table>
							<col style="width:1.5cm;"/>
							<col style="width:2.0cm;"/>
							<col style="width:7.0cm;"/>
							<col style="width:1.5cm;"/>
							<col style="width:2.0cm;"/>
							<tr>
								<td align="center" valign="botton" style="border-left: 1px solid; border-top: 1px solid; border-bottom: 1px solid; font-size: 8px; font-weight: bold; width:1.5cm; padding-top:4px;">Cantidad</td>
								<td align="center" valign="botton" style="border-top: 1px solid; border-bottom: 1px solid; font-size: 8px; font-weight: bold; width:2.0cm; padding-top:4px;">U. medida</td>
								<td align="center" valign="botton" style="border-top: 1px solid; border-bottom: 1px solid; font-size: 8px; font-weight: bold; width:7.0cm; padding-top:4px;">Descripci&#xf3;n</td>
								<td align="center" valign="botton" style="border-top: 1px solid; border-bottom: 1px solid; font-size: 8px; font-weight: bold;width:1.5cm; padding-top:4px;">Precio Unitario</td>
								<td align="center" valign="botton" style="border-right: 1px solid; border-top: 1px solid; border-bottom: 1px solid; font-size: 8px; font-weight: bold; width:2.0cm; padding-top:4px;">Precio Total</td>
							</tr>
							<xsl:for-each select="factura/detallesFactura/detalle">
								<tr>
									<td align="left" style="border-left: 1px solid; font-size: 7px; width:1.5cm; height:8px; padding-left:4px;"><xsl:value-of select="cantidad"/></td>
									<td align="left" style="border-left: 1px solid; font-size: 7px; width:2.0cm; height:8px; padding-left:4px;"><xsl:value-of select="unidadManejo"/></td>
									<td align="left" style="border-left: 1px solid; font-size: 7px; width:7.0cm; height:8px; padding-left:4px;"><xsl:value-of select="descripcion"/></td>
									<td align="right" style="border-left: 1px solid; font-size: 7px; width:1.5cm; height:8px; padding-right: 3px;"><xsl:value-of select="valorUnitario"/></td>
									<td align="right" style="border-left: 1px solid; border-right: 1px solid; font-size: 7px; width:2.0cm; height:8px; padding-right: 3px;"><xsl:value-of select="subTotal"/></td>								
								</tr>
							</xsl:for-each>
										
							<tr>
								<td colspan="2" style="border-left: 1px solid; border-top: 1px solid; font-size: 8px; width:3.5cm;"></td>
								<td style="border-top: 1px solid; font-size: 8px; width:7.0cm;"></td>
								<td align="right" style="border-left: 1px solid; border-top: 1px solid; font-size: 8px; width:1.5cm; height:0.4cm; padding-right:4px; padding-top:4px; font-weight: bold;">SUBTOTAL:</td>															
								<td align="right" style="border-top: 1px solid; border-left: 1px solid; border-right: 1px solid; font-size: 8px; width:2.0cm; height:0.4cm; padding-right: 3px; padding-top:4px;"><xsl:value-of select="factura/subtotal"/></td>								
							</tr>			
							<tr>
								<td colspan="2" style="border-left: 1px solid; font-size: 8px; width:3.5cm;"></td>
								<td style="font-size: 8px; width:7.0cm;"></td>
								<td align="right" style="border-left: 1px solid; font-size: 8px; width:1.5cm; height:0.4cm; padding-right:4px; padding-top:4px; font-weight: bold;">DESCUENTO:</td>															
								<td align="right" style="border-left: 1px solid; border-right: 1px solid; font-size: 8px; width:2.0cm; height:0.4cm; padding-right: 3px; padding-top:4px;"><xsl:value-of select="factura/descuento"/></td>								
							</tr>
							<tr>
								<td colspan="2" style="border-left: 1px solid; font-size: 8px; width:3.5cm;"></td>
								<td style="font-size: 8px; width:7.0cm;"></td>
								<td align="right" style="border-left: 1px solid; font-size: 8px; width:1.5cm; height:0.4cm; padding-right:4px; padding-top:4px; font-weight: bold;">TARIFA 0%:</td>															
								<td align="right" style="border-left: 1px solid; border-right: 1px solid; font-size: 8px; width:2.0cm; height:0.4cm; padding-right: 3px; padding-top:4px;"><xsl:value-of select="factura/tarifaCero"/></td>								
							</tr>
							<tr>
								<td colspan="2" align="center" style="border-left: 1px solid; font-size: 8px; width:3.5cm;">________________________________</td>
								<td align="center" style="font-size: 8px; width:7.0cm;">________________________________</td>
								<td align="right" style="border-left: 1px solid; font-size: 8px; width:1.5cm; height:0.4cm; padding-right:4px; padding-top:4px; font-weight: bold;">TARIFA 12%:</td>															
								<td align="right" style="border-left: 1px solid; border-right: 1px solid; font-size: 8px; width:2.0cm; height:0.4cm; padding-right: 3px; padding-top:4px;"><xsl:value-of select="factura/tarifaDoce"/></td>								
							</tr>
							<tr>
								<td colspan="2" align="center" style="border-left: 1px solid; font-size: 8px; width:3.5cm;">FIRMA AUTORIZADA</td>
								<td align="center" style="font-size: 8px; width:7.0cm;">RECIBI CONFORME</td>
								<td align="right" style="border-left: 1px solid; font-size: 8px; width:1.5cm; height:0.4cm; padding-right:4px; padding-top:4px; font-weight: bold;">IVA   %:</td>															
								<td align="right" style="border-left: 1px solid; border-right: 1px solid; font-size: 8px; width:2.0cm; height:0.4cm; padding-right: 3px; padding-top:4px;"><xsl:value-of select="factura/iva"/></td>								
							</tr>
							<tr>
								<td colspan="2" style="border-left: 1px solid; border-bottom: 1px solid; font-size: 8px; width:3.5cm;"></td>
								<td style="border-bottom: 1px solid; font-size: 8px; width:7.0cm;"></td>
								<td align="right" style="border-left: 1px solid; border-bottom: 1px solid; font-size: 8px; width:1.5cm; height:0.4cm; padding-right:4px; padding-top:4px; font-weight: bold;">TOTAL:</td>															
								<td align="right" style="border-left: 1px solid; border-right: 1px solid; border-bottom: 1px solid; font-size: 8px; width:2.0cm; height:0.4cm; padding-right: 3px; padding-top:4px;"><xsl:value-of select="factura/total"/></td>								
							</tr>												
						</table>	
					</td>
				</tr>
				<col style="width:15.0cm;"/> 
				<tr>
					<td>
						<table>
							<col style="width:15.0cm;"/>
							<tr>
								<td style="font-size: 8px; width:15.0cm;" align="left">
									Fecha de vencimiento:_____________________________________________________________________________________________________________
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<col style="width:15.0cm;"/>
				<tr>
					<td>	
						<table>
							<col style="width:9.5cm;"/>
							<col style="width:0.5cm;"/>
							<col style="width:5.0cm;"/>
							<tr>
								<td style="font-size: 8px; width:9.5cm;">
									Fecha:_________________________________________________________________________
									<br/>
									<br/>
									A:_______________dias vista se servira Ud. pagar por esta unica LETRA DE CAMBIO a la orden 
									<br/>
									<br/>
									de____________________________________________________________________________.
									<br/>
									<br/>
									La cantidad de __________________________________________________________________
									<br/>
									<br/>
									con el interes del________% anual desde su vencimiento.
									<br/>
									<br/>
									Sin protesto. Eximase de presentacion y pago asi como avisos por falta de estos hechos.
								</td>
								<td style="width:0.5cm;"></td>
								<td style="font-size: 8px; width:5.0cm; text-align: justify">
									Aceptada, Valor  recibido.  El pago  no  podra <br/>hacerce por partes ni aun por mis herederos.<br/>Me  sujeto a los  jueces de  esta ciudad  y al<br/>juicio ejecutivo o verbal sumario a eleccion<br/> del demandante.
								</td>
							</tr>	
							<tr>
								<td style="height:1.0cm;"></td>
							</tr>
							<col style="width:9.5cm;"/>
							<col style="width:0.5cm;"/>
							<col style="width:5.0cm;"/>
							<tr>
								<td align="center" style="font-size: 8px; width:9.5cm;">
									____________________________
								</td>
								<td style="width:0.5cm;"></td>
								<td align="center" style="font-size: 8px; width:5.0cm;">																
									____________________________
								</td>
							</tr>
							<col style="width:9.5cm;"/>
							<col style="width:0.5cm;"/>
							<col style="width:5.0cm;"/>
							<tr>
								<td align="center" style="font-size: 8px; width:9.5cm;">
									Atentamente
								</td>
								<td style="width:0.5cm;"></td>
								<td align="center" style="font-size: 8px; width:5.0cm;">																
									Deudor.
								</td>
							</tr>			
						</table>
					</td>
				</tr>
				<tr>
				  <td>
					<table>
						<col style="width:15.0cm; "/>
						<tr>
							<td align="center" valign="botton" style="height: 2px; background:#003a6c; color: #FFFFFF; text-transform: uppercase;"></td>
						</tr>						
					</table>        
				  </td>
				</tr>						
			</table>			
		</html>
	</xsl:template>
</xsl:stylesheet>