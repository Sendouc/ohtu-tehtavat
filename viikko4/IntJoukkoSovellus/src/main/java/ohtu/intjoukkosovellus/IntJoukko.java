
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int UUDEN_TAULUKON_OLETUSARVOINEN_KOKO = 5, // aloitustalukon oletusarvoinen koko
            OLETUSKASVATUS = 5; // luotava uusi taulukko on
    // näin paljon isompi kuin vanha
    private int kasvatuskoko; // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono; // Joukon luvut säilytetään taulukon alkupäässä.
    private int alkioidenLkm; // Tyhjässä joukossa alkioiden_määrä on nolla.

    public IntJoukko() {
        lukujono = new int[UUDEN_TAULUKON_OLETUSARVOINEN_KOKO];
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        lukujono = new int[kapasiteetti];
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;

    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Negatiivinen kapasiteetti");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Negatiivinen kasvatuskoko");
        }
        lukujono = new int[kapasiteetti];
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;

    }

    public boolean lisaa(int luku) {
        if (lukuKuuluuJoukkoon(luku)) {
            return false;
        }

        lukujono[alkioidenLkm] = luku;
        alkioidenLkm++;
        if (alkioidenLkm == lukujono.length) {
            kasvataTaulukkoa();
        }
        return true;
    }

    public boolean lukuKuuluuJoukkoon(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                return true;
            }
        }

        return false;
    }

    public int haeLuvunIndeksi(int luku) {
        int kohta = -1;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                kohta = i;
                lukujono[kohta] = 0;
                break;
            }
        }

        return kohta;
    }

    public boolean poista(int luku) {
        int apu;

        int kohta = haeLuvunIndeksi(luku);
        if (kohta == -1) {
            return false;
        }

        for (int j = kohta; j < alkioidenLkm - 1; j++) {
            apu = lukujono[j];
            lukujono[j] = lukujono[j + 1];
            lukujono[j + 1] = apu;
        }
        alkioidenLkm--;
        return true;
    }

    private void kasvataTaulukkoa() {
        int[] taulukkoOld = new int[lukujono.length];
        taulukkoOld = lukujono;
        kopioiTaulukko(lukujono, taulukkoOld);
        lukujono = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(taulukkoOld, lukujono);
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int getMahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        String palautettava = "{";
        for (int i = 0; i < alkioidenLkm; i++) {
            palautettava += lukujono[i];
            if (i < alkioidenLkm - 1) {
                palautettava += ", ";
            }
        }
        palautettava += "}";
        return palautettava;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukujono[i];
        }
        return taulu;
    }

    public static IntJoukko getJoukkojenYhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko getJoukkojenLeikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko palautettavaJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    palautettavaJoukko.lisaa(bTaulu[j]);
                }
            }
        }
        return palautettavaJoukko;

    }

    public static IntJoukko getJoukkojenErotus(IntJoukko a, IntJoukko b) {
        IntJoukko palautettavaJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            palautettavaJoukko.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            palautettavaJoukko.poista(bTaulu[i]);
        }

        return palautettavaJoukko;
    }

}
