package com.gestaosimples.arquitetura.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public final class StringUtil {

    public static List<Long> decodeInt(String s) {
        List<Long> lista = new ArrayList<Long>();
        String[] ids = s.split(",");
        for (String id : ids) {
            if (!ObjetoUtil.isVazio(id)) {
                lista.add(Long.parseLong(id));
            }
        }
        return lista;

        // return Arrays.asList(s.split(",")).stream().map(x -> Long.parseLong(x)).collect(Collectors.toList());
    }

    public static String decodeParam(String s) {
        try {
            return URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}