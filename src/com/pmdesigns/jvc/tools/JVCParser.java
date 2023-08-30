/* Generated By:JavaCC: Do not edit this line. JVCParser.java */
package com.pmdesigns.jvc.tools;

import java.io.*;
import java.util.*;

/**
 * Machine generated view template parser.  See JVCParser.jj for
 * actual source code (parser generated with javacc).
 */
public class JVCParser implements JVCParserConstants {

        private File template;
        private String templateName;
        private StringBuilder sb = new StringBuilder();
        private StringBuilder buf = new StringBuilder();
        private Set<String> imports;
        private boolean append = false;
        private int tagState = DEFAULT;
        private boolean inCommentedTag = false;
        private int cacheBlockCount = 0;
        private String cacheBlockKey;
        private int cacheBlockDepth = 0;

        // the name of the StringBuilder we are currently writing to.
        // This will be temporarily reset while parsing cached blocks
        private String sbName = "_sb";

        public int debugColumn = 100;


        /**
	 * Entry point for stand alone testing.
	 */
        public static void main(String args[]) throws FileNotFoundException, ParseException {
                if (args.length < 1 || args.length > 2) {
                        System.out.println("usage: JVCParser <path to template file> [-debug]");
                        return;
                }

                boolean debug = (args.length == 2 && "-debug".equals(args[1]));

                TreeSet<String> imports = new TreeSet<String>();
                JVCParser parser = new JVCParser(args[0], imports, debug);
                String s = parser.parse();
                System.out.println("imports: "+imports+"\n");
                System.out.println(s);
        }

        /**
	 * Constructor.
	 * Usage:
	 *    String template_file_path;
	 *    Set<String> imports;
	 *    JVCParser parser = new JVCParser(template_file_path, imports);
	 *    String pageGeneratorCode = parser.parse();
	 */
        public JVCParser(String path, Set<String> imports) throws FileNotFoundException {
                this(path, imports, false);
        }

        public JVCParser(String path, Set<String> imports, boolean debug) throws FileNotFoundException {
                this(new FileReader(path));
                this.template = new File(path);
                this.imports = imports;
                if (debug) {
                        // if templateName != null it means that debugging has been requested
                        templateName = template.getName();
                }
        }

        private void startTag(Token t) throws ParseException {
                if (tagState == IN_COMMENT) {
                        // comment tags can enclose other tags, but the enclosed tags still can't nest
                        if (inCommentedTag) {
                                throw new ParseException("Nested commented tag in "+template+", line: "+t.beginLine);
                        }

                        inCommentedTag = true;
                        buf.setLength(0);
                        return;
                }

                if (tagState != DEFAULT) {
                        throw new ParseException("Nested tag in "+template+", line: "+t.beginLine);
                }

                tagState = token_source.curLexState;

                addEscaped(t.specialToken);

                if (buf.length() > 0) {
                        if (!append) {
                                sb.append(sbName);
                                append = true;
                        }

                        escapeQuotes(buf);
                        sb.append(".append(\"").append(buf).append("\")");
                        buf.setLength(0);
                }
        }

