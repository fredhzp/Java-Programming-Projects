
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import progtech.bindingbox.ProgTechAss1;
import progtech.bindingbox.ProgTechAss1.Shape;
import progtech.bindingbox.ProgTechAss1.ShapeFromFile;

public class ShapeTest {
    private ShapeFromFile loader;

    @BeforeEach
    public void setUp() {
        loader = new ProgTechAss1().new ShapeFromFile();
    }

    
    /** 
     * @throws IOException
     */
    @Test
    public void testLargeValues() throws IOException {
        List<Shape> shapes = loader.shapeFileLoader("test_files/shapes_largeVal.txt");
        Shape largestShape = ProgTechAss1.findLargestBB(shapes);
        System.out.println(largestShape.getType());
        assertNotNull(largestShape);
        assertEquals("Hexagon", largestShape.getType());
    }

    
    /** 
     * @throws IOException
     */
    @Test
    public void testNegativeCoordinates() throws IOException {
        List<Shape> shapes = loader.shapeFileLoader("test_files/shapes_negCoord.txt");
        Shape largestShape = ProgTechAss1.findLargestBB(shapes);
        assertNotNull(largestShape);
        assertEquals("Square", largestShape.getType());
    }

    @Test
    public void testMinimalValues() throws IOException {
        List<Shape> shapes = loader.shapeFileLoader("test_files/shapes_minVal.txt");
        Shape largestShape = ProgTechAss1.findLargestBB(shapes);
        assertNotNull(largestShape);
        assertEquals("Circle", largestShape.getType());
    }

    @Test
    public void testSingleShape() throws IOException {
        List<Shape> shapes = loader.shapeFileLoader("test_files/shapes_singleShape.txt");
        Shape largestShape = ProgTechAss1.findLargestBB(shapes);
        assertNotNull(largestShape);
        assertEquals("Square", largestShape.getType());
    }

    @Test
    public void testIdenticalAreas() throws IOException {
        List<Shape> shapes = loader.shapeFileLoader("test_files/shapes_identicalBox.txt");
        Shape largestShape = ProgTechAss1.findLargestBB(shapes);
        assertNotNull(largestShape);
        assertTrue(largestShape.getType().equals("Square") || largestShape.getType().equals("Circle"));
    }
}
