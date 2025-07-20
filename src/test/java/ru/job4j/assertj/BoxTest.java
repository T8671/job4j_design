package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 12);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void isUnknown() {
        Box box = new Box(2, 7);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void getNumberOfVerticesNull() {
        Box box = new Box(0, 10);
        int num = box.getNumberOfVertices();
        assertThat(num).isEqualTo(0);
    }

    @Test
    void getNumberOfVerticesNegativeEdge() {
        Box box = new Box(4, -1);
        int num = box.getNumberOfVertices();
        assertThat(num).isEqualTo(-1);
    }

    @Test
    void isExistFigure() {
        Box box = new Box(0, 10);
        boolean figure = box.isExist();
        assertThat(figure).isTrue();
    }

    @Test
    void isNotExistFigure() {
        Box box = new Box(-1, 10);
        boolean figure = box.isExist();
        assertThat(figure).isFalse();
    }

    @Test
    void getAreaExistFigure() {
        Box box = new Box(0, 2);
        double area = box.getArea();
        assertThat(area).isEqualTo(55.26d, withPrecision(5d));
    }

    @Test
    void getNotAreaExistFigure() {
        Box box = new Box(0, 0);
        double area = box.getArea();
        assertThat(area).isEqualTo(0);
    }
}