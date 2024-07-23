package uluru.uluruspringbackend.data.enummer;

public enum TypeOfAlcohol {
    SOJU("SOJU"),
    BEER("BEER"),
    ETC("ETC");

    public String type;

    TypeOfAlcohol(String type){
        this.type = type;
    }

}
