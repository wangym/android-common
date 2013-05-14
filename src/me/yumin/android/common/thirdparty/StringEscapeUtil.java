package me.yumin.android.common.thirdparty;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;


/**
 * 字符串转义工具类，能将字符串转换成适应 Java、Java Script、HTML、XML、和SQL语句的形式。
 * 
 * @author Michael Zhou
 * @version $Id: StringEscapeUtil.java 4347 2007-09-17 09:32:01Z baobao $
 */
public class StringEscapeUtil {
 



    /* ============================================================================ */
    /* HTML和XML。 */
    /* ============================================================================ */

    /**
     * 根据HTML的规则，将字符串中的部分字符转换成实体编码。
     * <p>
     * 例如：<code>"bread" & "butter"</code>将被转换成<tt>&amp;quot;bread&amp;quot; &amp;amp;
     * &amp;quot;butter&amp;quot;</tt>.
     * </p>
     * <p>
     * 支持所有HTML 4.0 entities。
     * </p>
     * 
     * @param str 要转义的字符串
     * @return 用实体编码转义的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
     * @see <a href="http://hotwired.lycos.com/webmonkey/reference/special_characters/">ISO Entities</a>
     * @see <a href="http://www.w3.org/TR/REC-html32#latin1">HTML 3.2 Character Entities for ISO Latin-1</a>
     * @see <a href="http://www.w3.org/TR/REC-html40/sgml/entities.html">HTML 4.0 Character entity references</a>
     * @see <a href="http://www.w3.org/TR/html401/charset.html#h-5.3">HTML 4.01 Character References</a>
     * @see <a href="http://www.w3.org/TR/html401/charset.html#code-position">HTML 4.01 Code positions</a>
     */
    public static String escapeHtml(String str) {
        return escapeEntities(Entities.HTML40, str);
    }

    /**
     * 根据HTML的规则，将字符串中的部分字符转换成实体编码。
     * <p>
     * 例如：<code>"bread" & "butter"</code>将被转换成<tt>&amp;quot;bread&amp;quot; &amp;amp;
     * &amp;quot;butter&amp;quot;</tt>.
     * </p>
     * <p>
     * 支持所有HTML 4.0 entities。
     * </p>
     * 
     * @param str 要转义的字符串
     * @param out 输出流
     * @throws IllegalArgumentException 如果输出流为<code>null</code>
     * @throws IOException 如果输出失败
     * @see <a href="http://hotwired.lycos.com/webmonkey/reference/special_characters/">ISO Entities</a>
     * @see <a href="http://www.w3.org/TR/REC-html32#latin1">HTML 3.2 Character Entities for ISO Latin-1</a>
     * @see <a href="http://www.w3.org/TR/REC-html40/sgml/entities.html">HTML 4.0 Character entity references</a>
     * @see <a href="http://www.w3.org/TR/html401/charset.html#h-5.3">HTML 4.01 Character References</a>
     * @see <a href="http://www.w3.org/TR/html401/charset.html#code-position">HTML 4.01 Code positions</a>
     */
    public static void escapeHtml(String str, Writer out) throws IOException {
        escapeEntities(Entities.HTML40, str, out);
    }

    /**
     * 根据XML的规则，将字符串中的部分字符转换成实体编码。
     * <p>
     * 例如：<code>"bread" & "butter"</code>将被转换成<tt>&amp;quot;bread&amp;quot; &amp;amp;
     * &amp;quot;butter&amp;quot;</tt>.
     * </p>
     * <p>
     * 只转换4种基本的XML实体：<code>gt</code>、<code>lt</code>、<code>quot</code>和<code>amp</code>。 不支持DTD或外部实体。
     * </p>
     * 
     * @param str 要转义的字符串
     * @return 用实体编码转义的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
     */
    public static String escapeXml(String str) {
        return escapeEntities(Entities.XML, str);
    }

    /**
     * 根据XML的规则，将字符串中的部分字符转换成实体编码。
     * <p>
     * 例如：<code>"bread" & "butter"</code>将被转换成<tt>&amp;quot;bread&amp;quot; &amp;amp;
     * &amp;quot;butter&amp;quot;</tt>.
     * </p>
     * <p>
     * 只转换4种基本的XML实体：<code>gt</code>、<code>lt</code>、<code>quot</code>和<code>amp</code>。 不支持DTD或外部实体。
     * </p>
     * 
     * @param str 要转义的字符串
     * @param out 输出流
     * @throws IllegalArgumentException 如果输出流为<code>null</code>
     * @throws IOException 如果输出失败
     */
    public static void escapeXml(String str, Writer out) throws IOException {
        escapeEntities(Entities.XML, str, out);
    }

