package ohtuesimerkki;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {

  Reader readerStub = new Reader() {

    public List<Player> getPlayers() {
      ArrayList<Player> players = new ArrayList<>();

      players.add(new Player("Semenko", "EDM", 4, 12));
      players.add(new Player("Lemieux", "PIT", 45, 54));
      players.add(new Player("Kurri", "EDM", 37, 53));
      players.add(new Player("Yzerman", "DET", 42, 56));
      players.add(new Player("Gretzky", "EDM", 35, 89));

      return players;
    }
  };

  Statistics stats;

  @Before
  public void setUp() {
    // luodaan Statistics-olio joka käyttää "stubia"
    stats = new Statistics(readerStub);
  }

  @Test
  public void olematontaPelaajaaEiLoydy() {
    Player p = stats.search("Seppo");
    assertEquals(null, p);
  }

  @Test
  public void oikeallaNimellaLoytyyPelaaja() {
    Player p = stats.search("Semenko");
    assertEquals("Semenko", p.getName());
  }

  @Test
  public void kolmePelaajaaEdmontonissa() {
    List<Player> pl = stats.team("EDM");
    assertEquals(3, pl.size());
  }

  @Test
  public void topPlayerOnGretzky() {
    List<Player> pl = stats.topScorers(1);
    assertEquals(2, pl.size());
    assertEquals("Gretzky", pl.get(0).getName());
  }
}
