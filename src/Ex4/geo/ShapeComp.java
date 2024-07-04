package Ex4.geo;

import java.util.Comparator;

import Ex4.Ex4_Const;
import Ex4.GUI_Shapeable;

/*
 * @author Sapir Dahan ID: 325732972
 *
 */

/*
 * This class represents a Comparator over GUI_Shapes - 
 * as a linear order over GUI_Shapes.
 */

public class ShapeComp implements Comparator<GUI_Shapeable>{
	
	private int _flag;
	public ShapeComp(int flag) {
		_flag = flag;
	}
	
	public static final Comparator<GUI_Shapeable> CompByTag = new ShapeComp(Ex4_Const.Sort_By_Tag);
	public static final Comparator<GUI_Shapeable> CompByAntiTag = new ShapeComp(Ex4_Const.Sort_By_Anti_Tag);
	public static final Comparator<GUI_Shapeable> CompByArea = new ShapeComp(Ex4_Const.Sort_By_Area);
	public static final Comparator<GUI_Shapeable> CompByAntiArea = new ShapeComp(Ex4_Const.Sort_By_Anti_Area);
	public static final Comparator<GUI_Shapeable> CompByPerimeter = new ShapeComp(Ex4_Const.Sort_By_Perimeter);
	public static final Comparator<GUI_Shapeable> CompByAntiPerimeter = new ShapeComp(Ex4_Const.Sort_By_Anti_Perimeter);
	public static final Comparator<GUI_Shapeable> CompByToString = new ShapeComp(Ex4_Const.Sort_By_toString);
	public static final Comparator<GUI_Shapeable> CompByAntiToString = new ShapeComp(Ex4_Const.Sort_By_Anti_toString);
	
	@Override
	public int compare(GUI_Shapeable o1, GUI_Shapeable o2) {
		int ans=0;

		// Compare by tag
		if(_flag == Ex4_Const.Sort_By_Tag) {
			ans = Integer.compare(o1.getTag(), o2.getTag());
		}

		// Compare by anti tag
		if(_flag == Ex4_Const.Sort_By_Anti_Tag) {
			ans = Integer.compare(o2.getTag(), o1.getTag());
		}

		// Compare by area
		if(_flag == Ex4_Const.Sort_By_Area) {
			ans = Double.compare(o1.getShape().area(), o2.getShape().area());
		}

		// Compare by anti area
		if(_flag == Ex4_Const.Sort_By_Anti_Area) {
			ans = Double.compare(o2.getShape().area(), o1.getShape().area());		
		}
		
		// Compare by perimeter
		if(_flag == Ex4_Const.Sort_By_Perimeter) {
			ans = Double.compare(o1.getShape().perimeter(), o2.getShape().perimeter());
		}

		// Compare by anti perimeter
		if(_flag == Ex4_Const.Sort_By_Anti_Perimeter) {
			ans = Double.compare(o2.getShape().perimeter(), o1.getShape().perimeter());		
		}

		// Compare by to string
		if(_flag == Ex4_Const.Sort_By_toString) {
			ans = sortToString(o1).compareTo(sortToString(o2));
		}
		
		// Compare by anti to string
		if(_flag == Ex4_Const.Sort_By_Anti_toString) {
			ans = sortToString(o2).compareTo(sortToString(o1));
		}
		
		return ans;
	}
	
	// For sorting in toString
		public String sortToString(GUI_Shapeable o) {
			// Getting the object name
			GeoShapeable g = o.getShape();
			String s = g.getClass().getCanonicalName();
			String str =s.substring(s.lastIndexOf('.') + 1) + ",";
			
			// Get the objects points
			str = str + g.toString();
			
			return str;
		}

}
