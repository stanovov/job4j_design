package ru.job4j.ood.isp;

/**
 * Ошибка заключается не в прямом нарушении принципа ISP, а в неверном выделении абстракции, которое в свою очередь
 * ведет к нарушению ISP
 */
public interface Figure {

    double perimeter();
    double square();

    double volume();

}
