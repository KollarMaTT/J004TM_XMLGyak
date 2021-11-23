<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="html" indent="yes" version ="5.0"/>

<xsl:template match="/">
  <html>
  <body>
    <h2>Tanulok</h2>
    <table border="1">
      <tr bgcolor="#FFFF00">
      	<th>ID</th>
        <th>Vezeteknev</th>
        <th>Keresztnev</th>
        <th>Becenev</th>
        <th>Fizetes</th>
        <th>Fokozat</th>
      </tr>
      <xsl:for-each select="class/student">
        <tr>
          <td><xsl:value-of select="./@id"/></td>
          <td><xsl:value-of select="vezeteknev"/></td>
          <td><xsl:value-of select="keresztnev"/></td>
          <td><xsl:value-of select="becenev"/></td>
          <td><xsl:value-of select="fizetes"/></td>
          <xsl:choose>
      		<xsl:when test="fizetes > 50000">
         		<td>High</td>
      		</xsl:when>
      		<xsl:when test="fizetes > 30000">
         		<td>Medium</td>
      		</xsl:when>
      		<xsl:otherwise>
         		<td>Low</td>
      		</xsl:otherwise>
      	  </xsl:choose>
        </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>


</xsl:stylesheet>