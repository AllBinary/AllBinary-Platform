/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package allbinary.graphics.j2me.workarea.tools;

import allbinary.graphics.GPoint;
import allbinary.graphics.PointFactory;
import allbinary.graphics.j2me.workarea.WorkAreaJPanel;
import allbinary.graphics.j2me.workarea.canvas.CanvasDom;
import allbinary.math.PositionStrings;
import org.allbinary.util.BasicArrayList;
import org.w3c.dom.Node;

/**
 *
 * @author user
 */
public class PointsDomUtil {

    private static final PointsDomUtil instance = new PointsDomUtil();

    /**
     * @return the instance
     */
    public static PointsDomUtil getInstance()
    {
        return instance;
    }

    public final String LINES = "lines";

   public final String POINTONE = "pointOne";
   public final String POINTTWO = "pointTwo";

   //X
   //Y
   private final String ROTATEDPOINTONE = "rotatedPointOne";
   //X
   //Y
   private final String ROTATEDPOINTTWO = "rotatedPointTwo";
   //X
   //Y
   private final String LINE = "line";

   public Node toDom(CanvasDom canvasDom, BasicArrayList pointVector) throws Exception
   {
      BasicArrayList tempPointVector = PointsUtil.getInstance().doTransforms(
              pointVector, new Double(canvasDom.getAngle()),
              PointFactory.getInstance(canvasDom.getDimension().getWidth(),
              canvasDom.getDimension().getHeight()));

      org.w3c.dom.Document document = WorkAreaJPanel.getDocument();

      Node linesNode = (Node) document.createElement(LINES);

      int size2 = tempPointVector.size();
      int size = pointVector.size();

      GPoint firstPoint = null;
      GPoint rotatedFirstPoint = null;

      int index = 0;
      if (index < size && index < size2)
      {
         rotatedFirstPoint = (GPoint) tempPointVector.get(index);
         firstPoint = (GPoint) pointVector.get(index);
         index++;
      }

      while (index < size && index < size2)
      {
         Node lineNode = (Node) document.createElement(LINE);

         GPoint rotatedSecondPoint = (GPoint) tempPointVector.get(index);
         GPoint secondPoint = (GPoint) pointVector.get(index);

         Node pointOneNode = this.getPointNode(firstPoint, this.POINTONE);

         Node pointTwoNode = this.getPointNode(secondPoint, this.POINTTWO);

         Node rotatedPointOneNode = this.getPointNode(rotatedFirstPoint, this.ROTATEDPOINTONE);

         Node rotatedPointTwoNode = this.getPointNode(rotatedSecondPoint, this.ROTATEDPOINTTWO);

         lineNode.appendChild(pointOneNode);
         lineNode.appendChild(pointTwoNode);
         lineNode.appendChild(rotatedPointOneNode);
         lineNode.appendChild(rotatedPointTwoNode);

         firstPoint = secondPoint;
         rotatedFirstPoint = rotatedSecondPoint;

         linesNode.appendChild(lineNode);

         index++;
      }

      return linesNode;
   }

   private Node getPointNode(GPoint point, String pointName)
   {
      org.w3c.dom.Document document = WorkAreaJPanel.getDocument();

      Node pointNode = (Node) document.createElement(pointName);

      Node xNode = (Node) document.createElement(PositionStrings.getInstance().X);
      Node xTextNode = (Node) document.createTextNode(Integer.toString(point.getX()));
      xNode.appendChild(xTextNode);

      Node yNode = (Node) document.createElement(PositionStrings.getInstance().Y);
      Node yTextNode = (Node) document.createTextNode(Integer.toString(point.getY()));
      yNode.appendChild(yTextNode);

      pointNode.appendChild(xNode);
      pointNode.appendChild(yNode);

      return pointNode;
   }

}
