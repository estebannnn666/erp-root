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
					<td>
						<table>
							<col style="width:2.0cm;"/>
							<col style="width:3.0cm;"/>
							<col style="width:1.0cm;"/>
							<col style="width:3.0cm;"/>
							<col style="width:3.0cm;"/>
							<col style="width:2.0cm;"/>
							<col style="width:3.0cm;"/>
							<col style="width:3.0cm;"/>
							<tr>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">NRO GUIA....:</pre></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;"><xsl:value-of select="guiaDespacho/numeroGuiaDespacho"/></pre></td>
								<td align="left"></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">FECHA DESPACHO:</pre></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">2018-07-26 17:05:46</pre></td>
								<td align="left"></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">DESPACHADOR...:</pre></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">_______________</pre></td>
							</tr>
							<tr>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">NRO GUIA....:</pre></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;"><xsl:value-of select="guiaDespacho/numeroGuiaDespacho"/></pre></td>
								<td align="left"></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">FECHA DESPACHO:</pre></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">2018-07-26 17:05:46</pre></td>
								<td align="left"></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">DESPACHADOR...:</pre></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">_______________</pre></td>
							</tr>
							<tr>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">NRO GUIA....:</pre></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;"><xsl:value-of select="guiaDespacho/numeroGuiaDespacho"/></pre></td>
								<td align="left"></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">FECHA DESPACHO:</pre></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">2018-07-26 17:05:46</pre></td>
								<td align="left"></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">DESPACHADOR...:</pre></td>
								<td align="left"><pre style="font-size: 12px !important; font-weight: normal !important;">_______________</pre></td>
							</tr>
						</table>
					</td>
				</tr>								
			</table>			
		</html>
	</xsl:template>
</xsl:stylesheet>