package com.serviconanuvem.financas.filters;

public class FinancasResolver {

    private static ThreadLocal<String> identifier = new ThreadLocal<>();
    private static ThreadLocal<Long> planoDeContas = new ThreadLocal<>();

    public static String getUsuario() {
        return FinancasResolver.identifier.get();
    }

    public static void setUsuario(String value) {
        FinancasResolver.identifier.set(value);
    }

    public static Long getPlanoDeContas() {
        return FinancasResolver.planoDeContas.get();
    }

    public static void setPlanoDeContas(Long value) {
        FinancasResolver.planoDeContas.set(value);
    }
}
