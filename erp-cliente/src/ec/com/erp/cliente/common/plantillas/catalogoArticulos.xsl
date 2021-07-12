<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" 
xmlns:a="http://www.w3.org/1999/xhtml" exclude-result-prefixes="a">
	<xsl:output method="html"/>
	<xsl:decimal-format name="numerico" NaN="-" decimal-separator="," grouping-separator="."/>
	<xsl:decimal-format name="dolar" NaN="-" decimal-separator="." grouping-separator=","/>
	<xsl:template match="/">
		<html>
		    <table>		
				<col style="width:20.0cm;"/> 
				<tr>
				  <td>
					<table>
						<col style="width:20cm; "/>
						<tr>
							<td align="center" valign="botton" style="text-transform: uppercase; font-size:20px; font-weight: bold;"><pre>CAT&#xc1;LOGO DE ART&#xcd;CULOS</pre></td>
						</tr>						
					</table>        
				  </td>
				</tr>
				<col style="width:20.0cm;"/> 				
				<tr>
					<td>
						<table>
							<col style="width:1.0cm;"/>
							<col style="width:18.0cm;"/>
							<col style="width:1.0cm;"/>
							<tr><td></td></tr>
							<tr>
								<td align="center" valign="botton">
									<table>
										<col style="width:1.0cm;"/>
										<col style="width:18.0cm;"/>
										<col style="width:1.0cm;"/>
										<tr><td></td></tr>
										<tr>											
											<td class="borde" align="center" style="width:18.0cm;">
												<table border="1">
													<col style="width:5.0cm;"/>
													<col style="width:4.0cm;"/>
													<col style="width:9.0cm;"/>
													<xsl:for-each select="articulos/listaArticulos/articulo">
														<tr>
															<td style="width:5.0cm;" rowspan="4">
																<img height="105" width="100">
																	<xsl:attribute name="src">
																		<xsl:value-of select="imagen"/>
																	</xsl:attribute>
																</img>
															</td>
														</tr>
														<tr>
															<td align="left" style="font-size:12px; font-weight: bold; padding-left:5px; width:4.0cm;"><pre>Nombre del art&#xed;culo:</pre></td>
															<td align="left" style="font-size:12px; padding-left:5px; width:9.0cm;"><pre><xsl:value-of select="nombre"/></pre></td>
														</tr>
														<tr>
															<td align="left" style="font-size:12px; font-weight: bold; padding-left:5px; width:4.0cm;"><pre>Precio al por menor:</pre></td>
															<td align="left" style="font-size:12px; padding-left:5px; width:9.0cm;"><pre>$<xsl:value-of select="precioMenor"/></pre></td>
														</tr>
														<tr>
															<td align="left" style="font-size:12px; font-weight: bold; padding-left:5px; width:4.0cm;"><pre>Precio al por mayor</pre></td>
															<td align="left" style="font-size:12px; padding-left:5px; width:9.0cm;"><pre>$<xsl:value-of select="precioMayor"/></pre></td>
														</tr>
													</xsl:for-each>
												</table>
											</td>													
										 </tr>
										 <tr><td></td></tr>
									</table>
								</td>
							</tr>
							<tr><td></td></tr>
						</table>						
				    </td>
				</tr>							
			</table>			
		</html>
	</xsl:template>
</xsl:stylesheet>