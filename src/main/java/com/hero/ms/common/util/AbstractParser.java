package com.hero.ms.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.List;

public abstract class AbstractParser {
    private static final Log log = LogFactory.getLog(AbstractParser.class);
    private final String name;
    private int rowCount;
    private int blackhole;
    protected String encoding = "gbk";

    protected AbstractParser(String name) {
        this.name = name;
    }

    public final String getName() {
        return this.name;
    }

    protected boolean process(Object row) {
        if (row == null) {
            return false;
        } else {
            this.blackhole += System.identityHashCode(row);
            ++this.rowCount;
            return true;
        }
    }

    public void resetRowCount() {
        this.rowCount = 0;
    }

    public int getRowCount() {
        return this.rowCount;
    }

    public String getBlackhole() {
        return String.valueOf(this.blackhole);
    }

    protected Reader toReader(File input) {
        try {
            return new FileReader(input);
        } catch (FileNotFoundException var3) {
            throw new IllegalStateException(var3);
        }
    }

    protected Reader toReader(File input, String encoding) {
        try {
            DataInputStream in = new DataInputStream(new FileInputStream(input));
            BufferedReader br = new BufferedReader(new InputStreamReader(in, encoding));
            return br;
        } catch (FileNotFoundException var5) {
            throw new IllegalStateException(var5);
        } catch (UnsupportedEncodingException var6) {
            throw new IllegalStateException(var6);
        }
    }

    protected Writer toWriter(File out) {
        try {
            return new FileWriter(out);
        } catch (IOException var3) {
            throw new IllegalStateException(var3);
        }
    }

    protected Writer toWriter(File out, String encoding) {
        try {
            return new OutputStreamWriter(new FileOutputStream(out, true), encoding);
        } catch (FileNotFoundException var4) {
            throw new IllegalStateException(var4);
        } catch (UnsupportedEncodingException var5) {
            throw new IllegalStateException(var5);
        }
    }

    public String getEncoding() {
        return this.encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public abstract void processRows(File var1);

    public abstract List<String[]> parseRows(File var1);

    public abstract File writeRows(List<Object[]> var1);

    public abstract File writeRows(List<Object[]> var1, String var2);
}