    /**
     * 根据指定的规则，将字符串中的部分字符转换成实体编码。
     * 
     * @param entities 实体集合
     * @param str 要转义的字符串
     * @return 用实体编码转义的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
     */
    public static String escapeEntities(Entities entities, String str) {
        if (str == null) {
            return null;
        }

        try {
            StringWriter out = new StringWriter(str.length());

            if (escapeEntitiesInternal(entities, str, out)) {
                return out.toString();
            }

            return str;
        } catch (IOException e) {
            return str; // StringWriter不可能发生这个异常
        }
    }

    /**
     * 根据指定的规则，将字符串中的部分字符转换成实体编码。
     * 
     * @param entities 实体集合
     * @param str 要转义的字符串
     * @param out 输出流
     * @throws IllegalArgumentException 如果输出流为<code>null</code>
     * @throws IOException 如果输出失败
     */
    public static void escapeEntities(Entities entities, String str, Writer out) throws IOException {
        escapeEntitiesInternal(entities, str, out);
    }

    /**
     * 按HTML的规则对字符串进行反向转义，支持HTML 4.0中的所有实体，以及unicode实体如<code>&amp;#12345;</code>。
     * <p>
     * 例如："&amp;lt;Fran&amp;ccedil;ais&amp;gt;"将被转换成"&lt;Fran&ccedil;ais&gt;"
     * </p>
     * <p>
     * 如果实体不能被识别，它将被保留不变。
     * </p>
     * 
     * @param str 不包含转义字符的字符串
     * @return 恢复成未转义的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String unescapeHtml(String str) {
        return unescapeEntities(Entities.HTML40, str);
    }

    /**
     * 按HTML的规则对字符串进行反向转义，支持HTML 4.0中的所有实体，以及unicode实体如<code>&amp;#12345;</code>。
     * <p>
     * 例如："&amp;lt;Fran&amp;ccedil;ais&amp;gt;"将被转换成"&lt;Fran&ccedil;ais&gt;"
     * </p>
     * <p>
     * 如果实体不能被识别，它将被保留不变。
     * </p>
     * 
     * @param str 包含转义字符的字符串
     * @param out 输出流
     * @throws IllegalArgumentException 如果输出流为<code>null</code>
     * @throws IOException 如果输出失败
     */
    public static void unescapeHtml(String str, Writer out) throws IOException {
        unescapeEntities(Entities.HTML40, str, out);
    }

    /**
     * 按XML的规则对字符串进行反向转义，支持unicode实体如<code>&amp;#12345;</code>。
     * <p>
     * 例如："&amp;lt;Fran&amp;ccedil;ais&amp;gt;"将被转换成"&lt;Fran&ccedil;ais&gt;"
     * </p>
     * <p>
     * 如果实体不能被识别，它将被保留不变。
     * </p>
     * 
     * @param str 不包含转义字符的字符串
     * @return 恢复成未转义的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String unescapeXml(String str) {
        return unescapeEntities(Entities.XML, str);
    }

    /**
     * 按XML的规则对字符串进行反向转义，支持unicode实体如<code>&amp;#12345;</code>。
     * <p>
     * 例如："&amp;lt;Fran&amp;ccedil;ais&amp;gt;"将被转换成"&lt;Fran&ccedil;ais&gt;"
     * </p>
     * <p>
     * 如果实体不能被识别，它将被保留不变。
     * </p>
     * 
     * @param str 不包含转义字符的字符串
     * @param out 输出流
     * @throws IllegalArgumentException 如果输出流为<code>null</code>
     * @throws IOException 如果输出失败
     */
    public static void unescapeXml(String str, Writer out) throws IOException {
        unescapeEntities(Entities.XML, str, out);
    }

    /**
     * 按指定的规则对字符串进行反向转义。
     * 
     * @param entities 实体集合
     * @param str 不包含转义字符的字符串
     * @return 恢复成未转义的字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String unescapeEntities(Entities entities, String str) {
        if (str == null) {
            return null;
        }

        try {
            StringWriter out = new StringWriter(str.length());

            if (unescapeEntitiesInternal(entities, str, out)) {
                return out.toString();
            }

            return str;
        } catch (IOException e) {
            return str; // StringWriter不可能发生这个异常
        }
    }

    /**
     * 按指定的规则对字符串进行反向转义。
     * <p>
     * 如果实体不能被识别，它将被保留不变。
     * </p>
     * 
     * @param entities 实体集合
     * @param str 不包含转义字符的字符串
     * @param out 输出流
     * @throws IllegalArgumentException 如果输出流为<code>null</code>
     * @throws IOException 如果输出失败
     */
    public static void unescapeEntities(Entities entities, String str, Writer out) throws IOException {
        unescapeEntitiesInternal(entities, str, out);
    }

