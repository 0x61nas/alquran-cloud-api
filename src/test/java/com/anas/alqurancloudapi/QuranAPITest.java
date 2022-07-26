package com.anas.alqurancloudapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: <a href="https://github.com/anas-elgarhy">Anas Elgarhy</a>
 * @date: 7/26/22
 */
class QuranAPITest {

    @Test
    void getAllEditions() throws IOException {
        final var editions = QuranAPI.getEditions();
        Assertions.assertNotNull(editions);
        Assertions.assertNotEquals(0, editions.length);
        System.out.println("All editions number = " + editions.length);
    }
}