        private void endTag(Token t) throws FileNotFoundException, ParseException {
                addEscaped(t.specialToken);

                switch (tagState) {
                case IN_CODE:
                        if (append) {
                                sb.append(";");
                                append = false;
                        }

                        if (isDebug()) {
                                sb.append(buf.toString());
                        } else {
                                sb.append(buf.toString().trim());
                        }

                        // if the end tag included an EOL then stick that on the end
                        if (t.image.length() > 2) {
                                addDebugging(sb, t.beginLine);
                                sb.append(t.image.substring(2));
                        }
                        break;

                case IN_EXPR:
                case IN_EXPR2:
                        if (!append) {
                                sb.append(sbName);
                                append = true;
                        }

                        String expr = buf.toString().trim();
                        if (expr.endsWith(";")) {
                                 // strip optional trailing ';'
                                expr = expr.substring(0, expr.length()-1);
                        }

                        int tagLen = 2;
                        if (tagState == IN_EXPR) {
                                // wrap in null-filter to convert null to an empty string
                                sb.append(".append(_nf(").append(expr).append("))");
                        } else {
                                tagLen = 3;
                                // wrap in html-filter to also html escape the string
                                sb.append(".append(_hf(").append(expr).append("))");
                        }

                        // if the end tag included an EOL then stick that on the end
                        if (t.image.length() > tagLen) {
                                sb.append(";");
                                append = false;
                                addDebugging(sb, t.beginLine);
                                sb.append(t.image.substring(tagLen));
                        }
                        break;

                case IN_LOAD:
                        // NOTE: included file names must begin with an '_' character
                        // to distinguish them from regular template files
                        String path = buf.toString().trim();
                        if (path.length() == 0) {
                                throw new FileNotFoundException("Missing include file in "+template+", line "+t.beginLine);
                        }
                        File f = new File(path);
                        if (!f.exists()) {
                                // look for the file in parent directories
                                File p = template.getAbsoluteFile();
                                while ((p = p.getParentFile()) != null) {
                                        f = new File(p, path);
                                        if (f.exists()) break;
                                }
                        }
                        if (!f.exists()) {
                                throw new FileNotFoundException("Can't find include file: "+path);
                        } else if (f.getName().charAt(0) != '_') {
                                throw new ParseException("Include file ("+f+") does not begin with '_' character. In "+
                                                                                 template+", line "+t.beginLine);
                        }

                        if (append) {
                                sb.append(";");
                                append = false;
                        }

                        JVCParser parser = new JVCParser(f.getAbsolutePath(), imports, isDebug());
                        // set the cache-block-depth and string-buffer name for this sub-parser
                        // in case we're already in a cach block
                        parser.cacheBlockDepth = cacheBlockDepth;
                        parser.sbName = sbName;
                        sb.append(parser.parse());
                        break;

                case IN_COMMENT:
                        if (inCommentedTag) {
                                // just the end of a commented (ie enclosed) tag, not the end of *the comment tag*
                                inCommentedTag = false;
                                buf.setLength(0);
                                return;
                        }
                        break;

                default:
                        throw new ParseException("Unbalanced end tag in "+template+", line: "+t.beginLine);
                }

                buf.setLength(0);
                tagState = DEFAULT;
        }

        private void startCache(Token t) throws ParseException {

                if (cacheBlockDepth > 0) {
                        // already in cache block
                        System.out.println("Warn: nested cache block in "+template+", line "+t.beginLine);

                } else {
                        cacheBlockCount++;

                        // start new cache block
                        if (append) {
                                sb.append(";\n");
                                append = false;
                        }

                        sb.append("{\n");
                        sb.append("String _cached = getCachedBlock(\"");
                        addCacheBlockKey(sb);
                        sb.append("\");\n");
                        sb.append("if (_cached == null) {\n");
                        sb.append("StringBuilder _sb2 = new StringBuilder();\n");

                        sbName = "_sb2";
                }

                cacheBlockDepth++;
        }

        private void endCache(Token t) throws ParseException {
                if (cacheBlockDepth <= 0) {
                        throw new ParseException("Unbalanced cache block in "+template+", line "+t.beginLine);
                }
                cacheBlockDepth--;

                if (cacheBlockDepth == 0) {
                        // finished caching
                        if (append) {
                                sb.append(";\n");
                                append = false;
                        }

                        sb.append("_cached = _sb2.toString();\n");
                        sb.append("setCachedBlock(\"");
                        addCacheBlockKey(sb);
                        sb.append("\", _cached);\n");
                        sb.append("}\n");
                        sb.append("_sb.append(_cached);\n");
                        sb.append("}\n");

                        sbName = "_sb";
                }
        }

        private void addCacheBlockKey(StringBuilder sb) {
                cacheBlockKey = template.getAbsolutePath(); // use canonicalPath?
                sb.append(cacheBlockKey).append("_").append(cacheBlockCount);
        }

        private void addImport(Token t) {
                addEscaped(t.specialToken);

                // strip off 'import'.  note: the remaining 'package' may start with 'static'
                String pkg = t.image.substring(7, t.image.length()-1).trim();
                imports.add(pkg);
        }

