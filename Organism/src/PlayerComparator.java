import java.util.Comparator;

/**
 * This class compares the individual player's row and column to follow the movement from top-left to right-bottom
 * @author paulhsu
 *
 */
public class PlayerComparator implements Comparator<PlayerInfo> {
	
	public int compare(PlayerInfo o1, PlayerInfo o2){
		 Integer row1 = o1.getRow();
         Integer row2 = o2.getRow();
         int comp = row1.compareTo(row2);
         if (comp != 0) {
            return comp;
         } else {
            Integer x1 = o1.getColumn();
            Integer x2 = o2.getColumn();
            return x1.compareTo(x2);
         }
	}
}
