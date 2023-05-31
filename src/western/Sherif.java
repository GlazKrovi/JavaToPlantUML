package western;

import java.util.ArrayList;
import java.util.List;

public class Sherif extends Cowboy {
    /* VALUES */
    private final List<HorsLaLoi> wanted = new ArrayList<>();

    /* CREATE */
    public Sherif(String nom) {
        super(nom);
    }

    public Sherif(String nom, Boisson boisson) {
        super(nom, boisson);
    }

    /* GETTER-SETTER */
    @Override
    public String getNom() {
        return "Shérif " + super.getNom();
    }

    public List<HorsLaLoi> getWanted() {
        return wanted;
    }

    /* FUNCTIONS */
    @Override
    public void sePresenter() {
        super.sePresenter();
        // presente ses wanted, s'il en a :
        if (wanted.size() > 0) {
            for (HorsLaLoi horsLaLoi : wanted) {
                if (this.isWanted(horsLaLoi)) {
                    this.dire("Je suis a la recherche de " + horsLaLoi.getPseudo() + ". Ca vous dit quelque chose ?");
                }
            }
        }
    }

    @Override
    public void capturer(HorsLaLoi horsLaLoi) {
        this.dire("Au nom de la loi, " + horsLaLoi.getPseudo() + " je t'arrête !");
        super.capturer(horsLaLoi);
        if (this.isWanted(horsLaLoi)) { // s'il etait recherche...
            this.wanted.remove(horsLaLoi); // il ne l'est plus !
        }
    }

    /**
     * @param horsLaLoi
     * @pumlAggregation compote
     */
    public void rechercher(HorsLaLoi horsLaLoi) {
        if (!this.isWanted(horsLaLoi)) {
            this.wanted.add(horsLaLoi);
        }
    }

    public boolean isWanted(HorsLaLoi horsLaLoi) {
        return this.wanted.contains(horsLaLoi);
    }
}
