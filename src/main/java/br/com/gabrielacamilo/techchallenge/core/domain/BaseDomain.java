package br.com.gabrielacamilo.techchallenge.core.domain;

public abstract class BaseDomain {
    private String id;

    public BaseDomain(String id) {
    }

    @Deprecated
    public BaseDomain() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static <T> void isValid(T domain){
        System.out.println("Validating domain - TODO");
    }

    public String getDomainName() {
        return this.getClass().getSimpleName();
    }
}