    /**
     * 将字符串中的部分字符转换成实体编码。
     * 
     * @param entities 实体集合
     * @param str 要转义的字符串
     * @param out 字符输出流，不能为<code>null</code>
     * @return 如果字符串没有变化，则返回<code>false</code>
     * @throws IllegalArgumentException 如果<code>entities</code>或输出流为<code>null</code>
     * @throws IOException 如果输出失败
     */
    private static boolean escapeEntitiesInternal(Entities entities, String str, Writer out) throws IOException {
        boolean needToChange = false;

        if (entities == null) {
            throw new IllegalArgumentException("The Entities must not be null");
        }

        if (out == null) {
            throw new IllegalArgumentException("The Writer must not be null");
        }

        if (str == null) {
            return needToChange;
        }

        for (int i = 0; i < str.length(); ++i) {
            char ch = str.charAt(i);
            String entityName = entities.getEntityName(ch);

            if (entityName == null) {
                out.write(ch);
            } else {
                out.write('&');
                out.write(entityName);
                out.write(';');

                // 设置改变标志
                needToChange = true;
            }
        }

        return needToChange;
    }

    /**
     * 将字符串中的已定义实体和unicode实体如<code>&amp;#12345;</code>转换成相应的unicode字符。
     * <p>
     * 未定义的实体将保留不变。
     * </p>
     * 
     * @param entities 实体集合，如果为<code>null</code>，则只转换<code>&amp;#number</code>实体。
     * @param str 包含转义字符的字符串
     * @param out 字符输出流，不能为<code>null</code>
     * @return 如果字符串没有变化，则返回<code>false</code>
     * @throws IllegalArgumentException 如果输出流为<code>null</code>
     * @throws IOException 如果输出失败
     */
    private static boolean unescapeEntitiesInternal(Entities entities, String str, Writer out) throws IOException {
        boolean needToChange = false;

        if (out == null) {
            throw new IllegalArgumentException("The Writer must not be null");
        }

        if (str == null) {
            return needToChange;
        }

        for (int i = 0; i < str.length(); ++i) {
            char ch = str.charAt(i);

            if (ch == '&') {
                // 查找&xxxx;
                int semi = str.indexOf(';', i + 1);

                if ((semi == -1) || ((i + 1) >= (semi - 1))) {
                    out.write(ch);
                    continue;
                }

                // 如果是&#xxxxx;
                if (str.charAt(i + 1) == '#') {
                    int firstCharIndex = i + 2;
                    int radix = 10;

                    if ((firstCharIndex) >= (semi - 1)) {
                        out.write(ch);
                        out.write('#');
                        i++;
                        continue;
                    }

                    char firstChar = str.charAt(firstCharIndex);

                    if (firstChar == 'x' || firstChar == 'X') {
                        firstCharIndex++;
                        radix = 16;

                        if ((firstCharIndex) >= (semi - 1)) {
                            out.write(ch);
                            out.write('#');
                            i++;
                            continue;
                        }
                    }

                    try {
                        int entityValue = Integer.parseInt(str.substring(firstCharIndex, semi), radix);

                        out.write(entityValue);

                        // 设置改变标志
                        needToChange = true;
                    } catch (NumberFormatException e) {
                        out.write(ch);
                        out.write('#');
                        i++;
                        continue;
                    }
                } else {
                    String entityName = str.substring(i + 1, semi);
                    int entityValue = -1;

                    if (entities != null) {
                        entityValue = entities.getEntityValue(entityName);
                    }

                    if (entityValue == -1) {
                        out.write('&');
                        out.write(entityName);
                        out.write(';');
                    } else {
                        out.write(entityValue);

                        // 设置改变标志
                        needToChange = true;
                    }
                }

                i = semi;
            } else {
                out.write(ch);
            }
        }

        return needToChange;
    }





    /* ============================================================================ */
    /* URL/URI encoding/decoding。 */
    /*                                                                              */
    /* 根据RFC2396：http://www.ietf.org/rfc/rfc2396.txt */
    /* ============================================================================ */

}
