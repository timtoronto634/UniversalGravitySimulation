public class Body{
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;


    public Body(double xP, double yP, double xV,
                double yV, double m, String img) {
                    xxPos = xP;
                    yyPos = yP;
                    xxVel = xV;
                    yyVel = yV;
                    mass = m;
                    imgFileName = "images/" + img;
                }
    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body star) {
        double dx;
        double dy;
        double distance;
        double r2;

        dx = star.xxPos - this.xxPos;
        dy = star.yyPos - this.yyPos;
        r2 = (dx * dx) + (dy * dy);
        distance = Math.sqrt(r2);

        return distance;
    }

    public static final double G = 6.67e-11;

    public double calcForceExertedBy(Body star) {
        double Force;
        double r2 = Math.pow(calcDistance(star), 2);
        if (r2 == 0) {
            Force = 0;
        } else {
            Force = (G * this.mass * star.mass) / r2;
        }
        return Force;
    }

    public double calcForceExertedByX(Body star) {
        double Force = calcForceExertedBy(star);
        double dx = star.xxPos - this.xxPos;
        double distance = calcDistance(star);
        double xxForce;

        if (distance == 0) {
            xxForce = 0;
        } else {
            xxForce = (Force * dx) / distance;
        }
        return xxForce;
    }

    public double calcForceExertedByY(Body star) {
        double Force = calcForceExertedBy(star);
        double dy = star.yyPos - this.yyPos;
        double distance = calcDistance(star);
        double yyForce;

        if (distance == 0) {
            yyForce = 0;
        } else {
            yyForce = (Force * dy) / distance;
        }
        return yyForce;
    }

    public double calcNetForceExertedByX(Body[] stararray){
        double NetForce = 0;
        for (Body each_body : stararray) {
            if (this != each_body) {
                double xxForce = calcForceExertedByX(each_body);
                NetForce = NetForce + xxForce;
            }
        }
        return NetForce;
    }

    public double calcNetForceExertedByY(Body[] stararray){
        double NetForce = 0;
        for (Body each_body : stararray) {
            if (this != each_body){
                double yyForce = calcForceExertedByY(each_body);
                NetForce = NetForce + yyForce;
            }
        }
        return NetForce;
    }

    public void update(double seconds, double xxForce, double yyForce) {
        double xxAccel = xxForce / this.mass;
        double yyAccel = yyForce / this.mass;

        // update velocity
        this.xxVel = this.xxVel + (xxAccel * seconds);
        this.yyVel = this.yyVel + (yyAccel * seconds);

        //update position
        this.xxPos = this.xxPos + (this.xxVel * seconds);
        this.yyPos = this.yyPos + (this.yyVel * seconds);

    }

    public void draw() {
        double x = this.xxPos;
        double y = this.yyPos;
        String img = this.imgFileName;
        StdDraw.picture(x, y, img);
    }

}