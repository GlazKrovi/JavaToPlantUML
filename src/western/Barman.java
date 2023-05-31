package western;

import java.util.ArrayList;
import java.util.List;

public class Barman extends Personnage {
    /* VALUES */
    private final List<Personnage> clientele = new ArrayList<>();
    private final Boisson boissonParDefaut = this.getBoisson();
    private final String nomDuBar; // on pourrait renommer le bar

    /* CREATE */
    public Barman(String nom) {
        super(nom, Boisson.of("vin", Genre.MASCULIN));
        this.nomDuBar = "Chez " + nom;
    }

    public Barman(String nom, String nomDuBar) {
        super(nom, Boisson.of("vin", Genre.MASCULIN));
        this.nomDuBar = nomDuBar;
    }

    /* GETTER-SETTER */
    @Override
    public String getPseudo() {
        return this.getNom() + " le barman de " + this.nomDuBar;
    }

    /* FUNCTIONS */
    @Override
    public void dire(String texte) {
        System.out.printf("%s - %s Coco.%n", this.getNom(), texte);
    }

    @Override
    public void sePresenter() {
        super.sePresenter();
    }

    public void servir(Personnage client) {
        this.dire("Et un p'tit verre de " + client.getBoisson().getNom() + " pour " + client.getPseudo() + " !");
        // ajout a sa liste de connaissance/clients
        if (!this.clientele.contains(client)) this.clientele.add(client);
        // le client boit
        client.boire();
    }
}
