package progtech.bindingbox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * The main class for the program that processes shapes and calculates bounding boxes.
 */
public class ProgTechAss1 {

    /**
     * Abstract class representing a generic shape.
     */
    public abstract class Shape {
        protected double x, y; // Center coordinates of the shape

        /**
         * Constructor to initialize the shape's center coordinates.
         *
         * @param x The x-coordinate of the shape's center.
         * @param y The y-coordinate of the shape's center.
         */
        public Shape(double x, double y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Abstract method to calculate the bounding box of the shape.
         *
         * @return An array containing the coordinates of the bounding box.
         */
        public abstract double[] boundingBox();

        /**
         * Calculates the area of the bounding box.
         *
         * @return The area of the bounding box.
         */
        public double getBBArea() {
            double[] bbox = boundingBox();
            return (bbox[2] - bbox[0]) * (bbox[3] - bbox[1]);
        }

        /**
         * Gets the type of shape as a string.
         *
         * @return The class name of the shape.
         */
        public String getType() {
            return this.getClass().getSimpleName();
        }
    }

    /**
     * Class representing a square shape.
     */
    public class Square extends Shape {
        private double sideLength;

        /**
         * Constructor to initialize a square.
         *
         * @param x          The x-coordinate of the square's center.
         * @param y          The y-coordinate of the square's center.
         * @param sideLength The length of a side of the square.
         */
        public Square(double x, double y, double sideLength) {
            super(x, y);
            this.sideLength = sideLength;
        }

        @Override
        public double[] boundingBox() {
            double halfLength = sideLength / 2;
            return new double[]{x - halfLength, y - halfLength, x + halfLength, y + halfLength};
        }
    }

    /**
     * Class representing a circle shape.
     */
    public class Circle extends Shape {
        private double rad;

        /**
         * Constructor to initialize a circle.
         *
         * @param x  The x-coordinate of the circle's center.
         * @param y  The y-coordinate of the circle's center.
         * @param rad The radius of the circle.
         */
        public Circle(double x, double y, double rad) {
            super(x, y);
            this.rad = rad;
        }

        @Override
        public double[] boundingBox() {
            return new double[]{x - rad, y - rad, x + rad, y + rad};
        }
    }

    /**
     * Class representing a triangle shape.
     */
    public class Triangle extends Shape {
        private double length;

        /**
         * Constructor to initialize a triangle.
         *
         * @param x      The x-coordinate of the triangle's center.
         * @param y      The y-coordinate of the triangle's center.
         * @param length The length of a side of the equilateral triangle.
         */
        public Triangle(double x, double y, double length) {
            super(x, y);
            this.length = length;
        }

        @Override
        public double[] boundingBox() {
            double height = (Math.sqrt(3) / 2) * length; // Height of an equilateral triangle
            return new double[]{x - length / 2, y - height / 2, x + length / 2, y + height / 2};
        }
    }

    /**
     * Class representing a hexagon shape.
     */
    public class Hexagon extends Shape {
        private double rad;

        /**
         * Constructor to initialize a hexagon.
         *
         * @param x  The x-coordinate of the hexagon's center.
         * @param y  The y-coordinate of the hexagon's center.
         * @param rad The radius of the hexagon.
         */
        public Hexagon(double x, double y, double rad) {
            super(x, y);
            this.rad = rad;
        }

        @Override
        public double[] boundingBox() {
            double height = Math.sqrt(3) * rad; // Height of hexagon
            return new double[]{x - rad, y - height / 2, x + rad, y + height / 2};
        }
    }

    /**
     * Class to load shapes from a file.
     */
    public class ShapeFromFile {
        /**
         * Loads shapes from a specified file.
         *
         * @param file The path to the file containing shape data.
         * @return A list of shapes read from the file.
         * @throws IOException If an error occurs while reading the file.
         */
        public List<Shape> shapeFileLoader(String file) throws IOException {
            List<Shape> shapes = new ArrayList<>();
            BufferedReader r = new BufferedReader(new FileReader(file));

            int numOfShapes = Integer.parseInt(r.readLine().trim());

            for (int i = 0; i < numOfShapes; i++) {
                String[] inputs = r.readLine().trim().split("\\s+");
                String shape = inputs[0];
                double x = Double.parseDouble(inputs[1]);
                double y = Double.parseDouble(inputs[2]);

                switch (shape) {
                    case "S" -> {
                        double length = Double.parseDouble(inputs[3]);
                        shapes.add(new Square(x, y, length));
                    }
                    case "C" -> {
                        double rad = Double.parseDouble(inputs[3]);
                        shapes.add(new Circle(x, y, rad));
                    }
                    case "T" -> {
                        double triLength = Double.parseDouble(inputs[3]);
                        shapes.add(new Triangle(x, y, triLength));
                    }
                    case "H" -> {
                        double hexRad = Double.parseDouble(inputs[3]);
                        shapes.add(new Hexagon(x, y, hexRad));
                    }
                    default -> System.err.println("Shape type unknown, please enter S, T, H or C");
                }
            }
            r.close();
            return shapes;
        }
    }

    /**
     * Finds the shape with the largest bounding box area from a list of shapes.
     *
     * @param shapes The list of shapes to evaluate.
     * @return The shape with the largest bounding box area, or null if the list is empty.
     */
    public static Shape findLargestBB(List<Shape> shapes) {
        if (shapes.isEmpty())
            return null;

        Shape largestShape = shapes.get(0);
        double largestArea = largestShape.getBBArea();

        for (Shape s : shapes) {
            double area = s.getBBArea();
            if (area > largestArea) {
                largestShape = s;
                largestArea = area;
            }
        }
        return largestShape;
    }

    /**
     * Main method to execute the program.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("0.00"); // Formats output to two decimal points

        try {
            ProgTechAss1 app = new ProgTechAss1();
            ShapeFromFile loader = app.new ShapeFromFile();
            List<Shape> shapes = loader.shapeFileLoader("shapes_identicalBox.txt");
            Shape largestShape = findLargestBB(shapes);
            if (largestShape != null) {
                System.out.println("Largest Bounding Box: " + largestShape.getType());
                System.out.println("The area is " + df.format(largestShape.getBBArea()));
            } else {
                System.out.println("No shapes found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}