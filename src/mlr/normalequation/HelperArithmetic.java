package mlr.normalequation;

/**
 *
 * @author Ricardo
 */
class HelperArithmetic {
//Atributos
double sumX1,sumX2,sumY,sumX1Y,sumX2Y,sumX1pow2,sumX2pow2,sumX1X2;
int n;
//Constructor
HelperArithmetic(DataSet ds){
n = ds.getX1().length;
sumX1 = calcularSumatoria(ds.getX1());
sumX2 = calcularSumatoria(ds.getX2());
sumY = calcularSumatoria(ds.getY());
sumX1Y = calcularSumatoriaXY(ds.getX1(),ds.getY());
sumX2Y = calcularSumatoriaXY(ds.getX2(),ds.getY());
sumX1X2 = calcularSumatoriaXY(ds.getX1(),ds.getX2());
sumX1pow2 = calcularSumatoriaCuadrada(ds.getX1());
sumX2pow2 = calcularSumatoriaCuadrada(ds.getX2());
}
//Calculos comunes
private double calcularSumatoria(double [] columna){
double resultado = 0;
for(double campo : columna){
resultado+=campo;
}
return resultado;
}
private double calcularSumatoriaXY(double [] columna1, double [] columna2){
double resultado=0;
for(int i=0;i<columna1.length;i++){
resultado+=columna1[i]*columna2[i];
}
return resultado;
}
private double calcularSumatoriaCuadrada(double [] columna){
double resultado = 0;
for(double campo : columna){
resultado+=campo*campo;
}
return resultado;
}

//Getters & setters

    public double getSumX1() {
        return sumX1;
    }

    public double getSumX2() {
        return sumX2;
    }

    public double getSumY() {
        return sumY;
    }

    public double getSumX1Y() {
        return sumX1Y;
    }

    public double getSumX2Y() {
        return sumX2Y;
    }

    public double getSumX1pow2() {
        return sumX1pow2;
    }

    public double getSumX2pow2() {
        return sumX2pow2;
    }

    public double getSumX1X2() {
        return sumX1X2;
    }

    public int getN() {
        return n;
    }
}
