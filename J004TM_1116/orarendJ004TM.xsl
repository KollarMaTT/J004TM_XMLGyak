<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
    <h2>Tanulok</h2>
    <table border="1">
      <tr bgcolor="#9acd32">
      	<th>ID</th>
      	<th>Tipus</th>
        <th>Targy</th>
        <th>Idopont</th>
        <th>Helyszin</th>
        <th>Oktato</th>
        <th>Szak</th>
      </tr>
      <xsl:for-each select="orarend/ora">
        <tr>
          <td><xsl:value-of select="./@id"/></td>
          <td><xsl:value-of select="./@tipus"/></td>
          <td><xsl:value-of select="targy"/></td>
          <td><xsl:value-of select="idopont"/></td>
          <td><xsl:value-of select="helyszin"/></td>
          <td><xsl:value-of select="oktato"/></td>
          <td><xsl:value-of select="szak"/></td>
        </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>


</xsl:stylesheet>