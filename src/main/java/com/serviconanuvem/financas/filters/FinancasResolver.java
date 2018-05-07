package com.serviconanuvem.financas.filters;

public class FinancasResolver {

    private static ThreadLocal<String> identifier = new ThreadLocal<>();

    public static String getUsuario() {
        return FinancasResolver.identifier.get();
    }

    public static void setUsuario(String value) {
        FinancasResolver.identifier.set(value);
    }
}
