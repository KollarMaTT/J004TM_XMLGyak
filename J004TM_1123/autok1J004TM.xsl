<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
  <h2>Autok ar szerint novekvo sorrendben</h2>
    <xsl:for-each select="autok/auto">
      <xsl:sort select="ar"/>
      <ul>
        <li><xsl:value-of select="./@rsz"/></li>
        <li><xsl:value-of select="ar"/></li>
      </ul>
    </xsl:for-each>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>