        private void addEOL(Token t) {
                addEscaped(t.specialToken);

                if (tagState != DEFAULT) {
                        // in tag, just add to buf
                        addDebugging(buf, t.beginLine);
                        buf.append(t.image);
                } else {
                        // not in a tag
                        if (t.image.length() == 2) {
                                buf.append("\\r\\n");
                        } else {
                                buf.append((t.image.charAt(0) == '\n') ? "\\n" : "\\r");
                        }

                        if (!append) {
                                sb.append(sbName);
                                append = true;
                        }

                        escapeQuotes(buf);
                        sb.append(".append(\"").append(buf).append("\");");
                        buf.setLength(0);
                        append = false;

                        addDebugging(sb, t.beginLine);
                        sb.append(t.image);
                }
        }

        private boolean isDebug() {
                return templateName != null;
        }

        private void addDebugging(StringBuilder sb, int line) {
                if (isDebug()) {
                        int n = Math.max(sb.lastIndexOf("\n"), sb.lastIndexOf("\r"));
                        for (int i = sb.length()-n; i < debugColumn; i++) {
                                sb.append(" ");
                        }
                        sb.append("\t// ").append(templateName).append(" (").append(line).append(")");
                }
        }

        private void addOther(Token t) {
                addEscaped(t.specialToken);
                buf.append(t.image);
        }

        private void addEscaped(Token t) {
                if (t != null) {
                        // recursively add other special tokens
                        addEscaped(t.specialToken);

                        // put any escaped character into the buffer
                        if (t.kind == ESCAPED_CHAR) {
                                buf.append(t.image.charAt(1));
                        }
                }
        }

        private void flush() throws ParseException {
                if (tagState != DEFAULT) {
                        throw new ParseException("Unbalanced tag at end of "+template);
                }

                if (buf.length() == 0) {
                        if (append) {
                                sb.append(";");
                                append = false;
                        }
                } else {
                        if (!append) {
                                sb.append(sbName);
                                append = true;
                        }

                        escapeQuotes(buf);
                        sb.append(".append(\"").append(buf).append("\");");
                        buf.setLength(0);
                        append = false;
                }
        }

        private void escapeQuotes(StringBuilder sb) {
                int idx = sb.lastIndexOf("\"");
                while (idx >= 0) {
                        sb.insert(idx, '\\');
                        idx = sb.lastIndexOf("\"", idx);
                }
        }

/**
 * Root production.
 */
  final public String parse() throws ParseException, ParseException, FileNotFoundException {
        Token t;
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case START_CODE_TAG:
      case START_EXPR_TAG:
      case START_EXPR2_TAG:
      case START_LOAD_TAG:
      case START_COMMENT_TAG:
      case EOL:
      case START_CACHE_BLOCK:
      case END_CACHE_BLOCK:
      case END_TAG:
      case IMPORT:
      case OTHER:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case EOL:
        t = jj_consume_token(EOL);
                addEOL(t);
        break;
      case START_CODE_TAG:
        t = jj_consume_token(START_CODE_TAG);
                startTag(t);
        break;
      case START_EXPR_TAG:
        t = jj_consume_token(START_EXPR_TAG);
                startTag(t);
        break;
      case START_EXPR2_TAG:
        t = jj_consume_token(START_EXPR2_TAG);
                startTag(t);
        break;
      case START_LOAD_TAG:
        t = jj_consume_token(START_LOAD_TAG);
                startTag(t);
        break;
      case START_COMMENT_TAG:
        t = jj_consume_token(START_COMMENT_TAG);
                startTag(t);
        break;
      case END_TAG:
        t = jj_consume_token(END_TAG);
                endTag(t);
        break;
      case START_CACHE_BLOCK:
        t = jj_consume_token(START_CACHE_BLOCK);
                startCache(t);
        break;
      case END_CACHE_BLOCK:
        t = jj_consume_token(END_CACHE_BLOCK);
                endCache(t);
        break;
      case IMPORT:
        t = jj_consume_token(IMPORT);
                addImport(t);
        break;
      case OTHER:
        t = jj_consume_token(OTHER);
                addOther(t);
        break;
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    jj_consume_token(0);
          flush();
          {if (true) return sb.toString();}
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public JVCParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[2];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x107fe,0x107fe,};
   }

  /** Constructor with InputStream. */
  public JVCParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public JVCParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new JVCParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public JVCParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new JVCParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public JVCParser(JVCParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(JVCParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List jj_expentries = new java.util.ArrayList();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[17];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 2; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 17; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
