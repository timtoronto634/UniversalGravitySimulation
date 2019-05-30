public class NBody {

    // read the text file, return radius of the universe
    public static double readRadius(String planets) {
        In in = new In(planets);

        int NumofPlanets = in.readInt();
        double Radius = in.readDouble();
        
        return Radius;
    }

    // read the text file, return the Body instance array with info written in the file
    public static Body[] readBodies(String planets) {
        In in = new In(planets);

        int NumofPlanets = in.readInt();
        double Radius = in.readDouble();

        Body[] BodyArray = new Body[5];
        for (int iter=0; iter < 5; iter++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();

            BodyArray[iter] =  new Body(xP, yP, xV, yV, m, img);
        } 
        return BodyArray;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt =  Double.parseDouble(args[1]);

        String filename = args[2];

        double radius = readRadius(filename);


        // config to open offscreen drawing 
        StdDraw.enableDoubleBuffering();

        

        Body[] BodyArray = readBodies(args[2]);


        double time = 0;
        //System.out.println(time);

        while (time < T) {
            double[] xForceArray = new double[5];
            double[] yForceArray = new double[5];

            for (int i = 0 ; i < 5; i++) {
                // calcurate forces and store them
                double xForce = BodyArray[i].calcNetForceExertedByX(BodyArray);
                xForceArray[i] = xForce;
                //System.out.println(xForceArray);

                double yForce = BodyArray[i].calcNetForceExertedByY(BodyArray);
                yForceArray [i] = yForce;

                // update planets in the BodyArray
                BodyArray[i].update(dt, xForce, yForce);



            }
            // draw background
            StdDraw.setScale(-radius, radius);
            StdDraw.picture(0, 0, "images/starfield.jpg");

            // draw bodys
            for (Body b : BodyArray) {
                b.draw();
            }

            // show
            StdDraw.show();

            //pause the animation for 10 milliseconds
            StdDraw.pause(10);

            // increase dt
            time = time + dt;
        }
    }
}