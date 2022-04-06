package mlr.normalequation;
class MultipleLinearRegression {
    double beta2,beta1,beta0,Ds;
    double [][] matrizX = new double [3][3];
    double [] matrizY = new double [3];
    double [] betas;
    MultipleLinearRegression(HelperArithmetic consultar){
        inicializarMatrizX(consultar);
        inicializarMatrizY(consultar);

        System.out.println("Matriz X");
        imprimirMatriz(matrizX);
        double [][] matrizXTX = multiplicarMatrices(matrizX,matrizX);
        System.out.println("Matriz XTX");
        imprimirMatriz(matrizXTX);
        double [][] matrizInversaXTX = invert(matrizXTX);
        System.out.println("Matriz inversa de XTX");
        imprimirMatriz(matrizInversaXTX);
        double [] matrizXTY = multiplicarMatrizXTY(matrizX,matrizY);
        System.out.println("Matriz XTY");
        imprimirMatriz(matrizXTY);
        betas = multiplicarMatrizXTY(matrizInversaXTX,matrizXTY);
        System.out.println("Matriz de betas");
        imprimirMatriz(betas);
    }
//MLR: A normal equation approach
void inicializarMatrizX(HelperArithmetic ha){
matrizX[0][0]=ha.getN();
matrizX[0][1]=ha.getSumX1();
matrizX[0][2]=ha.getSumX2();
matrizX[1][0]=ha.getSumX1();
matrizX[1][1]=ha.getSumX1pow2();
matrizX[1][2]=ha.getSumX1X2();
matrizX[2][0]=ha.getSumX2();
matrizX[2][1]=ha.getSumX1X2();
matrizX[2][2]=ha.getSumX2pow2();
}
void inicializarMatrizY(HelperArithmetic ha){
matrizY[0]=ha.getSumY();
matrizY[1]=ha.getSumX1Y();
matrizY[2]=ha.getSumX2Y();
}

//Operaciones con matrices
public static void imprimirMatriz(double [][] matriz){
for (int x=0; x < matriz.length; x++) {
  System.out.print("|");
  for (int y=0; y < matriz[x].length; y++) {
    System.out.print (matriz[x][y]);
    if (y!=matriz[x].length-1) System.out.print("\t");
  }
  System.out.println("|");
}
}
public static void imprimirMatriz(double [] vector){
for(int i=0;i<vector.length;i++){
System.out.println(vector[i]);}
}

public static double[][] multiplicarMatrices(double[][] a, double[][] b) {
    double[][] c = new double[a.length][b[0].length];
    if (a[0].length == b.length) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                c[i][j]=0;
                for (int k = 0; k < a[0].length; k++) {
                    // aquí se multiplica la matriz
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
    }
    return c;
}

public static double[] multiplicarMatrizXTY(double[][] a, double[] b) {
    double[] c = new double[3];
    //if (a.length == b[0].length) {
        for (int i = 0; i < 3; i++) {
                for (int k = 0; k < 3; k++) {
                    // aquí se multiplica la matriz
                    c[i] += a[i][k] * b[k];
                }
        }
    //}
    return c;
}
public static double[][] invert(double a[][]) 
{
    int n = a.length;
    double x[][] = new double[n][n];
    double b[][] = new double[n][n];
    int index[] = new int[n];
    for (int i=0; i<n; ++i) 
        b[i][i] = 1;
// Transform the matrix into an upper triangle
    gaussian(a, index);
// Update the matrix b[i][j] with the ratios stored
    for (int i=0; i<n-1; ++i)
        for (int j=i+1; j<n; ++j)
            for (int k=0; k<n; ++k)
                b[index[j]][k] -= a[index[j]][i]*b[index[i]][k];
// Perform backward substitutions
    for (int i=0; i<n; ++i) 
    {
        x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
        for (int j=n-2; j>=0; --j) 
        {
            x[j][i] = b[index[j]][i];
            for (int k=j+1; k<n; ++k) 
            {
                x[j][i] -= a[index[j]][k]*x[k][i];
            }
            x[j][i] /= a[index[j]][j];
        }
    }
    return x;
}
// Method to carry out the partial-pivoting Gaussian
// elimination.  Here index[] stores pivoting order.
public static void gaussian(double a[][], int index[]) 
{
    int n = index.length;
    double c[] = new double[n];
// Initialize the index
    for (int i=0; i<n; ++i) 
        index[i] = i;
// Find the rescaling factors, one from each row
    for (int i=0; i<n; ++i) 
    {
        double c1 = 0;
        for (int j=0; j<n; ++j) 
        {
            double c0 = Math.abs(a[i][j]);
            if (c0 > c1) c1 = c0;
        }
        c[i] = c1;
    }
// Search the pivoting element from each column
    int k = 0;
    for (int j=0; j<n-1; ++j) 
    {
        double pi1 = 0;
        for (int i=j; i<n; ++i) 
        {
            double pi0 = Math.abs(a[index[i]][j]);
            pi0 /= c[index[i]];
            if (pi0 > pi1) 
            {
                pi1 = pi0;
                k = i;
            }
        }
// Interchange rows according to the pivoting order
        int itmp = index[j];
        index[j] = index[k];
        index[k] = itmp;
        for (int i=j+1; i<n; ++i) 	
        {
            double pj = a[index[i]][j]/a[index[j]][j];
// Record pivoting ratios below the diagonal
            a[index[i]][j] = pj;
// Modify other elements accordingly
            for (int l=j+1; l<n; ++l)

                a[index[i]][l] -= pj*a[index[j]][l];
        }
    }
}
//Getters & setters
public double [] getBetas(){return betas;}
}


