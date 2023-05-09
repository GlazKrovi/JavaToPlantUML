package western;

public class Histoire5 {

    public static void main(String[] args) {
        // Initiation des personnages
        Barman Petter = new Barman("Petter");
        Sherif John = new Sherif("John", Boisson.of("Tequila", Genre.FEMININ));
        Ripou Basile = new Ripou("Basile", Boisson.of("Vodka", Genre.FEMININ), "le Bienheureux");

        // On suspecte le Ripou // (test non precise dans l'enonce)
        John.rechercher(Basile);

        // Presentation
        Petter.sePresenter();
        John.sePresenter();
        Basile.sePresenter();

        // Collation
        Petter.servir(Basile);
        Petter.servir(John);
        Petter.dire("Aller ! Je goûte aussi !");
        Petter.servir(Petter);

        // Bagarre
        John.capturer(Basile);

        // Re-Presentation
        Petter.sePresenter();
        John.sePresenter();
        Basile.sePresenter();
    }
}



