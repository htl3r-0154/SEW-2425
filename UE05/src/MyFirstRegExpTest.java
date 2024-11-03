import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyFirstRegExpTest {

  @Test
  void testIsIP() {
    assertFalse(MyFirstRegExp.isIP("192.168.0"));
    assertTrue(MyFirstRegExp.isIP("192.168.0.1"));
    assertFalse(MyFirstRegExp.isIP("a.b.c.d"));
    assertTrue(MyFirstRegExp.isIP("10.0.0.1"));
    assertFalse(MyFirstRegExp.isIP("2555.2555.255.255"));
    assertFalse(MyFirstRegExp.isIP("65sdaf416s5dfas"));
    assertFalse(MyFirstRegExp.isIP("192.168.0.333"));
  }


  @Test
  void testIsNumber() {
    assertTrue(MyFirstRegExp.isNumber("-123.1654e-91"));
    assertTrue(MyFirstRegExp.isNumber("-123.5"));
    assertFalse(MyFirstRegExp.isNumber("-123.asdf"));
    assertTrue(MyFirstRegExp.isNumber("-12345.78e-91"));
    assertTrue(MyFirstRegExp.isNumber("-20"));
    assertTrue(MyFirstRegExp.isNumber("-99"));
    assertTrue(MyFirstRegExp.isNumber(".234"));
    assertTrue(MyFirstRegExp.isNumber("0"));
    assertTrue(MyFirstRegExp.isNumber("0.99"));
    assertTrue(MyFirstRegExp.isNumber("1"));
    assertFalse(MyFirstRegExp.isNumber(".123.0"));
    assertTrue(MyFirstRegExp.isNumber("12345."));
    assertFalse(MyFirstRegExp.isNumber("54.22.5646"));
    assertFalse(MyFirstRegExp.isNumber("adsf"));
    assertFalse(MyFirstRegExp.isNumber("."));
  }

  @Test
  void testHasEvenZeros() {
    assertTrue(MyFirstRegExp.hasEvenZeros("00001"));
    assertFalse(MyFirstRegExp.hasEvenZeros("0000asdf1"));
    assertFalse(MyFirstRegExp.hasEvenZeros("0001"));
    assertTrue(MyFirstRegExp.hasEvenZeros("00100"));
    assertFalse(MyFirstRegExp.hasEvenZeros("11011"));
    assertFalse(MyFirstRegExp.hasEvenZeros("asdf"));
  }


  @Test
  void testHasNoDoubles() {
    assertFalse(MyFirstRegExp.hasNoDoubles("001001"));
    assertFalse(MyFirstRegExp.hasNoDoubles("0110101"));
    assertTrue(MyFirstRegExp.hasNoDoubles("01010101"));
    assertTrue(MyFirstRegExp.hasNoDoubles("010101010"));
    assertTrue(MyFirstRegExp.hasNoDoubles("101010"));
    assertFalse(MyFirstRegExp.hasNoDoubles("asfd"));
    assertTrue(MyFirstRegExp.hasNoDoubles("0"));
    assertTrue(MyFirstRegExp.hasNoDoubles("1"));
  }

  @Test
  void testIsClassName() {
    assertFalse(MyFirstRegExp.isClassName(""));
    assertTrue(MyFirstRegExp.isClassName("IPAddress"));
    assertTrue(MyFirstRegExp.isClassName("K"));
    assertFalse(MyFirstRegExp.isClassName("_K"));
    assertFalse(MyFirstRegExp.isClassName("K_"));
    assertTrue(MyFirstRegExp.isClassName("KlaSSen_Name1"));
    assertFalse(MyFirstRegExp.isClassName("KlaSSen_Name1_"));
    assertFalse(MyFirstRegExp.isClassName("MYFRST_"));
    assertFalse(MyFirstRegExp.isClassName("a"));
    assertFalse(MyFirstRegExp.isClassName("asfd"));
  }

  @Test
  void testIsDate() {
    assertFalse(MyFirstRegExp.isDate("001.1.2011"));
    assertTrue(MyFirstRegExp.isDate("08. 1.2014"));
    assertTrue(MyFirstRegExp.isDate("1.1."));
    assertTrue(MyFirstRegExp.isDate("1.1.0018"));
    assertFalse(MyFirstRegExp.isDate("23.12.196"));
    assertTrue(MyFirstRegExp.isDate("29.2.2011"));
    assertTrue(MyFirstRegExp.isDate("31. 12. 14"));
  }

  @Test
  void testGetFirstIp() {
    assertEquals("10.0.0.1", MyFirstRegExp.getFirstIp("10.0.0.1IPIPIP192.168.0.1IPIPIPIP192.168.0.2"));
    assertNull(MyFirstRegExp.getFirstIp("IPIPIPIPIPIPIP"));
    assertEquals("10.0.0.0", MyFirstRegExp.getFirstIp("TEXZ10.0.0.0sadf"));
    assertEquals("192.168.1.2", MyFirstRegExp.getFirstIp("asdfasdf192.168.1.2asdf"));
  }

  @Test
  void testGetFirstDate() {
    assertEquals("23.12.19", MyFirstRegExp.getFirstDate("23.12.196 1.1.IPIPIP31. 12. 14IPIPIPIP08. 1.2014"));
    assertNull(MyFirstRegExp.getFirstDate("IPIPIPIPIPIPIP"));
    assertEquals("31.12.", MyFirstRegExp.getFirstDate("TEXZ31.12.sadf"));
    assertEquals("21.12.2000", MyFirstRegExp.getFirstDate("asdfasdf21.12.2000asdf"));
  }
}