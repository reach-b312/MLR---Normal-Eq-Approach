//Autor: Ricardo Avalos
package mlr.normalequation;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import javax.swing.JOptionPane;

public class MLRAgent extends Agent {
  @Override
  protected void setup() {
    System.out.println("Agent "+getLocalName()+" started.");
    addBehaviour(new SLRBehaviour());
  } 

  private class SLRBehaviour extends Behaviour {
    int cont=0;
    @Override
    public void action() {
        double mlrx1 = Double.parseDouble(JOptionPane.showInputDialog("Valor de x1: "));
        double mlrx2 = Double.parseDouble(JOptionPane.showInputDialog("Valor de x2: "));
        System.out.println("Agent's action method is executed");
        DataSet ds = new DataSet();
        HelperArithmetic ha = new HelperArithmetic(ds);
        MultipleLinearRegression mlr = new MultipleLinearRegression(ha);
        //slr.regress(ds.getData, ha);
        //System.out.printf("y = %.4f + %.4f x",mlr.getBeta0(),mlr.getBeta1());
        double [] betaArray = mlr.getBetas();
        System.out.printf("\ny = %f +%fX1 + %fX2 ",betaArray[0],betaArray[1],betaArray[2]);
        double y = betaArray[0] + betaArray[1]*mlrx1 + betaArray[2]*mlrx2;
        System.out.print("\ny = "+y);
        cont+=1;
    } 
    @Override
    public boolean done() {
      if (cont == 1)
        return true;
      else
	return false;
    }
   @Override
    public int onEnd() {
      myAgent.doDelete();
      return super.onEnd();
    } 
  }    // END of inner class ...Behaviour
}